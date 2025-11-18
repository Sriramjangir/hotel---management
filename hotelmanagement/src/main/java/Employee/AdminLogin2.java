package Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;  // JDBC के लिए

public class AdminLogin2 extends JFrame implements ActionListener {
    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton;

    // DatabaseConnection का इंस्टेंस (Singleton से)
    private DatabaseConnection dbConnection;

    public AdminLogin2() {
        super("Admin Login - HOTEL MANAGEMENT SYSTEM");

        // DatabaseConnection इंस्टेंस बनाएँ
        dbConnection = DatabaseConnection.getInstance();

        setLayout(null);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Username label और field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(20, 20, 100, 25);
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(120, 20, 150, 25);
        add(usernameField);

        // Password label और field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(20, 60, 100, 25);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(120, 60, 150, 25);
        add(passwordField);

        // Login button
        loginButton = new JButton("Login");
        loginButton.setBounds(100, 100, 80, 30);
        loginButton.setBackground(new Color(255, 98, 0));
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(this);
        add(loginButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            // खाली फील्ड चेक करें
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Username और Password भरें!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // डेटाबेस से चेक करें (DatabaseConnection से कनेक्शन लें)
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;


            try {
                conn = dbConnection.getConnection();  // कनेक्शन लें

                if (conn == null) {
                    JOptionPane.showMessageDialog(this, "डेटाबेस कनेक्शन नहीं हो सका!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // SQL क्वेरी: sadak टेबल से username और password मैच चेक करें
                String sql = "SELECT * FROM sadak WHERE username = ? AND password = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, username);
                pstmt.setString(2, password);

                rs = pstmt.executeQuery();

                if (rs.next()) {  // अगर रिकॉर्ड मिला
                    JOptionPane.showMessageDialog(this, "लॉगिन सफल! स्वागत है, " + username + "।");
                    new admin();
                    setVisible(false);// लॉगिन विंडो बंद करें

                    // AdminDashboard खोलें (सफल लॉगिन पर)
                      // अगर AdminDashboard क्लास पहले से बनी हो
                } else {
                    JOptionPane.showMessageDialog(this, "गलत Username या Password!", "Error", JOptionPane.ERROR_MESSAGE);
                    passwordField.setText("");  // Password क्लियर करें
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "डेटाबेस एरर: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            } finally {
                // रिसोर्सेज बंद करें (कनेक्शन बंद न करें, Singleton हैंडल करेगा)
                try {
                    if (rs != null) rs.close();
                    if (pstmt != null) pstmt.close();
                    // conn.close();  // न बंद करें, DatabaseConnection हैंडल करेगी
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
