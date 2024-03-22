package com.investmentapplication.investmentapplication.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Setter
@Getter
@Table(name="investmentplans")

public class InvestmentPlansEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String planName;
    private String plansDesc;
    private Timestamp updatedAt;
    private Timestamp createdAt;

}
