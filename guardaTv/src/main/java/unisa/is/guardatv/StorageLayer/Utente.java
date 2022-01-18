package unisa.is.guardatv.StorageLayer;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class Utente {
    private String passwordhash;
    private String email;
    private String salt;
    private Date dataDiNascita;
    private String username;
    private boolean administrator;


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

    public String getPasswordhash() {
        return passwordhash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getAdministrator() {
        return administrator;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Date getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(Date dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


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
