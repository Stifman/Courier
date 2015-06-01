<%-- 
    Document   : index
    Created on : 09.05.2015, 18:29:50
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
            <table class="table table-striped">

                
                    <tr>
                        <td>
                            <div  class="text-right-my">Общий доход за выполнение всех заказов составит:</div>
                        </td>
                        <td> 
                            <div class="text">${summ}</div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="text-right-my">В процессе выполнения находится заявок:</div>
                    </td>
                    <td>
                        <div class="text">${numberParcel}</div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="text-right-my">Общий доход за выполненные заказы составил:</div>
                    </td>
                    <td> 
                        <div class="text">${summAll}</div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="text-right-my">Выполнено заказов:</div>
                    </td>
                    <td>
                        <div class="text">${numberParcelAll}</div>
                    </td>
                </tr>

                <tr>
                    <td>
                        <div class="text-right-my">Средняя стоимость одного заказа составляет:</div>
                    </td>
                    <td>
                        <div class="text">${midCost}</div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="text-right-my">Самый загруженый курьер:</div>
                    </td>
                    <td>
                        <div class="text">${numberOrdersMax}</div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="text-right-my">Курьер с минимальным количеством зоявок:</div>
                    </td>
                    <td>
                        <div class="text">${numberOrdersMin}</div>
                    </td>
                </tr>
            
        </table>
                    <img src="src/update.png" height="30px" width="30px" border="1"/> <h3>- Изменить</h3><br>
                    <img src="src/delete.png" height="30px" width="30px" border="1"/> <h3>- Удалить</h3><br>
                    <img src="src/check.png" height="30px" width="30px" border="1"/> <h3>- Доставлено</h3><br>
                    
    </body>
</html>
