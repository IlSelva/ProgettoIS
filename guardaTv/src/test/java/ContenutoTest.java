

import static org.junit.Assert.*;

import org.junit.Test;
import unisa.is.guardatv.StorageLayer.Contenuto;
import unisa.is.guardatv.StorageLayer.ContenutoDAO;
import java.util.Collection;

public class ContenutoTest {



    private ContenutoDAO contenutoDAO = new ContenutoDAO();

    @Test
    public void testSaveRetrieveById() throws Exception {
        Contenuto x = new Contenuto();
        x.setId(id1);
        contenutoDAO.doSave(x);
        assertNotNull(contenutoDAO.doRetrieveById(id1));
        assertNull(contenutoDAO.doRetrieveById(id1));
    }

    @Test
    public void testUpdate() throws Exception {
        Contenuto x = new Contenuto();
        contenutoDAO.doSave(x);
        x.setId(id2);
        contenutoDAO.doUpdate(x);
        assertNull(contenutoDAO.doRetrieveById(id1));
        assertNotNull(contenutoDAO.doRetrieveById(id2));
        contenutoDAO.doDelete(id2);
    }

    @Test
    public void testDoRetrieveAll() throws Exception {
        Contenuto x = new Contenuto();
        Contenuto y = new Contenuto();
        Contenuto z = new Contenuto();
        x.setId(id1);
        y.setId(id2);
        z.setId(id3);
        contenutoDAO.doSave(x);
        contenutoDAO.doSave(y);
        contenutoDAO.doSave(z);

        Collection<Contenuto> coll = contenutoDAO.doRetrieveAll(0, 100);
        assertNotNull(coll);
        assertTrue(coll.size() >= 3);
        contenutoDAO.doDelete(id1);
        contenutoDAO.doDelete(id2);
        contenutoDAO.doDelete(id3);
    }

    @Test
    public void testDoRetrieveByTitolo() throws Exception {
        Contenuto x = new Contenuto();
        Contenuto y = new Contenuto();
        Contenuto z = new Contenuto();
        x.setTitolo("Game of Thrones");
        y.setTitolo("Due uomini e mezzo");
        z.setTitolo("Shutter Island");
        contenutoDAO.doSave(x);
        contenutoDAO.doSave(y);
        contenutoDAO.doSave(z);
        Collection<Contenuto> coll = contenutoDAO.doRetrieveByTitolo("Due uomini e mezzo",0, 100);
        assertNotNull(coll);
        assertTrue(coll.size() >= 3);
        contenutoDAO.doDelete(id1);
        contenutoDAO.doDelete(id2);
        contenutoDAO.doDelete(id3);
    }

    private String id1 = "0000-0002-6509-0000-0-0000-0008-O";
    private String id2 = "0000-0006-13A9-001F-3-0000-0000-S";
    private String id3 = "0000-0004-5D0D-0000-S-0000-R";

}
