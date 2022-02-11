package GestioneRecensione;

import static org.mockito.Mockito.*;

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
import unisa.is.guardatv.controller.servlet.GestioneRecensione.AggiuntaRecensioneServlet;
import unisa.is.guardatv.controller.servlet.MyServletException;

import java.io.PrintWriter;
import java.util.stream.IntStream;


public class RecensioneTest {
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    private AggiuntaRecensioneServlet servlet = new AggiuntaRecensioneServlet();
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private Contenuto contenuto;

    @Before
    public void inizializzaMock() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        contenuto = mock(Contenuto.class);
    }


    @Test
    public void testPunteggioVuoto() throws Exception {
        Utente utente = new Utente();
        utente.setEmail("email@gmail.com");
        utente.setUsername("Username");
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("utente")).thenReturn(utente);
        exceptionRule.expect(MyServletException.class);
        exceptionRule.expectMessage("Il punteggio non può essere 0");
        when(request.getParameter("punteggio")).thenReturn(String.valueOf(0));
        when(request.getParameter("descrizione")).thenReturn("Bel film");
        PrintWriter printWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);
        servlet.doPost(request, response);
    }

    @Test
    public void testPunteggioMaggiore() throws Exception {
        Utente utente = new Utente();
        utente.setEmail("email@gmail.com");
        utente.setUsername("Username");
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("utente")).thenReturn(utente);
        exceptionRule.expect(MyServletException.class);
        exceptionRule.expectMessage("Il punteggio non può essere maggiore di 5");
        when(request.getParameter("punteggio")).thenReturn(String.valueOf(6));
        when(request.getParameter("descrizione")).thenReturn("Bel film");
        PrintWriter printWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);
        servlet.doPost(request, response);
    }

    @Test
    public void testDescrizioneTroppoLunga() throws Exception {
        Utente utente = new Utente();
        utente.setEmail("email@gmail.com");
        utente.setUsername("Username");
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("utente")).thenReturn(utente);
        exceptionRule.expect(MyServletException.class);
        StringBuilder stringBuilder = new StringBuilder();
        IntStream.range(0, 256).forEach((num) -> stringBuilder.append("A"));
        String descrizione = stringBuilder.toString();
        exceptionRule.expectMessage("La descrizione supera la lunghezza massima");
        when(request.getParameter("punteggio")).thenReturn(String.valueOf(5));
        when(request.getParameter("descrizione")).thenReturn(descrizione);
        PrintWriter printWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);
        servlet.doPost(request, response);
    }

    @Test
    public void testUtente() throws Exception {
        Utente utente = null;
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("utente")).thenReturn(utente);
        exceptionRule.expect(MyServletException.class);
        exceptionRule.expectMessage("Utente non loggato");
        when(request.getParameter("punteggio")).thenReturn(String.valueOf(4));
        when(request.getParameter("descrizione")).thenReturn("Bel film");
        PrintWriter printWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);
        servlet.doPost(request, response);
    }

}
