package com.tax.dao;

import com.tax.beans.IncomeRange;
import com.tax.beans.TaxAllowance;
import com.tax.beans.Users;
import com.tax.util.UsersTaxUtil;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Ola
 * Date: 24/05/15
 * Time: 21:48
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class UsersTax implements UsersTaxInterface{
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    /**
     * Create site user and persist into db
     * @param amount
     * @return void
     * @see com.tax.service.TaxService#getUser(double, double, List)
     */
    @Override
    public void createTaxUser(double amount, double taxAllowance, List<Double> incomeRangeList) {
        Users users = new Users();
        users.setIncome(amount);
        IncomeRange incomeRange = (IncomeRange)sessionFactory.getCurrentSession().get(IncomeRange.class, UsersTaxUtil.generateIncomeRangeId(amount,
                taxAllowance, incomeRangeList));
        users.setIncomeRange(incomeRange);
        users.setTaxAllowance((TaxAllowance)sessionFactory.getCurrentSession().get(TaxAllowance.class, 1));
        sessionFactory.getCurrentSession().persist(users);

    }

    /**
     * Get list of imaginary site users
     * @param
     * @return List<Users>
     * @see com.tax.service.TaxService#getUser(double, double, List)
     */
    @Override
    public List<Users> getUsers() {
        return (List<Users>)sessionFactory.getCurrentSession().createQuery("FROM Users ").list();
    }

    /**
     * Get TaxAllowance
     * @param
     * @return double
     * @see com.tax.service.TaxService#getAllowance()
     */
    @Override
    public double getTaxAllowance(){
        TaxAllowance taxAllowance = (TaxAllowance)sessionFactory.getCurrentSession().get(TaxAllowance.class, 1);
        return taxAllowance.getAmount();
    }

    /**
     * Get list of IncomeRange
     * @param
     * @return List
     * @see com.tax.service.TaxService#getTaxIncomeRange()
     */
    @Override
    public List<IncomeRange> getIncomeRanges(){
        return (List<IncomeRange>)sessionFactory.getCurrentSession().createQuery("FROM IncomeRange ").list();
    }
}
