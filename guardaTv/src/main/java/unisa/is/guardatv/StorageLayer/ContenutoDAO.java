package unisa.is.guardatv.StorageLayer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContenutoDAO {

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

    /* contenuti ordinati in base al titolo  */
    public List<Contenuto> doRetrieveLast(int offset, int limit) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con
                    .prepareStatement("SELECT  id,titolo,descrizione,regista,durata,dataDiUscita,immagineDelContenuto,videoTrailer,film,stagioni,puntate FROM Contenuto ORDER BY titolo desc LIMIT ?, ?");
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
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("UPDATE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

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