package unisa.is.guardatv.controller.servlet.GestioneUtente;

import unisa.is.guardatv.StorageLayer.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Questa classe è una Servlet che gestisce la visualizzazione delle liste e dei rispettivi contenuti
 */
@WebServlet("/ContenutiLista")
public class ContenutiListaServlet extends HttpServlet {

    private final ListaDAO listaDAO = new ListaDAO();
    private final ContenutoListaDAO contenutoListaDAO = new ContenutoListaDAO();
    /**
     * @param request un oggetto HttpServletRequest che contiene la richiesta che il client invia alla servlet
     * @param response un oggetto HttpServletRequest che contiene la risposta che la servlet invia al client
     * @throws ServletException se la richiesta POST non può essere gestita
     * @throws IOException se un errore di input o output viene rilevato quando la servlet gestisce la richiesta
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    /**
     * @param request un oggetto HttpServletRequest che contiene la richiesta che il client invia alla servlet
     * @param response un oggetto HttpServletRequest che contiene la risposta che la servlet invia al client
     * @throws ServletException se la richiesta GET non può essere gestita
     * @throws IOException se la richiesta per il GET non può essere gestita
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Utente utente = (Utente) request.getSession().getAttribute("utente");
        if(request.getSession().getAttribute("utente") == null)
            throw new unisa.is.guardatv.controller.servlet.MyServletException("Utente non loggato.");
        HashMap<Lista, List<Contenuto>> map = new HashMap<>();
        if (utente != null) {
            String id = utente.getEmail();
            List<Lista> lista = listaDAO.doRetrieveByUtente(id, 0, 10); // prendo le liste dell'utente
            for (Lista x: lista) { // ciclo la lista delle liste
                if ((!map.containsKey(x.getNome()))) {
                    // creo una lista con i Contenuti di quella lista
                    List<Contenuto> listaContenuti = contenutoListaDAO.allContenutiByLista(x.getNome(), utente.getEmail());
                        map.put(x, listaContenuti); // aggiungo alla map la Lista e la lista dei Contenuti
                }
            }
            request.setAttribute("liste", lista);
            request.setAttribute("listaContenuti", map);
        }


        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/liste.jsp");
        requestDispatcher.forward(request, response);
    }
}