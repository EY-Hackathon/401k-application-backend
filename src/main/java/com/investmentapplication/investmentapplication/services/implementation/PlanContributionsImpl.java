package com.investmentapplication.investmentapplication.services.implementation;
import com.investmentapplication.investmentapplication.entity.PlanContributionsEntity;
import com.investmentapplication.investmentapplication.entity.UserAccountsEntity;
import com.investmentapplication.investmentapplication.exception.PlanContributionException;
import com.investmentapplication.investmentapplication.repository.PlanContributionsRepository;
import com.investmentapplication.investmentapplication.repository.UserAccountsRepository;
import com.investmentapplication.investmentapplication.services.PlanContributionsServices;
import com.investmentapplication.investmentapplication.util.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * Service class for managing plan contributions.
 */
import static org.apache.logging.log4j.util.Strings.EMPTY;

@Service
public class PlanContributionsImpl implements PlanContributionsServices {
    @Autowired
    PlanContributionsRepository planContributionsRepository;

    @Autowired
    UserAccountsRepository userAccountsRepository;

    /**
     * Retrieves plan contribution details for a given email.
     * @param email The email of the user.
     * @return The plan contribution entity corresponding to the email.
     * @throws PlanContributionException if plan contribution is not found for the given email.
     */
    @Override
    public PlanContributionsEntity getPlanContribution(String email) {
        PlanContributionsEntity planContribution = planContributionsRepository.findByEmail(email);
        if (planContribution == null) {
            throw new PlanContributionException(ErrorCode.PLAN_CONTRIBUTION_NOT_FOUND, "Plan contribution not found for email: " + email);
        }
        return planContribution;
    }

    /**
     * Updates plan contribution details for a given email.
     * @param email The email of the user.
     * @param planContribution The updated plan contribution entity.
     * @return A message indicating successful update.
     * @throws PlanContributionException if plan contribution is not found for the given email.
     */
    @Override
    public String updatePlanContribution(String email, PlanContributionsEntity planContribution) {
        PlanContributionsEntity existingRecord =  planContributionsRepository.findByEmail(email);

        List<UserAccountsEntity> userAccountDetails = userAccountsRepository.findByEmail(email);
        List<String> userAccountsEmailList = userAccountDetails.stream().map(UserAccountsEntity::getEmail).toList();
        String accountEmail = userAccountsEmailList.get(0) != null ? userAccountsEmailList.get(0) : EMPTY;

        if(accountEmail.isEmpty() && existingRecord == null) {
            throw new PlanContributionException(ErrorCode.PLAN_CONTRIBUTION_NOT_FOUND, "Plan contribution could not be updated for email: " + email);
        }

        existingRecord.setGovernmentalPlans(planContribution.getGovernmentalPlans());
        existingRecord.setDefinedBenefitPlan(planContribution.getDefinedBenefitPlan());
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
        if(existingRecord != null){
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
        }
        else if(accountEmail.equals(email)) {
            PlanContributionsEntity newRecord = new PlanContributionsEntity();
            newRecord.setGovernmentalPlans(planContribution.getGovernmentalPlans());
            newRecord.setDefinedBenefitPlan((planContribution.getDefinedBenefitPlan()));
            newRecord.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            newRecord.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            newRecord.setPlans457(planContribution.getPlans457());
            newRecord.setMoneyPurchasePlans(planContribution.getMoneyPurchasePlans());
            newRecord.setProfitSharingPlans(planContribution.getProfitSharingPlans());
            newRecord.setMultipleEmployerPlans(planContribution.getMultipleEmployerPlans());
            newRecord.setPlans403b(planContribution.getPlans403b());
            newRecord.setESOPs(planContribution.getESOPs());
            newRecord.setPayrollDeductionIRAs(planContribution.getPayrollDeductionIRAs());
            newRecord.setRothIRA(planContribution.getRothIRA());
            newRecord.setSARSEP(planContribution.getSARSEP());
            newRecord.setSEP(planContribution.getSEP());
            newRecord.setEmail(planContribution.getEmail());
            newRecord.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            planContributionsRepository.save(newRecord);
        }
        // Save the updated record
        planContributionsRepository.save(existingRecord);
        return "Updated successfully";
    }

    /**
     * Validates investment percentages to ensure they sum up to 100%.
     * @param investmentPercentages List of investment percentages.
     * @return True if the sum of investment percentages is equal to 100, false otherwise.
     */
    public boolean validateInvestmentPercentages(List<Double> investmentPercentages) {
        double sum = investmentPercentages.stream().mapToDouble(Double::doubleValue).sum();
        return Double.compare(sum, 100.0) == 0;
    }
}
