package com.investmentapplication.investmentapplication.services;

import com.investmentapplication.investmentapplication.entity.UserContributionsEntity;

import java.util.*;

public interface UserContributionServices {

    List<UserContributionsEntity> getUserContribution(String email);

    String updateUserContribution(String email, List<UserContributionsEntity> userContributions);
}
