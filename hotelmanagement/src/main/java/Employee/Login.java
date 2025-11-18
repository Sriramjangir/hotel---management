package Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class Vo {
    Connection connection;

    public Vo() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  // MySQL 8+
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/last", "root", "Skj@8949"); // replace with your DB credentials
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database connection failed: " + e.getMessage());
        }
    }
}

public class Login extends JFrame implements ActionListener {

    JTextField textField1;
    JPasswordField passwordField1;
    JButton b1, b2;

    public Login() {
        // Username Label
        JLabel label1 = new JLabel("Username");
        label1.setBounds(40, 20, 100, 30);
        label1.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(label1);

        // Password Label
        JLabel label2 = new JLabel("Password");
        label2.setBounds(40, 70, 100, 30);
        label2.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(label2);

        // Username TextField
        textField1 = new JTextField();
        textField1.setBounds(150, 20, 150, 30);
        add(textField1);

        // Password Field
        passwordField1 = new JPasswordField();
        passwordField1.setBounds(150, 70, 150, 30);
        add(passwordField1);

        // Login Button
        b1 = new JButton("Login");
        b1.setBounds(40, 140, 120, 30);
        b1.addActionListener(this);
        add(b1);

        // Cancel Button
        b2 = new JButton("Cancel");
        b2.setBounds(180, 140, 120, 30);
        b2.addActionListener(this);
        add(b2);

        setLayout(null);
        setSize(900, 500);
        setLocationRelativeTo(null);  // Center screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            try {
                Vo c = new Vo();
                String user = textField1.getText().trim();
                String pass = new String(passwordField1.getPassword()).trim();

                // Keep your query exactly as you want
                String q = "SELECT * FROM last.tera WHERE username = '" + user + "' AND password = '" + pass + "'";
                PreparedStatement pst = c.connection.prepareStatement(q);

            
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    new Dashboard();
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password!");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        } else if (e.getSource() == b2) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
