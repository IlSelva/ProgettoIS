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
import java.util.Random;

@WebServlet("/ModificaPassword")
public class ModificaPasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    private final UtenteDAO utenteDAO = new UtenteDAO();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente utente = (Utente) request.getSession().getAttribute("utente"); // prendo l'utente dalla sessione

        String nuovaPassword;
        String confermaPassword;
        String saltedPassword = "";
        String salt = "";

        if (request.getParameter("modifica") != null) {
            nuovaPassword = request.getParameter("nuovaPassword");
            if (!(nuovaPassword != null && nuovaPassword.length() >= 8 && !nuovaPassword.toUpperCase().equals(nuovaPassword)
                    && !nuovaPassword.toLowerCase().equals(nuovaPassword) && nuovaPassword.matches(".*[0-9].*"))) {
                throw new unisa.is.guardatv.controller.servlet.MyServletException("Password non valida.");
            }

            String[] strArr = { "P", "Q", "R", "S","T", "U", "V", "W", "1", "2", "3", "4", "5" };
            Random rand = new Random();
            for (int i = 0; i <= strArr.length; i++) {
                int res = rand.nextInt(strArr.length);
                salt = salt + strArr[res];
                saltedPassword = nuovaPassword + salt;
            }

            confermaPassword = request.getParameter("confermaPassword");
            if (!nuovaPassword.equals(confermaPassword)) {
                throw new unisa.is.guardatv.controller.servlet.MyServletException("Password e conferma differenti.");
            }

            if (nuovaPassword != null) {
                utente.setSalt(salt);
                utente.setPassword(saltedPassword);
                utenteDAO.doUpdate(utente);
            }
        }



        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/profilo.jsp");
        requestDispatcher.forward(request, response);
    }
}
