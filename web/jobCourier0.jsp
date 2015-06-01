<%-- 
    Document   : jobCourier
    Created on : 15.05.2015, 20:08:31
    Author     : Степан
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link href="css/mystyles.css" rel="stylesheet" media="screen">
    </head>
    <body>
        <c:import url="head.jsp"></c:import>
        <button onclick="transitionCourier1Page()" class="button-fiol ">Добавить курьера</button><br>
            <br>
            <form name="Upd_courier" action="FirstServlet" method="POST">
                <button class="button-fiol " name="command" value="GoUpdCourier">Изменить информацию о курьере</button>
            </form>
            <br>
            <form name="Del_courier" action="FirstServlet" method="POST">
                <button class="button-fiol " name="command" value="GoDelCourier">Удалить курьера</button>
            </form>
            <br>
            <form name="All_couriers" action="FirstServlet" method="POST">
                <button class="button-fiol " name="command" value="ViewAllCouriers">Просмотреть список курьеров</button>
            </form>
            <br>
        <c:if test="${massage > 0}">
            <c:out value="Курьер не был добавлен так как он уже существует"></c:out>
        </c:if>
    </body>
</html>
<script type="text/javascript">
   
    function transitionCourier1Page(){
        window.location = "jobCourier1.jsp";
    }
</script>