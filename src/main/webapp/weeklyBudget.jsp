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

<h1 style="font-family: 'American Typewriter'"><center>Weekly Budget</center></h1>

<p>
<ul>
    <li>
        <a href="/mybudget/summary"><font color = "#a9a9a9">Go to Summary Page</font></a>
    </li>
</ul>
</p>


<p>
<ul>
    <li>
        <a href="createBudget.jsp"><font color = "#a9a9a9"> Add New Budget Item</font></a>
    </li>
</ul>
</p>

<form method="post" action="/mybudget/search">
<center>
    Search <input type="text" name="searchtext"/>
    <br>
    </p>
    <input type="submit" name="Filter Results"/>
</center>
    </form>

    <table align="center">
        <tr><th>Edit Option</th><th>Description</th><th>Category</th><th>Budgeted Amount</th><th>Actual Amount</th><th>Delete Option</th></tr>

      <c:forEach items="${budget}" var="budgetItem">
            <tr>

                <td>
                    <center>
                        <a href="/mybudget/select?id=<c:out value="${budgetItem.id}"/>">EDIT</a>
                <td>
                    <center>
                        <c:out value="${budgetItem.description}"/>
                    </center>
                </td>
                <td>
                    <center>
                        <c:out value="${budgetItem.category}"/>
                </td>
                    </center>
                <td>
                    <center>
                        <c:out value="${budgetItem.budgetedAmount}"/>
                </td>
                    </center>
                <td>
                    <center>
                        <c:out value="${budgetItem.actualAmount}"/>
                </td>
                    </center>
                <td>
                    <center>
                        <a href="/mybudget/delete?id=<c:out value="${budgetItem.id}"/>">DELETE</a>
                    </center>
                </td>
            </tr>
                        </c:forEach>

    </table>

</body>
</html>