<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: favianalopez
  Date: 9/30/16
  Time: 6:00 PM
  To change this template use File | Settings | File Templates.
--%>
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
<table>
    <tr><td>Category</td><td>Budgeted Amount</td><td>Actual Amount</td></tr>

    <c:forEach items="${budget}" var="budgetItem">

        <tr><td><c:out value="${budgetItem.category}"/></td>
            <td><c:out value="${budgetItem.budgetedAmount}"/></td>
            <td><c:out value="${budgetItem.actualAmount}"/></td></tr>
    </c:forEach>

</table>

<ul>
    <li><a href="/mybudget/budget">Go to...</a>
    </li>
</ul>
</body>
</html>
