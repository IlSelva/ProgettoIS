

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;


import unisa.is.guardatv.controller.servlet.GestioneUtente.LoginServlet;

public class LoginTest {

    private LoginServlet servlet = new LoginServlet();
    private HttpServletRequest request;
    private HttpServletResponse response;

    @Before
    public void inizializzaMock() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }


    @Test
    public void testUtente() throws Exception {
		when(request.getParameter("email")).thenReturn("emailCiao@email.com");
		when(request.getParameter("password")).thenReturn("passwordCiao");
		servlet.doPost(request, response);
		verify(response).sendRedirect("index.jsp");
		}


}
