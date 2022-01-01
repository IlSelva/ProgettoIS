<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/jsp/header.jsp">
    <jsp:param name="pageTitle" value="${contenuto.titolo}"/>
</jsp:include>
<!-- style -->

<section class="section">

        <div class="contenuto">
            <img src="img/contenuti/${contenuto.id}.jpg" alt=""/>
            <h2>${contenuto.titolo}</h2>
            <p> Descrizione: ${contenuto.descrizione}</p>
            <ul>
                <c:forEach items = "{generi}" var = "genere">
                    <li class="genere">
                        <c:out value="${genre}"/>
                    </li>
                </c:forEach>
            </ul>
            <form name="addList" action="addList" method="post">
                <select class="selectLista" name="lista">
                    <option value="" label="" selected> liste </option>
                    <c:forEach items="${liste}" var="lista">
                        <option value ="<c:out value="${lista.nome}"/>"> <c:out value="${lista.nome}"/>. </option>
                    </c:forEach>
                </select>
                <input id="AddList" type="submit" value="Aggiungi ">
            </form>
            <span id="messaggioConferma"></span>
        </div>

        <div class="recensioni">
            <div>
                <h2>Recensioni</h2>
                <a href="recensioni"><h3> vedi tutti </h3></a>
            </div>
            <div class="container">
                <c:forEach items = "{recensioni}" var = "recensione">
                    <div class="recensione">
                        <div class="riga1">
                            <h3>
                                <c:out value="${recensione.utente.username}"></c:out>
                            </h3>
                            <p>
                                <c:out value="${recensione.punteggio}"></c:out> / 5
                            </p>
                        </div>
                        <h2>
                            <c:out value="${recensione.descrizione}"></c:out>
                        </h2>
                    </div>
                </c:forEach>

            </div>
        </div>

</section>

<%@include file="footer.html"%>