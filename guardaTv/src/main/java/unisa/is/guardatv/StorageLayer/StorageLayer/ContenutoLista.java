package StorageLayer;

/**
 * Questa classe modella il concetto "ContenutoLista" di Contenuti appartenenti alla lista di uno specifico utente all'interno del sistema.
 *
 * @author Niccolo' Cacace
 * @version 0.1
 */

public class ContenutoLista {

    private String listaNome;
    private String listaUtente;
    private String contenuto;

    /**
     * Ritorna il nome della Lista
     *
     * @return      il nome della Lista
     */
    public String getListaNome() {
        return listaNome;
    }

    /**
     * Setta il nome della Lista
     *
     * @param  listaNome  il nome della Lista
     */
    public void setListaNome(String listaNome) {
        this.listaNome = listaNome;
    }

    /**
     * Ritorna il nome del'utente propietario della Lista
     *
     * @return      il nome del'utente propietario della Lista
     */
    public String getListaUtente() {
        return listaUtente;
    }

    /**
     * Setta il nome del'utente propietario della Lista
     *
     * @param  listaUtente  il nome del'utente propietario della Lista
     */
    public void setListaUtente(String listaUtente) {
        this.listaUtente = listaUtente;
    }

    /**
     * Ritorna l'identificativo ufficiale ISAN del Contenuto appartenente alla Lista
     *
     * @return      l'identificativo ufficiale ISAN del Contenuto appartenente alla Lista
     */
    public String getContenuto() {
        return contenuto;
    }

    /**
     * Setta l'identificativo ufficiale ISAN del Contenuto appartenente alla Lista
     *
     * @param  contenuto  l'identificativo ufficiale ISAN del Contenuto appartenente alla Lista
     */
    public void setContenuto(String contenuto) {
        this.contenuto = contenuto;
    }
}
