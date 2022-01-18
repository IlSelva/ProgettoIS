package unisa.is.guardatv.StorageLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenereDAO {

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
