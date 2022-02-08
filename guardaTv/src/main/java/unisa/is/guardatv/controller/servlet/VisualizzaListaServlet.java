package unisa.is.guardatv.controller.servlet;

import unisa.is.guardatv.StorageLayer.*;
import unisa.is.guardatv.controller.Utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * Servlet implementation class VisualizzaLista
 */
@WebServlet(name = "VisualizzaLista", urlPatterns = "/visualizza-lista")
public class VisualizzaListaServlet extends HttpServlet {

    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaListaServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    Utente utente = (Utente) request.getSession().getAttribute("utente"); // prendo l'utente dalla sessione
	    if (utente == null) {
		    throw new MyServletException(); //utente non loggato
	    }

	    String nomeLista = request.getParameter("nomeLista");
        // controllo che nomeLista sia una stringa valida
        if (Utils.getInstance().isValidString(nomeLista)) {
            throw new unisa.is.guardatv.controller.servlet.MyServletException("Nome lista non valido.");
        }

        String utenteId = utente.getEmail();
        // controllo che utente sia una stringa valida
        if (Utils.getInstance().isValidString(utenteId)) {
            throw new unisa.is.guardatv.controller.servlet.MyServletException("Utente non valido.");
        }


	    ListaDAO listaDAO = new ListaDAO();
	    Lista lista = listaDAO.doRetrieveById(nomeLista, utenteId,0,1);

        ContenutoListaDAO contenutoListaDAO = new ContenutoListaDAO();

        List<Contenuto> contenuti;
        try {
            contenuti = contenutoListaDAO.allContenutiByLista(nomeLista, utenteId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new unisa.is.guardatv.controller.servlet.MyServletException("Errore recupero contenuti by lista.");
        }

		request.setAttribute("lista", lista);
        request.setAttribute("contenuti", contenuti);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/lista.jsp");
        requestDispatcher.forward(request, response);
    }
}



