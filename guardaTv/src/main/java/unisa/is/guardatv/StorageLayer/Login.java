package unisa.is.guardatv.StorageLayer;

import java.sql.Timestamp;

/**
 * Questa classe modella il concetto di "Login" all'interno del sistema.
 *
 * @author Niccolo' Cacace
 * @version 0.1
 */
public class Login {
    private String id;
    private String idUtente;
    private String token;
    private Timestamp time;

    /**
     * Ritorna l'identificativo del Login
     *
     * @return      l'identificativo del Login
     */
    public String getId() {
        return id;
    }

    /**
     * Setta l'identificativo del Login
     *
     * @param  id  l'identificativo del Login da settare
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Ritorna l'identificativo dell'Utente
     *
     * @return      l'identificativo dell'Utente
     */
    public String getIdUtente() {
        return idUtente;
    }

    /**
     * Setta l'identificativo dell'Utente
     *
     * @param  idutente  l'identificativo dell'Utente da settare
     */
    public void setIdUtente(String idutente) {
        this.idUtente = idutente;
    }

    /**
     * Ritorna un Token identificativo dell'Utente
     *
     * @return      un Token identificativo dell'Utente
     */
    public String getToken() {
        return token;
    }

    /**
     * Setta un Token identificativo dell'Utente
     *
     * @param  token  un Token identificativo da settare
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Ritorna il momento in cui il Login è stato svolto
     *
     * @return      il momento in cui il Login è stato svolto
     */
    public Timestamp getTime() {
        return time;
    }

    /**
     * Setta il momento in cui il Login è stato svolto
     *
     * @param  time  il momento in cui il Login è stato svolto da settare
     */
    public void setTime(Timestamp time) {
        this.time = time;
    }

}
