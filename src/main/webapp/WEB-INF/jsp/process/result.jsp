<%--
  Created by IntelliJ IDEA.
  User: Ola
  Date: 24/05/15
  Time: 13:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../taglib.jsp" %>
<html>
<head>
    <title>Tax Payable Breakdown<</title>
    <style>
        table, th, td {
            border: 1px solid black;
        }
        .tax-payable{
            border-bottom: 3px double;
        }
        td.currency{
            text-align:right;
        }
        td.rate{
            text-align:center;
        }
    </style>
</head>
<body>
  <h2>Tax Payable Breakdown</h2>
  <br/>
  <table>
      <tr>
          <td width="200">Income</td>
          <c:set var="amountInput" value="£${amountInput}"/>
          <td class="currency" width="75">${amountInput}</td>
      </tr>
      <tr>
          <td width="200">Allowance</td>
          <c:set var="taxAllowance" value="£${taxAllowance}"/>
          <td class="currency" width="75">${taxAllowance}</td>
      </tr>
      <tr>
          <td width="200">Total Taxable</td>
          <c:set var="taxableIncome" value="£${taxableIncome}"/>
          <td class="currency" width="75">${taxableIncome}</td>
      </tr>
  </table>
  <br/>
  <br/>
  Breakdown
  <table>
      <tr>
          <th width="100">Taxable Income</th>
          <th width="50">Rate(%)</th>
          <th width="75">Tax</th>

      </tr>

      <c:forEach items="${summaryList}" var="summary">
          <tr>   <c:set var="summary0" value="£${summary[0]}"/>
                 <c:set var="summary2" value="£${summary[2]}"/>
                 <td class="currency" width="150">${summary0}</td>
                 <td class="rate" width="50">${fn:split(summary[1], ".")[0]}</td>
                 <td class="currency" width="70">${summary2}</td>

          </tr>
      </c:forEach>

  </table>
  <br/>
  <c:set var="totalTaxPayable" value="£${totalTaxPayable}"/>
  Tax Payable: <span class="tax-payable">${totalTaxPayable}</span>

  <br/>
  <br/>
  <button OnClick=" location.href='/tax' ">Back</button>
</body>
</html>