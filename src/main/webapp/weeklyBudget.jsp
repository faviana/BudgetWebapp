<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>

    <title>Finance</title>

    <style> table, th, td {
        border: 1px solid black;
        padding: 5px;
    }table{
         border-spacing: 15px;
     }
    </style>

</head>

<body>

<h1>My Weekly Budget list</h1>



<form method="post" action="/mybudget/budget">

    <table>
        <tr><td>Description</td><td>Category</td><td>Budgeted Amount</td><td>Actual Amount</td></tr>

        <c:forEach items="${budget}" var="budgetItem">

            <tr><td><c:out value="${budgetItem.description}"/>
            <td><c:out value="${budgetItem.category}"/></td>
            <td><c:out value="${budgetItem.budgetedAmount}"/></td>
            <td><c:out value="${budgetItem.actualAmount}"/></td></tr>
        </c:forEach>

    </table>

    <p></p>
</form>

<p></p>

<ul>
    <li><a href="summeryPage.jsp">Go to...</a>
    </li>
</ul>

</body>
</html>