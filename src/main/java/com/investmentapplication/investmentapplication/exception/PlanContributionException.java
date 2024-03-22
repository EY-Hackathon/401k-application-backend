package com.investmentapplication.investmentapplication.exception;

import com.investmentapplication.investmentapplication.util.ErrorCode;

public class PlanContributionException extends RuntimeException {

    public PlanContributionException(String message) {
        super(message);
    }

    public PlanContributionException(ErrorCode errorCode, String s) {
    }
}