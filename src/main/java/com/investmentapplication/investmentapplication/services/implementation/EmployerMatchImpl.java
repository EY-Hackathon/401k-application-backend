package com.investmentapplication.investmentapplication.services.implementation;

import com.investmentapplication.investmentapplication.entity.EmployerMatchEntity;
import com.investmentapplication.investmentapplication.entity.EmploymentDetailsEntity;
import com.investmentapplication.investmentapplication.entity.UserContributionsEntity;
import com.investmentapplication.investmentapplication.repository.EmployerMatchRepository;
import com.investmentapplication.investmentapplication.repository.EmploymentDetailsRepository;
import com.investmentapplication.investmentapplication.repository.UserContributionsRepository;
import com.investmentapplication.investmentapplication.services.EmployerMatchServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class EmployerMatchImpl implements EmployerMatchServices {

    @Autowired
    EmployerMatchRepository employerMatchRepository;

    @Autowired
    EmploymentDetailsRepository employmentDetailsRepository;

    @Autowired
    UserContributionsRepository userContributionsRepository;

    @Override
    public double GetEmployerMatchValue(String email) {
        double totalEmployerMatchValue = 0.0;

        EmploymentDetailsEntity employeeDetails = employmentDetailsRepository.findByEmail(email);
        Date employeeStartDate = employeeDetails.getEmploymentStartDate();
        Date today = new Date();

        Instant startInstant = employeeStartDate.toInstant();
        Instant endInstant = today.toInstant();

        // Convert Instant to LocalDate (using system default time zone)
        LocalDate employmentStartDate = startInstant.atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate = endInstant.atZone(ZoneId.systemDefault()).toLocalDate();

        int employedSinceYears = (int) ChronoUnit.YEARS.between(employmentStartDate, endDate);

        int employedSinceMonths = (int) ChronoUnit.MONTHS.between(employmentStartDate, endDate);
        double remainingMonths = employedSinceMonths % employedSinceYears;


        if (endDate.getMonthValue() < employmentStartDate.getMonthValue() ||
                (endDate.getMonthValue() == employmentStartDate.getMonthValue() &&
                        endDate.getDayOfMonth() < employmentStartDate.getDayOfMonth())) {
            // Subtract 1 year if employment has not completed one full year
            employedSinceYears--;
        }

        List<EmployerMatchEntity> employerMatchDetails = employerMatchRepository.findByEmployerName(employeeDetails.getEmployerName());

        List<UserContributionsEntity> userContributions = userContributionsRepository.findByEmail(email);
        List<Double> userContributionPercentageList = userContributions.stream().map(UserContributionsEntity::getCurrentContributionPercentage).toList();
        double userContributionPercentage = userContributionPercentageList.get(0);
        //double totalYearsToContribute = employedSinceYears - minYearsToStartMatch;

        for (EmployerMatchEntity details : employerMatchDetails) {
            double employerMatchPercentage = details.getEmployerMatchPercentage();
            double employerMatchMaxContribution = details.getEmployerMatchMaxContribution();
            double employmentYearsRequired = details.getEmploymentYears();

            // Calculate total years to contribute based on completed years of employment
            double totalYearsToContribute = employedSinceYears - employmentYearsRequired;

            // Check if employee has completed the required years for match
            if (totalYearsToContribute >= 0) {
                // Calculate employer contribution for each year of match
                for (int i = 0; i < totalYearsToContribute; i++) {
                    double employerMatchPercentageForYear = (employerMatchPercentage / employerMatchMaxContribution);
                    double employerMatchValueBasedOnUserContribution = employerMatchPercentageForYear * userContributionPercentage / 100;
                    totalEmployerMatchValue += employerMatchValueBasedOnUserContribution * employeeDetails.getAnnualSalary();
                }

                // Check if there are remaining months after completed years

                // If there are remaining months and the next match percentage is available
                if (remainingMonths > 0 && employerMatchDetails.size() > 1) {
                    // Get the next employer match details
                    EmployerMatchEntity nextDetails = employerMatchDetails.get(1);
                    double nextEmploymentYears = nextDetails.getEmploymentYears();
                    double nextEmployerMatchPercentage = nextDetails.getEmployerMatchPercentage();
                    double nextEmployerMatchMaxContribution = nextDetails.getEmployerMatchMaxContribution();

                    if (employedSinceYears >= nextEmploymentYears) {
                        double employerMatchPercentageForYear = (nextEmployerMatchPercentage / nextEmployerMatchMaxContribution);
                        double employerMatchValueBasedOnUserContribution = employerMatchPercentageForYear * userContributionPercentage / 100;
                        totalEmployerMatchValue += employerMatchValueBasedOnUserContribution * employeeDetails.getAnnualSalary();
                    }
                }
            }
        }
        return totalEmployerMatchValue;
    }

    private static double calculateMatchPercentage(double employmentYears, double matchPercentage) {
        // Check if employment years exceed the maximum defined years in the employer match details
        if (employmentYears >= 1.0) {
            return matchPercentage;
        } else {
            return 0.0;
        }
    }

    @Override
    public List<EmployerMatchEntity> GetEmployerMatchDetails(String email) {
        EmploymentDetailsEntity employeeDetails = employmentDetailsRepository.findByEmail(email);

        return employerMatchRepository.findByEmployerName(employeeDetails.getEmployerName());
    }

    @Override
    public AtomicReference<String> UpdateEmployerMatchDetails(List<EmployerMatchEntity> employerMatchDetails){
        AtomicReference<String> response = new AtomicReference<>("");
        List<String> distinctEmployerNames = employerMatchDetails.stream()
                .map(EmployerMatchEntity::getEmployerName)
                .distinct()
                .collect(Collectors.toList());
        distinctEmployerNames.forEach(employerName -> {
            List<EmployerMatchEntity> existingEmployerDetails = employerMatchRepository.findByEmployerName(employerName);
            if(existingEmployerDetails.isEmpty()){
                employerMatchDetails.forEach(employerDetails -> {
                    EmployerMatchEntity employerMatch = new EmployerMatchEntity();
                    employerMatch.setEmployerName(employerDetails.getEmployerName());
                    employerMatch.setEmployerMatchMaxContribution(employerDetails.getEmployerMatchMaxContribution());
                    employerMatch.setEmployerMatchPercentage(employerDetails.getEmployerMatchPercentage());
                    employerMatch.setEmploymentYears(employerDetails.getEmploymentYears());
                    employerMatch.setCreatedAt(new Timestamp(System.currentTimeMillis()));
                    employerMatch.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

                    employerMatchRepository.save(employerMatch);

                    response.set("Added employer match details successfully");
                });
            }
            else{
                response.set("Employer details already exist");
            }
        });
        return response;
    }
}
