<%--
  Created by IntelliJ IDEA.
  User: German
  Date: 13.07.2018
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<html>
<head>
    <title>
        Meals
    </title>
    <style type="text/css">
        .norm{color: forestgreen;}
        .excess{color: crimson;}
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<table border="1">
    <caption><h3>Meals table</h3></caption>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>
    <c:forEach var="meal" items="${requestScope.mealsList}">
        <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealWithExceed"/>
    <tr class="${meal.isExceed()? 'excess':'norm'}">
        <td>
            <fmt:parseDate value="${meal.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime"/>
            <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${parsedDateTime}" /></td>
        <td align="center">${meal.description}</td>
        <td align="center">${meal.calories}</td>
    </tr>
    </c:forEach>
</table>
</body>
</html>
