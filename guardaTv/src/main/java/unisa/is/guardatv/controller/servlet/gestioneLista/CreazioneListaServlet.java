package unisa.is.guardatv.controller.servlet.gestioneLista;

import unisa.is.guardatv.StorageLayer.Lista;
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

import static unisa.is.guardatv.controller.Constants.*;


/**
 * Questa classe é una servlet che gestisce la creazione di una lista da parte dell'utente
 */
@WebServlet(name = "CreazioneLista", urlPatterns = "/creazione-lista")
public class CreazioneListaServlet extends HttpServlet {

    public CreazioneListaServlet() {
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

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    Utente utente = (Utente) request.getSession().getAttribute("utente");
		if(utente == null){
			throw new MyServletException("Utente non loggato.");
		}

        String nome = request.getParameter("nomelista");
        // controllo il nome in input che rispetti la lunghezza definita
        if (!Utils.getInstance().checkStringLength(nome, MIN_NAME_LENGTH, MAX_NAME_LENGTH, REGEX_AZaz09)) {
            throw new unisa.is.guardatv.controller.servlet.MyServletException("Nome non valido.");
        }

        String descrizione = request.getParameter("descrizione");
        // controllo se la descrizione è presente
        if (!Utils.getInstance().checkStringLength(descrizione, 0, MAX_DESCRIPTION_LENGTH, REGEX_AZaz09)) {
            throw new unisa.is.guardatv.controller.servlet.MyServletException("Descrizione non valida.");
        }

        // controllo che l'utente sia una stringa valida
        if (!Utils.getInstance().isValidString(utente.getEmail())) {
            throw new unisa.is.guardatv.controller.servlet.MyServletException("Utente non valido.");
        }

        ListaDAO listaDAO = new ListaDAO();
        Lista lista = new Lista();
        lista.setNome(nome);
        lista.setDescrizione(descrizione);
        lista.setUtente(utente.getEmail());

        try {
            listaDAO.DoSave(lista);
        } catch (Exception e) {
            e.printStackTrace();
            throw new unisa.is.guardatv.controller.servlet.MyServletException("Errore creazione lista.");
        }

        request.setAttribute("notifica", "Lista creata con successo");

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/profilo.jsp");
        requestDispatcher.forward(request, response);
    }
}



