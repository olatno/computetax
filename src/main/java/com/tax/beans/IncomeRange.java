package com.tax.beans;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Ola
 * Date: 24/05/15
 * Time: 00:52
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "income_range")
public class IncomeRange implements Serializable {

    private int id;
    private double amount;
    private Collection <TaxRate> taxRates;

    public IncomeRange(){}

    @Id
    @GeneratedValue
    @Column (name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column (name = "range_amount")
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "incomeRange", fetch= FetchType.EAGER)
    public Collection<TaxRate> getTaxRates() {
        return taxRates;
    }

    public void setTaxRates(Collection<TaxRate> taxRates) {
        this.taxRates = taxRates;
    }

}
