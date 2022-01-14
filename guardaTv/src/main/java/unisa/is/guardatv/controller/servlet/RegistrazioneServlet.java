package unisa.is.guardatv.controller.servlet;

import unisa.is.guardatv.StorageLayer.Utente;
import unisa.is.guardatv.StorageLayer.UtenteDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

@WebServlet("/Registrazione")
public class RegistrazioneServlet extends HttpServlet {
    private static final long serialVersionUID = -3025497960801432201L;
    private UtenteDAO utenteDAO = new UtenteDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        if (!(email != null && email.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w+)+$"))) {
            throw new unisa.is.guardatv.controller.servlet.MyServletException("Email non valida."); // da aggiungere i file per le eccezioni
        }

        String password = request.getParameter("password");
        String saltedPassword = "";
        String salt = "";
        if (!(password != null && password.length() >= 8 && !password.toUpperCase().equals(password)
                && !password.toLowerCase().equals(password) && password.matches(".*[0-9].*"))) {
            throw new unisa.is.guardatv.controller.servlet.MyServletException("Password non valida.");
        } else {
            String[] strArr = { "P", "Q", "R", "S","T", "U", "V", "W", "1", "2", "3", "4" ,"5" };
            Random rand = new Random();
            for (int i = 0; i <= strArr.length; i++) {
                int res = rand.nextInt(strArr.length);
                salt = salt + strArr[res];
            }
            saltedPassword = password + salt;
        }

        String passwordConferma = request.getParameter("passwordConferma");
        if (!password.equals(passwordConferma)) {
            throw new unisa.is.guardatv.controller.servlet.MyServletException("Password e conferma differenti.");
        }

        String username = request.getParameter("nome");
        if (!(username != null && username.trim().length() > 0 && username.matches("^[ a-zA-Z\u00C0-\u00ff]+$"))) {
            throw new unisa.is.guardatv.controller.servlet.MyServletException("Username non valido.");
        }


        String data = request.getParameter("datadinascita");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy, dd, MM", Locale.ITALIAN);
        LocalDate date = LocalDate.parse(data, formatter);

        if (date.getYear() >= 2002) // deve essere maggiorenne
            throw new unisa.is.guardatv.controller.servlet.MyServletException("Non sei maggiorenne.");

        Utente utente = new Utente();
        utente.setSalt(salt);
        utente.setPassword(saltedPassword);
        utente.setUsername(username);
        utente.setEmail(email);
        utente.setDataDiNascita(date);

        utenteDAO.doSave(utente);
        request.getSession().setAttribute("utente", utente);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/profilo.jsp");
        requestDispatcher.forward(request, response);
    }
}
