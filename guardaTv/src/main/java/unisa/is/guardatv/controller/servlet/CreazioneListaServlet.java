package unisa.is.guardatv.controller.servlet;

import unisa.is.guardatv.StorageLayer.Lista;
import unisa.is.guardatv.StorageLayer.ListaDAO;
import unisa.is.guardatv.StorageLayer.Utente;
import unisa.is.guardatv.controller.Utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static unisa.is.guardatv.controller.Constants.*;


/**
 * Servlet implementation class RimuoviContenuto
 */
@WebServlet(name = "CreazioneLista", urlPatterns = "/creazione-lista")
public class CreazioneListaServlet extends HttpServlet {

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreazioneListaServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    Utente utente = (Utente) request.getSession().getAttribute("utente");
		if(utente == null){
			throw new MyServletException("Utente loggato.");
		}

        String nome = request.getParameter("nomelista");
        // controllo il nome in input che rispetti la lunghezza definita
        if (!Utils.getInstance().checkStringLength(nome, MIN_NAME_LENGTH, MAX_NAME_LENGTH)) {
            throw new unisa.is.guardatv.controller.servlet.MyServletException("Nome non valido.");
        }

        String descrizione = request.getParameter("descrizione");
        // controllo se la descrizione è presente
        if (Utils.getInstance().isValidString(descrizione) && !Utils.getInstance().checkStringLength(descrizione, 0, MAX_DESCRIPTION_LENGTH)) {
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



