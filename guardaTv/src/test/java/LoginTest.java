

import static org.junit.Assert.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


import org.junit.rules.ExpectedException;
import unisa.is.guardatv.StorageLayer.Utente;
import unisa.is.guardatv.controller.servlet.GestioneUtente.LoginServlet;
import unisa.is.guardatv.controller.servlet.MyServletException;

import java.io.PrintWriter;

public class LoginTest {
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    private LoginServlet servlet = new LoginServlet();
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;

    @Before
    public void inizializzaMock() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }


    @Test
    public void testUtente() throws Exception {
        exceptionRule.expect(MyServletException.class);
        exceptionRule.expectMessage("email e/o password non validi.");
        when(request.getParameter("utente")).thenReturn(null);
        when(request.getParameter("email")).thenReturn("ciao@gmail.com");
        when(request.getParameter("password")).thenReturn(null);
        PrintWriter printWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);
        servlet.doPost(request, response);
        verify(response).sendRedirect("index.jsp");
		}


}
