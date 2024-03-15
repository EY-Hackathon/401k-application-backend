package com.investmentapplication.investmentapplication.services;

import com.investmentapplication.investmentapplication.entity.DashboardEntity;

import java.util.List;

public interface DashboardServices {

    long getTotalBalance(String userId);

    long getYTDContribution(String userId);

    long getTotalEarnings(String userId);
}
