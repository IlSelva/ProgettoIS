package unisa.is.guardatv.controller.servlet;

import unisa.is.guardatv.StorageLayer.Utente;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class MyServletException extends ServletException {
    private static final long serialVersionUID = 6495284345029379282L;

    public MyServletException() {
        super();
    }

    public MyServletException(String message, Throwable rootCause) {
        super(message, rootCause);
    }

    public MyServletException(String message) {
        super(message);
    }

    public MyServletException(Throwable rootCause) {
        super(rootCause);
    }

    public static void checkAdmin(HttpServletRequest request) throws MyServletException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        if (utente == null || !utente.getAdministrator()) {
            throw new MyServletException("Utente non autorizzato");
        }
    }

}
