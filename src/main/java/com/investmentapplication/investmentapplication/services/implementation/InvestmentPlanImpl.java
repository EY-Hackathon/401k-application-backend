package com.investmentapplication.investmentapplication.services.implementation;

import com.investmentapplication.investmentapplication.dto.InvestmentPlanDTO;
import com.investmentapplication.investmentapplication.entity.InvestmentPlansEntity;
import com.investmentapplication.investmentapplication.repository.InvestmentPlanRepository;
import com.investmentapplication.investmentapplication.services.InvestmentPlanServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * This class implements the InvestmentPlanServices interface and provides methods
 * for inserting investment plans and retrieving all existing plans.
 */
@Service
public class InvestmentPlanImpl implements InvestmentPlanServices {

    // Autowired repository for accessing investment plan data
    @Autowired
    private InvestmentPlanRepository investmentPlanRepository;

    /**
     * Inserts a list of investment plans into the database.
     * @param investmentPlanDTO The list of investment plans to be inserted.
     * @return A message indicating that the plans have been successfully inserted.
     */
    @Override
    public String insertInvestmentPlans(List<InvestmentPlanDTO> investmentPlanDTO) {
        // For each plan DTO in the list, create a corresponding entity and save it to the database
        investmentPlanDTO.forEach(Plans -> {
            InvestmentPlansEntity investmentPlansEntity = new InvestmentPlansEntity();

            // Set attributes of the entity based on the corresponding DTO
            investmentPlansEntity.setPlanName(Plans.getPlanName());
            investmentPlansEntity.setPlansDesc(Plans.getPlansDesc());
            investmentPlansEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            investmentPlansEntity.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

            // Save the entity to the repository
            investmentPlanRepository.save(investmentPlansEntity);
        });

        // Return a message indicating successful insertion of plans
        return "Plans inserted";
    }

    /**
     * Retrieves all investment plans from the database.
     * @return A list of all investment plans stored in the database.
     */
    public List<InvestmentPlansEntity> getAllPlans() {
        // Retrieve all investment plans from the repository
        return investmentPlanRepository.findAll();
    }
}