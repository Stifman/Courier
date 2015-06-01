<%-- 
    Document   : jobParcel6.2
    Created on : 23.05.2015, 22:34:44
    Author     : Степан
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="ct" uri="/WEB-INF/tld/userTag.tld" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Просмотр информации по клиенту</title>
    </head>
    <body>
    <center>
        <c:import url="head.jsp"></c:import>
        <c:if test="${not empty list}">
            
            <ct:myTag>Отправитель: ${nameSender}</ct:myTag>
            <table class="table-hover">
                <tr>
                    <td></td><td></td> 
                    <td>
                        <h3>Название посылки</h3>
                    </td> 
                    <td>
                        <h3>Дата доставки</h3>
                    </td> 
                </tr>
                <c:forEach var="elem" items="${list}" varStatus="status"> 
                    <tr>
                        <td>
                            <form name="upd" action="FirstServlet" method="POST">
                                <input type="hidden" name="id" value="${elem.idparcel}"/>
                                <input type="hidden" name="nameSender" value="${nameSender}"/>
                                <button class="btn btn-default" name="command" value="UpdParcel">
                                    <img  src="src/update.png" height="30px" width="30px" border="0"/>
                                </button>
                            </form>
                        </td> 
                        <td>
                            <form name="del" action="FirstServlet" method="POST">
                                <input type="hidden" name="id" value="${elem.idparcel}"/>
                                <input type="hidden" name="nameSender" value="${nameSender}"/>
                                <button class="btn btn-default" name="command" value="DelParcel">
                                    <img src="src/delete.png" height="30px" width="30px" border="0"/>
                                </button>
                            </form>
                        </td> 
                        <td>
                            <div class="text">${ elem.name }</div>
                        </td>  
                        <td>
                            <div class="text">${ elem.deliveryDate }</div>
                        </td> 
                    </tr>
                </c:forEach>

            </table>
        </c:if>
        <c:if test="${empty list}">
            <h1>${nameSender}</h1><br>
            <h1>Все заказы этого пользователя выполнены</h1>
        </c:if>
        <form name="Break" action="FirstServlet" method="POST">
            <br>
            <button class="button " name="command" value="GoSenderInfo">Назад</button>
        </form>
    </center>
</body>
</html>
