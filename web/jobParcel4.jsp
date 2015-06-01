<%-- 
    Document   : jobParcel4
    Created on : 19.05.2015, 12:09:58
    Author     : Степан
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body >
        <c:import url="head.jsp"></c:import>

            <table class=" table table-hover">
                <tr>
                    <td onclick="sort(this)">
                        <h4>Название </h4>
                    </td> 
                    <td onclick="sort(this)">
                        <h4>Дата доставки</h4>
                    </td> 
                    <td onclick="sort(this)">
                        <h4>Стоимость</h4>
                    </td> 
                    <td onclick="sort(this)">
                        <h4>Имя курьера</h4>
                    </td> 
                    <td onclick="sort(this)">
                        <h4>Откуда</h4>
                    </td> 
                    <td onclick="sort(this)">
                        <h4>Куда</h4></td>
                    <td onclick="sort(this)">
                        <h4>Тип посылки</h4>
                    </td> 
                    <td onclick="sort(this)">
                        <h4>Получатель</h4>
                    </td> 
                    <td onclick="sort(this)">
                        <h4>Отправитель</h4>
                    </td> 
                    <td onclick="sort(this)">
                        <h4>Статус</h4>
                    </td> 
                </tr>
            <c:forEach var="elem" items="${list}" varStatus="status"> 
                <tr>
                    <td>
                        <div class="text-small">${ elem.name }</div>
                    </td>  
                    <td>
                        <div class="text-small">${ elem.deliveryDate }</div>
                    </td> 
                    <td>
                        <div class="text-small">${ elem.cost }</div>
                    </td> 
                    <td>
                        <div class="text-small">${ elem.fkCourier.name }</div>
                    </td> 
                    <td>
                        <div class="text-small">${ elem.fkPlaceDelivery.name }</div>
                    </td> 
                    <td>
                        <div class="text-small">${ elem.fkPlaceDispatch.name }</div>
                    </td> 
                    <td>
                        <div class="text-small">${ elem.fkTipParcel.name }</div>
                    </td> 
                    <td>
                        <div class="text-small">${ elem.fkRecipient.name }</div>
                    </td> 
                    <td>
                        <div class="text-small">${ elem.fkSender.name }</div>
                    </td> 
                    <td>
                        <div class="text-small">${ elem.fkStatus.name }</div>
                    </td> 

                </tr>   
            </c:forEach>
        </table>
        <br>
        <button onclick="transitionParcelPage()" class="button">Назад</button>
        <a class="button" title="Сместиться в начало списка" href="#">Вверх</a>

    </body>
</html>
