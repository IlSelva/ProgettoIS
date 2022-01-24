package unisa.is.guardatv.StorageLayer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Questa classe comunica con il Database per la gestione e raccolta degli
 * oggetti "Utente" attraverso vari tipi di query.
 *
 * @author Niccolo' Cacace
 * @version 0.1
 */
public class UtenteDAO {

    /**
     * Ritorna la lista di tutti gli Utenti
     *
     * @param  offset  il limite inferiore impostato nella query al database
     * @param  limit   il limite superiore impostato nella query al database
     * @return      la lista di tutti gli Utenti
     */
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

    /**
     * Ritorna la lista di tutti gli Utenti in base all'email identificativa dell'Utente
     *
     * @param  email   l'email identificativa dell'Utente
     * @return      la lista di tutti gli Utenti in base all'email identificativa dell'Utente
     */
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

    /**
     * Ritorna la lista di tutti gli Utenti in base all'email identificativa e la password dell'Utente
     *
     * @param  email   l'email identificativa dell'Utente
     * @param  password   la password dell'Utente
     * @return      la lista di tutti gli Utenti in base all'email identificativa e la password dell'Utente
     */
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

    /**
     * Aggiunge al Database un nuovo Utente
     *
     * @param  utente   l'Utente da aggiungere al Database
     */
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

    /**
     * Modifica nel Database un Utente esistente
     *
     * @param  utente   l'Utente da modificare nel Database
     */
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

    /**
     * Elimina dal Database un Utente esistente
     *
     * @param  email  l'email identificativo dell'Utente da eliminare
     */
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
