<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/jsp/header.jsp">
    <jsp:param name="pageTitle" value="Home"/>
</jsp:include>
<!--
<style><%@include file="css"%></style>
-->

<section class="banner">

</section>

<div class="container">

    <h2 class="section-title"> Ultime aggiunte</h2>
    <section class="griglia">
        <c:forEach items = "{ultimi}" var = "contenuto">
            <article class="contenuto">

                <a class="copertina" href="Contenuto?id=<c:out value="${conetnuto.id}"/>">
                    <img class="copertina" src="img/contenuti/<c:out value="${contenuto.id}"/>.jpg" alt=""/>">
                </a>
                <h4>
                    <a class="titolo" href="Contenuto?id=<c:out value="${conetnuto.id}"/>">
                        <c:out value="${contenuto.titolo}"/>
                    </a>
                </h4>

            </article>
        </c:forEach>
    </section>

    <h2 class="section-title"> Esplora la libreria </h2>
    <section class="griglia">
        <c:forEach items = "{ultimi}" var = "contenuto">
            <article class="contenuto">

                <a class="copertina" href="Contenuto?id=<c:out value="${conetnuto.id}"/>">
                    <img class="copertina" src="img/contenuti/<c:out value="${contenuto.id}"/>.jpg" alt=""/>">
                </a>
                <h4>
                    <a class="titolo" href="Contenuto?id=<c:out value="${conetnuto.id}"/>">
                        <c:out value="${contenuto.titolo}"/>
                    </a>
                </h4>

            </article>
        </c:forEach>
    </section>

</div>

<%@include file="footer.html"%>