package com.tax.beans;


import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "tax_rate")
/**
 * Created with IntelliJ IDEA.
 * User: Ola
 * Date: 24/05/15
 * Time: 00:52
 * To change this template use File | Settings | File Templates.
 */
public class TaxRate implements Serializable {


    private int id;
    private int rate;
    private IncomeRange incomeRange;

    public TaxRate(){}

    @Id
    @GeneratedValue
    @Column (name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Column (name = "rate")
    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @ManyToOne()
    @JoinColumn(name = "range_id")
    public IncomeRange getIncomeRange() {
        return incomeRange;
    }

    public void setIncomeRange(IncomeRange incomeRange) {
        this.incomeRange = incomeRange;
    }

}
