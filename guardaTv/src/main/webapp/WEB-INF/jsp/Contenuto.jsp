<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/jsp/header.jsp">
    <jsp:param name="pageTitle" value="${contenuto.titolo}"/>
</jsp:include>
<!-- style -->

<div class="container">

        <div class="contenuto">
            <img src="img/contenuti/${contenuto.id}.jpg" alt=""/>
            <h2>${contenuto.titolo}</h2>
            <p>${contenuto.descrizione}</p>
            <ul class="generi">
                <c:forEach items = "{generi}" var = "genere">
                    <li class="genere">
                        <c:out value="${genre}"/>
                    </li>
                </c:forEach>
            </ul>
            <form name="addList" action="addList" method="post">
                <select class="listselector" name="lista">
                    <option value="" label="" selected> liste </option>
                    <c:forEach items="${liste}" var="lista">
                        <option value ="<c:out value="${lista.nome}"/>"> <c:out value="${lista.nome}"/>. </option>
                    </c:forEach>
                </select>
                <input class="listselector" id="addList" type="submit" value="Aggiungi ">
            </form>
            <span id="messaggioConferma"></span>
        </div>


        <div class="recensioni">

            <div class="container">
                <div class="newreviewform">
                    <label class="sottotitolo" for="nuovaRecensione">
                        <h3>aggiungi una nuova recensione</h3>
                    </label>

                    <form id="nuovaRecensione">
                        <div class="riga1">
                            <h3 class="nomeutente"> <:out value="${utente.username}"/> </h3>
                            <div class="rating">
                                <input type="radio" name="star" id="star5">
                                <label for="star5"></label>
                                <input type="radio" name="star" id="star4">
                                <label for="star4"></label>
                                <input type="radio" name="star" id="star3">
                                <label for="star3"></label>
                                <input type="radio" name="star" id="star2">
                                <label for="star2"></label>
                                <input type="radio" name="star" id="star1">
                                <label for="star1"></label>
                            </div>
                        </div>
                        <textarea name="descrizione" id="descrizione" placeholder="Scrivi la tua recensione" required></textarea>
                        <input class="inviarecensione" id="inviaRecensione" type="submit" value="invia">
                    </form>
                </div>

                <div class="titolo">
                    <h2 class="titolo-sezione">Recensioni</h2>
                    <a class="fullview-link" href="recensioni">
                        <h3>vedi tutti</h3>
                    </a>
                </div>
                <div class="container">
                    <c:forEach items = "{recensioni}" var = "recensione">
                        <div class="recensione">
                            <div class="riga1">
                                <h3 class="nomeutente">
                                    <c:out value="${recensione.utenteusername}"></c:out>
                                </h3>
                                <p class="punteggio">
                                    <c:forEach var="i" begin="1" end="${recensione.punteggio}" step="1">
                                        <span class="fa fa-star"></span>
                                    </c:forEach>

                                    <c:forEach var="i" begin="${recensione.punteggio}+1" end="5" step="1">
                                        <span class="fa fa-star-o"></span>
                                    </c:forEach>
                                </p>
                                <c:if test="${utente.admin == true}">
                                    <form class="eliminarecensione">
                                        <input type="hidden" name="idutente" value=${recensione.utenteid} />
                                        <input class="confirmbutton" type="submit" value="f1f8" name="elimina" id="eliminarecensione">
                                    </form>
                                </c:if>
                            </div>
                            <h4 class="recensionecontenuto">
                                <c:out value="${recensione.descrizione}"></c:out>
                            </h4>
                        </div>
                    </c:forEach>
                </div>
            </div>

    </div>
</div>
<%@include file="footer.html"%>