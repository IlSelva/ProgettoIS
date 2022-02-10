
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
import unisa.is.guardatv.StorageLayer.Utente;
import unisa.is.guardatv.controller.servlet.GestioneRecensione.RimozioneRecensioneServlet;
import unisa.is.guardatv.controller.servlet.MyServletException;


import java.io.PrintWriter;

public class RimozioneRecensioneTest {
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    private RimozioneRecensioneServlet servlet = new RimozioneRecensioneServlet();
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
    public void testUtenteNonAdmin() throws Exception {
        exceptionRule.expect(MyServletException.class);
        exceptionRule.expectMessage("L'utente non è amministratore o non è loggato");
        Utente utente = new Utente();
        utente.setEmail("email@gmail.com");
        utente.setUsername("Username");
        utente.setAdministrator(false);
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("utente")).thenReturn(utente);
        PrintWriter printWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);
        servlet.doPost(request, response);
    }

    @Test
    public void testUtenteNull() throws Exception {
        exceptionRule.expect(MyServletException.class);
        exceptionRule.expectMessage("L'utente non è amministratore o non è loggato");
        Utente utente = null;
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("utente")).thenReturn(utente);
        PrintWriter printWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);
        servlet.doPost(request, response);
    }





}
