package StorageLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Questa classe comunica con il Database per la gestione e raccolta degli
 * oggetti "ContenutoLista" attraverso vari tipi di query.
 *
 * @author Niccolo' Cacace
 * @version 0.1
 */

public class ContenutoListaDAO {

    ContenutoDAO service = new ContenutoDAO();

    /**
     * Ritorna la lista dei Contenuti appartenenti ad una specifica Lista
     *
     * @param  lsNome   il nome della Lista
     * @param  lsUtente   l'identificativo dell'Utente propietario della Lista
     * @return      la lista dei Contenuti appartenenti ad una specifica Lista
     */
    public List<Contenuto> allContenutiByLista(String lsNome,String lsUtente){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT Contenuto FROM ContenutoLista WHERE ListaNome = ? AND ListaUtente = ?");
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


    /**
     * Aggiunge un Contenuto ad una Lista nel Database
     *
     * @param  lsNome   il nome della Lista
     * @param  lsUtente   l'identificativo dell'Utente propietario della Lista
     * @param  idContenuto   l'identificativo ufficiale ISAN del Contenuto
     */
    public void AddContenuto(String lsNome,String lsUtente,String idContenuto){
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



    /**
     * Rimuove un Contenuto da una Lista nel Database
     *
     * @param  lsNome   il nome della Lista
     * @param  lsUtente   l'identificativo dell'Utente propietario della Lista
     * @param  idContenuto   l'identificativo ufficiale ISAN del Contenuto
     */
    public void RemoveContenuto(String lsNome,String lsUtente,String idContenuto) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM ContenutoLista WHERE ListaNome=? AND ListaUtente=? AND Contenuto=?");
            ps.setString(1, lsNome);
            ps.setString(2, lsUtente);
            ps.setString(3, idContenuto);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("DELETE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}