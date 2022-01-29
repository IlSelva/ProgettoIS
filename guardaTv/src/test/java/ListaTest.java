

import static org.junit.Assert.*;

import org.junit.Test;

import unisa.is.guardatv.StorageLayer.Lista;
import unisa.is.guardatv.StorageLayer.ListaDAO;

public class ListaTest {


    private ListaDAO listaDAO = new ListaDAO();

    @Test
    public void testSaveRetrieveByUtente() throws Exception {
        Lista x = new Lista();
        x.setUtente(u1);
        listaDAO.DoSave(x);
        assertNotNull(listaDAO.doRetrieveByUtente(u1, 0,100));
        assertNull(listaDAO.doRetrieveByUtente(u1, 0,1000));
    }

    private String u1 = "marioRossi@gmail.com";



}
