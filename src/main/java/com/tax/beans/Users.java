package com.tax.beans;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Ola
 * Date: 24/05/15
 * Time: 00:51
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "users")
public class Users implements Serializable {

    private int id;
    private double income;
    private IncomeRange incomeRange;
    private TaxAllowance taxAllowance;


    public Users(){}

    @Id
    @GeneratedValue
    @Column (name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column (name = "income_amount")
    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    @OneToOne (cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinColumn(name = "range_id",referencedColumnName = "id")
    public IncomeRange getIncomeRange() {
        return incomeRange;
    }

    public void setIncomeRange(IncomeRange incomeRange) {
        this.incomeRange = incomeRange;
    }

    @OneToOne (cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinColumn(name = "allowance_id",referencedColumnName = "id")
    public TaxAllowance getTaxAllowance() {
        return taxAllowance;
    }

    public void setTaxAllowance(TaxAllowance taxAllowance) {
        this.taxAllowance = taxAllowance;
    }

}
