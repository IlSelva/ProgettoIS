

import static org.junit.Assert.*;

import org.junit.Test;

import unisa.is.guardatv.StorageLayer.*;


public class RecensioneTest {

    private RecensioneDAO recensioneDAO = new RecensioneDAO();

    @Test
    public void testSaveRetrieveById() throws Exception {
        Recensione x = new Recensione();
        x.setContenuto(id2);
        recensioneDAO.doSave(x);
        assertNotNull(recensioneDAO.doRetrieveByUtente("mail@mail.it", 0,100));
        assertNull(recensioneDAO.doRetrieveByUtente("mail@mail.it", 0,100));
    }

    @Test
    private Recensione testCreaRecensione() throws Exception {
        Recensione x = new Recensione();
        x.setContenuto(id1);
        x.setDescrizione("Bel film");
        x.setPunteggio(3);
        x.setUtente("mailBe@gmail.com");
        return x;
    }

    @Test
    public void testUpdate() throws Exception {
        Recensione x = new Recensione();
        recensioneDAO.doSave(x);
        x.setContenuto(id2);
        recensioneDAO.doUpdate(x);
        assertNull(recensioneDAO.doRetrieveByContenuto(id2, 0, 100));
        assertNotNull(recensioneDAO.doRetrieveByContenuto(id2, 0,100));
        recensioneDAO.doDelete("mailbrutta@gmail.it", id2);
    }


    private String id1 = "0000-0002-6509-0000-0-0000-0008-O";
    private String id2 = "0000-0006-13A9-001F-3-0000-0000-S";
    private String id3 = "0000-0004-5D0D-0000-S-0000-R";

}
