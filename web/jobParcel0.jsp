<%-- 
    Document   : index2
    Created on : 09.05.2015, 19:51:02
    Author     : Степан
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link href="css/mystyles.css" rel="stylesheet" media="screen">
    </head>
    <body >
        
            <c:import url="head.jsp"></c:import>
    
        <form name="Add_parcel" action="FirstServlet" method="POST">
            <button class="enjoy-css" name="command" value="GoAddParcel">Добавить посылку</button>
        </form><br>
        <form name="Upd_parcel" action="FirstServlet" method="POST">
            <button class="enjoy-css" name="command" value="GoUpdParcel">Изменить информацию о посылке</button>
        </form><br>

        <form name="Del_parcel" action="FirstServlet" method="POST">
            <button class="enjoy-css" name="command" value="GoDelParcel">Отменить заказ</button>
        </form><br>

        <form name="All_parcels" action="FirstServlet" method="POST">
            <button class="enjoy-css" name="command" value="ViewAllParcels">Просмотреть всю информацию о посылке</button>
        </form><br>

        <form name="Mark_parcel" action="FirstServlet" method="POST">
            <button class="enjoy-css" name="command" value="MarkParcel">Сделать отметку о доставке посылки</button>
        </form><br>

        <form name="Sender_Info" action="FirstServlet" method="POST">
            <button class="enjoy-css" name="command" value="GoSenderInfo">Просмотреть заказы отправителя</button>
        </form>

   
</body>
</html>
