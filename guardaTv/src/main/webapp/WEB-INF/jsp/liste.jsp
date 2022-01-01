<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/jsp/header.jsp">
    <jsp:param name="pageTitle" value="${contenuto.titolo}"/>
</jsp:include>
<!-- style -->

<div calss="container1">

    <div class="titolo">
        <h1>Le mie liste</h1>
    </div>

    <div class="creazione">
        <h1>Crea una nuova lista</h1>
        <h3>Inserisci i tuoi film e serie preferite</h3>
        <a href="nuovaLista">
            <button> crea la tua lista </button>
        </a>
    </div>

</div>

<div class="container2">
    <ul>
        <c:forEach items = "{liste}" var = "lista">
            <li class="lista">
                <h2> <c:out value="${lista.titolo}"/> </h2>
                <h3> <c:out value="${lista.descrizione}"/> </h3>
            </li>
        </c:forEach>
    </ul>
</div>


<%@include file="footer.html"%>