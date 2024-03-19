package com.investmentapplication.investmentapplication.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Entity
@Getter
@Setter
@Table(name = "employmentinfo")
public class UserEmploymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private String employment_Name;

    private Date employmentStartDate;

    private Double annualSalary;

    private String payFrequency;
}
