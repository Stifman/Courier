<%-- 
    Document   : jobParcel2.0
    Created on : 20.05.2015, 17:43:00
    Author     : Степан
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body class="background_parcel">
        <c:import url="head.jsp"></c:import>
        <c:if test="${not empty listParcel}">
            <table class="table table-hover">
                <tr>
                    <td></td>
                    <td>
                        <h3>Название</h3>
                    </td> 
                    <td>
                        <h3>Дата доставки</h3>
                    </td> 
                    <td>
                        <h3>Имя курьера</h3>
                    </td> 
                    <td>
                        <h3>Отправитель</h3>
                    </td> 
                    <td>
                        <h3>Тип посылки</h3>
                    </td> 
                </tr>
                <c:forEach var="elem" items="${listParcel}" varStatus="status"> 
                    <tr>
                        <td>
                            <form name="upd" action="FirstServlet" method="POST">
                                <input type="hidden" name="id" value="${elem.idparcel}"/>
                                <button class="btn btn-default" name="command" value="UpdParcel">
                                    <img src="src/update.png" height="30px" width="30px" border="1"/>
                                </button>
                            </form>
                        </td> 
                        <td>
                            <div class="text">${ elem.name }</div>
                        </td>  
                        <td>
                            <div class="text">${ elem.deliveryDate }</div>
                        <td>
                            <div class="text">${ elem.fkCourier.name }</div>
                        </td>
                        <td>
                            <div class="text">${ elem.fkSender.name }</div>
                        </td>
                        <td>
                            <div class="text">${ elem.fkTipParcel.name }</div>
                        </td>
                    </tr>   
                </c:forEach>
            </table>
        </c:if>
    <center>
        <c:if test="${empty listParcel}">
            <h1>Все посылки доставлены</h1>
        </c:if>
    
    <br>
    <button onclick="transitionParcelPage()" class="button">Назад</button>
    </center>
</body>
</html>
