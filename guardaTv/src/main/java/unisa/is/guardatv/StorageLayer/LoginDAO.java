package unisa.is.guardatv.StorageLayer;

import java.sql.*;
import java.util.UUID;

/**
 * Questa classe comunica con il Database per la gestione e raccolta degli
 * oggetti "Login" attraverso vari tipi di query.
 *
 * @author Niccolo' Cacace
 * @version 0.1
 */
public class LoginDAO {

    /**
     * Ritorna il Login in base all'identificativo
     *
     * @param  id   l'identificativo del Login
     * @return      il Login in base all'identificativo
     */
    public Login doRetrieveById(String id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT id, emailUtente, token, time FROM login WHERE id=?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Login l = new Login();
                l.setId(rs.getString(1));
                l.setIdUtente(rs.getString(2));
                l.setToken(rs.getString(3));
                l.setTime(rs.getTimestamp(4));
                return l;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Aggiunge al Database un nuovo Login
     *
     * @param  login   il Login da aggiungere al Database
     */
    public void doSave(Login login) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO login (id, emailUtente, token, time) VALUES(?, ?,?,?)", Statement.RETURN_GENERATED_KEYS);
            String id = UUID.randomUUID().toString();
            ps.setString(1, id);
            ps.setString(2, login.getIdUtente());
            ps.setString(3, login.getToken());
            ps.setTimestamp(4, login.getTime());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            login.setId(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Modifica nel Database un Login esistente
     *
     * @param  login   un Login da modificare nel Database
     */
    public void doUpdate(Login login) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE login SET emailUtente=?, token=?, time=? WHERE id=?");
            ps.setString(1, login.getIdUtente());
            ps.setString(2, login.getToken());
            ps.setTimestamp(3, login.getTime());
            ps.setString(4, login.getId());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("UPDATE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Elimina dal Database un Login esistente
     *
     * @param  id   l'identificativo del Login
     */
    public void doDelete(String id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM login WHERE id=?");
            ps.setString(1, id);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("DELETE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}