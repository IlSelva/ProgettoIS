<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/jsp/header.jsp">
    <jsp:param name="pageTitle" value="Registrazione utente"/>
</jsp:include>

<style><%@include file="../css/generalStyle.css"%></style>

<div class="container">
    <div class="signform">
        <h1 class="titolo"> Registrazione </h1>
        <form name="registrazione" action="Registrazione" method="post">

            <label for="email"> Email</label>
            <input type="email" name="email" id="email" oninput="validaEmail()">
            <span class="returnmessage" id="messaggioemail"></span>

            <label for="password">Password</label>
            <input type="password" name="password" id="password" oninput="validaPassword()">
            <!--meter max="4" id="passwordstrength"></meter-->
            <span class="returnmessage" id="messaggiopassword"></span>

            <label for="confermapassword">Conferma Password</label>
            <input type="password" name="passwordConferma" id="confermapassword" oninput="validaPassword()">
            <span class="returnmessage" id="messaggiopasswordconferma"></span>

            <label for="username">Username</label>
            <input type="text" name="nome" id="username" oninput="validaUsername()">
            <span class="returnmessage" id="messaggiousername"></span>

            <label for="datanascita"> Data di nascita</label>
            <input type="date" id="datanascita" name="datadinascita" id="datanascita">
            <!-- data in formato yyyy-MM-dd secondo le specifiche RFC 3339 -->
            <span class="returnmessage" id="messaggiodatanascita"></span>

            <input class="confirmbutton" id="confermaregistrazione" type="submit" value="Conferma" disabled>
            <span class="returnmessage" id="registramessaggio"></span>

        </form>
        <h4>
            <a href="LoginForm"> sei già registrato? clicca qui.</a>
        </h4>
    </div>
</div>

<script>
    var borderOk = '2px solid #080';
    var borderNo = '2px solid #f00';
    var emailOk = false;
    var passwordOk = false;
    var nomeOk = false;

    function validaPassword() {
        var inputpw = document.forms['registrazione']['password'];
        var inputpwconf = document.forms['registrazione']['passwordConferma'];
        var password = inputpw.value;
        if (password.length >= 8 && password.toUpperCase() != password
            && password.toLowerCase() != password && /[0-9]/.test(password)) {
            inputpw.style.border = borderOk;
            document.getElementById('messaggiopassword').innerHTML ="&check;";
            document.getElementById('messaggiopassword').style.color="#080";
            if (password == inputpwconf.value) {
                inputpwconf.style.border = borderOk;
                passwordOk = true;
                document.getElementById('messaggiopasswordconferma').innerHTML ="&check;";
                document.getElementById('messaggiopasswordconferma').style.color="#080";
            } else {
                inputpwconf.style.border = borderNo;
                passwordOk = false;
                document.getElementById('messaggiopasswordconferma').innerHTML ="(!) Le password non coincidono";
                document.getElementById('messaggiopasswordconferma').style.color="#f00";
            }
        } else {
            inputpw.style.border = borderNo;
            inputpwconf.style.border = borderNo;
            passwordOk = false;
            document.getElementById('messaggiopassword').innerHTML = '(!) formato non valido, La password deve contenere almeno 8 caratteri, una lettera maiuscola, una lettera minuscola e un numero';
            document.getElementById('messaggiopassword').style.color="#f00";
        }
        cambiaStatoRegistrami();
    }

    function validaUsername() { //rifare con parametri del test
        var input = document.forms['registrazione']['nome'];
        if (input.value.trim().length > 0
            && input.value.match(/^[ a-zA-Z\u00C0-\u00ff]+$/)) {
            input.style.border = borderOk;
            nomeOk = true;
            document.getElementById('messaggiousername').innerHTML = '&check;';
            document.getElementById('messaggiousername').style.color="#080";
        } else {
            input.style.border = borderNo;
            nomeOk = false;
            document.getElementById('messaggiousername').innerHTML = 'messaggio di errore';
            document.getElementById('messaggiousername').style.color="#f00";
        }
        cambiaStatoRegistrami();
    }

    function validaEmail() {
        var input = document.forms['registrazione']['email'];
        if (input.value.match(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w+)+$/)) {
            input.style.border = borderOk;
            emailOk = true;
            document.getElementById('messaggioemail').innerHTML = '&check;';
            document.getElementById('messaggioemail').style.color="#080";
        } else {
            input.style.border = borderNo;
            emailOk = false;
            document.getElementById('messaggioemail').innerHTML = 'messaggio di errore;';
            document.getElementById('messaggioemail').style.color="#f00";
        }
        cambiaStatoRegistrami();
    }

    function cambiaStatoRegistrami() {
        if (passwordOk && nomeOk && emailOk) {
            document.getElementById('confermaregistrazione').disabled = false;
            document.getElementById('registramimessaggio').innerHTML = '';
        } else {
            document.getElementById('confermaregistrazione').disabled = true;
            document.getElementById('registramessaggio').innerHTML = 'Verifica che tutti i campi siano validi.';
        }
    }
</script>

<%@include file="footer.html"%>