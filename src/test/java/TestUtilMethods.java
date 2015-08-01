import com.tax.beans.IncomeRange;
import com.tax.dao.UsersTax;
import com.tax.dao.UsersTaxInterface;
import com.tax.service.TaxServiceInterface;
import org.junit.Test;
import com.tax.util.UsersTaxUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Created with IntelliJ IDEA.
 * User: Ola
 * Date: 24/05/15
 * Time: 21:33
 * To change this template use File | Settings | File Templates.
 */
public class TestUtilMethods {
    private static final double DELTA = 0.1;
    private ArrayList<String> listTaxableIncome = null;
    private ArrayList<String> listPaybleTax = null;
    private static List<Integer> rate = null;
    private static List<Double> range = null;
    private List<Double> rangeList = new ArrayList<Double>();
    /**
     * testTaxPayable -- check if the tax calculator returns expected result
     * @return No return value.
     */
    @Test
    public void testTaxPayable () throws Exception{

        assertEquals(6357, UsersTaxUtil.calculateTax(31785, 20), DELTA);


    }

    /**
     * testIncomeRange -- criteria given in order to apply tax rate e.g 31785 is in range number 1
     * @return No return value.    taxServiceInterface.getTaxIncomeRange()
     */
    @Test
    public void testIncomeRange () throws Exception{
        rangeList.add(31785.0);
        rangeList.add(150000.0);
        assertEquals(1, UsersTaxUtil.generateIncomeRangeId(31785.0, 10600.0, rangeList));


    }


    /**
     * testTotalTaxPaid -- add all taxes on taxable income
     * @return No return value.
     */
    @Test
    public void testTotalTaxPaid () throws Exception{
        ArrayList<ArrayList<String>> arrayLists = new ArrayList<ArrayList<String>>();
        listPaybleTax = new ArrayList<String>();
        listPaybleTax.add("31785");
        listPaybleTax.add("20");
        listPaybleTax.add("6357.0");
        arrayLists.add(listPaybleTax);
        listPaybleTax = new ArrayList<String>();
        listPaybleTax.add("13855");
        listPaybleTax.add("40");
        listPaybleTax.add("5542.0");
        arrayLists.add(listPaybleTax);
        assertEquals(11899,UsersTaxUtil.computeAccumulatedAmount(arrayLists, 2) , DELTA);


    }

    /**
     * testTotalTaxPaid -- add all taxes on taxable income
     * @return No return value.
     */
    @Test
    public void testTotalTaxableIncome () throws Exception{
        ArrayList<ArrayList<String>> arrayLists = new ArrayList<ArrayList<String>>();
        listTaxableIncome = new ArrayList<String>();
        listTaxableIncome.add("6357.0");
        listTaxableIncome.add("20");
        listTaxableIncome.add("6357.0");
        arrayLists.add(listTaxableIncome);
        listTaxableIncome = new ArrayList<String>();
        listTaxableIncome.add("18886.0");
        listTaxableIncome.add("40");
        listTaxableIncome.add("5542.0");
        arrayLists.add(listTaxableIncome);
        assertEquals(25243.0,UsersTaxUtil.computeAccumulatedAmount(arrayLists, 0) , DELTA);


    }

    /**
     * testIncomeTaxSummary -- Get tax income summary
     * @return No return value.
     */
    @Test
    public void testIncomeTaxSummary() throws Exception{
        ArrayList<String> arrayList = new ArrayList<String>();
        rate = new ArrayList<Integer>();
        range = new ArrayList<Double>();

        arrayList.add("15250.00");
        arrayList.add("20.0");
        arrayList.add("3050.00");


        rate.add(20);
        range.add(31785.0);
        range.add(150000.0);

        assertEquals(arrayList, UsersTaxUtil.incomeTaxSummary(25850, rate, 10600, range ).get(0));
    }

}
