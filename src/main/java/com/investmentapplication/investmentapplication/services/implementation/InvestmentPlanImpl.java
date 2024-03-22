package com.investmentapplication.investmentapplication.services.implementation;

import com.investmentapplication.investmentapplication.dto.InvestmentPlanDTO;
import com.investmentapplication.investmentapplication.entity.InvestmentPlansEntity;
import com.investmentapplication.investmentapplication.repository.InvestmentPlanRepository;
import com.investmentapplication.investmentapplication.services.InvestmentPlanServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class InvestmentPlanImpl implements InvestmentPlanServices {

@Autowired
    private InvestmentPlanRepository investmentPlanRepository;

    @Override
    public String insertInvestmentPlans(List<InvestmentPlanDTO> investmentPlanDTO) {

        investmentPlanDTO.forEach(Plans -> {
            InvestmentPlansEntity investmentPlansEntity = new InvestmentPlansEntity();

            investmentPlansEntity.setPlanName(Plans.getPlanName());
            investmentPlansEntity.setPlansDesc(Plans.getPlansDesc());
            investmentPlansEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            investmentPlansEntity.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

            investmentPlanRepository.save(investmentPlansEntity);


        });


    return "Plans inserted";
    }


    public List<InvestmentPlansEntity> getAllPlans(){

    return investmentPlanRepository.findAll();
}



}
