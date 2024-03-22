package com.investmentapplication.investmentapplication.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Setter
@Getter
@Table(name="usercontributions")
public class UserContributionsEntity {

    @Id
    private String email;

    private long originalContributionPercentage;

    private Date planStartDate;

    private Date actualPlanStartDate;

    private String payFrequency;

    private Double perPayCheck;

    private Double currentContributionPercentage;

    private Double recurringPercentage;

    private Double contributionValue;

    private Timestamp updatedAt;

    private Double totalContributionValue;

    private Double totalYtdContributionValue;

    private Timestamp createdAt;

}
