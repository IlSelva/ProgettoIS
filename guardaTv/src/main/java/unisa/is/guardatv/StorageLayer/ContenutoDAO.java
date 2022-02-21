package unisa.is.guardatv.StorageLayer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Questa classe comunica con il Database per la gestione e raccolta degli
 * oggetti "Contenuto" attraverso vari tipi di query.
 *
 * @author Niccolo' Cacace
 * @version 0.1
 */

public class ContenutoDAO {

    /**
     * Ritorna la lista di tutti i Contenuti
     *
     * @param  offset  il limite inferiore impostato nella query al database
     * @param  limit   il limite superiore impostato nella query al database
     * @return      la lista di tutti i Contenuti
     */
    public List<Contenuto> doRetrieveAll(int offset, int limit) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con
                    .prepareStatement("SELECT  id,titolo,descrizione,regista,durata,dataDiUscita,immagineDelContenuto,videoTrailer,film,stagioni,puntate FROM Contenuto LIMIT ?, ?");
            ps.setInt(1, offset);
            ps.setInt(2, limit);
            ArrayList<Contenuto> contenuti = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Contenuto p = new Contenuto();
                p.setId(rs.getString(1));
                p.setTitolo(rs.getString(2));
                p.setDescrizione(rs.getString(3));
                p.setRegista(rs.getString(4));
                p.setDurata(rs.getInt(5));
                p.setDataDiUscita(rs.getDate(6));
                p.setImmagineDelContenuto(rs.getString(7));
                p.setVideoTrailer(rs.getString(8));
                p.setFilm(rs.getBoolean(9));
                p.setStagioni(rs.getInt(10));
                p.setPuntate(rs.getInt(11));
                contenuti.add(p);
            }
            return contenuti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Ritorna la lista di tutti i Contenuti ordinati in base al titolo
     *
     * @param  offset  il limite inferiore impostato nella query al database
     * @param  limit   il limite superiore impostato nella query al database
     * @return      la lista di tutti i Contenuti ordinati in base al titolo
     */
    public List<Contenuto> doRetrieveLast(int offset, int limit) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con
                    .prepareStatement("SELECT  id,titolo,descrizione,regista,durata,dataDiUscita,immagineDelContenuto,videoTrailer,film,stagioni,puntate FROM Contenuto ORDER BY dataDiUscita desc LIMIT ?, ?");
            ps.setInt(1, offset);
            ps.setInt(2, limit);
            ArrayList<Contenuto> contenuti = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Contenuto p = new Contenuto();
                p.setId(rs.getString(1));
                p.setTitolo(rs.getString(2));
                p.setDescrizione(rs.getString(3));
                p.setRegista(rs.getString(4));
                p.setDurata(rs.getInt(5));
                p.setDataDiUscita(rs.getDate(6));
                p.setImmagineDelContenuto(rs.getString(7));
                p.setVideoTrailer(rs.getString(8));
                p.setFilm(rs.getBoolean(9));
                p.setStagioni(rs.getInt(10));
                p.setPuntate(rs.getInt(11));
                contenuti.add(p);
            }
            return contenuti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Ritorna la lista di tutti i Contenuti che sono Film
     *
     * @param  offset  il limite inferiore impostato nella query al database
     * @param  limit   il limite superiore impostato nella query al database
     * @return      la lista di tutti i Contenuti che sono Film
     */
    public List<Contenuto> doRetrieveAllFilm(int offset, int limit) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con
                    .prepareStatement("SELECT  id,titolo,descrizione,regista,durata,dataDiUscita,immagineDelContenuto,videoTrailer,film FROM Contenuto WHERE film=true LIMIT ?, ?");
            ps.setInt(1, offset);
            ps.setInt(2, limit);
            ArrayList<Contenuto> contenuti = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Contenuto p = new Contenuto();
                p.setId(rs.getString(1));
                p.setTitolo(rs.getString(2));
                p.setDescrizione(rs.getString(3));
                p.setRegista(rs.getString(4));
                p.setDurata(rs.getInt(5));
                p.setDataDiUscita(rs.getDate(6));
                p.setImmagineDelContenuto(rs.getString(7));
                p.setVideoTrailer(rs.getString(8));
                p.setFilm(rs.getBoolean(9));
                contenuti.add(p);
            }
            return contenuti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Ritorna la lista di tutti i Contenuti che sono Serie
     *
     * @param  offset  il limite inferiore impostato nella query al database
     * @param  limit   il limite superiore impostato nella query al database
     * @return      la lista di tutti i Contenuti che sono Serie
     */
    public List<Contenuto> doRetrieveAllSeries(int offset, int limit) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con
                    .prepareStatement("SELECT  id,titolo,descrizione,regista,durata,dataDiUscita,immagineDelContenuto,videoTrailer,film,stagioni,puntate FROM Contenuto WHERE film=false LIMIT ?, ?");
            ps.setInt(1, offset);
            ps.setInt(2, limit);
            ArrayList<Contenuto> contenuti = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Contenuto p = new Contenuto();
                p.setId(rs.getString(1));
                p.setTitolo(rs.getString(2));
                p.setDescrizione(rs.getString(3));
                p.setRegista(rs.getString(4));
                p.setDurata(rs.getInt(5));
                p.setDataDiUscita(rs.getDate(6));
                p.setImmagineDelContenuto(rs.getString(7));
                p.setVideoTrailer(rs.getString(8));
                p.setFilm(rs.getBoolean(9));
                p.setStagioni(rs.getInt(10));
                p.setPuntate(rs.getInt(11));
                contenuti.add(p);
            }
            return contenuti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Ritorna la lista di tutti i Contenuti in base all'identificativo ufficiale ISAN
     *
     * @param  id   l'identificativo ufficiale ISAN
     * @return      la lista di tutti i Contenuti in base all'identificativo ufficiale ISAN
     */
    public Contenuto doRetrieveById(String id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con
                    .prepareStatement("SELECT  id,titolo,descrizione,regista,durata,dataDiUscita,immagineDelContenuto,videoTrailer,film,stagioni,puntate FROM Contenuto WHERE id=?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Contenuto p = new Contenuto();
                p.setId(rs.getString(1));
                p.setTitolo(rs.getString(2));
                p.setDescrizione(rs.getString(3));
                p.setRegista(rs.getString(4));
                p.setDurata(rs.getInt(5));
                p.setDataDiUscita(rs.getDate(6));
                p.setImmagineDelContenuto(rs.getString(7));
                p.setVideoTrailer(rs.getString(8));
                p.setFilm(rs.getBoolean(9));
                p.setStagioni(rs.getInt(10));
                p.setPuntate(rs.getInt(11));
                return p;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Ritorna la lista di tutti i Contenuti in base al titolo
     *
     * @param  offset  il limite inferiore impostato nella query al database
     * @param  limit   il limite superiore impostato nella query al database
     * @param  titolo   il titolo del Contenuto
     * @return      la lista di tutti i Contenuti in base al titolo
     */
    public List<Contenuto> doRetrieveByTitolo(String titolo, int offset, int limit) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT  id,titolo,descrizione,regista,durata,dataDiUscita,immagineDelContenuto,videoTrailer,film,stagioni,puntate FROM Contenuto WHERE MATCH(titolo) AGAINST(? IN BOOLEAN MODE) LIMIT ?, ?");
            ps.setString(1, titolo);
            ps.setInt(2, offset);
            ps.setInt(3, limit);
            ArrayList<Contenuto> contenuti = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Contenuto p = new Contenuto();
                p.setId(rs.getString(1));
                p.setTitolo(rs.getString(2));
                p.setDescrizione(rs.getString(3));
                p.setRegista(rs.getString(4));
                p.setDurata(rs.getInt(5));
                p.setDataDiUscita(rs.getDate(6));
                p.setImmagineDelContenuto(rs.getString(7));
                p.setVideoTrailer(rs.getString(8));
                p.setFilm(rs.getBoolean(9));
                p.setStagioni(rs.getInt(10));
                p.setPuntate(rs.getInt(11));
                contenuti.add(p);
            }
            return contenuti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Ritorna la lista di tutti i Contenuti,che sono Film, in base al titolo
     *
     * @param  offset  il limite inferiore impostato nella query al database
     * @param  limit   il limite superiore impostato nella query al database
     * @param  titolo   il titolo del Film
     * @return      la lista di tutti i Contenuti,che sono Film, in base al titolo
     */
    public List<Contenuto> doRetrieveByTitoloFilm(String titolo, int offset, int limit) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT  id,titolo,descrizione,regista,durata,dataDiUscita,immagineDelContenuto,videoTrailer,film FROM Contenuto WHERE MATCH(titolo) AGAINST(? IN BOOLEAN MODE) AND film=true LIMIT ?, ?");
            ps.setString(1, titolo);
            ps.setInt(2, offset);
            ps.setInt(3, limit);
            ArrayList<Contenuto> contenuti = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Contenuto p = new Contenuto();
                p.setId(rs.getString(1));
                p.setTitolo(rs.getString(2));
                p.setDescrizione(rs.getString(3));
                p.setRegista(rs.getString(4));
                p.setDurata(rs.getInt(5));
                p.setDataDiUscita(rs.getDate(6));
                p.setImmagineDelContenuto(rs.getString(7));
                p.setVideoTrailer(rs.getString(8));
                p.setFilm(rs.getBoolean(9));
                contenuti.add(p);
            }
            return contenuti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Ritorna la lista di tutti i Contenuti,che sono Serie, in base al titolo
     *
     * @param  offset  il limite inferiore impostato nella query al database
     * @param  limit   il limite superiore impostato nella query al database
     * @param  titolo   il titolo della Serie
     * @return      la lista di tutti i Contenuti,che sono Serie, in base al titolo
     */
    public List<Contenuto> doRetrieveByTitoloSerie(String titolo, int offset, int limit) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT  id,titolo,descrizione,regista,durata,dataDiUscita,immagineDelContenuto,videoTrailer,film,stagioni,puntate FROM Contenuto WHERE MATCH(titolo) AGAINST(? IN BOOLEAN MODE) AND film=false LIMIT ?, ?");
            ps.setString(1, titolo);
            ps.setInt(2, offset);
            ps.setInt(3, limit);
            ArrayList<Contenuto> contenuti = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Contenuto p = new Contenuto();
                p.setId(rs.getString(1));
                p.setTitolo(rs.getString(2));
                p.setDescrizione(rs.getString(3));
                p.setRegista(rs.getString(4));
                p.setDurata(rs.getInt(5));
                p.setDataDiUscita(rs.getDate(6));
                p.setImmagineDelContenuto(rs.getString(7));
                p.setVideoTrailer(rs.getString(8));
                p.setFilm(rs.getBoolean(9));
                contenuti.add(p);
            }
            return contenuti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Ritorna la lista di tutti i Contenuti in base al titolo o alla descrizione
     *
     * @param  offset  il limite inferiore impostato nella query al database
     * @param  limit   il limite superiore impostato nella query al database
     * @param  descrizione   la descrizione del Contenuto
     * @param  titolo   il titolo del Contenuto
     * @return      la lista di tutti i Contenuti in base al titolo o alla descrizione
     */
    public List<Contenuto> doRetrieveByTitoloOrDescrizione(String titolo, String descrizione, int offset, int limit) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT  id,titolo,descrizione,regista,durata,dataDiUscita,immagineDelContenuto,videoTrailer,film,stagioni,puntate FROM Contenuto WHERE MATCH(titolo) AGAINST(? IN BOOLEAN MODE) OR MATCH(descrizione) AGAINST(? IN BOOLEAN MODE) LIMIT ?, ?");
            ps.setString(1, titolo);
            ps.setString(2, descrizione);
            ps.setInt(3, offset);
            ps.setInt(4, limit);
            ArrayList<Contenuto> contenuti = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Contenuto p = new Contenuto();
                p.setId(rs.getString(1));
                p.setTitolo(rs.getString(2));
                p.setDescrizione(rs.getString(3));
                p.setRegista(rs.getString(4));
                p.setDurata(rs.getInt(5));
                p.setDataDiUscita(rs.getDate(6));
                p.setImmagineDelContenuto(rs.getString(7));
                p.setVideoTrailer(rs.getString(8));
                p.setFilm(rs.getBoolean(9));
                p.setStagioni(rs.getInt(10));
                p.setPuntate(rs.getInt(11));
                contenuti.add(p);
            }
            return contenuti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Ritorna la lista di tutti i Contenuti in base al regista
     *
     * @param  offset  il limite inferiore impostato nella query al database
     * @param  limit   il limite superiore impostato nella query al database
     * @param  regista   il regista del Contenuto
     * @return      la lista di tutti i Contenuti in base al regista
     */
    public List<Contenuto> doRetrieveByRegista(String regista, int offset, int limit) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT  id,titolo,descrizione,regista,durata,dataDiUscita,immagineDelContenuto,videoTrailer,film,stagioni,puntate FROM Contenuto WHERE regista=? LIMIT ?, ?");
            ps.setString(1, regista);
            ps.setInt(2, offset);
            ps.setInt(3, limit);
            ArrayList<Contenuto> contenuti = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Contenuto p = new Contenuto();
                p.setId(rs.getString(1));
                p.setTitolo(rs.getString(2));
                p.setDescrizione(rs.getString(3));
                p.setRegista(rs.getString(4));
                p.setDurata(rs.getInt(5));
                p.setDataDiUscita(rs.getDate(6));
                p.setImmagineDelContenuto(rs.getString(7));
                p.setVideoTrailer(rs.getString(8));
                p.setFilm(rs.getBoolean(9));
                p.setStagioni(rs.getInt(10));
                p.setPuntate(rs.getInt(11));
                contenuti.add(p);
            }
            return contenuti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Aggiunge al Database un nuovo Contenuto
     *
     * @param  contenuto   il Contenuto da aggiungere al Database
     */
    public void doSave(Contenuto contenuto) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Contenuto(id,titolo,descrizione,regista,durata,dataDiUscita,immagineDelContenuto,videoTrailer,film,stagioni,puntate) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, contenuto.getId());
            ps.setString(2, contenuto.getTitolo());
            ps.setString(3, contenuto.getDescrizione());
            ps.setString(4, contenuto.getRegista());
            ps.setInt(5, contenuto.getDurata());
            ps.setDate(6, (Date) contenuto.getDataDiUscita());
            ps.setString(7, contenuto.getImmagineDelContenuto());
            ps.setString(8, contenuto.getVideoTrailer());
            ps.setBoolean(9,contenuto.isFilm());
            ps.setInt(10,contenuto.getStagioni());
            ps.setInt(11,contenuto.getPuntate());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Modifica nel Database un Contenuto esistente
     *
     * @param  contenuto   il Contenuto da modificare nel Database
     */
    public void doUpdate(Contenuto contenuto) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con
                    .prepareStatement("UPDATE Contenuto SET id=?,titolo=?,descrizione=?,regista=?,durata=?,dataDiUscita=?,immagineDelContenuto=?,videoTrailer=?,film=?,stagioni=?,puntate=? WHERE id=?");
            ps.setString(1, contenuto.getId());
            ps.setString(2, contenuto.getTitolo());
            ps.setString(3, contenuto.getDescrizione());
            ps.setString(4, contenuto.getRegista());
            ps.setInt(5, contenuto.getDurata());
            ps.setDate(6, (Date) contenuto.getDataDiUscita());
            ps.setString(7, contenuto.getImmagineDelContenuto());
            ps.setString(8, contenuto.getVideoTrailer());
            ps.setBoolean(9,contenuto.isFilm());
            ps.setInt(10,contenuto.getStagioni());
            ps.setInt(11,contenuto.getPuntate());
            ps.setString(12, contenuto.getId());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("UPDATE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Elimina dal Database un Contenuto esistente
     *
     * @param  id   l'identificativo ufficiale ISAN
     */
    public void doDelete(String id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM Libro WHERE id=?");
            ps.setString(1, id);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("DELETE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}