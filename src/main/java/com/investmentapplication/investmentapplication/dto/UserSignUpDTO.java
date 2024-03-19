package com.investmentapplication.investmentapplication.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserSignUpDTO {

    private Integer id;
    private String firstname;
    private String lastname;
    private Date dateofbirth;
    private Long ssn;
    private String mailingaddress;
    private String email;
    private String phonenumber;
    private String password;

}
