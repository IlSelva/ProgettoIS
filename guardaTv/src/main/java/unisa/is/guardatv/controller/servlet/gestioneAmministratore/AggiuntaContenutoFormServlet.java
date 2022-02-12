package unisa.is.guardatv.controller.servlet.gestioneAmministratore;

import unisa.is.guardatv.StorageLayer.Utente;
import unisa.is.guardatv.controller.servlet.MyServletException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/AggiuntaContenutoForm")
public class AggiuntaContenutoFormServlet extends HttpServlet {
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
		if (utente == null || utente.getAdministrator()==false) {
			throw new MyServletException("Accesso non consentito");
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/uploadContenuto.jsp");
		requestDispatcher.forward(request, response);
	}

}
