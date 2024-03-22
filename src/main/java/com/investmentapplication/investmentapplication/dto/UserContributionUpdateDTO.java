package com.investmentapplication.investmentapplication.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserContributionUpdateDTO {
    private String email;
    private Double recurringPercentage;
    private Double userCurrentContributionPercentage;
}
