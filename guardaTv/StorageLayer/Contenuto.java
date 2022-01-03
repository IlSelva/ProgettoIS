package StorageLayer;

import java.util.Date;

public class Contenuto {

    private String id;
    private String titolo;
    private String descrizione;
    private String regista;
    private int durata;
    private Date dataDiUscita;
    private String immagineDelContenuto;
    private String videoTrailer;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }


    public String getRegista() {
        return regista;
    }

    public void setRegista(String regista) {
        this.regista = regista;
    }

    public int getDurata() {
        return durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    public Date getDataDiUscita() {
        return dataDiUscita;
    }

    public void setDataDiUscita(Date dataDiUscita) {
        this.dataDiUscita = dataDiUscita;
    }

    public String getImmagineDelContenuto() {
        return immagineDelContenuto;
    }

    public void setImmagineDelContenuto(String immagineDelContenuto) {
        this.immagineDelContenuto = immagineDelContenuto;
    }

    public String getVideoTrailer() {
        return videoTrailer;
    }

    public void setVideoTrailer(String videoTrailer) {
        this.videoTrailer = videoTrailer;
    }



}



