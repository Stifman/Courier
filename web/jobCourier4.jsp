<%-- 
    Document   : jobCourier4
    Created on : 15.05.2015, 21:43:46
    Author     : Степан
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Просмотр списка курьеров</title>
    </head>
    <body>
        <c:import url="head.jsp"></c:import>
        <table class="table table-hover">
            <tr>
                <td onclick="sort(this)">
                    <h3>ФИО</h3>
                </td> 
                <td >
                   <h3>№ телефона</h3>
                </td> 
                <td onclick="sort(this)">
                    <h3>Количество заказов</h3>
                </td> 
            </tr>
            <c:forEach var="elem" items="${list}" varStatus="status"> 
                <tr>
                    <td>
                        <div class="text">${ elem.name }</div>
                    </td>  
                    <td>
                       <div class="text">${ elem.numberTelephon }</div>
                    </td> 
                    <td>
                        <div class="text">${ elem.numberOrders }</div>
                    </td> 
                </tr>   
            </c:forEach>
        </table>                
                <br>
                <button onclick="transitionCourierPage()" class="button">Назад</button>
    </body>
</html>
