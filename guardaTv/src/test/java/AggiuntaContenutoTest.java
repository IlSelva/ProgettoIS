import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import unisa.is.guardatv.StorageLayer.Contenuto;
import unisa.is.guardatv.StorageLayer.Utente;
import unisa.is.guardatv.controller.servlet.MyServletException;
import unisa.is.guardatv.controller.servlet.gestioneAmministratore.AggiuntaContenutoServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.PrintWriter;
import java.util.stream.IntStream;


import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AggiuntaContenutoTest {

    private final AggiuntaContenutoServlet aggiuntaContenutoServlet = new AggiuntaContenutoServlet();
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private Utente utente;

    @Before
    public void inizializzaMock() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        utente = new Utente();
        utente.setEmail("achilleprezioso@gmail.com");
        utente.setUsername("Achille");
        utente.setAdministrator(true);

    }

    @Test
    public void testIdVuoto() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("utente")).thenReturn(utente);
        exceptionRule.expect(MyServletException.class);
        exceptionRule.expectMessage("ID non valido.");
        when(request.getParameter("contenutoId")).thenReturn("");
        PrintWriter printWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);
        aggiuntaContenutoServlet.doPost(request, response);
    }


    @Test
    public void testIdTroppoLungo() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("utente")).thenReturn(utente);
        exceptionRule.expect(MyServletException.class);
        exceptionRule.expectMessage("ID non valido.");

        StringBuilder stringBuilder = new StringBuilder();
        IntStream.range(0,60).forEach((num) -> stringBuilder.append("0"));
        String id = stringBuilder.toString();

        when(request.getParameter("contenutoId")).thenReturn(id);
        PrintWriter printWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);
        aggiuntaContenutoServlet.doPost(request, response);
    }



    @Test
    public void testIdNonCorretto() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("utente")).thenReturn(utente);
        exceptionRule.expect(MyServletException.class);
        exceptionRule.expectMessage("ID non valido.");

        String id = "0000000000023";

        when(request.getParameter("contenutoId")).thenReturn(id);
        PrintWriter printWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);
        aggiuntaContenutoServlet.doPost(request, response);

    }


    @Test
    public void testTitoloVuoto() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("utente")).thenReturn(utente);
        exceptionRule.expect(MyServletException.class);
        exceptionRule.expectMessage("Titolo non valido.");
        when(request.getParameter("contenutoId")).thenReturn("0000-1023-1347");
        when(request.getParameter("titolo")).thenReturn("");
        PrintWriter printWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);
        aggiuntaContenutoServlet.doPost(request, response);
    }


    @Test
    public void testTitoloTroppoLungo() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("utente")).thenReturn(utente);
        exceptionRule.expect(MyServletException.class);
        exceptionRule.expectMessage("Titolo non valido.");

        StringBuilder stringBuilder = new StringBuilder();
        IntStream.range(0, 60).forEach((num) -> stringBuilder.append("A"));
        String titolo = stringBuilder.toString();

        when(request.getParameter("contenutoId")).thenReturn("0000-1023-1347");
        when(request.getParameter("titolo")).thenReturn(titolo);
        PrintWriter printWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);
        aggiuntaContenutoServlet.doPost(request, response);
    }

    @Test
    public void testDescrizioneVuota() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("utente")).thenReturn(utente);
        exceptionRule.expect(MyServletException.class);
        exceptionRule.expectMessage("Descrizione non valida.");
        when(request.getParameter("contenutoId")).thenReturn("0000-1023-1347");
        when(request.getParameter("titolo")).thenReturn("La vita é bella");
        when(request.getParameter("descrizione")).thenReturn("");
        PrintWriter printWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);
        aggiuntaContenutoServlet.doPost(request, response);
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

        when(request.getParameter("contenutoId")).thenReturn("0000-1023-1347");
        when(request.getParameter("titolo")).thenReturn("La vita é bella");
        when(request.getParameter("descrizione")).thenReturn(descrizione);
        PrintWriter printWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);
        aggiuntaContenutoServlet.doPost(request, response);
    }


    @Test
    public void testGenereVuoto() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("utente")).thenReturn(utente);
        exceptionRule.expect(MyServletException.class);
        exceptionRule.expectMessage("Genere non valido.");
        when(request.getParameter("contenutoId")).thenReturn("0000-1023-1347");
        when(request.getParameter("titolo")).thenReturn("La vita é bella");
        when(request.getParameter("descrizione")).thenReturn("Film bello ambientato negli anni 30");
        when(request.getParameter("genere")).thenReturn("");
        PrintWriter printWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);
        aggiuntaContenutoServlet.doPost(request, response);
    }


    @Test
    public void testGenereTroppoLungo() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("utente")).thenReturn(utente);
        exceptionRule.expect(MyServletException.class);
        exceptionRule.expectMessage("Genere non valido.");

        StringBuilder stringBuilder = new StringBuilder();
        IntStream.range(0, 60).forEach((num) -> stringBuilder.append("A"));
        String genere = stringBuilder.toString();

        when(request.getParameter("contenutoId")).thenReturn("0000-1023-1347");
        when(request.getParameter("titolo")).thenReturn("La vita é bella");
        when(request.getParameter("descrizione")).thenReturn("Film bello ambientato negli anni 30");
        when(request.getParameter("genere")).thenReturn(genere);
        PrintWriter printWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);
        aggiuntaContenutoServlet.doPost(request, response);
    }


   @Test
   public void testGenereNonCorretto() throws Exception {
       when(request.getSession()).thenReturn(session);
       when(request.getSession().getAttribute("utente")).thenReturn(utente);
        exceptionRule.expect(MyServletException.class);
        exceptionRule.expectMessage("Genere non valido.");

        String genere = "ROM4NTIC0";

        when(request.getParameter("contenutoId")).thenReturn("0000-1023-1347");
        when(request.getParameter("titolo")).thenReturn("La vita é bella");
        when(request.getParameter("descrizione")).thenReturn("Film bello ambientato negli anni 30");
        when(request.getParameter("genere")).thenReturn(genere);
        PrintWriter printWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);
        aggiuntaContenutoServlet.doPost(request, response);

    }


    @Test
    public void testRegistaVuoto() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("utente")).thenReturn(utente);
        exceptionRule.expect(MyServletException.class);
        exceptionRule.expectMessage("Regista non valido.");
        when(request.getParameter("contenutoId")).thenReturn("0000-1023-1347");
        when(request.getParameter("titolo")).thenReturn("La vita é bella");
        when(request.getParameter("descrizione")).thenReturn("Film bello ambientato negli anni 30");
        when(request.getParameter("genere")).thenReturn("Romantico");
        when(request.getParameter("regista")).thenReturn("");
        PrintWriter printWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);
        aggiuntaContenutoServlet.doPost(request, response);
    }

    @Test
    public void testRegistaTroppoLungo() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("utente")).thenReturn(utente);
        exceptionRule.expect(MyServletException.class);
        exceptionRule.expectMessage("Regista non valido.");

        StringBuilder stringBuilder = new StringBuilder();
        IntStream.range(0, 60).forEach((num) -> stringBuilder.append("A"));
        String regista = stringBuilder.toString();

        when(request.getParameter("contenutoId")).thenReturn("0000-1023-1347");
        when(request.getParameter("titolo")).thenReturn("La vita é bella");
        when(request.getParameter("descrizione")).thenReturn("Film bello ambientato negli anni 30");
        when(request.getParameter("genere")).thenReturn("Romantico");
        when(request.getParameter("regista")).thenReturn(regista);
        PrintWriter printWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);
        aggiuntaContenutoServlet.doPost(request, response);
    }


    @Test
    public void testDurataNonCorretta() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("utente")).thenReturn(utente);
        exceptionRule.expect(MyServletException.class);
        exceptionRule.expectMessage("Durata non valida.");

       int durata = -1;

        when(request.getParameter("contenutoId")).thenReturn("0000-1023-1347");
        when(request.getParameter("titolo")).thenReturn("La vita é bella");
        when(request.getParameter("descrizione")).thenReturn("Film bello ambientato negli anni 30");
        when(request.getParameter("genere")).thenReturn("Romantico");
        when(request.getParameter("regista")).thenReturn("Roberto Benigni");
        when(request.getParameter("durata")).thenReturn(String.valueOf(durata));
        PrintWriter printWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);
        aggiuntaContenutoServlet.doPost(request, response);

    }




    @Test
    public void testDataDiUscitaNonCorretta() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("utente")).thenReturn(utente);
        exceptionRule.expect(MyServletException.class);
        exceptionRule.expectMessage("Data di uscita non valida.");

        String dataDiUscita = "1997/12/18";

        when(request.getParameter("contenutoId")).thenReturn("0000-1023-1347");
        when(request.getParameter("titolo")).thenReturn("La vita é bella");
        when(request.getParameter("descrizione")).thenReturn("Film bello ambientato negli anni 30");
        when(request.getParameter("genere")).thenReturn("Romantico");
        when(request.getParameter("regista")).thenReturn("Roberto Benigni");
        when(request.getParameter("durata")).thenReturn("124");
        when(request.getParameter("dataDiUscita")).thenReturn(dataDiUscita);
        PrintWriter printWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);
        aggiuntaContenutoServlet.doPost(request, response);

    }

    @Test
    public void testAggiuntaContenutoServlet() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("utente")).thenReturn(utente);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("contenutoId")).thenReturn("0000-1023-1341");
        when(request.getParameter("titolo")).thenReturn("La vita é bella");
        when(request.getParameter("descrizione")).thenReturn("Film bello ambientato negli anni 30");
        when(request.getParameter("genere")).thenReturn("Romantico");
        when(request.getParameter("regista")).thenReturn("Roberto Benigni");
        when(request.getParameter("durata")).thenReturn("124");
        when(request.getParameter("dataDiUscita")).thenReturn("1996-12-18");
        when(request.getParameter("categoria")).thenReturn("film");
        PrintWriter printWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);
        RequestDispatcher rd = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher(eq("WEB-INF/jsp/profilo.jsp"))).thenReturn(rd);
        aggiuntaContenutoServlet.doPost(request, response);
    }


}
