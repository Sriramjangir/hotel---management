package Employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;  // FIXED: Statement import

public class Vo {
    public Connection connection;
    public Statement statement;  // FIXED: Public statement ऐड किया (Room में यूज के लिए)

    public Vo() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/last",
                    "root",
                    "Skj@8949"
            );
            statement = connection.createStatement();  // FIXED: Statement create किया
            System.out.println("DB connected");
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("DB connection failed");
            e.printStackTrace();
        }
    }

    // Helper to safely close connection
    public void closeConnection() {
        try {
            if (statement != null) {
                statement.close();  // FIXED: Statement भी close
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("DB connection closed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
