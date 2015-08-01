package com.tax.dao;

import com.tax.beans.IncomeRange;
import com.tax.beans.TaxAllowance;
import com.tax.beans.Users;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Ola
 * Date: 24/05/15
 * Time: 21:48
 * To change this template use File | Settings | File Templates.
 */
public interface UsersTaxInterface {

    public void createTaxUser(double amount, double taxAllowance, List<Double> incomeRangeList);
    public List<Users> getUsers();
    public double getTaxAllowance();
    public List<IncomeRange> getIncomeRanges();
}
