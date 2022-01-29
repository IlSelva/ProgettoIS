

import static org.junit.Assert.*;

import org.junit.Test;
import unisa.is.guardatv.StorageLayer.*;
import java.util.Collection;

public class ContenutoListaTest {


    private ContenutoListaDAO contenutoListaDAO = new ContenutoListaDAO();

    @Test
    public void testAllContenutiByLista() throws Exception {
        ContenutoLista x = new ContenutoLista();
        x.setListaNome("Lista brutta");
        x.setListaUtente("mario@mail.it");
        x.setContenuto("0000-0002-1641-0012-T-0000-0000-O");
        Collection<Contenuto> coll = contenutoListaDAO.allContenutiByLista("Lista brutta", "mario@mail.it");
        assertNotNull(coll);
        assertTrue(coll.size() >= 3);
        contenutoListaDAO.RemoveContenuto("Lista brutta", "mario@mail.it", "0000-0002-1641-0012-T-0000-0000-O");
    }
}
