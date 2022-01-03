package unisa.is.guardatv.controller.servlet;

import unisa.is.guardatv.StorageLayer.ContenutoDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Pattern;

import static unisa.is.guardatv.controller.Constants.BAD_REQUEST_MESS;
import static unisa.is.guardatv.controller.Constants.ID_REGEX;


/**
 * Servlet implementation class RimuoviContenuto
 */
@WebServlet(name = "RimuoviContenuto", urlPatterns = "/rimuovi-contenuto")
public class RimuoviContenutoServlet extends HttpServlet {

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RimuoviContenutoServlet() {
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

        String id = request.getParameter("id");
        // controllo l'id in input che rispetti la regex definita in precedenza
        if (!Pattern.compile(ID_REGEX).matcher(id).find()) {
            response.getWriter().write(BAD_REQUEST_MESS);
            return;
        }

        ContenutoDAO contenutoDAO = new ContenutoDAO();

        try {
            contenutoDAO.doDelete(id);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write(BAD_REQUEST_MESS);
            return;
        }


        response.getWriter().write("ok");
    }
}



