package com.investmentapplication.investmentapplication.services;

import com.investmentapplication.investmentapplication.entity.DashboardEntity;

import java.util.List;

public interface DashboardServices {

    double getTotalBalance(String userId);

    double getYTDContribution(String userId);

    double getTotalEarnings(String userId);
}
