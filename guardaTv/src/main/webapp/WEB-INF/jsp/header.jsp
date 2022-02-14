<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style><%@include file="/WEB-INF/css/headerStyle.css"%></style>

<html lang="it">
    <head>
        <title>GuardaTV - ${paragram.pageTitle}</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
        <link rel="stylesheet" href="/WEB-INF/css/generalStyle.css">
        <!--script src="https://use.fontawesome.com/ca2b672bf9.js"></script-->
        <script src="https://kit.fontawesome.com/4367acb6a6.js" crossorigin="anonymous"></script>

    </head>

    <body>
        <header>
            <nav>
                <a class="logo" href=".">
                    <img src="img/logo_inverso.png" alt="logo"/>
                </a>


                <label for="ricerca"></label>
                <form id="ricerca" action="ricerca" method="get">
                    <!--select class="category" name="genere">
                        <option value="" label="" selected> generi </option>
                            <c:forEach items="${generi}" var="genere">
                                <option value ="<c:out value="${genere.nome}"/>"> <c:out value="${genere.nome}"/> </option>
                            </c:forEach>
                    </select-->

                    <input class="searchTerm" type="text" name="ricerca" autocomplete="off" list="ricerca-datalist" placeholder="Ricerca"/>
                    <datalist id="ricerca-datalist"></datalist>
                    <button class="searchButton" type="submit">
                        <span class="fa fa-search" aria-hidden="true"></span>
                    </button>
                </form>

                <ul class="header-menu">
                    <li>
                        <c:choose>
                            <c:when test="${utente == null}">
                                <a id="login" href="RegistrazioneForm"><span class="fa fa-user" aria-hidden="true"></span></a>
                            </c:when>
                            <c:otherwise>
                                <a id="profile" href="Utente"> <span class="fa fa-user" aria-hidden="true"> </span></a>
                            </c:otherwise>
                        </c:choose>
                    </li>
                </ul>
            </nav>
        </header>
