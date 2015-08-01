package com.tax.service;

import com.tax.beans.IncomeRange;
import com.tax.beans.Users;
import com.tax.dao.UsersTaxInterface;
import com.tax.util.UsersTaxUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Ola
 * Date: 24/05/15
 * Time: 22:21
 * To change this template use File | Settings | File Templates.
 */

@Service
public class TaxService implements TaxServiceInterface{
    @Autowired
    UsersTaxInterface usersTax;

    @Override
    @Transactional
    public Users getUser(double amount, double taxAllowance, List<Double> incomeRangeList){
        usersTax.createTaxUser(amount, taxAllowance, incomeRangeList);
        return usersTax.getUsers().get(usersTax.getUsers().size()-1);
    }

    @Override
    @Transactional
    public ArrayList<ArrayList<String>> taxSummary(double incomeInput, List<Integer> taxRates, double allowance, List<Double> incomeRangeList){

        return UsersTaxUtil.incomeTaxSummary(incomeInput,taxRates, allowance, incomeRangeList);
    }

    @Override
    @Transactional
    public double totalTaxPaid(ArrayList<ArrayList<String>> payableSummary, int index){
      return  UsersTaxUtil.computeAccumulatedAmount(payableSummary, index);
    }

    @Override
    @Transactional
    public double totalTaxableIncome(ArrayList<ArrayList<String>> taxableSummary, int index){
        return  UsersTaxUtil.computeAccumulatedAmount(taxableSummary, index);
    }

    @Override
    @Transactional
    public double getAllowance(){
       return usersTax.getTaxAllowance();
    }

    @Override
    @Transactional
    public List<IncomeRange> getTaxIncomeRange(){
        return usersTax.getIncomeRanges();
    }
}
