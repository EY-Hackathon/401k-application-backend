package com.investmentapplication.investmentapplication.services.implementation;

import com.investmentapplication.investmentapplication.entity.UserAccountsEntity;
import com.investmentapplication.investmentapplication.entity.UserContributionsEntity;
import com.investmentapplication.investmentapplication.entity.UserEmploymentEntity;
import com.investmentapplication.investmentapplication.repository.UserAccountsRepository;
import com.investmentapplication.investmentapplication.repository.UserContributionsRepository;
import com.investmentapplication.investmentapplication.repository.UserEmploymentRepository;
import com.investmentapplication.investmentapplication.services.UserContributionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Timestamp;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

enum payFrequencyPerYear{
    weekly(52),
    biweekly(26),
    senimonthly(24),
    monthly(12);

    private final int payFrequencyPerYear;

    payFrequencyPerYear(int paychecksPerYear) {
        this.payFrequencyPerYear = paychecksPerYear;
    }

    private int getPaychecksPerYear() {
        return payFrequencyPerYear;
    }
}

@Service
public class UserContributionImpl implements UserContributionServices {

    @Autowired
    UserContributionsRepository userContributionsRepository;

    @Autowired
    UserEmploymentRepository userEmploymentRepository;

    @Autowired
    PaycheckCalculator paycheckCalculator;

    public List<UserContributionsEntity> getUserContribution(String email){

        return userContributionsRepository.findByEmail(email);
    }

    public String updateUserContribution(String email, List<UserContributionsEntity> userContributions){
        List<UserContributionsEntity> existingUserDetails = userContributionsRepository.findByEmail(email);
        UserEmploymentEntity userEmploymentDetails = userEmploymentRepository.findByEmail(email);
        Double salary = userEmploymentDetails.getAnnualSalary();

        existingUserDetails.forEach(contribution -> {
            userContributions.stream()
                    .filter(update -> contribution.getEmail().equals(update.getEmail()))
                    .findFirst()
                    .ifPresent(update -> {
                        if(update.getCurrentContributionPercentage() != null){
                            Double totalContributionValue = contribution.getTotalContributionValue();
                            if(totalContributionValue == null){
                                totalContributionValue = (double) 0;
                                contribution.setTotalContributionValue(updateTotalContributionValue(contribution.getRecurringPercentage(), salary, contribution.getCurrentContributionPercentage(), contribution.getUpdatedAt(), contribution.getActualPlanStartDate(), contribution.getPayFrequency(), totalContributionValue));
                            }
                            else {
                                contribution.setTotalContributionValue(updateTotalContributionValue(contribution.getRecurringPercentage(), salary, contribution.getCurrentContributionPercentage(), contribution.getUpdatedAt(), contribution.getActualPlanStartDate(), contribution.getPayFrequency(), totalContributionValue));
                            }
                            //contribution.setUpdatedAt(new Timestamp(System.()));
                            contribution.setCurrentContributionPercentage(update.getCurrentContributionPercentage());
                            Double newContributionValue = salary * (update.getCurrentContributionPercentage() / 100.0);
                            contribution.setContributionValue(newContributionValue);
                            Double perPayCheck = updatePerPayCheckValue(contribution.getPayFrequency(), newContributionValue);
                            contribution.setPerPayCheck(perPayCheck);

                        }
                        if(update.getRecurringPercentage() != null){
                            contribution.setRecurringPercentage(update.getRecurringPercentage());
                        }
                    });
        });

        userContributionsRepository.saveAll(existingUserDetails);

        return null;
    }

    public double updateTotalContributionValue(Double recurringPercentage, Double salary, Double currentContributionPercentage, Date updatedAt, Date acutalPlanStartDate, String payFrequency, double totalContributionValue){
        Date startDate;
        double updatedContributionValue = totalContributionValue;
        int updtedPosition = 0; //no total contribution value is calculated till now, will need to calculate this from actual plan start date
        if(updatedAt.toInstant().isAfter(acutalPlanStartDate.toInstant())){
            startDate = updatedAt;
            updtedPosition = 1;
        }
        else{
            startDate = acutalPlanStartDate;
        }

        if (updtedPosition == 0) {
            double checkCount = paycheckCalculator.getPayCheckCount(payFrequency, startDate);
            payFrequencyPerYear count = payFrequencyPerYear.valueOf(payFrequency);
            if (checkCount > 26) {
                int paycheckNumber = 0;
                for (int i = 0; i < checkCount; i++) {
                    paycheckNumber++;
                    if (paycheckNumber > 26) {
                        currentContributionPercentage += recurringPercentage;
                        paycheckNumber = 1; // Reset paycheck number after each increment of currentContributionPercentage
                    }
                    updatedContributionValue += (salary / 26) * (currentContributionPercentage / 100);
                }

                // Handle remaining paychecks if any
                double remainingPaychecks = checkCount % 26;
                if (remainingPaychecks > 0) {
                    currentContributionPercentage += recurringPercentage * (remainingPaychecks / 26);
                    updatedContributionValue += (salary / 26) * (currentContributionPercentage / 100) * remainingPaychecks;
                }
            }
        }
        return updatedContributionValue;
    }

    public double updatePerPayCheckValue(String payFrequency, Double currentContribution) {
        double perPayCheck = (double) 0;
        switch (payFrequency) {
            case "weekly":
                perPayCheck = currentContribution / 52; // Assuming 52 weeks in a month
                break;
            case "biweekly":
                perPayCheck = currentContribution / 26; // Assuming 26 paychecks in a month
                break;
            case "semimonthly":
                perPayCheck = currentContribution / 24; // Assuming 24 paychecks in a month
                break;
            case "monthly":
                perPayCheck = currentContribution / 12; // Assuming 12 paycheck in a month
                break;
            default:
                // Handle unsupported pay frequency
                break;
        }

        return perPayCheck;
    }
}
