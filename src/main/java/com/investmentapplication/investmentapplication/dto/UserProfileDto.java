package com.investmentapplication.investmentapplication.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserProfileDto {
    private long id;
    private String userName;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String mailingAddress;
    private long ssn;
    private String phoneNumber;
    private Date dateOfBirth;
    private String employerName;
    private Date employmentStartDate;
    private Double annualSalary;
    private String payFrequency;
}
