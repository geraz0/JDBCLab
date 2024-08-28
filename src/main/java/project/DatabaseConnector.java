package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {
    private static final String DB_URL = "jdbc:sqlite:/Users/gerazo/Documents/SQLite/Bookstore.db";

    public static void main(String[] args) {
        DatabaseConnector connector = new DatabaseConnector();
        Connection connection = connector.connect();

        if (connection != null) {
            connector.selectAllBooks();
        }
    }

    public Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL);
            if (connection != null) {
                System.out.println("Successfully connected to the database!");
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database.");
            e.printStackTrace();
        }
        return connection;
    }

    public void selectAllBooks() {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            connection = this.connect();
            String sql = "SELECT BookID, Title, Author, PublicationYear, Price FROM Books";
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int bookID = rs.getInt("BookID");
                String title = rs.getString("Title");
                String author = rs.getString("Author");
                int publicationYear = rs.getInt("PublicationYear");
                double price = rs.getDouble("Price");

                System.out.println(bookID + "\t" + title + "\t" + author + "\t" + publicationYear + "\t" + price);
            }
        } catch (SQLException e) {
            System.out.println("Error executing SELECT statement");
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources");
                e.printStackTrace();
            }
        }
    }
}
