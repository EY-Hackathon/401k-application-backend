package com.investmentapplication.investmentapplication.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Setter
@Getter
@Table(name="plancontributions")
public class PlanContributionsEntity {


    @Id
    private double Roth_IRA;
    private double plans403b;
    private double SEP;
    private double SARSEP;
    private double payroll_Deduction_IRAs;
    private double profit_sharing_plans;
    private double defined_benefit_plan;
    private double money_purchase_plans;
    private double ESOPs;
    private double governmental_plans;

    private double plans457;
    private double multiple_employer_plans;
    private String email;

    private Timestamp updatedAt;

    private Timestamp createdAt;



}
