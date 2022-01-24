package unisa.is.guardatv.controller.servlet.GestioneUtente;

import unisa.is.guardatv.StorageLayer.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ElencoListe")
public class ElencoListeServlet extends HttpServlet {

    private final ListaDAO listaDAO = new ListaDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Utente utente = (Utente) request.getSession().getAttribute("utente");
        if (utente != null) {
            String id = utente.getEmail();
            ArrayList<Lista> lista = (ArrayList<Lista>) listaDAO.doRetrieveByUtente(id, 0, 10);
            request.setAttribute("liste", lista);
        }


        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/liste.jsp");
        requestDispatcher.forward(request, response);
    }
}