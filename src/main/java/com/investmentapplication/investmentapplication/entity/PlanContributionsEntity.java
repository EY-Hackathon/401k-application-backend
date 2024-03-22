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
    private double RothIRA;
    private double plans403b;
    private double SEP;
    private double SARSEP;
    private double payrollDeductionIRAs;
    private double profitSharingPlans;
    private double definedBenefitPlan;
    private double moneyPurchasePlans;
    private double ESOPs;
    private double governmentalPlans;
    private double plans457;
    private double multipleEmployerPlans;
    private String email;

    private Timestamp updatedAt;

    private Timestamp createdAt;



}
