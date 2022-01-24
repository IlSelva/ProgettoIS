package unisa.is.guardatv.StorageLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Questa classe comunica con il Database per la gestione e raccolta degli
 * oggetti "Genere" attraverso vari tipi di query.
 *
 * @author Niccolo' Cacace
 * @version 0.1
 */
public class GenereDAO {

    /**
     * Ritorna la lista di tutti i Generi
     *
     * @return      la lista di tutti i Generi
     */
    public List<Genere> doRetrieveAll() {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT nomeGenere FROM Generi");
            ArrayList<Genere> generi = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Genere c = new Genere();
                c.setNome(rs.getString(1));
                generi.add(c);
            }
            return generi;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Ritorna la lista di tutti i Generi in base al nome
     *
     * @param  genere  il nome del genere
     * @return      la lista di tutti i Generi in base al nome
     */
    public Genere doRetrieveByName(String genere){ /*per controllare se un genere è presente */
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT nomeGenere FROM Generi WHERE genere = ?");
            ps.setString(1, genere);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Genere c = new Genere();
                c.setNome(rs.getString(1));
                return c;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Aggiunge al Database un nuovo Genere
     *
     * @param  gen  il Genere da aggiungere al Database
     */
    public void doSave(Genere gen){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Genere (nomeGenere) VALUES(?)");
            ps.setString(1, gen.getNome());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Elimina dal Database un Genere esistente
     *
     * @param  nomeGenere   nome del genere
     */
    public void doDelete(String nomeGenere){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM Genere WHERE nomeGenere=? ");
            ps.setString(1, nomeGenere);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("DELETE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
