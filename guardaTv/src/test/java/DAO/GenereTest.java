package DAO;

import static org.junit.Assert.*;

import org.junit.Test;

import unisa.is.guardatv.StorageLayer.*;


public class GenereTest {


    private GenereDAO genereDAO = new GenereDAO();

    @Test
    public void testDoRetrieveByName() throws Exception {
        Genere x = new Genere();
        x.setNome(g1);
        genereDAO.doSave(x);
        assertNotNull(genereDAO.doRetrieveByName(g1));

    }

    @Test
    public void testDoRetrieveAll() throws Exception {
        Genere x = new Genere();
        x.setNome(g2);
        genereDAO.doSave(x);
        assertNotNull(genereDAO.doRetrieveAll());

    }

    private String g1 = "Sku123";
    private String g2 = "Sku2";
}
