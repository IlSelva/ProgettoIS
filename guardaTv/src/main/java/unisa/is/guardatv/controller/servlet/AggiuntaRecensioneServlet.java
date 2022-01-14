package unisa.is.guardatv.controller.servlet;

import unisa.is.guardatv.StorageLayer.Utente;
import unisa.is.guardatv.StorageLayer.UtenteDAO;
import unisa.is.guardatv.StorageLayer.ContenutoDAO;
import unisa.is.guardatv.StorageLayer.Lista;
import unisa.is.guardatv.StorageLayer.ListaDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/AggiuntaRecensione")
public class AggiuntaRecensioneServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    private final RecensioneDAO recensioneDAO = new RecensioneDAO();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Recensione recensione;
        // devo prendere il contenuto
        int id = Integer.parseInt(request.getParameter("id"));
        Contenuto contenuto = ContenutoDAO.doRetrieveById(id);

        // prendo i dati della recensione
        int punteggio = Integer.parseInt(request.getParameter("punteggio"));
        String testo = request.getParameter("descrizione"); // il testo è opzionale

        if (punteggio > 0 && punteggio <= 5) {
            recensione = new Recensione();
            recensione.setPunteggio(punteggio);
            if (descrizione != null)
                recensione.setTesto(testo);

            recensioneDAO.doSave(recensione);
            contenuto.setRecensione(Recensione);
            ContenutoDAO.doUpdate(contenuto);
        }
        request.setAttribute("notifica", "Recensione salvata con successo");

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/Contenuto.jsp");
        requestDispatcher.forward(request, response);
    }
}