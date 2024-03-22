package com.investmentapplication.investmentapplication.dto;

import com.investmentapplication.investmentapplication.validation.AgeGreaterThan;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Data Transfer Object (DTO) for user sign-up information.
 */


@Getter
@Setter
public class UserSignUpDTO {

    private Integer id; // Unique identifier for the user
    private String firstName; // First name of the user
    private String lastName; // Last name of the user
    @NotNull(message = "Date of birth is required")
    @AgeGreaterThan(message = "Age must be greater than or equal to 18")
    private Date dateOfBirth; // Date of birth of the user

    @NotNull(message = "SSN is required")
    private Long ssn; // Social Security Number of the user

    private String mailingAddress; // Mailing address of the user

    // Authentication Information
    // Specifies that the 'Password' field must not be blank.
    // If the field is blank, the specified error message will be returned during validation.
    @NotBlank(message = "Password is required")
    private String password; // Password chosen by the user

    private String phoneNumber; //Phone number of the user

    @NotBlank(message = "Email is required")
    private String email; //Email is required

    @NotBlank(message = "Employer name is required")
    private String employerName; //Name of the user's employer

    @NotNull(message = "Employment start date is required")
    private Date employmentStartDate; // Start date of employment

    @NotNull(message = "Annual salary is required")
    private Double annualSalary; // Annual salary of the user

    @NotBlank(message = "Pay frequency is required")
    private String payFrequency; // Frequency of pay (e.g., monthly, bi-weekly)

}
