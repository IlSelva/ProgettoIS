package StorageLayer;


public class ContenutoListaDAO {


    ContenutoDAO serviceC = new ContenutoDAO();
    GenereDAO serviceG = nre

    GenereDAO();


    public List<Contenuto> allContenutiByGenere(String genere) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT Contenuto FROM Tipologia WHERE genere = ?");
            ps.setString(1, genere);
            ArrayList<Contenuto> contenuti = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                contenuti.add(serviceC.doRetrieveById(rs.getString(1)));
            }
            return contenuti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Genere> allGeneriByContenuto(String contenuto) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT Genere FROM Tipologia WHERE contenuto = ?");
            ps.setString(1, contenuto);
            ArrayList<Genere> generi = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                generi.add(serviceG.doRetrieveByName(rs.getString(1)));
            }
            return generi;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSave(Tipologia tip) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Tipologia (Contenuto,Genere) VALUES(?,?)");
            ps.setString(1, tip.getContenuto());
            ps.setInt(2, tip.getGenere());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doDelete(String contenuto, String genere) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM Tipologia WHERE contenuto=? AND genere=?");
            ps.setString(1, contenuto);
            ps.setString(2, genere);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("DELETE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}


