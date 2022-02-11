package unisa.is.guardatv.StorageLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Questa classe comunica con il Database per la gestione e raccolta degli
 * oggetti "Lista" attraverso vari tipi di query.
 *
 * @author Niccolo' Cacace
 * @version 0.1
 */
public class ListaDAO {

    /**
     * Ritorna la lista di tutte le Liste dello specifico utente
     *
     * @param  offset  il limite inferiore impostato nella query al database
     * @param  limit   il limite superiore impostato nella query al database
     * @param  utente   l'identificativo dell'utente
     * @return      la lista di tutte le Liste dello specifico utente
     */
    public List<Lista> doRetrieveByUtente(String utente, int offset, int limit){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT  nome,Utente,descrizione FROM Lista WHERE Utente = ? LIMIT ?,?");
            ps.setString(1, utente);
            ps.setInt(2, offset);
            ps.setInt(3, limit);
            ArrayList<Lista> liste = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Lista p = new Lista();
                p.setNome(rs.getString(1));
                p.setUtente(rs.getString(2));
                p.setDescrizione(rs.getString(3));
                liste.add(p);
            }
            return liste;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Ritorna la specifica Lista appartenente allo specifico utente
     *
     * @param  offset  il limite inferiore impostato nella query al database
     * @param  limit   il limite superiore impostato nella query al database
     * @param  utente   l'identificativo dell'utente
     * @param  nome    il nome della lista
     * @return      la specifica Lista appartenente allo specifico utente
     */
    public Lista doRetrieveById (String nome,String utente,int offset,int limit){ // idlista = idutente+nomelista
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT  nome,Utente,descrizione FROM Lista WHERE nome = ? AND Utente = ? LIMIT ?, ?");
            ps.setString(1, nome);
            ps.setString(2, utente);
            ps.setInt(3, offset);
            ps.setInt(4, limit);
            ResultSet rs = ps.executeQuery();
            Lista p = new Lista();
            while (rs.next()) {
                p.setNome(rs.getString(1));
                p.setUtente(rs.getString(2));
                p.setDescrizione(rs.getString(3));
            }
            return p;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Aggiunge al Database una nuova Lista
     *
     * @param  ls   la nuova Lista da aggiungere al Database
     */
    public void DoSave(Lista ls){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Lista (nome,Utente,descrizione) VALUES(?,?,?)");
            ps.setString(1, ls.getNome());
            ps.setString(2, ls.getUtente());
            ps.setString(3, ls.getDescrizione());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Elimina dal Database una Lista esistente
     *
     * @param  nome     il nome della Lista
     * @param  utente   l'identificativo dell'utente
     */
    public void doDelete(String nome, String utente){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM Lista WHERE nome=? AND Utente=?");
            ps.setString(1, nome);
            ps.setString(2,utente);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("DELETE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}