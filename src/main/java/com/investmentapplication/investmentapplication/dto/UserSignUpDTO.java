package com.investmentapplication.investmentapplication.dto;

import com.investmentapplication.investmentapplication.validation.AgeGreaterThan;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Getter
@Setter
public class UserSignUpDTO {

    private Integer id;
    private String firstname;
    private String lastname;
    @NotNull(message = "Date of birth is required")
    @AgeGreaterThan(message = "Age must be greater than or equal to 18")
    private Date dateofbirth;

    @NotNull(message = "SSN is required")
    private Long ssn;

    private String mailingaddress;

    @NotBlank(message = "Password is required")
    private String password;

    private String phonenumber;

    @NotBlank(message = "Email is required")
    private String email;

}
