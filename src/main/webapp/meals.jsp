<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meals</title>
    <style>
        .red {
            background-color: red;
        }
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>

<jsp:useBean id="meals" class="java.util.ArrayList" scope="request"/>
<table>
    <th>
        <tr>
            <td>Дата</td>
            <td>Описание</td>
            <td>Калории</td>
        </tr>
    </th>
    <c:forEach var="meal" items="${meals}">
        <jsp:useBean id="meal" class="ru.javawebinar.topjava.model.MealWithExceed"/>
        <c:set var="className" value=""/>
        <c:if test="${meal.exceed}">
            <c:set var="className" value="red"/>
        </c:if>
        <tr class="${className}">
            <td>
                <jsp:getProperty name="meal" property="dateTime"/>
            </td>
            <td>
                <jsp:getProperty name="meal" property="description"/>
            </td>
            <td>
                <jsp:getProperty name="meal" property="calories"/>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>