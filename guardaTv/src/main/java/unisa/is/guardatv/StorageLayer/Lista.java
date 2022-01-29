package StorageLayer;

/**
 * Questa classe modella il concetto di "Lista" all'interno del sistema.
 *
 * @author Niccolo' Cacace
 * @version 0.1
 */
public class Lista {

    private String nome;
    private String utente;
    private String descrizione;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Ritorna l'identificativo dell'Utente propieteario della Lista
     *
     * @return      l'identificativo dell'Utente propieteario della Lista
     */
    public String getUtente() {
        return utente;
    }

    /**
     * Setta  l'identificativo dell'Utente propieteario della Lista
     *
     * @param  utente   l'identificativo dell'Utente propieteario della Lista
     */
    public void setUtente(String utente) {
        this.utente = utente;
    }

    /**
     * Ritorna la descrizione della Lista
     *
     * @return      la descrizione della Lista
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Setta  la descrizione della Lista
     *
     * @param  descrizione   la descrizione della Lista
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

}
