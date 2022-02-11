package unisa.is.guardatv.controller.servlet.GestioneContenuto;

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
 * Questa classe è una Servlet che gestisce la visualizzazione di un Contenuto e delle informazioni relative ad esso
 */
@WebServlet("/Contenuto")
public class ContenutoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ContenutoDAO contenutoDAO = new ContenutoDAO();
    private final RecensioneDAO recensioneDAO = new RecensioneDAO();
    private final TipologiaDAO tipologiaDAO = new TipologiaDAO();
    private final ListaDAO listaDAO = new ListaDAO();

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
     * @throws ServletException se la richiesta POST non può essere gestita
     * @throws IOException se la richiesta per il GET non può essere gestita
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente utente = (Utente) request.getSession().getAttribute("utente"); // prendo l'utente dalla sessione
        List<Lista> listaDelleListe = null;

        String id = request.getParameter("id");
        Contenuto contenuto = contenutoDAO.doRetrieveById(id);

        if (contenuto == null) {
            throw new unisa.is.guardatv.controller.servlet.MyServletException("Contenuto non trovato.");
        }
        List<Genere> generi = tipologiaDAO.allGeneriByContenuto(id); // generi del contenuto
        if (utente != null) {
            listaDelleListe = listaDAO.doRetrieveByUtente(utente.getEmail(), 0, 100); // lista delle liste
        }

        List<Recensione> recensioni = recensioneDAO.doRetrieveByContenuto(id, 0,100); // recensioni contenuto


        request.setAttribute("contenuto", contenuto);
        request.setAttribute("recensioni", recensioni);
        request.setAttribute("generi", generi);
        request.setAttribute("listaDelleListe", listaDelleListe);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/Contenuto.jsp");
        requestDispatcher.forward(request, response);
    }
}
