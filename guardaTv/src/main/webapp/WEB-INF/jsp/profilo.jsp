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

    <a href="Liste">
        <button> Le mie Liste </button>
    </a>

    <a href="Logout">
        <button> Logout </button>
    </a>

    <a href="ModificaUtente">
        <button> Modifica dati</button>
    </a>

    <a href="ModificaPassword">
        <button> Modifica password </button>
    </a>
    <br>

    <c:if test="${utente.admin == true}">
        <a href="UploadContenuto">
            <button>Inserimento Contenuto</button>
        </a>
        <a href="DeleteContenuto">
            <button>Elimina Contenuto</button>
        </a>
    </c:if>
</section>

<%@include file="footer.html"%>
