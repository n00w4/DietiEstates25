package dietiEstates.backendDietiEstates.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PgSQL {
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/DietiEstates25?currentSchema=est";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    private static volatile Connection connection;

    private PgSQL() {
        // Costruttore privato per impedire istanziazione
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            synchronized (PgSQL.class) {
                if (connection == null || connection.isClosed()) {
                    try {
                        Class.forName(DRIVER);
                        connection = DriverManager.getConnection(URL, USER, PASSWORD);
                    } catch (ClassNotFoundException e) {
                        throw new SQLException("Driver JDBC non trovato", e);
                    }
                }
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

