
import static org.junit.Assert.*;

import org.junit.Test;

import unisa.is.guardatv.StorageLayer.Tipologia;
import unisa.is.guardatv.StorageLayer.TipologiaDAO;

public class TipologiaTest {


    private TipologiaDAO tipologiaDAO = new TipologiaDAO();

    @Test
    public void testSaveAllGeneriByContenuto() throws Exception {
        Tipologia x = new Tipologia();
        x.setContenuto("0000-0002-1641-0012-T-0000-0000-O");
        x.setGenere("Azione");
        tipologiaDAO.doSave(x);
        assertNotNull(tipologiaDAO.allGeneriByContenuto("0000-0002-1641-0012-T-0000-0000-O"));
        assertNull(tipologiaDAO.allGeneriByContenuto("0000-0002-1641-0012-T-0000-0000-O"));
    }

    private String g1 = "Azione";

}
