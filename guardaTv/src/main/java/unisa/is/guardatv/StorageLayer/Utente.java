package unisa.is.guardatv.StorageLayer;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utente {
    private String passwordhash;
    private String email;
    private boolean administrator;


    public void setPassword(String password) {
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

    public void setPasswordhash(String passwordhash) {
        this.passwordhash = passwordhash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return administrator;
    }

    public void setAdmin(boolean administrator) {
        this.administrator = administrator;
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
    @Override
    public String toString() {
        return "Utente [ passwordhash=" + passwordhash
                + ", email=" + email + ", admin=" + administrator + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (administrator ? 1231 : 1237);
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((passwordhash == null) ? 0 : passwordhash.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Utente other = (Utente) obj;
        if (administrator != other.administrator)
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (passwordhash == null) {
            if (other.passwordhash != null)
                return false;
        } else if (!passwordhash.equals(other.passwordhash))
            return false;
        return true;
    }



}
