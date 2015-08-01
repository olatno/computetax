package com.tax.beans;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Ola
 * Date: 27/05/15
 * Time: 10:30
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "tax_allowance")
public class TaxAllowance implements Serializable {

    private int id;
    private double amount;

    public TaxAllowance(){}

    @Id
    @GeneratedValue
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column (name = "allowance_amount")
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
