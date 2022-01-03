<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/jsp/header.jsp">
    <jsp:param name="pageTitle" value="${contenuto.titolo}"/>
</jsp:include>
<!-- style -->

<div class="container">
    <h1 class="titolo"> Log-in </h1>
    <form name="Login" action="Login" method="post">

        <label for="email"> Email</label>
        <input type="text" name="email" id="email">
        <span id="messaggioemail"></span>

        <label for="password">Password</label>
        <input type="password" name="password" id="password">
        <span id="messaggiopassword"></span>

        <input id="confermalogin" type="submit" value="Login">
        <span id="messaggioconferma"></span>

    </form>

</div>

<%@include file="footer.html"%>