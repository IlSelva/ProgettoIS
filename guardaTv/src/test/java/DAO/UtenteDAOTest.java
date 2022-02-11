package DAO;

import org.junit.Test;
import unisa.is.guardatv.StorageLayer.Utente;
import unisa.is.guardatv.StorageLayer.UtenteDAO;

import java.sql.Date;
import java.util.Collection;

import static org.junit.Assert.*;

public class UtenteDAOTest {


    private final UtenteDAO utenteDAO = new UtenteDAO();

    @Test
    public void testSaveRetrieveByEmail() throws Exception {
        Utente x = new Utente();
        x.setEmail(email1);
        x.setPasswordhash("cubcabDhfy433");
        x.setSalt("grgsfds");
        x.setAdministrator(false);
        Date d = new Date(2000,12,12);
        x.setDataDiNascita(d);
        x.setUsername("Marosss");
        utenteDAO.doSave(x);
        assertNotNull(utenteDAO.doRetrieveByEmail(email1));
        //assertNull(utenteDAO.doRetrieveByEmail(email1));
    }


    @Test
    public void testDoRetrieveAll() throws Exception {
        Utente x = new Utente();
        x.setEmail(email1);
        x.setPasswordhash("cubcabDhfy433");
        x.setSalt("grgsfds");
        x.setAdministrator(false);
        Date d = new Date(2000,12,12);
        x.setDataDiNascita(d);
        x.setUsername("Marosss");
        utenteDAO.doSave(x);
        Collection<Utente> coll = utenteDAO.doRetrieveAll(0, 100);
        assertNotNull(coll);
        assertTrue(coll.size() >= 1);
        UtenteDAO.doDelete(email1);
    }


    private final String email1 = "mariene@gmail.com";
    private final String email2 = "lucabianci@email.it";
    private final String email3 = "luigibruno@hotmail.com";


}
