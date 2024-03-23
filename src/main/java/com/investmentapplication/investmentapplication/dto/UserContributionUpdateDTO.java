package com.investmentapplication.investmentapplication.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserContributionUpdateDTO {
    private String email;
    private Double recurringPercentage;
    private Double userCurrentContributionPercentage;
    private Date actualPlanStartDate;
}
