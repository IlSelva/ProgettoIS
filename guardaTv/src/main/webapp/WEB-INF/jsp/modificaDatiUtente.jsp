<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/jsp/header.jsp">
    <jsp:param name="pageTitle" value="${contenuto.titolo}"/>
</jsp:include>

<style><%@include file="/WEB-INF/css/listeStyle.css"%></style>

<section class="formModifica">
    <h1 class="section-title">Modifica Dati Utente</h1>
    <form action="ModificaUtente" method="post">
        <input type="hidden" name="id" value="${utente.email}">
        <label>Nome</label>
        <input type="text" name="nome" value="${utente.nome}" required>
        <label>e-mail</label>
        <input type="text" name="username" value="${utente.username}" required>
        <label>data di nascita</label>
        <input type="date" name="datanascita" value="${utente.dataDiNascita}" required>
        <c:if test="${utente != null}">
            <input class="confirmbutton" type="submit" name="modifica" value="Modifica">
        </c:if>
    </form>
</section>

<%@include file="footer.html"%>
