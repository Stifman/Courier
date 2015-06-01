<%-- 
    Document   : jobParcel5
    Created on : 23.05.2015, 13:36:04
    Author     : Степан
--%>
<%--
<h3> Фильтрация по курьеру</h3>
<select name="nameCourier">
    <option>Просмотр всех курьеров</option>
    <c:forEach var="elem2" items="${listCouriers}">
        <option>${elem2}</option>
    </c:forEach>
</select>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body class="background_parcel">
        <c:import url="head.jsp"></c:import>
        <c:if test="${not empty listParcels}">
            <form name="Simple" action="FirstServlet" method="POST" >
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
                            <h3>Получатель</h3>
                        </td> 
                    </tr>
                    <c:forEach var="elem" items="${listParcels}" varStatus="status"> 
                        <tr>
                            <td>
                                <input type="hidden" name="id" value="${elem.idparcel}"/>
                                <input type="hidden" name="nameCourierOrder" value="${elem.fkCourier.name}"/>
                                <button class="btn btn-default" name="command" value="FinishMarkParcel">
                                    <img src="src/check.png" height="30px" width="30px" border="0"/>
                                </button>
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
                                <div class="text">${ elem.fkRecipient.name }</div>
                            </td> 
                        </tr>   
                    </c:forEach>
                </table>
            </form>
        </c:if>
    <center>
        <c:if test="${empty listParcels}">
            <h1>Все посылки доставлены</h1>
        </c:if>
    
    <br>
    <button onclick="transitionParcelPage()" class="button">Назад</button>
    </center>
</body>
</html>
