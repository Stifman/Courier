<%-- 
    Document   : head
    Created on : 16.05.2015, 17:40:02
    Author     : Степан
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<!--        <link href="css/mystyles.css" rel="stylesheet" media="screen">-->
        <link href="css/button.css" rel="stylesheet" media="screen">
        <link href="css/backgraund.css" rel="stylesheet" media="screen">
        <link href="css/input.css" rel="stylesheet" media="screen">
        <script src="js/sort_table.js"></script>

        <%--<link href="js/input.js" rel="stylesheet" media="screen">--%>
        
    </head>
    <body class="background_parcel">
        <nav align="center" class="navbar navbar-inverse"  role="navigation">
        <form  name="s" action="FirstServlet" method="POST">
            <button name="command" value="HEAD" class="btn btn-primary">Главная</button>
            <!--<button class="button"><a  href ="jobParcel0.jsp" title="Просмотр всех посылок и работа с ними">Работа с посылками</a></button>
            <button class="btn btn-success"><a href ="jobCourier0.jsp" title="Просмотр всех курьеров и работа с ними">Работа с курьерами</a></button>-->
        </form>
        
            <button onclick="transitionParcelPage()" class="button" >Работа с посылками</button>
            <button onclick="transitionCourierPage()" class="button">Работа с курьерами</button>
            
       </nav>        
    </body>
</html>
<script type="text/javascript">
    function transitionParcelPage(){
        window.location = "jobParcel0.jsp";
    }
    function transitionCourierPage(){
        window.location = "jobCourier0.jsp";
    }
</script>
