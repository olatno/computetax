<%--
  Created by IntelliJ IDEA.
  User: Ola
  Date: 24/05/15
  Time: 02:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="taglib.jsp" %>
<html>
<head>
    <title>Tax Computation</title>
</head>
<body>
    <h2>Tax Computation</h2>
    <form:form action="process"  modelAttribute="users" method="post">
        <table>
            <tr>
                <td>Income :</td>
                <td><form:input path="income" name="income"/>
                </td>
                <td><form:errors path="income" cssClass="error" />
                </td>
            </tr>
            <tr>
                <td colspan="3"><input type="submit" />
                </td>
            </tr>
        </table>

    </form:form>
</body>
</html>