package com.investmentapplication.investmentapplication.services.implementation;

import com.investmentapplication.investmentapplication.entity.UserContributionsEntity;
import com.investmentapplication.investmentapplication.entity.UserEmploymentEntity;
import com.investmentapplication.investmentapplication.repository.UserAccountsRepository;
import com.investmentapplication.investmentapplication.repository.UserContributionsRepository;
import com.investmentapplication.investmentapplication.repository.UserEmploymentRepository;
import com.investmentapplication.investmentapplication.services.DashboardServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DashboardServicesImpl implements DashboardServices {

    @Autowired
    UserAccountsRepository userAccountsRepository;

    @Autowired
    UserContributionsRepository userContributionsRepository;

    @Autowired
    PaycheckCalculator paycheckCalculator;

    @Autowired
    UserContributionImpl userContributionClass;

    @Autowired
    UserEmploymentRepository userEmploymentRepository;

    @Override
    public double getTotalBalance(String email) {
        double ytdContribution = getYTDContribution(email);
        double totalEarnings = getTotalEarnings(email);
        return ytdContribution + totalEarnings;
    }

    @Override
    public double getTotalContribution(String email){
        double[] totalContributionValue = new double[1];
        List<UserContributionsEntity> userContribution = userContributionsRepository.findByEmail(email);
        UserEmploymentEntity userEmploymentDetails = userEmploymentRepository.findByEmail(email);
        Double salary = userEmploymentDetails.getAnnualSalary();

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
        double checkCount = paycheckCalculator.getPayCheckCount(payFrequency.get(0), actionPlanStartDate, currentDate);
        return checkCount * contributionPerPayCheck.get(0);
    }

    @Override
    public double getTotalEarnings(String email) {
        return 522;
    }
}
