package com.investmentapplication.investmentapplication.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
@Table(name="usercontributions")
public class UserContributionsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String email;

    private long usercontribution;

    private Date planstartdate;

    private Date actualplanstartdate;

    private String payfrequency;

    private long perpaycheck;

}
