import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import unisa.is.guardatv.StorageLayer.Utente;
import unisa.is.guardatv.controller.servlet.MyServletException;
import unisa.is.guardatv.controller.servlet.gestioneLista.CreazioneListaServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.stream.IntStream;


import static org.mockito.Mockito.*;

public class CreazioneListaTest {

    private CreazioneListaServlet creazioneListaServlet = new CreazioneListaServlet();
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    Utente utente;

    @Before
    public void inizializzaMock() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        utente = new Utente();
        utente.setEmail("achilleprezioso@gmail.com");
        utente.setUsername("Achille");
    }

    @Test
    public void testNomeVuoto() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("utente")).thenReturn(utente);
        exceptionRule.expect(MyServletException.class);
        exceptionRule.expectMessage("Nome non valido.");
        when(request.getParameter("nomelista")).thenReturn("");
        PrintWriter printWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);
        creazioneListaServlet.doPost(request, response);
    }


    @Test
    public void testNomeTroppoLungo() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("utente")).thenReturn(utente);
        exceptionRule.expect(MyServletException.class);
        exceptionRule.expectMessage("Nome non valido.");

        StringBuilder stringBuilder = new StringBuilder();
        IntStream.range(0, 60).forEach((num) -> stringBuilder.append("A"));
        String nome = stringBuilder.toString();

        when(request.getParameter("nomelista")).thenReturn(nome);
        PrintWriter printWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);
        creazioneListaServlet.doPost(request, response);
    }

    @Test
    public void testNomeNonCorretto() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("utente")).thenReturn(utente);
        exceptionRule.expect(MyServletException.class);
        exceptionRule.expectMessage("Nome non valido.");

        String nome = "N!ccol*,’s";

        when(request.getParameter("nomelista")).thenReturn(nome);
        PrintWriter printWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);
        creazioneListaServlet.doPost(request, response);
    }

    @Test
    public void testDescrizioneTroppoLunga() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("utente")).thenReturn(utente);
        exceptionRule.expect(MyServletException.class);
        exceptionRule.expectMessage("Descrizione non valida.");

        StringBuilder stringBuilder = new StringBuilder();
        IntStream.range(0, 300).forEach((num) -> stringBuilder.append("A"));
        String descrizione = stringBuilder.toString();

        when(request.getParameter("nomelista")).thenReturn("Preferiti");
        when(request.getParameter("descrizione")).thenReturn(descrizione);
        PrintWriter printWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);
        creazioneListaServlet.doPost(request, response);

    }


    @Test
    public void testDescrizioneNonCorretta() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("utente")).thenReturn(utente);
        exceptionRule.expect(MyServletException.class);
        exceptionRule.expectMessage("Descrizione non valida.");

        String descrizione = "I mi€! Pr£f&r?t%";

        when(request.getParameter("nomelista")).thenReturn("Preferiti");
        when(request.getParameter("descrizione")).thenReturn(descrizione);
        PrintWriter printWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);
        creazioneListaServlet.doPost(request, response);

    }


    @Test
    public void testCreazioneLista() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("utente")).thenReturn(utente);
        when(request.getParameter("nomelista")).thenReturn("preferiti10");
        when(request.getParameter("descrizione")).thenReturn("I miei preferiti");
        PrintWriter printWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);
        RequestDispatcher rd = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher(eq("WEB-INF/jsp/profilo.jsp"))).thenReturn(rd);
        creazioneListaServlet.doPost(request, response);
    }
}










