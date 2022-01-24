package unisa.is.guardatv.controller.servlet.GestioneRecensione;

import unisa.is.guardatv.StorageLayer.*;

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
    private final ContenutoDAO contenutoDAO = new ContenutoDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    private final RecensioneDAO recensioneDAO = new RecensioneDAO();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Recensione recensione;
        // devo prendere il contenuto
        String id = request.getParameter("id");
        Contenuto contenuto = contenutoDAO.doRetrieveById(id);

        // prendo i dati della recensione
        int punteggio = Integer.parseInt(request.getParameter("punteggio"));
        String descrizione = request.getParameter("descrizione"); // il testo è opzionale

        if (punteggio > 0 && punteggio <= 5) {
            recensione = new Recensione();
            recensione.setPunteggio(punteggio);
            if (descrizione != null)
                recensione.setDescrizione(descrizione);

            recensioneDAO.doSave(recensione);
            recensione.setContenuto(contenuto.getId());
            contenutoDAO.doUpdate(contenuto);
        }
        request.setAttribute("notifica", "Recensione salvata con successo");

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/Contenuto.jsp");
        requestDispatcher.forward(request, response);
    }
}