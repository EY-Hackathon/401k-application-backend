package com.investmentapplication.investmentapplication.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name="investmentplans")

public class InvestmentPlansEntity {


    @Id
    private String plan_name;
    private String plans_desc;
}
