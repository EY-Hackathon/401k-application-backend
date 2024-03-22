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
        // Get the total contribution, total earnings, and employer match for the user
        double totalContribution = getTotalContribution(email);
        double totalEarnings = getTotalEarnings(email);
        double employerMatch = employerMatchServices.GetEmployerMatchValue(email);

        // Return the sum of total contribution, total earnings, and employer match
        return totalContribution + totalEarnings + employerMatch;

    }


    /**
     * Retrieves the total contribution of the user.
     * @param email The email of the user.
     * @return The total contribution of the user.
     */

    @Override
    public double getTotalContribution(String email){

        // Initialize an array to store the total contribution value
        double[] totalContributionValue = new double[1];

        // Retrieve user contribution and employment details from repositories
        List<UserContributionsEntity> userContribution = userContributionsRepository.findByEmail(email);
        UserEmploymentEntity userEmploymentDetails = userEmploymentRepository.findByEmail(email);
        Double salary = userEmploymentDetails.getAnnualSalary();


        // Calculate total contribution based on user contribution data
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

        // Return the total contribution value
        return totalContributionValue[0];
    }
    /**
     * Retrieves the year-to-date contribution of the user.
     * @param email The email of the user.
     * @return The year-to-date contribution of the user.
     */
    @Override
    public double getYTDContribution(String email) {

        Date currentDate = new Date();
        // Retrieve user contribution data from repository
        List<UserContributionsEntity> userContributionData = userContributionsRepository.findByEmail(email);

        // Extract necessary data for calculation
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

    /**
     * Retrieves the total earnings of the user.
     * @param email The email of the user.
     * @return The total earnings of the user.
     */

    @Override
    public double getTotalEarnings(String email) {
        return 522;
    }
}
