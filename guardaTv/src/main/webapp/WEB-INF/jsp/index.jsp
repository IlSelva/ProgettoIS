<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/jsp/header.jsp">
    <jsp:param name="pageTitle" value="Home"/>
</jsp:include>

<style><%@include file="../css/generalStyle.css"%></style>


<div class="container">

    <section class="banner">

    </section>

    <h2 class="section-title"> Ultime uscite</h2>
    <section class="griglia">
        <c:forEach items = "${ultimi}" var = "contenuto">
            <article class="cont">

                <a class="copertina" href="Contenuto?id=<c:out value="${contenuto.id}"/>">
                    <img class="copertina" src="img/contenuti/<c:out value="${contenuto.immagineDelContenuto}"/>" alt=""/>
                </a>
                <h3 class="titolofilm">
                    <a class="titolofilm" href="Contenuto?id=<c:out value="${contenuto.id}"/>">
                        <c:out value="${contenuto.titolo}"/>
                    </a>
                </h3>

            </article>
        </c:forEach>
    </section>

    <h2 class="section-title"> Esplora la libreria </h2>
    <section class="griglia">
        <c:forEach items = "${contenuti}" var = "contenuto">
            <article class="cont">

                <a class="copertina" href="Contenuto?id=<c:out value="${contenuto.id}"/>">
                    <img class="copertina" src="img/contenuti/<c:out value="${contenuto.immagineDelContenuto}"/>" alt=""/>
                </a>
                <h3 class="titolofilm">
                    <a class="titolofilm" href="Contenuto?id=<c:out value="${contenuto.id}"/>">
                        <c:out value="${contenuto.titolo}"/>
                    </a>
                </h3>

            </article>
        </c:forEach>
    </section>

</div>

<%@include file="footer.html"%>