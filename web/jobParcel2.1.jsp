<%-- 
    Document   : jobParcel2.1
    Created on : 20.05.2015, 17:43:29
    Author     : Степан
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Замените необходимые данные</title>
    </head>
    <body class="background_parcel">
        <c:import url="head.jsp"></c:import>

            <form name="Simple" action="FirstServlet" method="POST" >
                <input type="hidden" name="list" value="${list}"/>
            <input type="hidden" name="nextPage" value="jobParcel6.2.jsp"/>
            <input type="hidden" name="nameSender" value="${nameSender}"/>

            <input type="hidden" name ="idParcel" value="${parcel.idparcel}"/>
            <input type="hidden" name="nameCourierDel" value="${nameCourierDel}"/>

            <input type="hidden" name ="idPlaceDelivery" value="${parcel.fkPlaceDelivery.idplaceDelivery}"/>
            <input type="hidden" name ="idPlaceDispatch" value="${parcel.fkPlaceDispatch.idplaceDispatch}"/>
            <input type="hidden" name ="idRecipient" value="${parcel.fkRecipient.idrecipient}"/>
            <input type="hidden" name ="idSender" value="${parcel.fkSender.idsender}"/>

            <table>
                <td><h3>Введите информацию о посылке* </h3></td>
                <td><textarea class="enjoy-white"  rows="2" cols="20" maxlength="44" name="nameParcel"  required>${parcel.name}</textarea></td>
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
                        <c:forEach var="elem2" items="${listCourier}">
                            <option>${elem2}</option>
                        </c:forEach>
                    </select>
                </td>

                <td><h3>Введите дату доставки*</h3>

                    <input class="enjoy-css" type="date" name="deliveryDate"  value="${parcel.deliveryDate}" required></td>
                </tr>


                <tr>
                    <td><h3>Введите ФИО получателя* </h3></td>
                    <td><input class="enjoy-white"  pattern="^[А-Яа-яЁё\s]+$" type="text" name="nameRecipient" value="${parcel.fkRecipient.name}" required/></td>
                    <td>


                </tr>
                <tr>
                    <td><h3>Введите № телефона получателя(12 цифр)*</h3></td>
                    <td><input class="enjoy-white" pattern="[0-9]{12}" type="text" name ="numberTelephonRecipient" value="${parcel.fkRecipient.numberTelephon}" required/></td>

                </tr></div>
                <tr>
                    <td><h3>Введите ФИО отправителя* </h3></td>
                    <td><input class="enjoy-white" pattern="^[А-Яа-яЁё\s]+$"  type="text" name="nameSender" value="${parcel.fkSender.name}" required/></td>
                </tr>
                <tr>
                    <td><h3>Введите № телефона отправителя(12 цифр)*</h3></td>
                    <td><input class="enjoy-white" pattern="[0-9]{12}" type="text" name ="numberTelephonSender" value="${parcel.fkSender.numberTelephon}" required/></td>
                </tr>
                <tr>
                    <td><h3>Куда* . . . . . . . . . . . </h3></td>
                    <td><input class="enjoy-white" pattern="^[А-Яа-яЁё,0-9-\.\s]+$" type="text" name="namePlaceDelivery" value="${parcel.fkPlaceDelivery.name}" required/></td>
                </tr>
                <tr>
                    <td><h3>Откуда* . . . . . . . . .</h3></td>
                    <td><input class="enjoy-white" pattern="^[А-Яа-яЁё,0-9-\.\s]+$" type="text" name ="namePlaceDispatch"  value="${parcel.fkPlaceDispatch.name}" required/></td>

                </tr>
                <tr>
                    <td><button class="enjoy-css" name="command" value="finishUpdParcel">Сохранить изменения</button>
                    <td><input class="enjoy-css" type="reset" name="Reset" value="Вернуть первоначальные значения"><td>
                    <td>
                </tr>

            </table>

            <br>
            <button class="button" name="command" value="GoUpdParcel">Назад</button>
        </form>

    </body>
</html>
