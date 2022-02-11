<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/jsp/header.jsp">
    <jsp:param name="pageTitle" value="${contenuto.titolo}"/>
</jsp:include>

<style><%@include file="/WEB-INF/css/generalStyle.css"%></style>
<style><%@include file="/WEB-INF/css/listeStyle.css"%></style>

<section class="formModifica">
    <h1 class="section-title">Cambiare Password</h1>
    <form name="modificaPassword" action="ModificaPassword" method="post">
        <label>Nuova password</label>
        <input type="password" name="nuovaPassword" required>
        <label>conferma nuova password</label>
        <input type="password" name="confermaPassword" required>

        <c:if test="${utente != null}">
            <input class="confirmbutton" type="submit" name="modifica" value="Modifica">
        </c:if>
    </form>
</section>


<%@include file="footer.html"%>
