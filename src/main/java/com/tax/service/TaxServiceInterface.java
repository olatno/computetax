package com.tax.service;

import com.tax.beans.IncomeRange;
import com.tax.beans.Users;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Ola
 * Date: 24/05/15
 * Time: 22:22
 * To change this template use File | Settings | File Templates.
 */
public interface TaxServiceInterface {

    public Users getUser(double amount, double taxAllowance, List<Double> incomeRangeList);
    public ArrayList<ArrayList<String>> taxSummary(double incomeInput, List<Integer> taxRates, double allowance, List<Double> incomeRangeList);
    public double totalTaxPaid(ArrayList<ArrayList<String>> payableSummary, int index);
    public double totalTaxableIncome(ArrayList<ArrayList<String>> taxableSummary, int index);
    public double getAllowance();
    public List<IncomeRange> getTaxIncomeRange();
}
