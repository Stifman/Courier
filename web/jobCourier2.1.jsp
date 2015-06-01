<%-- 
    Document   : jobCourier2.1
    Created on : 16.05.2015, 15:57:11
    Author     : Степан
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Редактирование информации о курьере</title>
    </head>
    <body>
        <c:import url="head.jsp"></c:import>
            <form name="Simple" action="FirstServlet" method="POST" >
                <input type="hidden" name ="idCourier" value="${courier.idcourier}"/>
            <table>
                <tr><td>
                        <h3>ФИО курьера </h3></td>
                    <td><input class="input"  pattern="^[А-Яа-яЁё\s]+$" type="text" name="nameCourier" value="${courier.name}"/></td>
                </tr>
                <tr><td>
                        <h3>№ телефона курьера(12 цифр)</h3></td>
                    <td><input class="input" pattern="[0-9]{12}" type="text" name ="numberTelephonCourier" value="${courier.numberTelephon}"/></td>
                </tr>
            </table>
            <button class="enjoy-css" name="command"  value ="finishUpdCourier">Сохранить изменения</button>
            <br>

        </form>
        <button class="button" name="command" value="GoUpdCourier">Назад</button>
    </body>
</html>
