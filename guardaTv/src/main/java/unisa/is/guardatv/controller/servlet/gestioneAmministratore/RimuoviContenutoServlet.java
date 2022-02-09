package unisa.is.guardatv.controller.servlet.gestioneAmministratore;

import unisa.is.guardatv.StorageLayer.ContenutoDAO;
import unisa.is.guardatv.StorageLayer.Utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Pattern;

import static unisa.is.guardatv.controller.Constants.BAD_REQUEST_MESS;
import static unisa.is.guardatv.controller.Constants.ID_REGEX;


/**
 * Questa é una Servlet che gestisce la rimozione di un contenuto da parte dell'amministratore
 */
@WebServlet(name = "RimuoviContenuto", urlPatterns = "/rimuovi-contenuto")
public class RimuoviContenutoServlet extends HttpServlet {

    public RimuoviContenutoServlet() {
        super();
    }

    /**
     * @param request un oggetto HttpServletRequest che contiene la richiesta che il client invia alla servlet
     * @param response un oggetto HttpServletRequest che contiene la risposta che la servlet invia al client
     * @throws ServletException se la richiesta GET non può essere gestita
     * @throws IOException se la richiesta per il GET non può essere gestita
     */

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @param request un oggetto HttpServletRequest che contiene la richiesta che il client invia alla servlet
     * @param response un oggetto HttpServletRequest che contiene la risposta che la servlet invia al client
     * @throws ServletException se la richiesta GET non può essere gestita
     * @throws IOException se la richiesta per il GET non può essere gestita
     */

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //controllo utente amministratore
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        if (utente == null) {
            throw new unisa.is.guardatv.controller.servlet.MyServletException("Utente non loggato.");
        }

        if (!utente.getAdministrator()) {
            throw new unisa.is.guardatv.controller.servlet.MyServletException("Non hai i permessi necessari");
        }

        String id = request.getParameter("contenutoId");
        // controllo l'id in input che rispetti la regex definita in precedenza
        if (!Pattern.compile(ID_REGEX).matcher(id).find()) {
            throw new unisa.is.guardatv.controller.servlet.MyServletException("ID non valido.");
        }

        ContenutoDAO contenutoDAO = new ContenutoDAO();

        try {
            contenutoDAO.doDelete(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new unisa.is.guardatv.controller.servlet.MyServletException("Errore rimozione conteuto.");
        }


        request.setAttribute("notifica", "Contenuto rimosso con successo");

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/lista.jsp");
        requestDispatcher.forward(request, response);
    }
}



