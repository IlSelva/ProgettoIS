<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/jsp/header.jsp">
    <jsp:param name="pageTitle" value="${contenuto.titolo}"/>
</jsp:include>

<style><%@include file="../css/generalStyle.css"%></style>
<style><%@include file="../css/contenutoStyle.css"%></style>

<div class="container">
        <div class="contenuto">
            <img src="img/contenuti/${contenuto.immagineDelContenuto}" alt=""/>
            <h2>${contenuto.titolo}</h2>
            <p>${contenuto.descrizione}</p>
            <ul class="generi">
                <c:forEach items = "${generi}" var = "genere">
                    <li class="genere">
                        <c:out value="${genere.nome}"/>
                    </li>
                </c:forEach>
            </ul>
        <c:choose>
            <c:when test="${utente != null}">
                <form name="addList" action="aggiunta-contenuto-lista" method="post">
                    <input type="text" name="contenuto" value="<c:out value="${contenuto.id}"/>" hidden>
                    <label for="selectList"></label>
                    <select class="listselector" name="nomeLista" id="selectList">
                        <option value="" label="" selected> liste </option>
                        <c:forEach items="${liste}" var="lista">
                            <option value ="<c:out value="${lista.nome}"/>"> <c:out value="${lista.nome}"/>. </option>
                        </c:forEach>
                    </select>
                    <input class="listselector" id="addList" type="submit" value="Aggiungi ">
                </form>
                <span id="messaggioConferma"></span>
            </c:when>
        </c:choose>
        </div>


        <div class="recensioni">

            <div class="container">
                <c:choose>
                    <c:when test="${utente != null}">
                        <div class="newreviewform">
                            <h3>
                                <label class="sottotitolo" for="nuovaRecensione">
                                    aggiungi una nuova recensione
                                </label>
                            </h3>
                            <form name="recensione" id="nuovaRecensione" action="/AggiuntaRecensione" method="post">
                                <input type="text" name="contenuto" value="<c:out value="${contenuto.id}"/>" hidden>
                                <div class="riga1">
                                    <h3 class="nomeutente"> <c:out value="${utente.email}"/> </h3>
                                    <div class="rating">
                                        <input type="radio" name="punteggio" id="star5" value="5">
                                        <label for="star5"></label>
                                        <input type="radio" name="punteggio" id="star4" value="4">
                                        <label for="star4"></label>
                                        <input type="radio" name="punteggio" id="star3" value="3">
                                        <label for="star3"></label>
                                        <input type="radio" name="punteggio" id="star2" value="2">
                                        <label for="star2"></label>
                                        <input type="radio" name="punteggio" id="star1" value="1">
                                        <label for="star1"></label>
                                    </div>
                                </div>
                                <label for="descrizione"></label>
                                <textarea name="descrizione" id="descrizione" placeholder="Scrivi la tua recensione" required></textarea>
                                <input class="confirmbutton inviarecensione" id="inviaRecensione" type="submit" value="invia">
                            </form>
                        </div>
                    </c:when>
                </c:choose>
                <div class="titolo">
                    <h2 class="titolo-sezione">Recensioni</h2>
                    <!--a class="fullview-link" href="re">
                        <h3>vedi tutti</h3>
                    </a-->
                </div>
                <div class="container">
                    <c:forEach items = "${recensioni}" var = "recensione">
                        <div class="recensione">
                            <div class="riga1">
                                <h3 class="nomeutente"> <!-- email -->
                                    <c:out value="${recensione.utente}"></c:out>
                                </h3>
                                <p class="punteggio">
                                    <c:forEach var="i" begin="1" end="${recensione.punteggio}" step="1">
                                        <span class="fa fa-star"></span>
                                    </c:forEach>

                                    <c:forEach var="i" begin="${recensione.punteggio}" end="4" step="1">
                                        <span class="fa fa-star-o"></span>
                                    </c:forEach>
                                </p>
                                <c:if test="${utente.administrator == true}">
                                    <form class="eliminarecensione">
                                        <input type="hidden" name="idutente" value=${recensione.utente} />
                                        <input class="confirmbutton" type="submit" value="f1f8" name="rimozioneRecensione" id="eliminarecensione">
                                    </form>
                                </c:if>
                            </div>
                            <h4 class="contenutorecensione">
                                <c:out value="${recensione.descrizione}"></c:out>
                            </h4>
                        </div>
                    </c:forEach>
                </div>
            </div>

    </div>
</div>

<%@include file="footer.html"%>