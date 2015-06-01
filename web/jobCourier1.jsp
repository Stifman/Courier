<%-- 
    Document   : jobCourier1
    Created on : 15.05.2015, 20:55:47
    Author     : Степан
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Добавление курьера</title>
    </head>
    <body>
        <c:import url="head.jsp"></c:import>
            <form name="Simple" action="FirstServlet" method="POST" >
                <table>
                    <tr>
                        <td><h3>Введите ФИО курьера* </h3></td>
                        <td><input class="input"  pattern="^[А-Яа-яЁё\s]+$" type="text" name="nameCourier" required/></td>
                    </tr>
                    <tr>
                    <td><h3>Введите № телефона курьера(12 цифр)* </h3></td>
                    <td><input class="input" pattern="[0-9]{12}" type="text" name ="numberTelephonCourier" required/></td>
                </tr>
                </table>
            <button class="enjoy-css" name="command" value="addCourier">Добавить</button>

        </form>

        <br>
        <button onclick="transitionCourierPage()" class="button">Назад</button>
    </body>
</html>
