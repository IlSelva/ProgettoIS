<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/jsp/header.jsp">
    <jsp:param name="pageTitle" value="Login utente"/>
</jsp:include>
<style><%@include file="../css/generalStyle.css"%></style>

<div class="container">
    <div class="signform">
        <h1 class="titolo"> Log-in </h1>
        <form name="Login" action="Login" method="post">

            <label for="email"> Email</label>
            <input type="text" name="email" id="email">
            <span class="returnmessage" id="messaggioemail"></span>

            <label for="password">Password</label>
            <input type="password" name="password" id="password">
            <span class="returnmessage" id="messaggiopassword"></span>

            <input class="confirmbutton" id="confermalogin" type="submit" value="Login">
            <span class="returnmessage" id="messaggioconferma"></span>

        </form>
    </div>
</div>

<%@include file="footer.html"%>