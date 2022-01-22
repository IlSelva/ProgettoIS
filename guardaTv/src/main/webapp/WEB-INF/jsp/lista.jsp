<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/jsp/header.jsp">
    <jsp:param name="pageTitle" value="${contenuto.titolo}"/>
</jsp:include>
<style><%@include file="/WEB-INF/css/listeStyle.css"%></style>

<div class="containerliste">
    <div calss="container">

        <div class="titololiste">
            <h1>La mia lista</h1>
        </div>

        <div class="listainf">
            <h2> <c:out value="${lista.titolo}"/> </h2>
            <h4> <c:out value="${lista.descrizione}"/> </h4>
        </div>

        <div class="creazionelista">
            <h2>Crea una nuova lista</h2>
            <h4>Inserisci i tuoi film e serie preferite</h4>
            <a href="nuovaLista">
                <button class="confirmbutton"> crea la tua lista </button>
            </a>
        </div>

    </div>
</div>

<div class="containercontenuti">
    <section class="griglialista">
        <c:forEach items = "{lista}" var = "contenuto">
            <article class="contenutolista">
                <a class="copertinalista" href="Contenuto?id=<c:out value="${conetnuto.id}"/>">
                    <img class="copertinalista" src="img/contenuti/<c:out value="${contenuto.id}"/>.jpg" alt=""/>">
                </a>
                <h3 class="titolo">
                    <a class="titolo" href="Contenuto?id=<c:out value="${conetnuto.id}"/>">
                        <c:out value="${contenuto.titolo}"/>
                    </a>
                </h3>
                <h4 class="info">
                    <c:out value="${conetnuto.durata}"/> min
                    <ul class="generi">
                        <c:forEach items = "{contenuto}" var = "genere">
                            <li class="genere"> <c:out value="${genere} "/> </li>
                        </c:forEach>
                    </ul>
                </h4>
                <h5 class="descrizione">
                    <c:out value="${contenuto.descrizione}"/>
                </h5>
            </article>
        </c:forEach>
    </section>
</div>

<%@include file="footer.html"%>