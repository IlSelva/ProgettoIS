package unisa.is.guardatv.controller.servlet.GestioneHome;


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
 * Questa classe è una Servlet che gestisce la visualizzazione della home
 */
@WebServlet(name = "HomeServlet", urlPatterns="", loadOnStartup=1)
public class HomeServlet extends HttpServlet {
	private final ContenutoDAO contenutoDAO = new ContenutoDAO();

	@Override
	public void init() throws ServletException {
		GenereDAO serviceGen = new GenereDAO();
		List<Genere> generi = serviceGen.doRetrieveAll();
		getServletContext().setAttribute("generi", generi);
		super.init();
	}
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

		List<Contenuto> contenuti = serviceCont.doRetrieveAll(0, 10);
		List<Contenuto> ultimi = serviceCont.doRetrieveLast(0,10);
		request.setAttribute("contenuti", contenuti);
		request.setAttribute("ultimi",ultimi);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/index.jsp");
		requestDispatcher.forward(request, response);
	}


	ContenutoDAO serviceCont = new ContenutoDAO();
	
}
