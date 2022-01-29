package StorageLayer;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * Questa classe modella il concetto di "Utente" all'interno del sistema.
 *
 * @author Niccolo' Cacace
 * @version 0.1
 */
public class Utente {
    private String passwordhash;
    private String email;
    private String salt;
    private Date dataDiNascita;
    private String username;
    private boolean administrator;

    /**
     * Setta l'hash della password applicando un algoritmo di hashing alla password originale
     *
     * @param  password  la password in chiaro da cui generare l'hash
     */
    public void setPasswordhash(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");//implemento l'algoritmo di hash "SHA-1"
            digest.reset(); //pulisco MessageDigest per evitare eventuali problemi legati al riutilizzo del MessageDigest
            digest.update(password.getBytes(StandardCharsets.UTF_8));//carico il file di cui fare l'hash code
            this.passwordhash = String.format("%040x", new BigInteger(1, digest.digest()));//genero l Hashcode che formatto in esadecimale
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Ritorna l'hash della password
     *
     * @return      l'hash della password
     */
    public String getPasswordhash() {
        return passwordhash;
    }

    /**
     * Ritorna l'email identificativa dell'Utente
     *
     * @return      l'email identificativa dell'Utente
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setta l'email identificativa dell'Utente
     *
     * @param  email  l'email identificativa dell'Utente da settare
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Ritorna il booleano settato a true nel caso in cui l'utente sia Amministratore ,a false nel caso in cui non sia Amministratore
     *
     * @return     il booleano settato a true nel caso in cui l'utente sia Amministratore ,a false nel caso in cui non sia Amministratore
     */
    public boolean getAdministrator() {
        return administrator;
    }

    /**
     * Setta il booleano settato a true nel caso in cui l'utente sia Amministratore ,a false nel caso in cui non sia Amministratore
     *
     * @param  administrator  il booleano da settare a true nel caso in cui l'utente sia Amministratore ,a false nel caso in cui non sia Amministratore
     */
    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }

    /**
     * Ritorna il Salt della password, un aggiunta di sicurezza alla password dell'utente
     *
     * @return      il Salt della password
     */
    public String getSalt() {
        return salt;
    }

    /**
     * Setta il Salt della password ,un aggiunta di sicurezza alla password dell'utente
     *
     * @param  salt  il Salt della password da settare
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * Ritorna la data di nascita dell'utente
     *
     * @return      la data di nascita dell'utente
     */
    public Date getDataDiNascita() {
        return dataDiNascita;
    }

    /**
     * Setta la data di nascita dell'utente
     *
     * @param  dataDiNascita  la data di nascita dell'utente da settare
     */
    public void setDataDiNascita(Date dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    /**
     * Ritorna lo usename dell'Utente
     *
     * @return      lo usename dell'Utente
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setta lo usename dell'Utente
     *
     * @param  username  lo usename dell'Utente da settare
     */
    public void setUsername(String username) {
        this.username = username;
    }


    /**
     * Svolge il confronto tra password inserita dall'utente,fatta passare per l'algoritmo di hash,e corrispettivo hash memorizzato nel Database
     *
     * @param  password  la password da confrontare
     * @return      il boleano avente valore true se la password è corretta, false altrimenti
     */
    public boolean checkPassword(String password){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(password.getBytes(StandardCharsets.UTF_8));
            String passwordhash = String.format("%040x", new BigInteger(1, digest.digest()));
            if (passwordhash.compareTo(this.passwordhash) == 0) //se gli hashcode delle password sono uguali ritorna true
                return true;
            else
                return false;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}