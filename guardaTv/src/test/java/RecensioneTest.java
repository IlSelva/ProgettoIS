

import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


import org.junit.rules.ExpectedException;

import unisa.is.guardatv.controller.servlet.GestioneRecensione.AggiuntaRecensioneServlet;
import unisa.is.guardatv.controller.servlet.MyServletException;

import java.io.PrintWriter;


public class RecensioneTest {
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    private AggiuntaRecensioneServlet servlet = new AggiuntaRecensioneServlet();
    private HttpServletRequest request;
    private HttpServletResponse response;

    @Before
    public void inizializzaMock() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }


    @Test
    public void testPunteggioMaggiore() throws Exception {
        exceptionRule.expect(MyServletException.class);
        exceptionRule.expectMessage("Il punteggio non può essere 0");
        when(request.getParameter("punteggio")).thenReturn(String.valueOf(0));
        when(request.getParameter("descrizione")).thenReturn("Bel film");
        PrintWriter printWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);
        servlet.doPost(request, response);
    }


}
