package com.investmentapplication.investmentapplication.services.implementation;

import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

/**
 * Service class for calculating the number of paychecks between two dates based on pay frequency.
 */
@Service
public class PaycheckCalculator {

    /**
     * Calculates the number of paychecks between two dates based on the pay frequency.
     * @param payFrequency The frequency of pay (e.g., monthly, biweekly, weekly, semimonthly).
     * @param startDate The start date.
     * @param endDate The end date.
     * @return The number of paychecks between the start and end dates.
     * @throws IllegalArgumentException if the pay frequency is invalid.
     */
    public int getPayCheckCount(String payFrequency, Date startDate, Date endDate) {
        int payCheckCount = 0;

        switch (payFrequency.toLowerCase()) {
            case "monthly":
                payCheckCount = getMonthDifference(startDate, endDate);
                break;
            case "biweekly":
                payCheckCount = getBiWeeklyPayCheckCount(startDate, endDate);
                break;
            case "weekly":
                payCheckCount = getWeekDifference(startDate, endDate);
                break;
            case "semimonthly":
                payCheckCount = getSemiMonthlyPayCheckCount(startDate, endDate);
                break;
            default:
                throw new IllegalArgumentException("Invalid pay frequency");
        }

        return payCheckCount;
    }

    /**
     * Calculates the difference in months between two dates.
     * @param startDate The start date.
     * @param endDate The end date.
     * @return The difference in months between the start and end dates.
     */
    private int getMonthDifference(Date startDate, Date endDate) {
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);

        int diffYear = endCal.get(Calendar.YEAR) - startCal.get(Calendar.YEAR);
        int diffMonth = diffYear * 12 + endCal.get(Calendar.MONTH) - startCal.get(Calendar.MONTH);

        return diffMonth;
    }

    /**
     * Calculates the number of bi-weekly paychecks between two dates.
     * @param startDate The start date.
     * @param endDate The end date.
     * @return The number of bi-weekly paychecks between the start and end dates.
     */
    private int getBiWeeklyPayCheckCount(Date startDate, Date endDate) {
        Date nextBiWeeklyPayday = getNextBiWeeklyPayday(startDate);
        int payCheckCount = 0;

        while (nextBiWeeklyPayday.before(endDate) || nextBiWeeklyPayday.equals(endDate)) {
            payCheckCount++;
            nextBiWeeklyPayday = addDays(nextBiWeeklyPayday, 14); // Add 14 days for bi-weekly
        }

        return payCheckCount;
    }

    /**
     * Calculates the difference in weeks between two dates.
     * @param startDate The start date.
     * @param endDate The end date.
     * @return The difference in weeks between the start and end dates.
     */
    private int getWeekDifference(Date startDate, Date endDate) {
        long diffInMillis = endDate.getTime() - startDate.getTime();
        return (int) (diffInMillis / (1000 * 60 * 60 * 24 * 7)); // Convert milliseconds to weeks
    }

    /**
     * Calculates the number of semi-monthly paychecks between two dates.
     * @param startDate The start date.
     * @param endDate The end date.
     * @return The number of semi-monthly paychecks between the start and end dates.
     */
    private int getSemiMonthlyPayCheckCount(Date startDate, Date endDate) {
        Date nextFirstPayday = getNextFirstPayday(startDate);
        int payCheckCount = 0;

        while (nextFirstPayday.before(endDate) || nextFirstPayday.equals(endDate)) {
            payCheckCount++;
            nextFirstPayday = addDays(nextFirstPayday, 15); // Add 15 days for semi-monthly
        }

        return payCheckCount;
    }

    /**
     * Calculates the next bi-weekly payday based on the given start date.
     * @param startDate The start date.
     * @return The next bi-weekly payday.
     */
    private Date getNextBiWeeklyPayday(Date startDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(Calendar.DATE, 1); // Move to next day to avoid counting start date
        cal.add(Calendar.WEEK_OF_YEAR, 2); // Add 2 weeks for bi-weekly
        cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY); // Set to next Friday
        return cal.getTime();
    }

    /**
     * Calculates the next semi-monthly payday based on the given start date.
     * @param startDate The start date.
     * @return The next semi-monthly payday.
     */
    private Date getNextFirstPayday(Date startDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(Calendar.DATE, 1); // Move to next day to avoid counting start date
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        int daysToAdd = (dayOfMonth <= 15) ? 15 - dayOfMonth : 30 - dayOfMonth;
        cal.add(Calendar.DATE, daysToAdd); // Add days to get to next semi-monthly payday
        return cal.getTime();
    }

    /**
     * Adds the specified number of days to the given date.
     * @param date The date to which days are added.
     * @param days The number of days to add.
     * @return The date after adding the specified number of days.
     */
    private Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }
}