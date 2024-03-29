package com.investmentapplication.investmentapplication.repository;

import com.investmentapplication.investmentapplication.entity.InvestmentPlansEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvestmentPlanRepository extends JpaRepository <InvestmentPlansEntity, String> {

}
