<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/jsp/header.jsp">
    <jsp:param name="pageTitle" value="${contenuto.titolo}"/>
</jsp:include>
<!-- style -->

<div class="container">
    <h1 class="titolo"> Contenuto </h1>
    <form name="aggiuntacontenuto" action="aggiunto-contenuto" method="post">
        <label for="categoria"></label>
        <select class="categoria" name="categoria" id="categoria" onchange="categorySelectCheck(this);">
            <option value="film" selected> Film </option>
            <option value="serie" id="categoriaSerie"> Serie </option>
        </select>

        <label for="id"> ID</label>
        <input type="text" name="id" id="id" required>

        <label for="titolo"> Titolo</label>
        <input type="text" name="titolo" id="titolo" required>

        <label for="descrizione"> Descrizione</label>
        <textarea name="descrizione" id="descrizione" required> </textarea> <!-- rows and cols -->

        <!-- aggiungere i generi, nella servlet è una stringa di generi separati da , -->
        <label for="regista"> Regista </label>
        <input type="text" name="regista" id="regista" required>

        <label for="durata"> Durata (minuti) </label>
        <input type="number" name="durata" id="durata" min="1" required>

        <label for="datauscita"> Data di uscita </label>
        <input type="date" name="datauscita" id="datauscita" required>

        <label for="trailer"> Trailer </label>
        <input type="url" name="trailer" id="trailer">

        <div id="seriefields" style="display:none;">
            <label for="stagioni"> Numero Stagioni </label>
            <input type="number" name="stagioni" id="stagioni" required>

            <label for="puntate"> Numero puntate (totali?)</label>
            <input type="number" name="puntate" id="puntate" required>
        </div>

        <input class="confirmbutton" id="confermaregistrazione" type="submit" value="Conferma" disabled>

    </form>



<script>

    function categorySelectCheck(categoria) {
        if (categoria) {
            serie = document.getElementById("categoriaSerie").value;
            if (serie == categoria.value) {
                document.getElementById("seriefields").style.display = "block";
            } else {
                document.getElementById("seriefields").style.display = "none";
            }
        } else {
            document.getElementById("seriefields").style.display = "none";
        }
    }

</script>


<%@include file="footer.html"%>
