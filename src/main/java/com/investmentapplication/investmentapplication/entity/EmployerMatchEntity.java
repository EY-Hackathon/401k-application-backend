package com.investmentapplication.investmentapplication.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Setter
@Getter
@Table(name = "employer_match")
public class EmployerMatchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String employerName;

    private double employerMatchPercentage;

    private double employmentYears;

    private double employerMatchMaxContribution;

    private Timestamp updatedAt;

    private Timestamp createdAt;
}
