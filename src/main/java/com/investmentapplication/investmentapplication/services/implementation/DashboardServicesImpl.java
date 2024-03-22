package com.investmentapplication.investmentapplication.services.implementation;

import com.investmentapplication.investmentapplication.entity.UserContributionsEntity;
import com.investmentapplication.investmentapplication.entity.UserEmploymentEntity;
import com.investmentapplication.investmentapplication.repository.UserContributionsRepository;
import com.investmentapplication.investmentapplication.repository.UserEmploymentRepository;
import com.investmentapplication.investmentapplication.services.DashboardServices;
import com.investmentapplication.investmentapplication.services.EmployerMatchServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DashboardServicesImpl implements DashboardServices {

    @Autowired
    UserContributionsRepository userContributionsRepository;

    @Autowired
    PaycheckCalculator paycheckCalculator;

    @Autowired
    UserContributionImpl userContributionClass;

    @Autowired
    UserEmploymentRepository userEmploymentRepository;

    @Autowired
    EmployerMatchServices employerMatchServices;

    @Override
    public double getTotalBalance(String email) {
        double totalContribution = getTotalContribution(email);
        double totalEarnings = getTotalEarnings(email);
        double employerMatch = employerMatchServices.GetEmployerMatchValue(email);
        return totalContribution + totalEarnings + employerMatch;
    }

    @Override
    public double getTotalContribution(String email){
        double[] totalContributionValue = new double[1];
        List<UserContributionsEntity> userContribution = userContributionsRepository.findByEmail(email);
        UserEmploymentEntity userEmploymentDetails = userEmploymentRepository.findByEmail(email);
        Double salary = userEmploymentDetails.getAnnualsalary();

        userContribution.forEach(contribution -> {
            if(email.equals(contribution.getEmail())){
                Map<String, Object> tempTotalContributionValue  = userContributionClass.calculateTotalContributionValue(
                        contribution.getRecurringPercentage(),
                        salary,
                        contribution.getCurrentContributionPercentage(),
                        contribution.getUpdatedAt(),
                        contribution.getActualPlanStartDate(),
                        contribution.getPayFrequency(),
                        contribution.getTotalContributionValue()
                );
                totalContributionValue[0] = (double) tempTotalContributionValue.get("updatedContributionValue");
            }
        });

        return totalContributionValue[0];
    }

    @Override
    public double getYTDContribution(String email) {
        Date currentDate = new Date();
        List<UserContributionsEntity> userContributionData = userContributionsRepository.findByEmail(email);
        List<Double> contributionPerPayCheck = userContributionData.stream().map(UserContributionsEntity::getPerPayCheck).toList();
        List<String> payFrequency = userContributionData.stream().map(UserContributionsEntity::getPayFrequency).toList();
        Optional<Date> planStartDate = userContributionData.stream().map(UserContributionsEntity::getActualPlanStartDate).findFirst();
        Date actionPlanStartDate = planStartDate.orElse(null);

        // Create a Calendar instance
        Calendar calendar = Calendar.getInstance();

        // Set the calendar to the current date
        calendar.setTime(new Date());

        // Set the calendar to the first day of the year
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        // Get the first day of the year as a Date object
        Date firstDayOfThisYear = calendar.getTime();
        double checkCount = paycheckCalculator.getPayCheckCount(payFrequency.get(0), firstDayOfThisYear, currentDate);

        return checkCount * contributionPerPayCheck.get(0);
    }

    @Override
    public double getTotalEarnings(String email) {
        return 522;
    }
}
