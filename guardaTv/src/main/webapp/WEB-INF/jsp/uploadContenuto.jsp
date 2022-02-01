<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/jsp/header.jsp">
    <jsp:param name="pageTitle" value="${contenuto.titolo}"/>
</jsp:include>
<style><%@include file="../css/generalStyle.css"%></style>

<div class="container">
    <div class="uploadform">
        <h1 class="titolo"> Contenuto </h1>
        <form name="aggiuntacontenuto" action="aggiunto-contenuto" method="post" enctype="multipart/form-data">
            <label for="categoria"></label>
            <select class="categoriaselect" name="categoria" id="categoria" onchange="categorySelectCheck(this);">
                <option value="film" selected> Film </option>
                <option value="serie" id="categoriaSerie"> Serie </option>
            </select>

            <label for="id"> ID</label>
            <input type="text" size="35" name="contenutoId" id="id" required>

            <label for="titolo"> Titolo</label>
            <input type="text" size="50" name="titolo" id="titolo" required>

            <label for="copertinaIn"> Copertina</label>
            <input type="file" name="immagine" id="copertinaIn" accept="image/vnd.sealedmedia.softseal.jpg, image/jpeg, image/png">

            <label for="descrizione"> Descrizione</label>
            <textarea name="descrizione" maxlength="255" id="descrizione" required></textarea> <!-- rows and cols -->

            <label for="genere" title="generi separati da virgola: genere1, genere2"> Generi</label>
            <input type="text" size="20" name="genere" id="genere" required>
            <!-- aggiungere i generi, nella servlet è una stringa di generi separati da , -->
            <label for="regista"> Regista </label>
            <input type="text" name="regista" id="regista" required>

            <label for="durata"> Durata (minuti) </label>
            <input type="number" name="durata" id="durata" min="1" required>

            <label for="datauscita"> Data di uscita </label>
            <input type="date" name="dataDiUscita" id="datauscita" required>

            <label for="trailer"> Trailer </label>
            <input type="url" name="trailer" id="trailer">

            <div id="seriefields" style="display:none;">
                <label for="stagioni"> Numero Stagioni </label>
                <input type="number" name="stagioni" id="stagioni">

                <label for="puntate"> Numero puntate (totali?)</label>
                <input type="number" name="puntate" id="puntate">
            </div>

            <label for="confermaregistrazione"></label>
            <input class="confirmbutton" id="confermaregistrazione" type="submit" value="Conferma">

        </form>
    </div>


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
