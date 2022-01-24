package unisa.is.guardatv.controller.servlet.GestioneContenuto;

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

@WebServlet("/Contenuto")
public class ContenutoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ContenutoDAO contenutoDAO = new ContenutoDAO();
    private final RecensioneDAO recensioneDAO = new RecensioneDAO();
    private final TipologiaDAO tipologiaDAO = new TipologiaDAO();
    private final ListaDAO listaDAO = new ListaDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente utente = (Utente) request.getSession().getAttribute("utente"); // prendo l'utente dalla sessione


        String id = request.getParameter("id");
        Contenuto contenuto = contenutoDAO.doRetrieveById(id);

        if (contenuto == null) {
            throw new unisa.is.guardatv.controller.servlet.MyServletException("Contenuto non trovato.");
        }
        List<Genere> generi = tipologiaDAO.allGeneriByContenuto(id); // generi del contenuto

        List<Lista> listaDelleListe = listaDAO.doRetrieveByUtente(utente.getEmail(), 0, 1); // lista delle liste

        List<Recensione> recensioni = recensioneDAO.doRetrieveByContenuto(id, 0,100); // recensioni contenuto


        request.setAttribute("contenuto", contenuto);
        request.setAttribute("recensioni", recensioni);
        request.setAttribute("generi", generi);
        request.setAttribute("listaDelleListe", listaDelleListe);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/Contenuto.jsp");
        requestDispatcher.forward(request, response);
    }
}
