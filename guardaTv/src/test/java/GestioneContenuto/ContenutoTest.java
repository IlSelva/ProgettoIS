package GestioneContenuto;

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
import unisa.is.guardatv.StorageLayer.Contenuto;
import unisa.is.guardatv.StorageLayer.Utente;
import unisa.is.guardatv.controller.servlet.GestioneContenuto.ContenutoServlet;
import unisa.is.guardatv.controller.servlet.MyServletException;

import java.io.PrintWriter;
import java.sql.Date;

public class ContenutoTest {
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    private ContenutoServlet servlet = new ContenutoServlet();
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
    public void testContenutoNull() throws Exception {
        Contenuto contenuto = null;
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("contenuto")).thenReturn(contenuto);
        exceptionRule.expect(MyServletException.class);
        exceptionRule.expectMessage("Contenuto non trovato.");
        PrintWriter printWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);
        servlet.doPost(request, response);
    }
    
}
