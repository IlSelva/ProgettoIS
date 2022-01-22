<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/jsp/header.jsp">
    <jsp:param name="pageTitle" value="${contenuto.titolo}"/>
</jsp:include>
<!-- style -->

<h5 id="notifica">${notifica}</h5>

<section>
    <h4> Username: <c:out value="${utente.username}" /> </h4>
    <h4> E-mail: <c:out value="${utente.email}" /> </h4>

    <div class="bottoni">
        <a class="confirmbutton" href="Liste">
            Le mie Liste
        </a>

        <a class="confirmbutton" href="Logout">
            Logout
        </a>

        <a class="confirmbutton" href="ModificaUtente">
            Modifica dati
        </a>

        <a class="confirmbutton" href="ModificaPassword">
            Modifica password
        </a>
    </div>
    <br>

    <c:if test="${utente.admin == true}">
        <h3 class="section-title">Sezione amministratore</h3>
        <div class="bottoni">
            <a class="confirmbutton" href="UploadContenuto">
                Inserimento Contenuto
            </a>
            <a class="confirmbutton" href="DeleteContenuto">
                Elimina Contenuto
            </a>
        </div>
    </c:if>
</section>

<%@include file="footer.html"%>
