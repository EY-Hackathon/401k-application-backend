package com.investmentapplication.investmentapplication.services;

public interface DashboardServices {

    double getTotalBalance(String email);

    double getTotalContribution(String email);

    double getYTDContribution(String email);

    double getTotalEarnings(String email);
}
