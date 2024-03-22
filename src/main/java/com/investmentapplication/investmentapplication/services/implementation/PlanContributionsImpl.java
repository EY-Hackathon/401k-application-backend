package com.investmentapplication.investmentapplication.services.implementation;
import com.investmentapplication.investmentapplication.entity.PlanContributionsEntity;
import com.investmentapplication.investmentapplication.exception.PlanContributionException;
import com.investmentapplication.investmentapplication.repository.PlanContributionsRepository;
import com.investmentapplication.investmentapplication.services.PlanContributionsServices;
import com.investmentapplication.investmentapplication.util.ErrorCode;
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
        PlanContributionsEntity planContribution = planContributionsRepository.findByEmail(email);
        if (planContribution == null) {
            throw new PlanContributionException(ErrorCode.PLAN_CONTRIBUTION_NOT_FOUND, "Plan contribution not found for email: " + email);
        }
        return planContribution;
    }

    @Override
    public String updatePlanContribution(String email, PlanContributionsEntity planContribution) {
        PlanContributionsEntity existingRecord =  planContributionsRepository.findByEmail(email);
        if(existingRecord == null) {
            throw new PlanContributionException(ErrorCode.PLAN_CONTRIBUTION_NOT_FOUND, "Plan contribution not found for email: " + email);
        }

        existingRecord.setGovernmentalPlans(planContribution.getGovernmentalPlans());
        existingRecord.setDefinedBenefitPlan((planContribution.getDefinedBenefitPlan()));
        existingRecord.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        existingRecord.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        existingRecord.setPlans457(planContribution.getPlans457());
        existingRecord.setMoneyPurchasePlans(planContribution.getMoneyPurchasePlans());
        existingRecord.setProfitSharingPlans(planContribution.getProfitSharingPlans());
        existingRecord.setMultipleEmployerPlans(planContribution.getMultipleEmployerPlans());
        existingRecord.setPlans403b(planContribution.getPlans403b());
        existingRecord.setESOPs(planContribution.getESOPs());
        existingRecord.setPayrollDeductionIRAs(planContribution.getPayrollDeductionIRAs());
        existingRecord.setRothIRA(planContribution.getRothIRA());
        existingRecord.setSARSEP(planContribution.getSARSEP());
        existingRecord.setSEP(planContribution.getSEP());
        existingRecord.setEmail(planContribution.getEmail());
        existingRecord.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        planContributionsRepository.save(existingRecord);
        return "Updated successfully";
    }

    public boolean validateInvestmentPercentages(List<Double> investmentPercentages) {
        double sum = investmentPercentages.stream().mapToDouble(Double::doubleValue).sum();
        return Double.compare(sum, 100.0) == 0;
    }
}