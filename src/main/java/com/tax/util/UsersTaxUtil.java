package com.tax.util;


import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Ola
 * Date: 24/05/15
 * Time: 22:03
 * To change this template use File | Settings | File Templates.
 */
public class UsersTaxUtil {

    /**
     * Get current income input amount, use for generate IncomeRange id
     * @param amount
     * @return int
     * @see com.tax.dao.UsersTax#createTaxUser(double, double, List)
     */
    public static int generateIncomeRangeId(double amount, double taxAllowance, List<Double> incomeRangeList){
        double range1 = incomeRangeList.get(0), range2 = incomeRangeList.get(1);
       if((amount - taxAllowance) <= range1 )
           return 1;
        else if((amount - taxAllowance) <= range2)
           return 2;
        else
           return 3;
    }

    /**
     * Add all income amount
     * @param amountList
     * @return double
     * @see com.tax.service.TaxService#totalTaxableIncome(ArrayList, int)
     * @see com.tax.service.TaxService#totalTaxPaid(ArrayList, int)
     */
    public static double computeAccumulatedAmount(ArrayList<ArrayList<String>> amountList, int index){
        double total = 0;
        for(ArrayList<String> taxableAmount : amountList) {
            total+=Double.valueOf(taxableAmount.get(index));
        }
        return total;
    }

    /**
     * Calculate tax payable given the amount and tax rate
     * @param amount and rate
     * @return double
     * @see com.tax.util.UsersTaxUtil#incomeTaxSummary(double, List, double, List)
     */
    public static double calculateTax(double amount, double rate){

       return (amount * rate) / 100;
    }


    /**
     * Get all the income tax summary (Taxable income, Rate, Tax paid), use as tax view summary
     * @param incomeInput, taxRates, taxAllowance,  incomeRangeList
     * @return ArrayList<ArrayList<Double>>
     * @see com.tax.controller.Controller#computeResult(com.tax.beans.Users, org.springframework.validation.BindingResult, java.util.Map, String)
     */
    public static ArrayList<ArrayList<String>> incomeTaxSummary(double incomeInput, List<Integer> taxRates, double taxAllowance, List<Double> incomeRangeList) {
        ArrayList<String> taxList ;
        ArrayList<ArrayList<String>> summary = new ArrayList<ArrayList<String>>();

        double rate20, rate40, rate45;
        double range1 = incomeRangeList.get(0), range2 = incomeRangeList.get(1);
        double userTaxable = incomeInput - taxAllowance;

        if( userTaxable  <= range1 ){
            taxList = new ArrayList<String>();
            if(userTaxable > 0){
                rate20 = taxRates.get(0);
                taxList.add(String.format("%.2f",userTaxable));
                taxList.add(String.valueOf(rate20));
                taxList.add(String.format("%.2f",calculateTax(userTaxable, rate20)));
                summary.add(taxList);
                return  summary;
            }
            else{
                taxList.add("0.00");
                taxList.add("0.00");
                taxList.add("0.00");
                summary.add(taxList);
                return  summary;

            }
        }
        else if(userTaxable > range1 && userTaxable <= range2 ) {
            rate20 = taxRates.get(0);
            rate40 = taxRates.get(1);
            taxList = new ArrayList<String>();
            taxList.add(String.format("%.2f",range1));
            taxList.add(String.valueOf(rate20));
            taxList.add(String.format("%.2f",calculateTax(range1, rate20)));
            summary.add(taxList);

            taxList = new ArrayList<String>();
            taxList.add(String.format("%.2f",userTaxable - range1));
            taxList.add(String.valueOf(rate40));
            taxList.add(String.format("%.2f",calculateTax(userTaxable - range1, rate40)));
            summary.add(taxList);
            return summary;
        }
        else{
            rate20 = taxRates.get(0);
            rate40 = taxRates.get(1);
            rate45 = taxRates.get(2);
            taxList = new ArrayList<String>();
            taxList.add(String.format("%.2f",range1));
            taxList.add(String.valueOf(rate20));
            taxList.add(String.format("%.2f",calculateTax(range1, rate20)));
            summary.add(taxList);

            if(userTaxable - range1 < range2) {
                taxList = new ArrayList<String>();
                taxList.add(String.format("%.2f",userTaxable - range1));
                taxList.add(String.valueOf(rate40));
                taxList.add(String.format("%.2f",calculateTax(userTaxable - range1, rate40)));
                summary.add(taxList);
                return summary;
            }
            else if(userTaxable - range1 > range2){
                taxList = new ArrayList<String>();
                taxList.add(String.format("%.2f",range2));
                taxList.add(String.valueOf(rate40));
                taxList.add(String.format("%.2f",calculateTax(range2, rate40)));
                summary.add(taxList);

                taxList = new ArrayList<String>();
                taxList.add(String.format("%.2f",userTaxable - (range1 + range2)));
                taxList.add(String.valueOf(rate45));
                taxList.add(String.format("%.2f",calculateTax(userTaxable - (range1 + range2), rate45)));
                summary.add(taxList);
                return summary;
            }
        }
        return null;
    }
}
