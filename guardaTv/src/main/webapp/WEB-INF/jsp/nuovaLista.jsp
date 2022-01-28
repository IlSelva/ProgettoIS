<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/jsp/header.jsp">
    <jsp:param name="pageTitle" value="Nuoa Lista"/>
</jsp:include>

<style><%@include file="../css/generalStyle.css"%></style>
<style><%@include file="../css/listeStyle.css"%></style>

<div class="container">

    <div class="titololiste">
        <h1>Nuova Lista</h1>
        <form name="nuovalista" action="creazione-lista" method="post">
            <label for="nomelista"> Nome Lista:</label>
            <input type="text" name="nomelista" id="nomelista">
            <label for="descrizione"> Descrizione:</label>
            <textarea name="descrizione" id="descrizione" placeholder="Scrivi la tua recensione" required></textarea>
            <input class="confirmbutton" id="confermacreazione" type="submit" value="Conferma">
        </form>
    </div>

</div>

<%@include file="footer.html"%>