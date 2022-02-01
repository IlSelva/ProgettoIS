package unisa.is.guardatv.controller.servlet.GestioneRecensione;

import unisa.is.guardatv.StorageLayer.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Questa classe è una Servlet che gestisce l'aggiunta di una Recensione ad un Contenuto
 */
@WebServlet("/AggiuntaRecensione")
public class AggiuntaRecensioneServlet extends HttpServlet {
    private final ContenutoDAO contenutoDAO = new ContenutoDAO();

    /**
     * @param request un oggetto HttpServletRequest che contiene la richiesta che il client invia alla servlet
     * @param response un oggetto HttpServletRequest che contiene la risposta che la servlet invia al client
     * @throws ServletException se la richiesta POST non può essere gestita
     * @throws IOException se un errore di input o output viene rilevato quando la servlet gestisce la richiesta
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    private final RecensioneDAO recensioneDAO = new RecensioneDAO();
    /**
     * @param request un oggetto HttpServletRequest che contiene la richiesta che il client invia alla servlet
     * @param response un oggetto HttpServletRequest che contiene la risposta che la servlet invia al client
     * @throws ServletException se la richiesta GET non può essere gestita
     * @throws IOException se la richiesta per il GET non può essere gestita
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");

        Recensione recensione;
        // devo prendere il contenuto
        String id = request.getParameter("id");
        Contenuto contenuto = contenutoDAO.doRetrieveById(id);

        // prendo i dati della recensione
        int punteggio = Integer.parseInt(request.getParameter("punteggio"));
        String descrizione = request.getParameter("descrizione"); // il testo è opzionale

        if (punteggio > 0 && punteggio <= 5) {
            recensione = new Recensione();
            recensione.setPunteggio(punteggio);
            if (descrizione != null)
                recensione.setDescrizione(descrizione);
            recensione.setUtente(utente.getEmail());
            recensione.setContenuto(id);
            recensioneDAO.doSave(recensione);
            recensione.setContenuto(contenuto.getId());
            contenutoDAO.doUpdate(contenuto);
        }
        request.setAttribute("notifica", "Recensione salvata con successo");

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/Contenuto.jsp");
        requestDispatcher.forward(request, response);
    }
}