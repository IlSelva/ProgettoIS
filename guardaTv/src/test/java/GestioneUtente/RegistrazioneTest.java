package GestioneUtente;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Rule;
import org.junit.rules.ExpectedException;
import unisa.is.guardatv.controller.servlet.GestioneUtente.RegistrazioneServlet;
import java.io.PrintWriter;
import java.sql.Date;


import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;



import unisa.is.guardatv.controller.servlet.GestioneUtente.RegistrazioneServlet;
import unisa.is.guardatv.controller.servlet.MyServletException;

public class RegistrazioneTest{
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
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
    public void testEmail() throws Exception {
        exceptionRule.expect(MyServletException.class);
        exceptionRule.expectMessage("E-mail non valida");
        when(request.getParameter("registrazione")).thenReturn("registrazione");
        when(request.getParameter("email")).thenReturn("marioRossi");
        when(request.getParameter("password")).thenReturn("MarioRossi2000");
        when(request.getParameter("passwordConferma")).thenReturn("MarioRossi2000");
        when(request.getParameter("nome")).thenReturn("MarioRossi");
        Date d = new Date(12,12,2000);
        when(request.getParameter("dataDiNascita")).thenReturn(String.valueOf(d));
        when(request.getSession()).thenReturn(session);
        PrintWriter MyWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(MyWriter);
        servlet.doPost(request, response);
    }

    @Test
    public void testPassword() throws Exception {
        exceptionRule.expect(MyServletException.class);
        exceptionRule.expectMessage("Password non valida.");
        when(request.getParameter("registrazione")).thenReturn("registrazione");
        when(request.getParameter("email")).thenReturn("marioRossi@gmail.com");
        when(request.getParameter("password")).thenReturn("Mario");
        when(request.getParameter("passwordConferma")).thenReturn("Mario");
        when(request.getParameter("nome")).thenReturn("MarioRossi");
        Date d = new Date(12,12,2000);
        when(request.getParameter("dataDiNascita")).thenReturn(String.valueOf(d));
        when(request.getSession()).thenReturn(session);
        PrintWriter MyWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(MyWriter);
        servlet.doPost(request, response);
    }

    @Test
    public void testPasswordConferma() throws Exception {
        exceptionRule.expect(MyServletException.class);
        exceptionRule.expectMessage("Password e conferma differenti");
        when(request.getParameter("registrazione")).thenReturn("registrazione");
        when(request.getParameter("email")).thenReturn("marioRossi@gmail.com");
        when(request.getParameter("password")).thenReturn("MarioRossi2000");
        when(request.getParameter("passwordConferma")).thenReturn("MarioRossi200");
        when(request.getParameter("nome")).thenReturn("MarioRossi");
        Date d = new Date(12,12,2000);
        when(request.getParameter("dataDiNascita")).thenReturn(String.valueOf(d));
        when(request.getSession()).thenReturn(session);
        PrintWriter MyWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(MyWriter);
        servlet.doPost(request, response);
    }

    @Test
    public void testUsername() throws Exception {
        exceptionRule.expect(MyServletException.class);
        exceptionRule.expectMessage("Username non valido");
        //when(request.getParameter("registrazione")).thenReturn("registrazione");
        when(request.getParameter("email")).thenReturn("marioRossi@gmail.com");
        when(request.getParameter("password")).thenReturn("MarioRossi2000");
        when(request.getParameter("passwordConferma")).thenReturn("MarioRossi2000");
        when(request.getParameter("nome")).thenReturn("7");
        Date d = new Date(12,12,2000);
        when(request.getParameter("dataDiNascita")).thenReturn(String.valueOf(d));
        when(request.getSession()).thenReturn(session);
        PrintWriter MyWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(MyWriter);
        servlet.doGet(request, response);
    }

    @Test
    public void testRegistrazione() throws Exception {
        when(request.getParameter("email")).thenReturn("maronBel@gmail.com");
        when(request.getParameter("password")).thenReturn("MarioRossi2000");
        when(request.getParameter("passwordConferma")).thenReturn("MarioRossi2000");
        when(request.getParameter("nome")).thenReturn("MarioRossi");
        Date d = new Date(12,12,2007);
        when(request.getParameter("datadinascita")).thenReturn(String.valueOf(d));
        when(request.getSession()).thenReturn(session);
        PrintWriter MyWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(MyWriter);
        RequestDispatcher rd = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher(eq("WEB-INF/jsp/profilo.jsp"))).thenReturn(rd);
        servlet.doGet(request, response);
    }

    /*@Test
    public void testDataDue() throws Exception {
        exceptionRule.expect(MyServletException.class);
        exceptionRule.expectMessage("Devi essere maggiorenne");
        when(request.getParameter("email")).thenReturn("marioo2o@gmail.com");
        when(request.getParameter("password")).thenReturn("MarioRossi2000");
        when(request.getParameter("passwordConferma")).thenReturn("MarioRossi2000");
        when(request.getParameter("nome")).thenReturn("MarioRossi");
        Date d = new Date(12,12,2007);
        when(request.getParameter("datadinascita")).thenReturn(String.valueOf(d));
        when(request.getSession()).thenReturn(session);
        PrintWriter MyWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(MyWriter);
        /*RequestDispatcher rd = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher(eq("WEB-INF/jsp/profilo.jsp"))).thenReturn(rd);
        servlet.doGet(request, response);
    }*/
}
