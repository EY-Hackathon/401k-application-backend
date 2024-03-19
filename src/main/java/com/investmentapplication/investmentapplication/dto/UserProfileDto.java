package com.investmentapplication.investmentapplication.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserProfileDto {
    private long id;
    private String username;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String mailingaddress;
    private String ssn;
    private String phoneNumber;
    private Date dateofbirth;
    private String employername;
    private Date employment_start_date;
    private double annual_salary;
    private String payfrequency;
}
