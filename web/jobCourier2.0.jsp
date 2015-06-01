<%-- 
    Document   : jobCourier2
    Created on : 15.05.2015, 20:57:15
    Author     : Степан
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Выбор курьера для редактирования</title>
    </head>
    <body>
        <c:import url="head.jsp"></c:import>
        <table class="table table-hover">
            <tr>
                <td></td>
                <td>
                    <h3>ФИО</h3>
                </td> 
                <td>
                    <h3>№ телефона</h3>
                </td> 
            </tr>
            <c:forEach var="elem" items="${list}" varStatus="status"> 
                <tr>
                    <td>
                        <form name="upd" action="FirstServlet" method="POST">
                            <input type="hidden" name="id" value="${elem.idcourier}"/>
                            <button class="btn btn-default" name="command" value="UpdCourier">
                                <img src="src/update.png" height="30px" width="30px" border="1"/>
                            </button>
                        </form>
                    </td> 
                    <td>
                        <div class="text">${ elem.name }</div>
                    </td>  
                    <td>
                        <div class="text">${ elem.numberTelephon }</div>
                    </td>
                </tr>   
            </c:forEach>
        </table>
        
                <button onclick="transitionCourierPage()" class="button">Назад</button>
    </body>
</html>
