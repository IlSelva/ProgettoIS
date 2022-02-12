package unisa.is.guardatv.controller.servlet.GestioneRecensione;

import unisa.is.guardatv.StorageLayer.*;
import unisa.is.guardatv.controller.servlet.MyServletException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
/**
 * Questa classe è una Servlet che gestisce la rimozione di una Recensione da un Contenuto
 */
@WebServlet("/RimozioneRecensione")
public class RimozioneRecensioneServlet extends HttpServlet {

    /**
     * @param request un oggetto HttpServletRequest che contiene la richiesta che il client invia alla servlet
     * @param response un oggetto HttpServletRequest che contiene la risposta che la servlet invia al client
     * @throws ServletException se la richiesta POST non può essere gestita
     * @throws IOException se un errore di input o output viene rilevato quando la servlet gestisce la richiesta
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    private final ContenutoDAO contenutoDAO = new ContenutoDAO();
    private final RecensioneDAO recensioneDAO = new RecensioneDAO();
    /**
     * @param request un oggetto HttpServletRequest che contiene la richiesta che il client invia alla servlet
     * @param response un oggetto HttpServletRequest che contiene la risposta che la servlet invia al client
     * @throws ServletException se la richiesta GET non può essere gestita
     * @throws IOException se la richiesta per il GET non può essere gestita
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente utente = (Utente) request.getSession().getAttribute("utente"); // prendo l'utente dalla sessione
        if (utente == null || !utente.getAdministrator()) //controllo che l'utente sia amministratore
            throw new MyServletException("L'utente non è amministratore o non è loggato");


        // devo prendere il contenuto
        String idContenuto = request.getParameter("id");
        if (idContenuto == null)
            throw new MyServletException("Contenuto null");
        // id dell'utente che ha scritto la recensione
        String idUtente = request.getParameter("idutente");

        Recensione r = recensioneDAO.doRetrieveById(utente.getEmail(), idContenuto);
        recensioneDAO.doDelete(idUtente, idContenuto);
        request.setAttribute("notifica", "Recensione rimossa con successo");

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Contenuto");
        requestDispatcher.forward(request, response);
    }
}