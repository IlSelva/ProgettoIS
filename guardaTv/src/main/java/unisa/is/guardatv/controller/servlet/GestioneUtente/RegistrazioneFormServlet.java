package unisa.is.guardatv.controller.servlet.GestioneUtente;

import unisa.is.guardatv.controller.servlet.MyServletException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RegistrazioneForm")
public class RegistrazioneFormServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("utente") != null) {
			throw new MyServletException("Utente loggato.");
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/registrazioneForm.jsp");
		requestDispatcher.forward(request, response);
	}
}
