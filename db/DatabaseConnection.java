package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // SQLite database URL
    private static final String URL = "jdbc:sqlite:expenses.db";

    // Method to get a connection to the SQLite database
    public static Connection getConnection() {
        try {
            // Force loading the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");
            
            // Establish and return the connection
            return DriverManager.getConnection(URL);
        } catch (ClassNotFoundException e) {
            // This will be triggered if the JDBC driver is not found
            System.err.println("SQLite JDBC driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            // This will be triggered if the connection to the database fails
            System.err.println("Connection to database failed.");
            e.printStackTrace();
        }
        
        // Return null if any exception occurs
        return null;
    }
}
