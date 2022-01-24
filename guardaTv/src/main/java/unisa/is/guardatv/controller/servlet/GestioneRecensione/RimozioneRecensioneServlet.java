package unisa.is.guardatv.controller.servlet.GestioneRecensione;

import unisa.is.guardatv.StorageLayer.Utente;
import unisa.is.guardatv.StorageLayer.UtenteDAO;
import unisa.is.guardatv.StorageLayer.Recensione;
import unisa.is.guardatv.StorageLayer.RecensioneDAO;
import unisa.is.guardatv.StorageLayer.Contenuto;
import unisa.is.guardatv.StorageLayer.ContenutoDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException.;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/RimozioneRecensione")
public class RimozioneRecensioneServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    private final ContenutoDAO contenutoDAO = new ContenutoDAO();
    private final RecensioneDAO recensioneDAO = new RecensioneDAO();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente utente = (Utente) request.getSession().getAttribute("utente"); // prendo l'utente dalla sessione
        if (utente == null || !utente.getAdministrator()) //controllo che l'utente sia amministratore
            throw new unisa.is.guardatv.controller.servlet.MyServletException();


        // devo prendere il contenuto
        String idContenuto = request.getParameter("id");
        Contenuto contenuto = contenutoDAO.doRetrieveById(idContenuto);
        List<Recensione> recensione = new List<Recensione>();
        recensione = RecensioneDAO.doRetrieveByContenuto(idContenuto, 0, 1); // o devo fare l'add?
        Recensione recensione1 = recensione.get(0);

        recensioneDAO.doDelete((recensione1.getUtente().getEmail()), idContenuto);

        request.setAttribute("notifica", "Recensione rimossa con successo");

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/Contenuto.jsp");
        requestDispatcher.forward(request, response);
    }
}