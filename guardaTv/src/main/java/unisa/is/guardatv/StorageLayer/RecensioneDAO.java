package StorageLayer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecensioneDAO {

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
