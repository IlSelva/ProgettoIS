package unisa.is.guardatv.StorageLayer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.TimeZone;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

/**
 * Questa classe setta i parametri per la connessione al Database contenente tutti i dati persistenti
 *
 * @author Niccolo' Cacace
 * @version 0.1
 */
public class ConPool {
    private static DataSource datasource;

    /**
     * Setta i parametri per inizializzare la connessione al Database e ritorna la connessione al Database
     * ;o un eccezzione in caso di errore
     * @throws SQLException  lanciata in caso di errore nella connessione al Database
     * @return      la connessione al Database
     */
    public static Connection getConnection() throws SQLException {
        if (datasource == null) {
            PoolProperties p = new PoolProperties();
            p.setUrl("jdbc:mysql://localhost:3306/guardatv?serverTimezone=" + TimeZone.getDefault().getID());
            p.setDriverClassName("com.mysql.cj.jdbc.Driver");
            p.setUsername("root");
            p.setPassword("Salerno95");
            p.setMaxActive(100);
            p.setInitialSize(10);
            p.setMinIdle(10);
            p.setRemoveAbandonedTimeout(60);
            p.setRemoveAbandoned(true);
            datasource = new DataSource();
            datasource.setPoolProperties(p);
        }
        return datasource.getConnection();
    }
}
