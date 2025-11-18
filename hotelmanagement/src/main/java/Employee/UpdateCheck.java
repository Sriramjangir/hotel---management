package Employee;

import javax.swing.*;
import java.awt.*; // Color class ke liye zaroori import
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UpdateCheck extends JFrame {

    // Constructor
    UpdateCheck() {
    
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 940, 490);
        panel.setBackground(new Color(3, 45, 48)); // Dark greenish-blue color
        panel.setLayout(null);
        add(panel);

        JLabel label = new JLabel("Check-In Details");
        label.setBounds(124,11,222,25);
        label.setFont(new Font("Tahoma",Font.BOLD,14));
        label.setForeground(Color.white);
        panel.add(label);

        JLabel label2 = new JLabel("ID");
        label2.setBounds(25,88,46,14);
        label2.setFont(new Font("Tahoma",Font.BOLD,14));
        label2.setForeground(Color.white);
        panel.add(label2);

        Choice k = new Choice();
        k.setBounds(248,85,140,20);
        panel.add(k);

        try {
            Vo c = new Vo();
            String query = "SELECT number FROM customer";  // ID के लिए 'number' सेलेक्ट करें
            PreparedStatement pst = c.connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                k.add(rs.getString("number"));  // ID (number) को Choice में एड करें
            }

            rs.close();
            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel label3 = new JLabel("Room Number");
        label3.setBounds(25,129,107,14);
        label3.setFont(new Font("Tahoma",Font.BOLD,14));
        label3.setForeground(Color.white);
        panel.add(label3);

        JLabel label4 = new JLabel("Name");
        label4.setBounds(25,174,97,14);
        label4.setFont(new Font("Tahoma",Font.BOLD,14));
        label4.setForeground(Color.white);
        panel.add(label4);

        JTextField textField3 = new JTextField();
        textField3.setBounds(248,129,140,20);
        panel.add(textField3);

        JTextField textField4 = new JTextField();
        textField4.setBounds(248,174,140,20);
        panel.add(textField4);

        JLabel label5 = new JLabel("Check-In");
        label5.setBounds(25,216,97,14);
        label5.setFont(new Font("Tahoma",Font.BOLD,14));
        label5.setForeground(Color.white);
        panel.add(label5);

        JTextField textField5 = new JTextField();
        textField5.setBounds(248,216,140,20);
        panel.add(textField5);

        JLabel label6 = new JLabel("Amount Paid(Rs)");
        label6.setBounds(25,261,150,14);
        label6.setFont(new Font("Tahoma",Font.BOLD,14));
        label6.setForeground(Color.white);
        panel.add(label6);

        JTextField textField6 = new JTextField();
        textField6.setBounds(248,261,140,20);
        panel.add(textField6);

        JLabel label7 = new JLabel("Pending Amount(Rs)");
        label7.setBounds(25,302,150,14);
        label7.setFont(new Font("Tahoma",Font.BOLD,14));
        label7.setForeground(Color.white);
        panel.add(label7);

        JTextField textField7 = new JTextField();
        textField7.setBounds(248,302,140,20);
        panel.add(textField7);

        JButton button = new JButton("Update");
        button.setBounds(168,378,89,23);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        panel.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Vo c = new Vo();
                    String q = k.getSelectedItem();
                    String room = textField3.getText();
                    String name = textField4.getText();
                    String check = textField5.getText();
                    String amount = textField6.getText();
                    
                    // Basic validation
                    if (q == null || room.isEmpty() || name.isEmpty() || check.isEmpty() || amount.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please fill all fields and select an ID.");
                        return;
                    }
                    
                    // Use PreparedStatement for security and correctness
                    String updateQuery = "UPDATE customer SET room = ?, name = ?, checkintime = ?, deposit = ? WHERE number = ?";
                    PreparedStatement pst = c.connection.prepareStatement(updateQuery);
                    pst.setString(1, room);
                    pst.setString(2, name);
                    pst.setString(3, check);
                    pst.setString(4, amount);
                    pst.setString(5, q);
                    
                    int rowsAffected = pst.executeUpdate();  // Execute the update
                    pst.close();
                    
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Updated Successfully");
                        setVisible(false);  // Close window on success
                    } else {
                        JOptionPane.showMessageDialog(null, "Update failed. No matching record found for ID: " + q);
                    }
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error updating: " + ex.getMessage());
                }
            }
        });

        JButton back = new JButton("Back");
        back.setBounds(281,378,89,23);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        panel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                setVisible(false);
            }
        });

        JButton v = new JButton("Check");
        v.setBounds(380,378,89,23);
        v.setBackground(Color.BLACK);
        v.setForeground(Color.WHITE);
        panel.add(v);
        v.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String selectedItem = k.getSelectedItem();
                
                try {
                    Vo c = new Vo();
                    
                    // पहली क्वेरी: customer से डेटा (PreparedStatement इस्तेमाल, अब 'number' के आधार पर)
                    String q = "select * from customer where number = ?";
                    PreparedStatement pst = c.connection.prepareStatement(q);
                    pst.setString(1, selectedItem);
                    ResultSet resultSet = pst.executeQuery();
                    
                    while (resultSet.next()) {
                        textField3.setText(resultSet.getString("room"));
                        textField4.setText(resultSet.getString("name"));
                        textField5.setText(resultSet.getString("checkintime"));
                        textField6.setText(resultSet.getString("deposit"));
                    }
                    
                    resultSet.close();
                    pst.close();
                    
                    // दूसरी क्वेरी: room से डेटा (PreparedStatement इस्तेमाल)
                    String q2 = "select * from room where roomnumber = ?";
                    PreparedStatement pst2 = c.connection.prepareStatement(q2);
                    pst2.setString(1, textField3.getText());
                    ResultSet resultSet1 = pst2.executeQuery();
                    
                    while (resultSet1.next()) {
                        String price = resultSet1.getString("price");
                        int amountPaid = Integer.parseInt(price) - Integer.parseInt(textField6.getText());
                        textField7.setText("" + amountPaid);
                    }
                    
                    resultSet1.close();
                    pst2.close();
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error fetching data: " + ex.getMessage());
                }
            }
        });

        setTitle("Update Check Window"); // optional title
        setSize(950, 500);
        setLayout(null);
        setLocation(400, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // window close hone par exit kare
    }

    public static void main(String[] args) {
        new UpdateCheck(); // Frame ka object create
    }
}
