package com.investmentapplication.investmentapplication.entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Entity
@Getter
@Setter
@Table(name = "employmentinfo")
public class EmploymentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String employerName;
    private String email;
    private Date employmentStartDate;
    private double annualSalary;
    private String payFrequency;
    private String username;
}
