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

    <div class="listainf">
        <h2> <c:out value="${lista.titolo}"/> </h2>
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
    <section class="griglia">
        <c:forEach items = "{lista}" var = "contenuto">
            <article class="contenutoLista">
                <a class="copertina" href="Contenuto?id=<c:out value="${conetnuto.id}"/>">
                    <img class="copertina" src="img/contenuti/<c:out value="${contenuto.id}"/>.jpg" alt=""/>">
                </a>
                <h4 class="titolo">
                    <a href="Contenuto?id=<c:out value="${conetnuto.id}"/>">
                        <c:out value="${contenuto.titolo}"/>
                    </a>
                </h4>
                <h4 class="info">
                    <c:out value="${conetnuto.durata}"/> |
                    <c:forEach items = "{contenuto}" var = "genere">
                        <c:out value="${genere} "/>
                </h4>
                <h5 class="descrizione">
                    <c:out value="${contenuto.descrizione}"/>
                </h5>
            </article>
        </c:forEach>
    </section>
</div>

<%@include file="footer.html"%>