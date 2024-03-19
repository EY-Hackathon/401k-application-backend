package com.investmentapplication.investmentapplication.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name="investmentplans")

public class InvestmentPlansEntity {


    @Id
    private String plan_name;
    private String plans_desc;
}
