package StorageLayer;

import java.util.Objects;

public class Recensione {

    private String Utente;
    private String Contenuto;
    private int punteggio;
    private String descrizione;



    public int getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(int punteggio) {
        this.punteggio = punteggio;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getUtente() {
        return Utente;
    }

    public void setUtente(String utente) {
        Utente = utente;
    }

    public String getContenuto() {
        return Contenuto;
    }

    public void setContenuto(String contenuto) {
        Contenuto = contenuto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recensione that = (Recensione) o;
        return punteggio == that.punteggio && Objects.equals(descrizione, that.descrizione) && Objects.equals(Utente, that.Utente);
    }

    @Override
    public int hashCode() {
        return Objects.hash( punteggio, descrizione, Utente);
    }

    @Override
    public String toString() {
        return "Recensione{" +
                ", punteggio=" + punteggio +
                ", descrizione='" + descrizione + '\'' +
                ", Utente='" + Utente + '\'' +
                '}';
    }
}
