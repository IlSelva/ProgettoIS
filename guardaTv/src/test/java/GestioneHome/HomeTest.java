package GestioneHome;

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
import unisa.is.guardatv.controller.servlet.GestioneHome.HomeServlet;


import java.io.PrintWriter;

public class HomeTest {
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    private HomeServlet servlet = new HomeServlet();
    private HttpServletRequest request;
    private HttpServletResponse response;


    @Before
    public void inizializzaMock() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

    }


    @Test
    public void testHome() throws Exception {
        PrintWriter printWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);
        RequestDispatcher rd = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher(eq("WEB-INF/jsp/index.jsp"))).thenReturn(rd);
        servlet.doPost(request, response);
    }





}
