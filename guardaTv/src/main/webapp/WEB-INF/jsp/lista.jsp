<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/jsp/header.jsp">
    <jsp:param name="pageTitle" value="${lista.nome}"/>
</jsp:include>
<style><%@include file="/WEB-INF/css/generalStyle.css"%></style>
<style><%@include file="/WEB-INF/css/listeStyle.css"%></style>

<div class="containerliste">
    <div calss="container">

        <div class="titololiste">
            <h1>La mia lista</h1>
        </div>

        <div class="listainf">
            <h2> <c:out value="${lista.nome}"/> </h2>
            <h4> <c:out value="${lista.descrizione}"/> </h4>
        </div>

    </div>
    <div class="creazionelista">
        <h2>Crea una nuova lista</h2>
        <h4>Inserisci i tuoi film e serie preferite</h4>
        <a href="CreazioneNuovaLista">
            <button class="confirmbutton"> crea la tua lista </button>
        </a>
    </div>

</div>

<div class="containercontenuti">
    <section class="griglialista">
        <c:forEach items = "${contenuti}" var = "contenuto">
            <article class="contenutoLista">
                <a class="copertinalista" href="Contenuto?id=<c:out value="${contenuto.id}"/>">
                    <img class="copertinalista" src="img/contenuti/<c:out value="${contenuto.immagineDelContenuto}"/>" >
                </a>
                <div class="infoContenuto">
                    <h3 class="titolo">
                        <a class="titolo" href="Contenuto?id=<c:out value="${contenuto.id}"/>">
                            <c:out value="${contenuto.titolo}"/>
                        </a>
                    </h3>
                    <h4 class="info">
                        <c:out value="${contenuto.durata}"/> min
                    </h4>
                    <h5 class="descrizione">
                        <c:out value="${contenuto.descrizione}"/>
                    </h5>
                </div>
            </article>
        </c:forEach>
    </section>
</div>
<br>
<%@include file="footer.html"%>