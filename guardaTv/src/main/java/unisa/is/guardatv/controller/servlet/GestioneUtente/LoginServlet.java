package unisa.is.guardatv.controller.servlet.GestioneUtente;

import unisa.is.guardatv.StorageLayer.Utente;
import unisa.is.guardatv.StorageLayer.UtenteDAO;
import unisa.is.guardatv.StorageLayer.Login;
import unisa.is.guardatv.StorageLayer.LoginDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;


/**
 * Questa classe è una Servlet che gestisce il login di un utente
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 422938517959400504L;
    private final UtenteDAO utenteDAO = new UtenteDAO();
    private final LoginDAO loginDAO = new LoginDAO();
    /**
     * @param request un oggetto HttpServletRequest che contiene la richiesta che il client invia alla servlet
     * @param response un oggetto HttpServletRequest che contiene la risposta che la servlet invia al client
     * @throws ServletException se la richiesta POST non può essere gestita
     * @throws IOException se un errore di input o output viene rilevato quando la servlet gestisce la richiesta
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    /**
     * @param request un oggetto HttpServletRequest che contiene la richiesta che il client invia alla servlet
     * @param response un oggetto HttpServletRequest che contiene la risposta che la servlet invia al client
     * @throws ServletException se la richiesta GET non può essere gestita
     * @throws IOException se la richiesta per il GET non può essere gestita
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Utente utente = null;
        Utente utente1 = null;
        Utente utenteDaLoggare = null;
        if (email != null && password != null) {
            utente = utenteDAO.doRetrieveByEmail(email);
            utente1 = utenteDAO.doRetrieveByEmail(email);
            String salt = utente.getSalt(); // prendo il salt della pw
            String passwordSalt = password + salt; // concateno pw e salt
            utente1.setPasswordhash(passwordSalt); // faccio l'hash della pw
            if (utente1.getPasswordhash().equalsIgnoreCase(utente.getPasswordhash()))
                utente = utenteDAO.doRetrieveByEmailPassword(email, passwordSalt);
        }

        if (utente == null) {
            throw new unisa.is.guardatv.controller.servlet.MyServletException("email e/o password non validi.");
        }

        Login login = new Login();
        login.setIdUtente(utente.getEmail());
        login.setToken(UUID.randomUUID().toString());
        login.setTime(Timestamp.from(Instant.now()));
        try {
            loginDAO.doSave(login);
        } catch (Exception e) {
            e.printStackTrace();
            throw new unisa.is.guardatv.controller.servlet.MyServletException("Errore nel login.");
        }

        Cookie cookie = new Cookie("login", login.getId() + "_" + login.getToken());
        cookie.setMaxAge(30 * 24 * 60 * 60); // 30 giorni
        response.addCookie(cookie);

        request.getSession().setAttribute("utente", utente);

        String dest = request.getHeader("referer");
        if ( (dest == null || dest.contains("/Login") || dest.contains("/Registrazione") || dest.trim().isEmpty()) ) {
            dest = ".";
        }
        response.sendRedirect(dest);
    }
}
