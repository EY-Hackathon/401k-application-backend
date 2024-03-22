package com.investmentapplication.investmentapplication.services;

import com.investmentapplication.investmentapplication.entity.PlanContributionsEntity;


public interface PlanContributionsServices {


    PlanContributionsEntity getPlanContribution(String email);

    String updatePlanContribution(String email, PlanContributionsEntity planContribution);
}

