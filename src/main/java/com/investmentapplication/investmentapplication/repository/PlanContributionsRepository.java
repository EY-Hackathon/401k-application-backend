package com.investmentapplication.investmentapplication.repository;

import com.investmentapplication.investmentapplication.entity.PlanContributionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanContributionsRepository extends JpaRepository<PlanContributionsEntity, Long> {

        PlanContributionsEntity findByEmail(String email);
    }




