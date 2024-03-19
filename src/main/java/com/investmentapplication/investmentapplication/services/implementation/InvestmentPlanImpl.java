package com.investmentapplication.investmentapplication.services.implementation;

import com.investmentapplication.investmentapplication.entity.InvestmentPlansEntity;
import com.investmentapplication.investmentapplication.repository.InvestmentPlanRepository;
import com.investmentapplication.investmentapplication.services.InvestmentPlanServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestmentPlanImpl implements InvestmentPlanServices {

@Autowired
    private InvestmentPlanRepository investmentPlanRepository;
public List<InvestmentPlansEntity> getAllPlans(){

    return investmentPlanRepository.findAll();
}



}
