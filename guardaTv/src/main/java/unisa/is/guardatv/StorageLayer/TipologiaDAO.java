package unisa.is.guardatv.StorageLayer;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Questa classe comunica con il Database per la gestione e raccolta degli
 * oggetti "Tipologia",contenenti le liste di generi dei specifici Contenuti, attraverso vari tipi di query.
 *
 * @author Niccolo' Cacace
 * @version 0.1
 */
public class TipologiaDAO {


    ContenutoDAO serviceC = new ContenutoDAO();
    GenereDAO serviceG = new GenereDAO();

    /**
     * Ritorna la lista di tutti i Contenuti in base al Genere
     *
     * @param  genere   il nome del Genere
     * @return      la lista di tutti i Contenuti in base al Genere
     */
    public List<Contenuto> allContenutiByGenere(String genere) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT Contenuto FROM Tipologia WHERE genere = ?");
            ps.setString(1, genere);
            ArrayList<Contenuto> contenuti = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                contenuti.add(serviceC.doRetrieveById(rs.getString(1)));
            }
            return contenuti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Ritorna la lista di tutti i Generi in base all'identificativo ufficiale ISAN del Contenuto
     *
     * @param  contenuto   l'identificativo ufficiale ISAN del Contenuto
     * @return      la lista di tutti i Contenuti in base al Genere
     */
    public List<Genere> allGeneriByContenuto(String contenuto) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT Genere FROM Tipologia WHERE contenuto = ?");
            ps.setString(1, contenuto);
            ArrayList<Genere> generi = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                generi.add(serviceG.doRetrieveByName(rs.getString(1)));
            }
            return generi;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Aggiunge al Database una nuova Tipologia
     *
     * @param  tip   la Tipologia da aggiungere al Database
     */
    public void doSave(Tipologia tip) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Tipologia (Contenuto,Genere) VALUES(?,?)");
            ps.setString(1, tip.getContenuto());
            ps.setString(2, tip.getGenere());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Elimina dal Database una Tipologia esistente
     *
     * @param  contenuto   l'identificativo ufficiale ISAN del Contenuto
     * @param  genere      il nome del genere
     */
    public void doDelete(String contenuto, String genere) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM Tipologia WHERE contenuto=? AND genere=?");
            ps.setString(1, contenuto);
            ps.setString(2, genere);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("DELETE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

