package unisa.is.guardatv.controller.servlet;

import com.google.gson.Gson;
import unisa.is.guardatv.StorageLayer.Contenuto;
import unisa.is.guardatv.controller.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static unisa.is.guardatv.controller.Constants.*;


/**
 * Servlet implementation class Ricerca
 */
@WebServlet(name = "Ricerca", urlPatterns = "/ricerca")
public class RicercaServlet extends HttpServlet {

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RicercaServlet() {
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
        HttpSession session = request.getSession();

        String ricerca = request.getParameter("ricerca");
        // controllo la stringa della ricerca in input sia valida
        if (!Utils.getInstance().checkStringLength(ricerca, MIN_SEARCH_LENGTH, MAX_SEARCH_LENGTH) {
            response.getWriter().write(BAD_REQUEST_MESS);
            return;
        }

        RicercaDAO ricercaDAO = new RicercaDAO();

        List<Contenuto> contenuti;
        try {
            contenuti = ricercaDAO.cerca(ricerca);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write(BAD_REQUEST_MESS);
            return;
        }

        String json = new Gson().toJson(contenuti);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}



