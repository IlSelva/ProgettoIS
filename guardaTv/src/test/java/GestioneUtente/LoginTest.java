package GestioneUtente;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


import org.junit.rules.ExpectedException;
import unisa.is.guardatv.StorageLayer.Login;
import unisa.is.guardatv.StorageLayer.Utente;
import unisa.is.guardatv.controller.servlet.GestioneUtente.LoginServlet;
import unisa.is.guardatv.controller.servlet.MyServletException;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

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
        session = mock(HttpSession.class);
    }


    @Test
    public void testPasswordNull() throws Exception {
        exceptionRule.expect(MyServletException.class);
        exceptionRule.expectMessage("email e/o password non validi.");
        when(request.getParameter("email")).thenReturn("ciao@gmail.com");
        when(request.getParameter("password")).thenReturn(null);
        PrintWriter printWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);
        servlet.doPost(request, response);
		}

    @Test
    public void testEmailNull() throws Exception {
        exceptionRule.expect(MyServletException.class);
        exceptionRule.expectMessage("email e/o password non validi.");
        when(request.getParameter("email")).thenReturn(null);
        when(request.getParameter("password")).thenReturn("abcde3443FD");
        PrintWriter printWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);
        servlet.doPost(request, response);
    }

}
