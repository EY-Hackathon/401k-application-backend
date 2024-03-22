package com.investmentapplication.investmentapplication.services.implementation;

import com.investmentapplication.investmentapplication.dto.UserContributionUpdateDTO;
import com.investmentapplication.investmentapplication.entity.UserAccountsEntity;
import com.investmentapplication.investmentapplication.entity.UserContributionsEntity;
import com.investmentapplication.investmentapplication.entity.UserEmploymentEntity;
import com.investmentapplication.investmentapplication.repository.UserAccountsRepository;
import com.investmentapplication.investmentapplication.repository.UserContributionsRepository;
import com.investmentapplication.investmentapplication.repository.UserEmploymentRepository;
import com.investmentapplication.investmentapplication.services.UserContributionServices;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static org.apache.logging.log4j.util.Strings.EMPTY;

@Getter
enum PayFrequencyYearlyCount {
    WEEKLY(52),
    BIWEEKLY(26),
    SEMIMONTHLY(24),
    MONTHLY(12);

    private final int count;

    PayFrequencyYearlyCount(int count) {
        this.count = count;
    }

    public static int getCountForPayFrequency(String payFrequency) {
        for (PayFrequencyYearlyCount frequency : PayFrequencyYearlyCount.values()) {
            if (frequency.name().equalsIgnoreCase(payFrequency)) {
                return frequency.getCount();
            }
        }
        return -1; // Return -1 for unsupported pay frequency
    }
}

@Service
public class UserContributionImpl implements UserContributionServices {

    @Autowired
    UserContributionsRepository userContributionsRepository;

    @Autowired
    UserEmploymentRepository userEmploymentRepository;

    @Autowired
    UserAccountsRepository userAccountsRepository;

    @Autowired
    PaycheckCalculator paycheckCalculator;

    public List<UserContributionsEntity> getUserContribution(String email) {

        return userContributionsRepository.findByEmail(email);
    }

    public String updateUserContribution(String email, List<UserContributionUpdateDTO> userContributions) {
        List<UserContributionsEntity> existingUserDetails = userContributionsRepository.findByEmail(email);
        String userEmail = EMPTY;
        if (!existingUserDetails.isEmpty()) {
            List<String> userEmailList = existingUserDetails.stream().map(UserContributionsEntity::getEmail).toList();
            userEmail = userEmailList.get(0) != null ? userEmailList.get(0) : EMPTY;
        }
        UserEmploymentEntity userEmploymentDetails = userEmploymentRepository.findByEmail(email);
        Double salary = userEmploymentDetails.getAnnualSalary();

        if (!userEmail.isEmpty() && userEmail.equals(email)) {
            existingUserDetails.forEach(contribution -> {
                userContributions.stream()
                        .filter(update -> contribution.getEmail().equals(update.getEmail()))
                        .findFirst()
                        .ifPresent(update -> {
                            if (update.getUserCurrentContributionPercentage() != null) {
                                Double totalContributionValue = contribution.getTotalContributionValue();
                                if (totalContributionValue == null) {
                                    totalContributionValue = 0.0; // Initialize to zero if null
                                }
                                Map<String, Object> TotalContribution = calculateTotalContributionValue(
                                        contribution.getRecurringPercentage(),
                                        salary,
                                        contribution.getCurrentContributionPercentage(),
                                        contribution.getUpdatedAt(),
                                        contribution.getActualPlanStartDate(),
                                        contribution.getPayFrequency(),
                                        totalContributionValue
                                );
                                contribution.setTotalContributionValue(
                                        (double) TotalContribution.get("updatedContributionValue")
                                );
                                contribution.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
                                contribution.setCurrentContributionPercentage(update.getUserCurrentContributionPercentage());
                                double newContributionValue = salary * (update.getUserCurrentContributionPercentage() / 100.0);
                                contribution.setContributionValue(newContributionValue);
                                int count = PayFrequencyYearlyCount.getCountForPayFrequency(contribution.getPayFrequency());
                                Double perPayCheck = newContributionValue / count;
                                contribution.setPerPayCheck(perPayCheck);
                            }
                            if (update.getRecurringPercentage() != null) {
                                contribution.setRecurringPercentage(update.getRecurringPercentage());
                            }
                        });
                userContributionsRepository.saveAll(existingUserDetails);
            });
        } else {
            //create a new record for new user
            UserContributionsEntity userContributionsEntity = new UserContributionsEntity();
            List<UserAccountsEntity> userAccountDetails = userAccountsRepository.findByEmail(email);
            List<String> userAccountsEmailList = userAccountDetails.stream().map(UserAccountsEntity::getEmail).toList();
            String accountEmail = userAccountsEmailList.get(0) != null ? userAccountsEmailList.get(0) : EMPTY;
            Date currentDate = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currentDate);

            // Add one month to the Calendar
            calendar.add(Calendar.MONTH, 1);

            Date actualPlanStartDate = calendar.getTime();

            if (!accountEmail.isEmpty() && accountEmail.equals(email)) {
                userContributions.forEach(updatedUserContribution -> {
                    userContributionsEntity.setEmail(updatedUserContribution.getEmail());
                    userContributionsEntity.setOriginalContributionPercentage(updatedUserContribution.getUserCurrentContributionPercentage());
                    userContributionsEntity.setPlanStartDate(currentDate);
                    userContributionsEntity.setActualPlanStartDate(actualPlanStartDate);
                    userContributionsEntity.setPerPayCheck((updatedUserContribution.getUserCurrentContributionPercentage() * salary) / 100);
                    userContributionsEntity.setPayFrequency(userEmploymentDetails.getPayFrequency());
                    userContributionsEntity.setCurrentContributionPercentage(updatedUserContribution.getUserCurrentContributionPercentage());
                    userContributionsEntity.setRecurringPercentage(updatedUserContribution.getRecurringPercentage());
                    userContributionsEntity.setContributionValue((salary * updatedUserContribution.getUserCurrentContributionPercentage()) / 100);
                    userContributionsEntity.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
                    userContributionsEntity.setTotalContributionValue((double) 0);
                    userContributionsEntity.setTotalYtdContributionValue((double) 0);
                    userContributionsEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));
                });
                userContributionsRepository.save(userContributionsEntity);
            }
        }


        return "Updated Successfully";
    }

    public Map<String, Object> calculateTotalContributionValue(Double recurringPercentage, Double salary, Double currentContributionPercentage, Date updatedAt, Date acutalPlanStartDate, String payFrequency, double totalContributionValue) {
        Date currentDate = new Date();
        Date startDate;
        double updatedContributionValue = totalContributionValue;
        int updtedPosition = 0; //no total contribution value is calculated till now, will need to calculate this from actual plan start date
        if (updatedAt.toInstant().isAfter(acutalPlanStartDate.toInstant())) {
            startDate = updatedAt;
            updtedPosition = 1;
        } else {
            startDate = acutalPlanStartDate;
        }

        //pay frequency generic count
        int count = PayFrequencyYearlyCount.getCountForPayFrequency(payFrequency);
        int recurringIncreaseCount = 0;
        if (updtedPosition == 0) {
            double checkCount = paycheckCalculator.getPayCheckCount(payFrequency, startDate, currentDate);
            if (checkCount > count) {
                int paycheckNumber = 0;
                for (int i = 0; i <= checkCount; i++) {
                    paycheckNumber++;
                    if (paycheckNumber > count) {
                        currentContributionPercentage += recurringPercentage;
                        recurringIncreaseCount++;
                        paycheckNumber = 1; // Reset paycheck number after each increment of currentContributionPercentage
                    }
                    updatedContributionValue += (salary / count) * (currentContributionPercentage / 100);
                }
            }
        } else {
            //if there has already been calculated total contribution in the past, which is the updatedAt timestamp -
            //find when is the next recurring increase and calculate the new total and add it to the total contribution value

            Instant startInstant = acutalPlanStartDate.toInstant();
            Instant endInstant = updatedAt.toInstant();

            // Convert Instant to LocalDate (using system default time zone)
            LocalDate planStartDate = startInstant.atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate endDate = endInstant.atZone(ZoneId.systemDefault()).toLocalDate();


            int yearsSinceStart = (int) ChronoUnit.YEARS.between(planStartDate, endDate);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(acutalPlanStartDate);
            calendar.add(Calendar.YEAR, yearsSinceStart + 1);
            Date nextRecurringIncrease = calendar.getTime();

            double payCheckCountTillNextIncrease = paycheckCalculator.getPayCheckCount(payFrequency, updatedAt, nextRecurringIncrease);
            double payCountSinceUpdatedAt = paycheckCalculator.getPayCheckCount(payFrequency, updatedAt, currentDate);

            int paycheckNumber = 0;
            for (int i = 0; i < payCountSinceUpdatedAt; i++) {
                paycheckNumber++;
                if (paycheckNumber > count) {
                    paycheckNumber = 1; // Reset paycheck number after each increment of currentContributionPercentage
                    payCheckCountTillNextIncrease = count;
                }
                updatedContributionValue += (salary / count) * (currentContributionPercentage / 100);
                if (paycheckNumber == payCheckCountTillNextIncrease) {
                    currentContributionPercentage += recurringPercentage;
                    recurringIncreaseCount++;
                }
            }

        }

        Map<String, Object> result = new HashMap<>();
        result.put("updatedContributionValue", updatedContributionValue);
        result.put("recurringIncreaseCount", recurringIncreaseCount);

        return result;
    }
}

