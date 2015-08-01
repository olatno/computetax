package com.tax.controller;

import com.tax.beans.IncomeRange;
import com.tax.beans.TaxRate;
import com.tax.beans.Users;
import com.tax.service.TaxServiceInterface;
import com.tax.util.InputValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 * User: Ola
 * Date: 24/05/15
 * Time: 02:15
 * To change this template use File | Settings | File Templates.
 */


@Configuration
@org.springframework.stereotype.Controller //spring stereotype annotation
@ComponentScan("{com.tax.dao, com.tax.service, com.tax.util}")

public class Controller {

    @Autowired
    TaxServiceInterface taxService;

    @RequestMapping(value = "/")
    public String homePage(@ModelAttribute("users") Users users){

        return "home";
    }

    @RequestMapping(value = "/process", method = RequestMethod.POST)

    public String computeResult(@Valid Users users, BindingResult result, Map<String, Object> map, @RequestParam("income") String income){
        InputValidation inputValidation = new InputValidation();
        inputValidation.validate(users, result);
        if(result.hasErrors()){return "home";}

        double allowance = taxService.getAllowance();

        List<Double> rangeList = new ArrayList<Double>();
        List<IncomeRange> incomeRangeList = taxService.getTaxIncomeRange();
        for(IncomeRange range : incomeRangeList){
            rangeList.add(range.getAmount());
        }

        Users taxUser = taxService.getUser(Double.valueOf(income), allowance, rangeList);
        IncomeRange userTaxRange =  taxUser.getIncomeRange();
        List<TaxRate> taxRates = new ArrayList <TaxRate>(userTaxRange.getTaxRates());
        List<Integer> rateList = new ArrayList<Integer>();
        for(TaxRate rate : taxRates){
            rateList.add(rate.getRate());
        }
        ArrayList<ArrayList<String>> summaryList = taxService.taxSummary(taxUser.getIncome(),rateList, allowance, rangeList);

        map.put("taxAllowance", String.format("%.2f",allowance));
        map.put("amountInput", String.format("%.2f",taxUser.getIncome()));
        map.put("summaryList", summaryList);
        map.put("taxableIncome", String.format("%.2f",taxService.totalTaxableIncome(summaryList, 0)));
        map.put("totalTaxPayable", String.format("%.2f", taxService.totalTaxPaid(summaryList, 2)));

        return "process/result";
    }
}
