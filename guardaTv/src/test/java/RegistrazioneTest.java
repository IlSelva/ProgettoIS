
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;



import unisa.is.guardatv.controller.servlet.GestioneUtente.RegistrazioneServlet;
import unisa.is.guardatv.controller.servlet.MyServletException;

public class RegistrazioneTest{

    private RegistrazioneServlet servlet = new RegistrazioneServlet();
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
    public void test() throws Exception {
        when(request.getParameter("registrazione")).thenReturn(null);
        PrintWriter MyWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(MyWriter);
        servlet.doPost(request, response);
        verify(MyWriter).write(BADREQUEST_MESS);
    }

    @Test
    public void testEmail() throws Exception {
        when(request.getParameter("registrazione")).thenReturn("registrazione");
        when(request.getParameter("email")).thenReturn(null);
        PrintWriter MyWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(MyWriter);
        servlet.doPost(request, response);
        verify(MyWriter).write(BADREQUEST_MESS);
    }


    @Test
    public void testPassword() throws Exception {
        when(request.getParameter("registrazione")).thenReturn("registrazione");
        when(request.getParameter("email")).thenReturn("marioRossi@gmail.com");
        when(request.getParameter("password")).thenReturn(null);
        PrintWriter MyWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(MyWriter);
        servlet.doPost(request, response);
        verify(MyWriter).write(BADREQUEST_MESS);
    }

    @Test
    public void testPasswordConferma() throws Exception {
        when(request.getParameter("registrazione")).thenReturn("registrazione");
        when(request.getParameter("email")).thenReturn("marioRossi@gmail.com");
        when(request.getParameter("password")).thenReturn("MarioRossi2000");
        when(request.getParameter("passwordConferma")).thenReturn(null);
        PrintWriter MyWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(MyWriter);
        servlet.doPost(request, response);
        verify(MyWriter).write(BADREQUEST_MESS);
    }

    @Test
    public void testUsername() throws Exception {
        when(request.getParameter("registrazione")).thenReturn("registrazione");
        when(request.getParameter("email")).thenReturn("marioRossi@gmail.com");
        when(request.getParameter("password")).thenReturn("MarioRossi2000");
        when(request.getParameter("passwordConferma")).thenReturn("MarioRossi2000");
        when(request.getParameter("nome")).thenReturn(null);
        PrintWriter MyWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(MyWriter);
        servlet.doPost(request, response);
        verify(MyWriter).write(BADREQUEST_MESS);
    }

    @Test
    public void testDataDiNascita() throws Exception {
        when(request.getParameter("registrazione")).thenReturn("registrazione");
        when(request.getParameter("email")).thenReturn("marioRossi@gmail.com");
        when(request.getParameter("password")).thenReturn("MarioRossi2000");
        when(request.getParameter("passwordConferma")).thenReturn("MarioRossi2000");
        when(request.getParameter("nome")).thenReturn("MarioRossi");
        when(request.getParameter("dataDiNascita")).thenReturn("null");
        PrintWriter MyWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(MyWriter);
        servlet.doPost(request, response);
        verify(MyWriter).write(BADREQUEST_MESS);
    }

    @Test
    public void testRegistrazione() throws Exception {
        when(request.getParameter("registrazione")).thenReturn("registrazione");
        when(request.getParameter("email")).thenReturn("marioRossi@gmail.com");
        when(request.getParameter("password")).thenReturn("MarioRossi2000");
        when(request.getParameter("passwordConferma")).thenReturn("MarioRossi2000");
        when(request.getParameter("nome")).thenReturn("MarioRossi");
        when(request.getParameter("dataDiNascita")).thenReturn("12-12-2000");
        when(request.getSession()).thenReturn(session);
        PrintWriter MyWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(MyWriter);
        servlet.doPost(request, response);
        verify(MyWriter).write(SUCCESS_MESS);
    }













    /** messaggio di errore inviato in caso di bad request. **/
    private static final String BADREQUEST_MESS = "L'operazione richiesta non e' valida.";

    /** messaggio restituito in caso di successo dell'operazione. **/
    private static final String SUCCESS_MESS = "L'operazione e' avvenuta correttamente.";
}
