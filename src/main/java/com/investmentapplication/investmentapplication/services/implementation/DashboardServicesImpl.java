package com.investmentapplication.investmentapplication.services.implementation;

import com.investmentapplication.investmentapplication.entity.DashboardEntity;
import com.investmentapplication.investmentapplication.entity.UserContributionsEntity;
import com.investmentapplication.investmentapplication.repository.DashboardRepository;
import com.investmentapplication.investmentapplication.repository.UserContributionsRepository;
import com.investmentapplication.investmentapplication.services.DashboardServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DashboardServicesImpl implements DashboardServices {

    @Autowired
    DashboardRepository dashboardRepository;

    @Autowired
    UserContributionsRepository userContributionsRepository;

    @Autowired
    PaycheckCalculator paycheckCalculator;
    @Override
    public double getTotalBalance(String userId) {
        double ytdContribution = getYTDContribution(userId);
        double totalEarnings = getTotalEarnings(userId);
        return ytdContribution + totalEarnings;
    }

    @Override
    public double getYTDContribution(String userId) {
        List<UserContributionsEntity> userContributionData = userContributionsRepository.findAll();
        List<Long> contributionPerPayCheck = userContributionData.stream().map(UserContributionsEntity::getPerpaycheck).collect(Collectors.toList());
        List<String> payFrequency = userContributionData.stream().map(UserContributionsEntity::getPayfrequency).collect(Collectors.toList());
        Optional<Date> planStartDate = userContributionData.stream().map(UserContributionsEntity::getActualplanstartdate).findFirst();
        Date actionPlanStartDate = planStartDate.orElse(null);
        double checkCount = paycheckCalculator.getPayCheckCount(payFrequency.get(0), actionPlanStartDate);
        return checkCount * contributionPerPayCheck.get(0);
    }

    @Override
    public double getTotalEarnings(String userId) {
        return 522;
    }
}
