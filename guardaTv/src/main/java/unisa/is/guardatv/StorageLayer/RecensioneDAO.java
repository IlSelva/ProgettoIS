package StorageLayer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Questa classe comunica con il Database per la gestione e raccolta degli
 * oggetti "Recensione" attraverso vari tipi di query.
 *
 * @author Niccolo' Cacace
 * @version 0.1
 */
public class RecensioneDAO {

    /**
     * Ritorna la lista delle Recensioni di un Contenuto identificato in base all'identificativo ufficiale ISAN
     * @param  offset  il limite inferiore impostato nella query al database
     * @param  limit   il limite superiore impostato nella query al database
     * @param  idContenuto   l'identificativo ufficiale ISAN del Contenuto
     * @return      la lista delle Recensioni di un Contenutoi identificato in base all'identificativo ufficiale ISAN
     */
    public List<Recensione> doRetrieveByContenuto(String idContenuto, int offset,int limit){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT  Utente,Contenuto,punteggio,descrizione FROM Recensione WHERE idContenuto = ? LIMIT ?, ?");
            ps.setString(1, idContenuto);
            ps.setInt(2, offset);
            ps.setInt(3, limit);
            ArrayList<Recensione> recensioni = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Recensione p = new Recensione();
                p.setUtente(rs.getString(1));
                p.setContenuto(rs.getString(2));
                p.setPunteggio(rs.getInt(3));
                p.setDescrizione(rs.getString(4));
                recensioni.add(p);
            }
            return recensioni;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Ritorna la lista delle Recensioni di un Utente
     * @param  offset  il limite inferiore impostato nella query al database
     * @param  limit   il limite superiore impostato nella query al database
     * @param  idUtente   l'identificativo dell'Utente
     * @return      la lista delle Recensioni di un Utente
     */
    public List<Recensione> doRetrieveByUtente(String idUtente, int offset,int limit){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT Utente,Contenuto,punteggio,descrizione FROM Recensione WHERE idUtente = ? LIMIT ?, ?");
            ps.setString(1, idUtente);
            ps.setInt(2, offset);
            ps.setInt(3, limit);
            ArrayList<Recensione> recensioni = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Recensione p = new Recensione();
                p.setUtente(rs.getString(1));
                p.setContenuto(rs.getString(2));
                p.setPunteggio(rs.getInt(3));
                p.setDescrizione(rs.getString(4));
                recensioni.add(p);
            }
            return recensioni;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Ritorna la lista delle Recensioni di un Utente e di uno specifico Contenuto
     * @param  idContenuto  l'dentificativo del Contenuto
     * @param  idUtente   l'identificativo dell'Utente
     * @return      la lista delle Recensioni di un Utente e di uno specifico Contenuto
     */
    public Recensione doRetrieveById(String idUtente,String idContenuto){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT Utente,Contenuto,punteggio,descrizione FROM Recensione WHERE idUtente = ? AND idContenuto = ?");
            ps.setString(1, idUtente);
            ps.setString(2, idContenuto);
            ResultSet rs = ps.executeQuery();
            Recensione p = new Recensione();
            while (rs.next()) {
                p.setUtente(rs.getString(1));
                p.setContenuto(rs.getString(2));
                p.setPunteggio(rs.getInt(3));
                p.setDescrizione(rs.getString(4));
            }
            return p;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Aggiunge al Database una nuova Recensione
     *
     * @param  rec   la Recensione da aggiungere al Database
     */
    public void doSave(Recensione rec){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Recensione (Utente,Contenuto,punteggio,descrizione) VALUES(?,?,?,?)");
            ps.setString(1, rec.getUtente());
            ps.setString(2, rec.getContenuto());
            ps.setInt(3, rec.getPunteggio());
            ps.setString(4,rec.getDescrizione());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Modifica nel Database una Recensione esistente
     *
     * @param  rec   la Recensione da modificare nel Database
     */
    public void doUpdate(Recensione rec){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE Recensione SET punteggio=?, descrizione=? WHERE Utente=? AND Contenuto=?");
            ps.setInt(1, rec.getPunteggio());
            ps.setString(2,rec.getDescrizione());
            ps.setString(3, rec.getUtente());
            ps.setString(4, rec.getContenuto());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Elimina dal Database una Recensione esistente
     *
     * @param  idContenuto   l'identificativo del Contenuto
     * @param  idUtente      l'identificativo dell'Utente
     */
    public void doDelete(String idUtente,String idContenuto){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM Recensione WHERE idUtente=? AND idContenuto=?");
            ps.setString(1, idUtente);
            ps.setString(2,idContenuto);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("DELETE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}