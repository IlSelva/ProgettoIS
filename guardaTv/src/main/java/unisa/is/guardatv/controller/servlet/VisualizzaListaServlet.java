package unisa.is.guardatv.controller.servlet;

import com.google.gson.Gson;
import unisa.is.guardatv.StorageLayer.Contenuto;
import unisa.is.guardatv.StorageLayer.ContenutoLista;
import unisa.is.guardatv.StorageLayer.ContenutoListaDAO;
import unisa.is.guardatv.controller.Utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static unisa.is.guardatv.controller.Constants.BAD_REQUEST_MESS;


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


        ContenutoListaDAO contenutoListaDAO = new ContenutoListaDAO();

        List<Contenuto> contenuti;
        try {
            contenuti = contenutoListaDAO.allContenutiByLista(nomeLista, utente);
        } catch (Exception e) {
            e.printStackTrace();
            throw new unisa.is.guardatv.controller.servlet.MyServletException("Errore recupero contenuti by lista.");
        }
        request.setAttribute("contenuti", contenuti);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/lista.jsp");
        requestDispatcher.forward(request, response);
    }
}



