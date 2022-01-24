package unisa.is.guardatv.StorageLayer;


import java.util.Date;

/**
 * Questa classe modella il concetto di "Contenuto" all'interno del sistema.
 *
 * @author Niccolo' Cacace
 * @version 0.1
 */

public class Contenuto {

    private String id;
    private String titolo;
    private String descrizione;
    private String regista;
    private int durata;
    private Date dataDiUscita;
    private String immagineDelContenuto;
    private String videoTrailer;
    private boolean film;
    private int stagioni;
    private int puntate;

    /**
     * Ritorna l'identificativo ufficiale ISAN del Contenuto
     *
     * @return      l'identificativo ISAN del Contenuto
     */
    public String getId() {
        return this.id;
    }

    /**
     * Setta l'dentificativo ufficiale ISAN del Contenuto
     *
     * @param  id  l'identificativo ufficiale ISAN da settare
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Ritorna il titolo del Contenuto
     *
     * @return      il titolo del Contenuto
     */
    public String getTitolo() {
        return titolo;
    }

    /**
     * Setta il titolo del Contenuto
     *
     * @param  titolo  il titolo del Contenuto da settare
     */
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    /**
     * Ritorna la descrizione del Contenuto
     *
     * @return      la descrizione del Contenuto
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Setta la descrizione del Contenuto
     *
     * @param  descrizione  la descrizione del Contenuto da settare
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    /**
     * Ritorna il regista del Contenuto
     *
     * @return      il regista del Contenuto
     */
    public String getRegista() {
        return regista;
    }

    /**
     * Setta il regista del Contenuto
     *
     * @param  regista  il regista del Contenuto da settare
     */
    public void setRegista(String regista) {
        this.regista = regista;
    }

    /**
     * Ritorna la durata del Contenuto
     *
     * @return      la durata del Contenuto
     */
    public int getDurata() {
        return durata;
    }

    /**
     * Setta la durata del Contenuto
     *
     * @param  durata  la durata del Contenuto da settare
     */
    public void setDurata(int durata) {
        this.durata = durata;
    }

    /**
     * Ritorna la data di uscita del Contenuto
     *
     * @return      la data di uscita del Contenuto
     */
    public Date getDataDiUscita() {
        return dataDiUscita;
    }

    /**
     * Setta la data di uscita del Contenuto
     *
     * @param  dataDiUscita  la data di uscita del Contenuto da settare
     */
    public void setDataDiUscita(Date dataDiUscita) {
        this.dataDiUscita = dataDiUscita;
    }

    /**
     * Ritorna l'immagine del Contenuto
     *
     * @return      l'immagine del Contenuto
     */
    public String getImmagineDelContenuto() {
        return immagineDelContenuto;
    }

    /**
     * Setta l'immagine del Contenuto
     *
     * @param  immagineDelContenuto l'immagine del Contenuto da settare
     */
    public void setImmagineDelContenuto(String immagineDelContenuto) { this.immagineDelContenuto = immagineDelContenuto; }

    /**
     * Ritorna il video trailer del Contenuto
     *
     * @return      il video trailer del Contenuto
     */
    public String getVideoTrailer() {
        return videoTrailer;
    }

    /**
     * Setta il video trailer del Contenuto
     *
     * @param  videoTrailer il video trailer del Contenuto da settare
     */
    public void setVideoTrailer(String videoTrailer) {
        this.videoTrailer = videoTrailer;
    }

    /**
     * Ritorna true se il Contenuto è un film , ritorna false se il Contenuto è una serie
     *
     * @return      true se il Contenuto è un film , ritorna false se il Contenuto è una serie
     */
    public boolean isFilm() {
        return film;
    }

    /**
     * Setta true se il Contenuto è un film , ritorna false se il Contenuto è una serie
     *
     * @param  film il booleano che ha valore true se il Contenuto è un film , ritorna false se il Contenuto è una serie
     */
    public void setFilm(boolean film) {
        this.film = film;
    }
    /**
     * Ritorna il numero di puntate del Contenuto
     *
     * @return      il numero di puntate del Contenuto
     */
    public int getPuntate() {
        return puntate;
    }

    /**
     * Setta il numero di puntate del Contenuto
     *
     * @param  puntate il numero di puntate del Contenuto da settare
     */
    public void setPuntate(int puntate) {
        this.puntate = puntate;
    }

    /**
     * Ritorna il numero di stagioni del Contenuto
     *
     * @return      il numero di stagioni del Contenuto
     */
    public int getStagioni() {
        return stagioni;
    }

    /**
     * Setta il numero di stagioni del Contenuto
     *
     * @param  stagioni il numero di stagioni del Contenuto da settare
     */
    public void setStagioni(int stagioni) {
        this.stagioni = stagioni;
    }
}


