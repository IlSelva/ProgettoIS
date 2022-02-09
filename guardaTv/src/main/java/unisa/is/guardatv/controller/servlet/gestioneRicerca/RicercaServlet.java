package unisa.is.guardatv.controller.servlet.gestioneRicerca;

import com.google.gson.Gson;
import unisa.is.guardatv.StorageLayer.Contenuto;
import unisa.is.guardatv.StorageLayer.ContenutoDAO;
import unisa.is.guardatv.controller.Utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static unisa.is.guardatv.controller.Constants.*;


/**
 * Questa classe é una Servlet che gestice la ricerca
 */
@WebServlet(name = "Ricerca", urlPatterns = "/ricerca")
public class RicercaServlet extends HttpServlet {

    public RicercaServlet() {
        super();
    }

    /**
     * @param request un oggetto HttpServletRequest che contiene la richiesta che il client invia alla servlet
     * @param response un oggetto HttpServletRequest che contiene la risposta che la servlet invia al client
     * @throws ServletException se la richiesta GET non può essere gestita
     * @throws IOException se la richiesta per il GET non può essere gestita
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * @param request un oggetto HttpServletRequest che contiene la richiesta che il client invia alla servlet
     * @param response un oggetto HttpServletRequest che contiene la risposta che la servlet invia al client
     * @throws ServletException se la richiesta GET non può essere gestita
     * @throws IOException se la richiesta per il GET non può essere gestita
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String ricerca = request.getParameter("ricerca");
        // controllo la stringa della ricerca in input sia valida
        if (!Utils.getInstance().checkStringLength(ricerca, MIN_SEARCH_LENGTH, MAX_SEARCH_LENGTH)) {
            throw new unisa.is.guardatv.controller.servlet.MyServletException("Ricerca non valida.");
        }

        ContenutoDAO contenutoDAO = new ContenutoDAO();

        List<Contenuto> contenuti;
        try {
            contenuti = contenutoDAO.doRetrieveByTitolo(ricerca, 0, 100);
        } catch (Exception e) {
            e.printStackTrace();
            throw new unisa.is.guardatv.controller.servlet.MyServletException("Errore recupero contenuti by titolo.");
        }

        request.setAttribute("contenuti", contenuti);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/ricerca.jsp");
        requestDispatcher.forward(request, response);


    }
}



