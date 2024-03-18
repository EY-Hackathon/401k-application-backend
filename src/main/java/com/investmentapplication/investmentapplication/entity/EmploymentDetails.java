package com.investmentapplication.investmentapplication.entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class EmploymentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    String employername;
    String username;
    Date employment_start_date;
    double annual_salary;
    int payfrequency;

    public String getEmployername() {
        return employername;
    }

    public void setEmployername(String employername) {
        this.employername = employername;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getEmployment_start_date() {
        return employment_start_date;
    }

    public void setEmployment_start_date(Date employment_start_date) {
        this.employment_start_date = employment_start_date;
    }

    public double getAnnual_salary() {
        return annual_salary;
    }

    public void setAnnual_salary(double annual_salary) {
        this.annual_salary = annual_salary;
    }

    public int getPayfrequency() {
        return payfrequency;
    }

    public void setPayfrequency(int payfrequency) {
        this.payfrequency = payfrequency;
    }
}
