package project;

public class Main {

    public static void main(String[] args) {
        // Create an instance of the DatabaseConnector
        DatabaseConnector connector = new DatabaseConnector();

        // Call the selectAllBooks method to test the database connection and query
        connector.selectAllBooks();
    }
}
