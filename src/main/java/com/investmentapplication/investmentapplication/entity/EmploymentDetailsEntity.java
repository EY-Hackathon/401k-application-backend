package com.investmentapplication.investmentapplication.entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
@Entity
@Getter
@Setter
@Table(name = "employmentinfo")
public class EmploymentDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String employerName;
    private String email;
    private Date employmentStartDate;
    private double annualSalary;
    private String payFrequency;
    private String userName;
    private Timestamp updatedAt;

    private Timestamp createdAt;
}
