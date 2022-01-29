package StorageLayer;

/**
 * Questa classe modella il concetto di "Tipologia" all'interno del sistema.
 *
 * @author Niccolo' Cacace
 * @version 0.1
 */
public class Tipologia {

    private String Contenuto;
    private String Genere;

    /**
     * Ritorna l'identificativo ufficiale ISAN del Contenuto
     *
     * @return      l'identificativo ISAN del Contenuto
     */
    public String getContenuto() {
        return Contenuto;
    }

    /**
     * Setta l'dentificativo ufficiale ISAN del Contenuto
     *
     * @param  contenuto  l'identificativo ufficiale ISAN da settare
     */
    public void setContenuto(String contenuto) {
        Contenuto = contenuto;
    }

    /**
     * Ritorna il nome del Genere
     *
     * @return      il nome del Genere
     */
    public String getGenere() {
        return Genere;
    }

    /**
     * Setta il nome del Genere
     *
     * @param  genere il nome del Genere
     */
    public void setGenere(String genere) {
        Genere = genere;
    }
}