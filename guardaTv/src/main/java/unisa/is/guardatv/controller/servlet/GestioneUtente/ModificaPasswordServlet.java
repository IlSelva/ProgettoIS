package unisa.is.guardatv.controller.servlet.GestioneUtente;

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
/**
 * Questa classe è una Servlet che gestisce la modifica della password di un utente
 */
@WebServlet("/ModificaPassword")
public class ModificaPasswordServlet extends HttpServlet {
    /**
     * @param request un oggetto HttpServletRequest che contiene la richiesta che il client invia alla servlet
     * @param response un oggetto HttpServletRequest che contiene la risposta che la servlet invia al client
     * @throws ServletException se la richiesta POST non può essere gestita
     * @throws IOException se un errore di input o output viene rilevato quando la servlet gestisce la richiesta
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    private final UtenteDAO utenteDAO = new UtenteDAO();
    /**
     * @param request un oggetto HttpServletRequest che contiene la richiesta che il client invia alla servlet
     * @param response un oggetto HttpServletRequest che contiene la risposta che la servlet invia al client
     * @throws ServletException se la richiesta GET non può essere gestita
     * @throws IOException se la richiesta per il GET non può essere gestita
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente utente = (Utente) request.getSession().getAttribute("utente"); // prendo l'utente dalla sessione
        if(request.getSession().getAttribute("utente") == null)
            throw new unisa.is.guardatv.controller.servlet.MyServletException("Utente non loggato.");
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
                utente.setPasswordhash(saltedPassword);
                utenteDAO.doUpdate(utente);
            }
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/profilo.jsp");
        requestDispatcher.forward(request, response);
    }
}
