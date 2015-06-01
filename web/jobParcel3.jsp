<%-- 
    Document   : jobParcel3
    Created on : 19.05.2015, 17:53:35
    Author     : Степан
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Отменить заказ</title>
    </head>
    <body class="background_parcel">
        <c:import url="head.jsp"></c:import>
        <c:if test="${not empty list}">

            <table class="table table-hover">
                <tr>
                    <td>
                    </td> 
                    <td>
                        <h3>Название</h3>
                    </td> 
                    <td>
                        <h3>Дата доставки</h3>
                    </td> 
                    <td>
                        <h3>Курьер</h3>
                    </td> 
                    <td>
                        <h3>Тип посылки</h3>
                    </td> 
                    <td>
                        <h3>Получатель</h3>
                    </td> 
                    <td>
                        <h3>Отправитель</h3>
                    </td> 
                </tr>
                <c:forEach var="elem" items="${list}" varStatus="status"> 
                    <tr>
                        <td>
                            <form name="del" action="FirstServlet" method="POST">
                                <input type="hidden" name="id" value="${elem.idparcel}"/>
                                <button class="btn btn-default" name="command" value="DelParcel">
                                    <img src="src/delete.png" height="30px" width="30px" border="1"/>
                                </button>
                            </form>
                        </td> 
                        <td>
                            <div class="text">${ elem.name }</div>
                        </td>  
                        <td>
                            <div class="text">${ elem.deliveryDate }</div>
                        </td> 
                        <td>
                            <div class="text">${ elem.fkCourier.name }</div>
                        </td> 
                        <td>
                            <div class="text">${ elem.fkTipParcel.name }</div>
                        </td> 
                        <td>
                            <div class="text">${ elem.fkRecipient.name }</div>
                        </td> 
                        <td>
                            <div class="text">${ elem.fkSender.name }</div>
                        </td>
                    </tr>
                </c:forEach>
            </table>  
        </c:if>
    <center>
        <c:if test="${empty list}">
            <h1>Все заказы выполнены</h1>
        </c:if>
        <br>
        <button onclick="transitionParcelPage()" class="button">Назад</button>
    </center>
</body>
</html>
