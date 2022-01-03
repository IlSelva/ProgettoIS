package unisa.is.guardatv.controller.servlet;

import unisa.is.guardatv.StorageLayer.ContenutoLista;
import unisa.is.guardatv.StorageLayer.ContenutoListaDAO;
import unisa.is.guardatv.controller.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static unisa.is.guardatv.controller.Constants.BAD_REQUEST_MESS;


/**
 * Servlet implementation class RimozioneContenutoLista
 */
@WebServlet(name = "RimozioneContenutoLista", urlPatterns = "/rimozione-contenuto-lista")
public class RimozioneContenutoListaServlet extends HttpServlet {

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RimozioneContenutoListaServlet() {
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
            response.getWriter().write(BAD_REQUEST_MESS);
            return;
        }

        String utente = request.getParameter("utente");
        // controllo che utente sia una stringa valida
        if (Utils.getInstance().isValidString(utente)) {
            response.getWriter().write(BAD_REQUEST_MESS);
            return;
        }

        String contenuto = request.getParameter("contenuto");
        // controllo che il contenuto sia una stringa valida
        if (Utils.getInstance().isValidString(utente)) {
            response.getWriter().write(BAD_REQUEST_MESS);
            return;
        }

        ContenutoListaDAO contenutoListaDAO = new ContenutoListaDAO();

        try {
            contenutoListaDAO.RemoveContenuto(nomeLista, utente, contenuto);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write(BAD_REQUEST_MESS);
            return;
        }

        response.getWriter().write("ok");
    }
}



