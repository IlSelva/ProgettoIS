package unisa.is.guardatv.controller.servlet;

import unisa.is.guardatv.StorageLayer.ContenutoListaDAO;
import unisa.is.guardatv.controller.Utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static unisa.is.guardatv.controller.Constants.BAD_REQUEST_MESS;


/**
 * Servlet implementation class AggiuntaContenutoLista
 */
@WebServlet(name = "AggiuntaContenutoLista", urlPatterns = "/aggiunta-contenuto-lista")
public class AggiuntaContenutoListaServlet extends HttpServlet {

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiuntaContenutoListaServlet() {
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

        String nomeLista = request.getParameter("nomeLista");
        // controllo che nomeLista sia una stringa valida
        if (Utils.getInstance().isValidString(nomeLista)) {
            throw new unisa.is.guardatv.controller.servlet.MyServletException("Nome lista non valido.");
        }

        String utente = request.getParameter("utente");
        // controllo che utente sia una stringa valida
        if (Utils.getInstance().isValidString(utente)) {
            throw new unisa.is.guardatv.controller.servlet.MyServletException("Utente non valido.");
        }

        String contenuto = request.getParameter("contenuto");
        // controllo che il contenuto sia una stringa valida
        if (Utils.getInstance().isValidString(utente)) {
            throw new unisa.is.guardatv.controller.servlet.MyServletException("Nome contenuto non valido.");
        }

        ContenutoListaDAO contenutoListaDAO = new ContenutoListaDAO();

        try {
            contenutoListaDAO.AddContenuto(nomeLista, utente, contenuto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new unisa.is.guardatv.controller.servlet.MyServletException("Errore nell'aggiunta del contenuto alla lista.");
        }
        request.setAttribute("notifica", "Contenuto aggiunto alla lista con successo");

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/lista.jsp");
        requestDispatcher.forward(request, response);
    }
}



