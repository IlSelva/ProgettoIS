<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/jsp/header.jsp">
    <jsp:param name="pageTitle" value="${contenuto.titolo}"/>
</jsp:include>

<div class="container">

    <section class="griglia">
        <c:forEach items = "${contenuti}" var = "contenuto">
            <article class="cont">

                <a class="copertina" href="Contenuto?id=<c:out value="${contenuto.id}"/>">
                    <img class="copertina" src="img/contenuti/<c:out value="${contenuto.id}"/>.png" alt=""/>
                </a>
                <h3>
                    <a class="titolo" href="Contenuto?id=<c:out value="${contenuto.id}"/>">
                        <c:out value="${contenuto.titolo}"/>
                    </a>
                </h3>

            </article>
        </c:forEach>
    </section>

</div>

<c:if test="${empty prodotti}">
    <h2 class="sottotitolo">Nessun prodotto trovato.</h2>
</c:if>

<%@include file="footer.html"%>
