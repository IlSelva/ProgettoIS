<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style><%@include file="/WEB-INF/css/headerStyle.css"%></style>

<html lang="it">
    <head>
        <title>GuardaTV - ${paragram.pageTitle}</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
        <link href="../css/generalStyle.css" rel="stylesheet" type="text/css">
        <script src="https://use.fontawesome.com/ca2b672bf9.js"></script>
    </head>

    <body>
        <header>
            <nav>
                <a class="logo" href=".">
                    <img src="img/logo2.png" alt="logo"/>
                </a>


                <label for="ricerca"></label>
                <form id="ricerca" action="Ricerca" method="get">
                    <select class="category" name="category">
                        <option value="" label="" selected> generi </option>
                            <c:forEach items="${generi}" var="genere">
                                <option value ="<c:out value="${genere.nome}"/>"> <c:out value="${genere.nome}"/> </option>
                            </c:forEach>
                    </select>
                    <input class="searchTerm" type="text" name="q" autocomplete="off" list="ricerca-datalist" placeholder="Ricerca" onkeyup="ricerca(this.value)" value="<c:out value="${param.q}" />">
                    <datalist id="ricerca-datalist"></datalist>
                    <button class="searchButton" type="submit">
                        <span class="fa fa-search" aria-hidden="true"></span>
                    </button>
                </form>

                <ul class="header-menu">
                    <li>
                        <c:choose>
                            <c:when test="${utente == null}">
                                <a id="login" href="Registrazione"><span class="fa fa-user" aria-hidden="true"></span></a>
                            </c:when>
                            <c:otherwise>
                                <a id="profile" href="Utente"> <span class="fa fa-user" aria-hidden="true"> </span></a>
                            </c:otherwise>
                        </c:choose>
                    </li>
                </ul>
            </nav>
        </header>
