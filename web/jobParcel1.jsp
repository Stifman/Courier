<%-- 
    Document   : jobParcel1
    Created on : 18.05.2015, 17:24:18
    Author     : Степан
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Добавить посылку</title>
        <link href="css/datepicker.css" rel="stylesheet">
        <script src="js/bootstrap-datepicker.js"></script>
    </head>
    <body >



        <c:import url="head.jsp"></c:import>
            <form name="Simple" action="FirstServlet" method="POST" >

                <table class="border">
                    <tr>
                        <td><h3>Введите информацию о посылке* </h3></td>
                        <td><textarea class="enjoy-white" rows="2" cols="20" maxlength="44" name="nameParcel" required></textarea></td>
                        <td>
                            <h3>Выберите тип посылки* </h3>
                            <select class="enjoy-css" name="tipParcel">
                            <c:forEach var="elem" items="${listTypeParcel}">
                                <option>${elem}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <h3>Выберите курьера*</h3>

                        <select class="enjoy-css" name="nameCourier">
                            <c:forEach var="elem2" items="${list}">
                                <option>${elem2}</option>
                            </c:forEach>
                        </select>
                    </td>

                    <td><h3>Введите дату доставки*</h3>

                        <input class="enjoy-css" type="date" name="deliveryDate" required></td>
                </tr>


                <tr>
                    <td><h3>Введите ФИО получателя* </h3></td>
                    <td><input class="enjoy-white" pattern="^[А-Яа-яЁё\s]+$" type="text" name="nameRecipient" required/></td>
                    <td>


                </tr>
                <tr>
                    <td><h3>Введите № телефона получателя(12 цифр)*</h3></td>
                    <td><input class="enjoy-white" pattern="[0-9]{12}" type="text" name ="numberTelephonRecipient" required/></td>

                </tr></div>
                <tr>
                    <td><h3>Введите ФИО отправителя* </h3></td>
                    <td><input class="enjoy-white" pattern="^[А-Яа-яЁё\s]+$"  type="text" name="nameSender" required/></td>
                </tr>
                <tr>
                    <td><h3>Введите № телефона отправителя(12 цифр)*</h3></td>
                    <td><input class="enjoy-white" pattern="[0-9]{12}" type="text" name ="numberTelephonSender" required/></td>
                </tr>
                <tr>
                    <td><h3>Куда* . . . . . . . . . . .</h3></td>
                    <td><input class="enjoy-white" pattern="^[А-Яа-яЁё,0-9-\.\s]+$" type="text" name="namePlaceDelivery" required/></td>
                </tr>
                <tr>
                    <td><h3>Откуда* . . . . . . . . .</h3></td>
                    <td><input class="enjoy-white"  pattern="^[А-Яа-яЁё,0-9-\.\s]+$" type="text" name ="namePlaceDispatch" required/></td>

                </tr>
                <tr>
                    <td><h3>Ввести стоимость, руб</h3></td>
                    <td><input class="enjoy-white" pattern="[0-9]{6}"  type="text" name="cost" value=""/></td>
                </tr>
                <tr>
                    <td>
                        <button class="enjoy-css" name="command" value="addParcel">Добавить</button>
                    </td>
                    <td>
                        <input class="enjoy-css" type="reset" name="Reset" value="Очистить все заполненные поля">
                    </td>
                </tr>
            </table>


        </form>
        <br>
        <button onclick="transitionParcelPage()" class="button">Назад</button>

        <script src="js/bootstrap-datepicker.js"></script>
    </body>
</html>


