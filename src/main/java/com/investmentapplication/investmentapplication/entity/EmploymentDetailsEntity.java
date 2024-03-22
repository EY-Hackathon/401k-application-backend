package com.investmentapplication.investmentapplication.entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Entity
@Getter
@Setter
@Table(name = "employmentinfo")
public class EmploymentDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String employername;
    private String email;
    private Date employmentstartdate;
    private double annualsalary;
    private String payfrequency;
    private String username;
}
