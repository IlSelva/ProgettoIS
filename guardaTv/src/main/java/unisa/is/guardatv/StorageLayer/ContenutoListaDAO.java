package unisa.is.guardatv.StorageLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContenutoListaDAO {

    ContenutoDAO service = new ContenutoDAO();

    /* ti do la lista dei contenuti data la chiave della lista */
    public List<Contenuto> allContenutiByLista(String lsNome, String lsUtente) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT Contenuto FROM ContenutoLista WHERE lsNome = ? AND lsUtente = ?");
            ps.setString(1, lsNome);
            ps.setString(2, lsUtente);
            ArrayList<Contenuto> contenuti = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                contenuti.add(service.doRetrieveById(rs.getString(1)));
            }
            return contenuti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void AddContenuto(String lsNome, String lsUtente, String idContenuto) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO ContenutoLista (ListaNome,ListaUtente,Contenuto) VALUES(?,?,?)");
            ps.setString(1, lsNome);
            ps.setString(2, lsUtente);
            ps.setString(3, idContenuto);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Lista RemoveContenuto(String lsNome, String lsUtente, String idContenuto) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM ContenutoLista WHERE lsNome=? AND lsUtente=? AND idContenuto=?");
            ps.setString(1, lsNome);
            ps.setString(2, lsUtente);
            ps.setString(3, idContenuto);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("DELETE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new Lista();
    }
}
