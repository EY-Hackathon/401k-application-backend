package com.investmentapplication.investmentapplication.controller;

import com.investmentapplication.investmentapplication.entity.PlanContributionsEntity;
import com.investmentapplication.investmentapplication.exception.PlanContributionException;
import com.investmentapplication.investmentapplication.services.PlanContributionsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
@RestController
public class PlanContributionsController implements PlanContributionsOperations {

    @Autowired
    PlanContributionsServices planContributionsServices;

    // This method overrides the getPlanContribution method defined in the PlanContributionsOperations interface
    @Override
    public ResponseEntity<Object> getPlanContribution(String email) {
        try {
            // Retrieve plan contribution for the given email
            PlanContributionsEntity planContribution = planContributionsServices.getPlanContribution(email);
            // Return the plan contribution if found
            return new ResponseEntity<>(planContribution, HttpStatus.OK);
        } catch (PlanContributionException e) {
            // Return error response if plan contribution is not found
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // This method overrides the updatePlanContribution method defined in the PlanContributionsOperations interface
    @Override
    public ResponseEntity<Object> updatePlanContribution(String email, PlanContributionsEntity planContribution) {
        try {
            // Calculate sum of contributions
            double sum = calculateSum(planContribution);
            // Check if sum is 100%
            if (sum != 100) {
                throw new PlanContributionException("The investments do not sum up to 100%");
            }
            // Update plan contribution
            String response = planContributionsServices.updatePlanContribution(email, planContribution);
            // Return success response
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (PlanContributionException e) {
            // Return error response if there's an exception
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Helper method to calculate the sum of contributions
    private double calculateSum(PlanContributionsEntity planContribution) {
        double sum = 0.0;
        Class<?> clazz = planContribution.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(planContribution);
                if (value instanceof Number && !field.getName().equals("email")) {
                    sum += ((Number) value).doubleValue();
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return sum;
    }
}
