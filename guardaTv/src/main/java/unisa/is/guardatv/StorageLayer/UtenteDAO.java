package unisa.is.guardatv.StorageLayer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtenteDAO {

    public List<Utente> doRetrieveAll(int offset, int limit) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con
                    .prepareStatement("SELECT email, passwordhash,salt,dataDiNascita,username,administrator FROM Utente LIMIT ?, ?");
            ps.setInt(1, offset);
            ps.setInt(2, limit);
            ArrayList<Utente> utenti = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Utente u = new Utente();
                u.setEmail(rs.getString(1));
                u.setPasswordhash(rs.getString(2));
                u.setSalt(rs.getString(3));
                u.setDataDiNascita(rs.getDate(4));
                u.setUsername(rs.getString(5));
                u.setAdministrator(rs.getBoolean(6));
                utenti.add(u);
            }
            return utenti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Utente doRetrieveByEmail(String email) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT email, passwordhash,salt,dataDiNascita,username,administrator FROM Utente WHERE email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Utente u = new Utente();
                u.setEmail(rs.getString(1));
                u.setPasswordhash(rs.getString(2));
                u.setSalt(rs.getString(3));
                u.setDataDiNascita(rs.getDate(4));
                u.setUsername(rs.getString(5));
                u.setAdministrator(rs.getBoolean(6));
                return u;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Utente doRetrieveByEmailPassword(String email, String password) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT email, passwordhash,salt,dataDiNascita,username,administrator FROM Utente WHERE email=? AND passwordhash=SHA1(?)");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Utente u = new Utente();
                u.setEmail(rs.getString(1));
                u.setPasswordhash(rs.getString(2));
                u.setSalt(rs.getString(3));
                u.setDataDiNascita(rs.getDate(4));
                u.setUsername(rs.getString(5));
                u.setAdministrator(rs.getBoolean(6));
                return u;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void doSave(Utente utente) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Utente (email, passwordhash,salt,dataDiNascita,username,administrator) VALUES(?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, utente.getEmail());
            ps.setString(2, utente.getPasswordhash());
            ps.setString(3,utente.getSalt());
            ps.setDate(4, (Date) utente.getDataDiNascita());
            ps.setString(5,utente.getUsername());
            ps.setBoolean(6, utente.getAdministrator());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doUpdate(Utente utente) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con
                    .prepareStatement("UPDATE Utente SET email=?, passwordhash=?,dataDiNascita=?,username=? administrator=? WHERE email=?");
            ps.setString(1, utente.getEmail());
            ps.setString(2, utente.getPasswordhash());
            ps.setDate(4, (Date) utente.getDataDiNascita());
            ps.setString(4,utente.getUsername());
            ps.setBoolean(5,utente.getAdministrator());
            ps.setString(6, utente.getEmail());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("UPDATE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void doDelete(String email) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM Utente WHERE email=?");
            ps.setString(1, email);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("DELETE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}