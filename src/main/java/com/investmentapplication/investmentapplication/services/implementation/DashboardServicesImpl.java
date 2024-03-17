package com.investmentapplication.investmentapplication.services.implementation;

import com.investmentapplication.investmentapplication.entity.UserContributionsEntity;
import com.investmentapplication.investmentapplication.repository.UserAccountsRepository;
import com.investmentapplication.investmentapplication.repository.UserContributionsRepository;
import com.investmentapplication.investmentapplication.services.DashboardServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DashboardServicesImpl implements DashboardServices {

    @Autowired
    UserAccountsRepository userAccountsRepository;

    @Autowired
    UserContributionsRepository userContributionsRepository;

    @Autowired
    PaycheckCalculator paycheckCalculator;
    @Override
    public double getTotalBalance(String email) {
        double ytdContribution = getYTDContribution(email);
        double totalEarnings = getTotalEarnings(email);
        return ytdContribution + totalEarnings;
    }

    @Override
    public double getYTDContribution(String email) {
        List<UserContributionsEntity> userContributionData = userContributionsRepository.findByEmail(email);
        List<Double> contributionPerPayCheck = userContributionData.stream().map(UserContributionsEntity::getPerPayCheck).toList();
        List<String> payFrequency = userContributionData.stream().map(UserContributionsEntity::getPayFrequency).toList();
        Optional<Date> planStartDate = userContributionData.stream().map(UserContributionsEntity::getActualPlanStartDate).findFirst();
        Date actionPlanStartDate = planStartDate.orElse(null);
        double checkCount = paycheckCalculator.getPayCheckCount(payFrequency.get(0), actionPlanStartDate);
        return checkCount * contributionPerPayCheck.get(0);
    }

    @Override
    public double getTotalEarnings(String email) {
        return 522;
    }
}
