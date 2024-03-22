package com.investmentapplication.investmentapplication.services.implementation;

import com.investmentapplication.investmentapplication.entity.PlanContributionsEntity;
import com.investmentapplication.investmentapplication.repository.PlanContributionsRepository;
import com.investmentapplication.investmentapplication.services.PlanContributionsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class PlanContributionsImpl implements PlanContributionsServices {
 @Autowired
    PlanContributionsRepository planContributionsRepository;

    @Override
    public PlanContributionsEntity getPlanContribution(String email) {
        return planContributionsRepository.findByEmail(email);
    }

    @Override
    public String updatePlanContribution(String email, PlanContributionsEntity planContribution) {
        PlanContributionsEntity existingRecord =  planContributionsRepository.findByEmail(email);

        if(existingRecord != null){
            existingRecord.setGovernmental_plans(planContribution.getGovernmental_plans());
            existingRecord.setDefined_benefit_plan((planContribution.getDefined_benefit_plan()));
            existingRecord.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            existingRecord.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            existingRecord.setPlans457(planContribution.getPlans457());
            existingRecord.setMoney_purchase_plans(planContribution.getMoney_purchase_plans());
            existingRecord.setProfit_sharing_plans(planContribution.getProfit_sharing_plans());
            existingRecord.setMultiple_employer_plans(planContribution.getMultiple_employer_plans());
            existingRecord.setPlans403b(planContribution.getPlans403b());
            existingRecord.setESOPs(planContribution.getESOPs());
            existingRecord.setPayroll_Deduction_IRAs(planContribution.getPayroll_Deduction_IRAs());
            existingRecord.setRoth_IRA(planContribution.getRoth_IRA());
            existingRecord.setSARSEP(planContribution.getSARSEP());
            existingRecord.setSEP(planContribution.getSEP());
            existingRecord.setEmail(planContribution.getEmail());
        }
        planContribution.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        assert existingRecord != null;
        planContributionsRepository.save(existingRecord);
        return "Updated successfully";
    }

    public boolean validateInvestmentPercentages(List<Double> investmentPercentages) {
        double sum = investmentPercentages.stream().mapToDouble(Double::doubleValue).sum();
        return Double.compare(sum, 100.0) == 0;
    }
}
