package com.investmentapplication.investmentapplication.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
@Table(name="useraccounts")
public class DashboardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String firstName;

    private String lastname;

    private Date dob;

    private Long ssn;

    private String mailingAddress;

    private String email;

    private long phoneNumber;

//    private String password;

    private String securityQuestion1;

    private String getSecurityQuestion1Answer;

    private String securityQuestion2;

    private String getSecurityQuestion2Answer;

    private String securityQuestion3;

    private String getSecurityQuestion3Answer;

}
