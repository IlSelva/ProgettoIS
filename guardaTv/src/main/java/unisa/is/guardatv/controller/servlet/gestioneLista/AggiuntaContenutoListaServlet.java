package unisa.is.guardatv.controller.servlet.gestioneLista;

import unisa.is.guardatv.StorageLayer.ContenutoListaDAO;
import unisa.is.guardatv.StorageLayer.Utente;
import unisa.is.guardatv.controller.Utils;
import unisa.is.guardatv.controller.servlet.MyServletException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static unisa.is.guardatv.controller.Constants.*;


/**
 * Questa classe é una Servlet che gestisce l'aggiunta di un contenuto da parte dell'utente ad una lista
 */
@WebServlet(name = "AggiuntaContenutoLista", urlPatterns = "/aggiunta-contenuto-lista")
public class AggiuntaContenutoListaServlet extends HttpServlet {
    public AggiuntaContenutoListaServlet() {
        super();
    }

    /**
     * @param request  un oggetto HttpServletRequest che contiene la richiesta che il client invia alla servlet
     * @param response un oggetto HttpServletRequest che contiene la risposta che la servlet invia al client
     * @throws ServletException se la richiesta GET non può essere gestita
     * @throws IOException      se la richiesta per il GET non può essere gestita
     */

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @param request  un oggetto HttpServletRequest che contiene la richiesta che il client invia alla servlet
     * @param response un oggetto HttpServletRequest che contiene la risposta che la servlet invia al client
     * @throws ServletException se la richiesta GET non può essere gestita
     * @throws IOException      se la richiesta per il GET non può essere gestita
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        if (utente == null) {
            throw new MyServletException("Utente non loggato.");
        }

        String nomeLista = request.getParameter("nomeLista");
        // controllo che nomeLista sia una stringa valida
        if (!Utils.getInstance().isValidString(nomeLista,REGEX_AZaz09)) {
            throw new unisa.is.guardatv.controller.servlet.MyServletException("Nome lista non valido.");
        }

        String contenuto = request.getParameter("contenutoId");

        // controllo che il contenuto sia una stringa valida
        if (!Utils.getInstance().isValidString(contenuto,ID_REGEX)) {
            throw new unisa.is.guardatv.controller.servlet.MyServletException("Nome contenuto non valido.");
        }

        ContenutoListaDAO contenutoListaDAO = new ContenutoListaDAO();

        try {
            contenutoListaDAO.AddContenuto(nomeLista, utente.getEmail(), contenuto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new unisa.is.guardatv.controller.servlet.MyServletException("Errore nell'aggiunta del contenuto alla lista.");
        }
        request.setAttribute("notifica", "Contenuto aggiunto alla lista con successo");

	    RequestDispatcher requestDispatcher = request.getRequestDispatcher("Utente");
        requestDispatcher.forward(request, response);
    }
}



