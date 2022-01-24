package unisa.is.guardatv.controller.servlet.GestioneContenuto;

import unisa.is.guardatv.StorageLayer.*;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/Contenuto")
public class ContenutoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ContenutoDAO contenutoDAO = new ContenutoDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Contenuto contenuto = contenutoDAO.doRetrieveById(id);

        if (contenuto == null) {
            throw new unisa.is.guardatv.controller.servlet.MyServletException("Contenuto non trovato.");
        }

        List<Recensione> recensioni = RecensioneDAO.doRetrieveByContenuto(id, 0,100);

        request.setAttribute("contenuto", contenuto);
        request.setAttribute("recensioni", recensioni);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/Contenuto.jsp");
        requestDispatcher.forward(request, response);
    }
}
