package it.dietiestates.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PgSQL {
    private static final String URL = "jdbc:postgresql://localhost:5432/DietiEstates25?currentSchema=est";
    private static final String USER = System.getenv("DB_USER");
    private static final String PASSWORD = System.getenv("DB_PASSWORD");

    private static Connection connection;

    private PgSQL() {}

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            synchronized (PgSQL.class) {
                if (connection == null || connection.isClosed()) {
                    connection = DriverManager.getConnection(URL, USER, PASSWORD);
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

