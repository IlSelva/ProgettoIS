

import static org.junit.Assert.*;

import org.junit.Test;

import unisa.is.guardatv.StorageLayer.Utente;
import unisa.is.guardatv.StorageLayer.UtenteDAO;

import java.sql.Date;
import java.util.Collection;

public class UtenteTest {


    private UtenteDAO utenteDAO = new UtenteDAO();

    @Test
    public void testSaveRetrieveByEmail() throws Exception {
        Utente x = new Utente();
        x.setEmail(email1);
        utenteDAO.doSave(x);
        assertNotNull(utenteDAO.doRetrieveByEmail(email1));
        assertNull(utenteDAO.doRetrieveByEmail(email1));
    }

    @Test
    public void testUpdate() throws Exception {
        Utente x = new Utente();
        utenteDAO.doSave(x);
        x.setEmail(email2);
        utenteDAO.doUpdate(x);
        assertNull(utenteDAO.doRetrieveByEmail(email1));
        assertNotNull(utenteDAO.doRetrieveByEmail(email2));
        utenteDAO.doDelete(email2);
    }

    @Test
    public void testDoRetrieveAll() throws Exception {
        Utente x = new Utente();
        Utente y = new Utente();
        Utente z = new Utente();
        x.setEmail(email1);
        y.setEmail(email2);
        z.setEmail(email3);
        utenteDAO.doSave(x);
        utenteDAO.doSave(y);
        utenteDAO.doSave(z);

        Collection<Utente> coll = utenteDAO.doRetrieveAll(0, 100);
        assertNotNull(coll);
        assertTrue(coll.size() >= 3);
        utenteDAO.doDelete(email1);
        utenteDAO.doDelete(email2);
        utenteDAO.doDelete(email3);
    }
    @Test
    private Utente creaUtente(String key) {
        Utente x = new Utente();
        x.setEmail(key);
        x.setPasswordhash("password");
        x.setUsername("marcoRossi");
        Date d = new Date(11,12,2001);
        x.setDataDiNascita(d);
        x.setAdministrator(false);
        x.setSalt("afngohhw31");
        return x;
    }





    private String email1 = "marioRossi@gmail.com";
    private String email2 = "lucabianci@email.it";
    private String email3 = "luigibruno@hotmail.com";


}
