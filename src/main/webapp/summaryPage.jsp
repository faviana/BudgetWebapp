<%--
  Created by IntelliJ IDEA.
  User: favianalopez
  Date: 9/30/16
  Time: 6:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Summary</title>

    <style>
        table, th, td {
    border: 1px solid black;
    padding: 5px;
    }table{
    border-spacing: 15px;
    }
    </style>

</head>
<body>

<h1 style="font-family: 'American Typewriter'"><center>Summary of Weekly Budget</center></h1>

<p>

    <ul>
        <li>
            <a href="/mybudget/detail"><font color = "#a9a9a9">Go to Weekly Report</font></a>
        </li>
    </ul>
</p>

<table align="center">
    <tr><th>CATEGORY</th><th>BUDGETED AMOUNT</th><th>ACTUAL AMOUNT</th></tr>

    <c:forEach items="${budgetTotal}" var="aTotal">

        <tr>
            <td>
                <center>
                    <c:out value="${aTotal.category}"/>
                </center>

            </td>

            <td>
                <center>
                    <c:out value="${aTotal.totalBudgetedAmount}"/>
                </center>
            </td>
            <td>
                <center>
                    <c:out value="${aTotal.totalActualAmount}"/>
                </center>
            </td>
    </c:forEach>

        <tr>
            <th>
                <center>
                    TOTAL
                </center>
            </th>
            <td>
                <center>
                    <c:out value="${totalbudgetedAmount}"/>
                </center>
            </td>
            <th>
                <center>
                    <c:out value="${totalactualAmount}"/>
                </center>
            </th>
</table>

</body>
</html>
