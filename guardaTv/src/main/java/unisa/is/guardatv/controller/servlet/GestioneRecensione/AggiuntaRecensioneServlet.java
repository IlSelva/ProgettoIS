package unisa.is.guardatv.controller.servlet.GestioneRecensione;

import unisa.is.guardatv.StorageLayer.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
        Contenuto contenuto;
        if (utente == null)
            throw new unisa.is.guardatv.controller.servlet.MyServletException("Utente non loggato");

        Recensione recensione;
        // devo prendere il contenuto
        String id = request.getParameter("id");
        try {
            contenuto = contenutoDAO.doRetrieveById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new unisa.is.guardatv.controller.servlet.MyServletException("Errore");
        }

        // prendo i dati della recensione
        int punteggio = Integer.parseInt(request.getParameter("punteggio"));
        String descrizione = request.getParameter("descrizione"); // il testo è opzionale

        if (punteggio == 0)
            throw new unisa.is.guardatv.controller.servlet.MyServletException("Il punteggio non può essere 0");
        if (punteggio > 5)
            throw new unisa.is.guardatv.controller.servlet.MyServletException("Il punteggio non può essere maggiore di 5");
        if (descrizione.length() > 255)
            throw new unisa.is.guardatv.controller.servlet.MyServletException("La descrizione supera la lunghezza massima");
        if (punteggio > 0) {
            recensione = new Recensione();
            recensione.setPunteggio(punteggio);
            if (descrizione != null)
                recensione.setDescrizione(descrizione);
            recensione.setUtente(utente.getEmail());
            recensione.setContenuto(contenuto.getId());
            request.setAttribute("contenuto", contenuto);
            // devo prendere tutte le recensioni del contenuto
            List<Recensione> listaRecensioni = recensioneDAO.doRetrieveByContenuto(contenuto.getId(), 0,100);
            // devo fare il setAttribute con la lista delle recensioni
            request.setAttribute("listaRecensioni", listaRecensioni);
            try {
                recensioneDAO.doSave(recensione);
            } catch (Exception e) {
                e.printStackTrace();
                throw new unisa.is.guardatv.controller.servlet.MyServletException("Errore creazione recensione");
            }


        }

        request.setAttribute("notifica", "Recensione salvata con successo");

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("Contenuto");
        requestDispatcher.forward(request, response);
    }
}