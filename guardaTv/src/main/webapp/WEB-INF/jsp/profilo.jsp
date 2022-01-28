<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/jsp/header.jsp">
    <jsp:param name="pageTitle" value="Profilo"/>
</jsp:include>

<style><%@include file="../css/generalStyle.css"%></style>

<h5 id="notifica">${notifica}</h5>
<div class="container">
    <section class="infoutente">
        <h4> Username: <c:out value="${utente.username}" /> </h4>
        <h4> E-mail: <c:out value="${utente.email}" /> </h4>

        <div class="bottoni">
            <a class="confirmbutton" href="ElencoListe">
                Le mie Liste
            </a>

            <a class="confirmbutton" href="Logout">
                Logout
            </a>

            <a class="confirmbutton" href="ModificaPasswordForm">
                Modifica password
            </a>
        </div>
        <br>

        <c:if test="${utente.administrator == true}">
            <h3 class="section-title">Sezione amministratore</h3>
            <div class="bottoni">
                <a class="confirmbutton" href="AggiuntaContenutoForm">
                    Inserimento Contenuto
                </a>
            </div>
        </c:if>
    </section>
</div>
<%@include file="footer.html"%>
