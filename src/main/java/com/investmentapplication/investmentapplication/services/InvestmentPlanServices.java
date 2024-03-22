package com.investmentapplication.investmentapplication.services;

import com.investmentapplication.investmentapplication.dto.InvestmentPlanDTO;
import com.investmentapplication.investmentapplication.entity.InvestmentPlansEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InvestmentPlanServices {

     String insertInvestmentPlans(List<InvestmentPlanDTO> investmentPlanDTO);
     List<InvestmentPlansEntity> getAllPlans();
}
