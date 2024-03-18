package com.investmentapplication.investmentapplication.services.implementation;

import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class PaycheckCalculator {
    public int getPayCheckCount(String payFrequency, Date startDate, Date endDate) {
        int payCheckCount = 0;

        switch (payFrequency.toLowerCase()) {
            case "monthly":
                payCheckCount = getMonthDifference(startDate, endDate);
                break;
            case "biweekly":
                Date nextBiWeeklyPayday = getNextBiWeeklyPayday(startDate);
                while (nextBiWeeklyPayday.before(endDate) || nextBiWeeklyPayday.equals(endDate)) {
                    payCheckCount++;
                    nextBiWeeklyPayday = addDays(nextBiWeeklyPayday, 14); // Add 14 days for bi-weekly
                }
                break;
            case "weekly":
                payCheckCount = getWeekDifference(startDate, endDate);
                break;
            case "semimonthly":
                Date nextFirstPayday = getNextFirstPayday(startDate);
                while (nextFirstPayday.before(endDate) || nextFirstPayday.equals(endDate)) {
                    payCheckCount++;
                    nextFirstPayday = addDays(nextFirstPayday, 15); // Add 15 days for semi-monthly
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid pay frequency");
        }

        return payCheckCount;
    }

    private int getMonthDifference(Date startDate, Date endDate) {
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);

        int diffYear = endCal.get(Calendar.YEAR) - startCal.get(Calendar.YEAR);
        int diffMonth = diffYear * 12 + endCal.get(Calendar.MONTH) - startCal.get(Calendar.MONTH);

        return diffMonth;
    }

    private int getWeekDifference(Date startDate, Date endDate) {
        long diffInMillis = endDate.getTime() - startDate.getTime();
        return (int) (diffInMillis / (1000 * 60 * 60 * 24 * 7)); // Convert milliseconds to weeks
    }

    private Date getNextBiWeeklyPayday(Date startDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(Calendar.DATE, 1); // Move to next day to avoid counting start date
        cal.add(Calendar.WEEK_OF_YEAR, 2); // Add 2 weeks for bi-weekly
        cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY); // Set to next Friday
        return cal.getTime();
    }

    private Date getNextFirstPayday(Date startDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(Calendar.DATE, 1); // Move to next day to avoid counting start date
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        int daysToAdd = (dayOfMonth <= 15) ? 15 - dayOfMonth : 30 - dayOfMonth;
        cal.add(Calendar.DATE, daysToAdd); // Add days to get to next semi-monthly payday
        return cal.getTime();
    }

    private Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }
}
