package Employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // डेटाबेस डिटेल्स (आपके अनुसार – डेटाबेस नाम 'last' है)
    private static final String DB_URL = "jdbc:mysql://localhost:3306/last";  // डेटाबेस नाम 'last'
    private static final String DB_USER = "root";  // MySQL username (XAMPP में root)
    private static final String DB_PASS = "Skj@8949";  // अपना MySQL password (यहाँ अपडेट किया)

    // Singleton: एक ही इंस्टेंस पूरे ऐप में
    private static DatabaseConnection instance = null;
    private Connection connection = null;

    // Private constructor (बाहर से direct न बन सके)
    private DatabaseConnection() {
        connectToDatabase();
    }

    // Singleton getInstance मेथड – इससे इंस्टेंस लें
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    // डेटाबेस से कनेक्ट करने का मेथड
    private void connectToDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  // MySQL Driver लोड करें (Connector 8+ के लिए)
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            System.out.println("डेटाबेस कनेक्शन सफल! (Database: last)");  // Console में मैसेज (टेस्ट के लिए)
        } catch (ClassNotFoundException ex) {
            System.err.println("MySQL Driver नहीं मिला। JAR फाइल ऐड करें: " + ex.getMessage());
        } catch (SQLException ex) {
            System.err.println("कनेक्शन एरर: " + ex.getMessage() + "\n(MySQL चेक करें: स्टार्ट हो, port 3306 हो)");
        }
    }

    // कनेक्शन लौटाने का मेथड
    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connectToDatabase();  // अगर बंद हो तो नया कनेक्शन बनाएँ
            }
        } catch (SQLException ex) {
            System.err.println("कनेक्शन चेक एरर: " + ex.getMessage());
        }
        return connection;
    }

    // कनेक्शन बंद करने का मेथड (ऐप बंद होने पर यूज करें, optional)
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("डेटाबेस कनेक्शन बंद!");
            }
        } catch (SQLException ex) {
            System.err.println("कनेक्शन बंद करने में एरर: " + ex.getMessage());
        }
    }
}
