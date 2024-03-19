package com.investmentapplication.investmentapplication.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@Table(name="useraccounts")
public class UserAccountsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String firstname;

    private String lastname;

    private Date dateofbirth;

    private Long ssn;

    private String mailingaddress;

    private String email;

    private String phonenumber;

    private String password;

    private String securityquestion1;

    private String securityquestion1answer;

    private String securityquestion2;

    private String securityquestion2answer;

    private String securityquestion3;

    private String securityquestion3answer;
    
}
