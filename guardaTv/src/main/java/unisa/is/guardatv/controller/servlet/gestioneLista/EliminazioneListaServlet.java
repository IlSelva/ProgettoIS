package unisa.is.guardatv.controller.servlet.gestioneLista;

import unisa.is.guardatv.StorageLayer.ListaDAO;
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

import static unisa.is.guardatv.controller.Constants.MAX_NAME_LENGTH;
import static unisa.is.guardatv.controller.Constants.MIN_NAME_LENGTH;


/**
 * Questa classe é una Servlet che gestisce l'eliminazione di una lista da parte dell'utente
 */
@WebServlet(name = "EliminazioneLista", urlPatterns = "/eliminazione-lista")
public class EliminazioneListaServlet extends HttpServlet {

    public EliminazioneListaServlet() {
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

        String nome = request.getParameter("nome");
        // controllo il nome in input che rispetti la lunghezza definita
        if (!Utils.getInstance().checkStringLength(nome, MIN_NAME_LENGTH, MAX_NAME_LENGTH)) {
            throw new unisa.is.guardatv.controller.servlet.MyServletException("Nome non valido.");
        }

        ListaDAO listaDAO = new ListaDAO();

        try {
            listaDAO.doDelete(nome, utente.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
            throw new unisa.is.guardatv.controller.servlet.MyServletException("Errore eliminazione lista.");
        }

        request.setAttribute("notifica", "Lista rimossa con successo");

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/liste.jsp");
        requestDispatcher.forward(request, response);
    }
}