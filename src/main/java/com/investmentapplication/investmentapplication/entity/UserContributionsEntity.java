package com.investmentapplication.investmentapplication.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Setter
@Getter
@Table(name="usercontributions")
public class UserContributionsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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

}
