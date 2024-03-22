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
    private String firstName;
    private String lastName;
    @NotNull(message = "Date of birth is required")
    @AgeGreaterThan(message = "Age must be greater than or equal to 18")
    private Date dateOfBirth;

    @NotNull(message = "SSN is required")
    private Long ssn;

    private String mailingAddress;

    @NotBlank(message = "Password is required")
    private String password;

    private String phoneNumber;

    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Employer name is required")
    private String employerName;

    @NotNull(message = "Employment start date is required")
    private Date employmentStartDate;

    @NotNull(message = "Annual salary is required")
    private Double annualSalary;

    @NotBlank(message = "Pay frequency is required")
    private String payFrequency;

}
