package unisa.is.guardatv.StorageLayer;

/**
 * Questa classe modella il concetto di "Recensione" all'interno del sistema.
 *
 * @author Niccolo' Cacace
 * @version 0.1
 */
public class Recensione {

    private String Utente;
    private String Contenuto;
    private int punteggio;
    private String descrizione;

    /**
     * Ritorna il punteggio della Recensione
     *
     * @return     il punteggio della Recensione
     */
    public int getPunteggio() {
        return punteggio;
    }

    /**
     * Setta il punteggio della Recensione
     *
     * @param  punteggio  il punteggio della Recensione  da settare
     */
    public void setPunteggio(int punteggio) {
        this.punteggio = punteggio;
    }

    /**
     * Ritorna la descrizione della Recensione
     *
     * @return      la descrizione della Recensione
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Setta la descrizione della Recensione
     *
     * @param  descrizione  la descrizione della Recensione settare
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    /**
     * Ritorna l'id dell'Utente propieteario della Recensione
     *
     * @return      l'id dell'Utente propieteario della Recensione
     */
    public String getUtente() {
        return Utente;
    }

    /**
     * Setta l'id dell'Utente propieteario della Recensione
     *
     * @param  utente  l'id dell'Utente propieteario della Recensione
     */
    public void setUtente(String utente) {
        Utente = utente;
    }

    /**
     * Ritorna il Contenuto contenente la Recensione
     *
     * @return      il Contenuto contenente la Recensione
     */
    public String getContenuto() {
        return Contenuto;
    }

    /**
     * Setta il Contenuto contenente la Recensione
     *
     * @param  contenuto  l'identificativo ufficiale ISAN del Contenuto contenente la Recensione
     */
    public void setContenuto(String contenuto) {
        Contenuto = contenuto;
    }

}