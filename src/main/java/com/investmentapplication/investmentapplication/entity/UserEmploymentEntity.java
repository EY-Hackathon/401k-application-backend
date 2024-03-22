package com.investmentapplication.investmentapplication.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
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

    private String employerName;

    private Date employmentStartDate;

    private Double annualSalary;

    private String payFrequency;

    private Timestamp updatedAt;

    private Timestamp createdAt;
}
