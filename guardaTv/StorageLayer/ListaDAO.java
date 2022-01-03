package StorageLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListaDAO {

    public List<Lista> doRetrieveByUtente(String utente, int offset, int limit){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT  nome,Utente,descrizione FROM Lista WHERE utente = ? LIMIT ?, ?");
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

    public Lista DoRetrievebyId (String nome,String utente,int offset,int limit){ // idlista = idutente+nomelista
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT  nome,Utente,descrizione FROM Lista WHERE nome = ? AND utente = ? LIMIT ?, ?");
            ps.setString(1, nome);
            ps.setString(2, utente);
            ps.setInt(2, offset);
            ps.setInt(3, limit);
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


    public void doDelete(String nome, String utente){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM Lista WHERE nome=? AND utente=?");
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

}
