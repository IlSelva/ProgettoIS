package unisa.is.guardatv.controller.servlet.gestioneLista;

import unisa.is.guardatv.StorageLayer.*;
import unisa.is.guardatv.controller.Utils;
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
 * Questa classe é una Servlet che gestisce la vista di una lista da parte dell'utente
 */
@WebServlet(name = "VisualizzaLista", urlPatterns = "/visualizza-lista")
public class VisualizzaListaServlet extends HttpServlet {

    public VisualizzaListaServlet() {
        super();
    }

    /**
     * @param request  un oggetto HttpServletRequest che contiene la richiesta che il client invia alla servlet
     * @param response un oggetto HttpServletRequest che contiene la risposta che la servlet invia al client
     * @throws ServletException se la richiesta GET non può essere gestita
     * @throws IOException      se la richiesta per il GET non può essere gestita
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    /**
     * @param request  un oggetto HttpServletRequest che contiene la richiesta che il client invia alla servlet
     * @param response un oggetto HttpServletRequest che contiene la risposta che la servlet invia al client
     * @throws ServletException se la richiesta GET non può essere gestita
     * @throws IOException      se la richiesta per il GET non può essere gestita
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente utente = (Utente) request.getSession().getAttribute("utente"); // prendo l'utente dalla sessione
        if (utente == null) {
            throw new MyServletException("Utente non loggato"); //utente non loggato
        }

        String nomeLista = request.getParameter("nomeLista");
        // controllo che nomeLista sia una stringa valida
        if (!Utils.getInstance().isValidString(nomeLista)) {
            throw new MyServletException("Nome lista non valido.");
        }

        ListaDAO listaDAO = new ListaDAO();
        Lista lista = listaDAO.doRetrieveById(nomeLista, utente.getEmail(), 0, 1);

        ContenutoListaDAO contenutoListaDAO = new ContenutoListaDAO();

        List<Contenuto> contenuti;
        try {
            contenuti = contenutoListaDAO.allContenutiByLista(nomeLista, utente.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyServletException("Errore recupero contenuti by lista.");
        }

        request.setAttribute("lista", lista);
        request.setAttribute("contenuti", contenuti);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/lista.jsp");
        requestDispatcher.forward(request, response);
    }
}



