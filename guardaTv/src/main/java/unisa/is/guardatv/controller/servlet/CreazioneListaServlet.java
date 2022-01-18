package unisa.is.guardatv.controller.servlet;

import unisa.is.guardatv.StorageLayer.ContenutoDAO;
import unisa.is.guardatv.StorageLayer.Lista;
import unisa.is.guardatv.StorageLayer.ListaDAO;
import unisa.is.guardatv.controller.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Pattern;

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

        String nome = request.getParameter("nome");
        // controllo il nome in input che rispetti la lunghezza definita
        if (!Utils.getInstance().checkStringLength(nome, MIN_NAME_LENGTH, MAX_NAME_LENGTH)) {
            response.getWriter().write(BAD_REQUEST_MESS);
            return;
        }

        String descrizione = request.getParameter("descrizione");
        // controllo se la descrizione è presente
        if (Utils.getInstance().isValidString(descrizione) && !Utils.getInstance().checkStringLength(descrizione, 0, MAX_DESCRIPTION_LENGTH)) {
            response.getWriter().write(BAD_REQUEST_MESS);
            return;
        }

        String utente = request.getParameter("utente");
        // controllo che l'utente sia una stringa valida
        if (Utils.getInstance().isValidString(utente)) {
            response.getWriter().write(BAD_REQUEST_MESS);
            return;
        }

        ListaDAO listaDAO = new ListaDAO();
        Lista lista = new Lista();
        lista.setNome(nome);
        lista.setDescrizione(descrizione);
        lista.setUtente(utente);

        try {
            listaDAO.DoSave(lista);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write(BAD_REQUEST_MESS);
            return;
        }

        response.getWriter().write("ok");
    }
}



