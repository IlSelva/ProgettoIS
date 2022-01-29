

import static org.junit.Assert.*;

import org.junit.Test;

import unisa.is.guardatv.StorageLayer.*;

import java.sql.Date;
import java.util.Collection;

public class GenereTest {


    private GenereDAO genereDAO = new GenereDAO();

    @Test
    public void testDoRetrieveByName() throws Exception {
        Genere x = new Genere();
        x.setNome(g1);
        genereDAO.doSave(x);
        assertNotNull(genereDAO.doRetrieveByName(g1));
        assertNull(genereDAO.doRetrieveByName(g1));
    }

    private String g1 = "Drammatico";
}
