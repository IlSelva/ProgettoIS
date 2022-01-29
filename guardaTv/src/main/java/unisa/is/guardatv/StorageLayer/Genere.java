package unisa.is.guardatv.StorageLayer;


/**
 * Questa classe modella il concetto di "Genere" all'interno del sistema.
 *
 * @author Niccolo' Cacace
 * @version 0.1
 */

public class Genere {
    private String nome;

    /**
     * Ritorna il nome del Genere
     *
     * @return      il nome del Genere
     */
    public String getNome(){
        return nome;
    }

    /**
     * Setta il nome del Genere
     *
     * @param  nome  il nome del Genere
     */
    public void setNome(String nome){
        this.nome = nome;
    }


}

