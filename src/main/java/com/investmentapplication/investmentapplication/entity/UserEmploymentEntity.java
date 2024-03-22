package com.investmentapplication.investmentapplication.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    private String employmentName;

    private Date employmentstartdate;

    private Double annualsalary;

    private String payfrequency;
}
