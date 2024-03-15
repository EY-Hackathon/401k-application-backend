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

@Service
public class DashboardServicesImpl implements DashboardServices {

    @Autowired
    DashboardRepository dashboardRepository;

    @Autowired
    UserContributionsRepository userContributionsRepository;

    @Autowired
    PaycheckCalculator paycheckCalculator;
    @Override
    public long getTotalBalance(String userId) {
        long ytdContribution = getYTDContribution(userId);
        long totalEarnings = getTotalEarnings(userId);
        return ytdContribution + totalEarnings;
    }

    @Override
    public long getYTDContribution(String userId) {
        //List<UserContributionsEntity> userContributionData = userContributionsRepository.findAll();
        //System.out.println(userContributionData);
//        String payFrequency = userContributionData.stream().map(UserContributionsEntity::getPayFrequency).toString();
//        Optional<Date> planStartDate = userContributionData.stream().map(UserContributionsEntity::getActualPlanStartDate).findFirst();
//        Date actionPlanStartDate = planStartDate.orElse(null);
//        return paycheckCalculator.getPayCheckCount(payFrequency, actionPlanStartDate);
        return 0;
    }

    @Override
    public long getTotalEarnings(String userId) {
        return 522;
    }
}
