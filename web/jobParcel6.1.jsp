<%-- 
    Document   : jobParcel6
    Created on : 23.05.2015, 21:45:21
    Author     : Степан
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Введите ФИО отпровителя</title>
    </head>
    <body class="background_parcel">
    <center>
        <c:import url="head.jsp"></c:import>
            <form class="long-shadow" action="FirstServlet" method="POST">

                <input class="enjoy-css" list="character" name="nameSender">
                <datalist id="character">
                <c:forEach  var="elem" items="${list}">
                    <option value="${elem.name}">${elem.name}</option>
                </c:forEach>
            </datalist>
            <button class="button-fiol" name="command" value="SenderInfo">Готово</button>
        </form>

        <br>
        <button onclick="transitionParcelPage()" class="button">Назад</button>
    </center>
</body>
</html>
