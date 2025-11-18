// String availability = textField4.getText();//es dabe me jo likha ha ussa lo 


//matalb eski jagah yah dedo matalb usme lakar daldo sir 
//textField3.setText(resultSet.getString("room"));  // Room number को textField3 में सेट करें
                    




package Employee;

import javax.swing.*;
import java.awt.*; // Color class ke liye zaroori import
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UpdateRoom extends JFrame {

    // Constructor
    UpdateRoom() {
    
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 940, 490);
        panel.setBackground(new Color(3, 45, 48)); // Dark greenish-blue color
        panel.setLayout(null);
        add(panel);

        JLabel label = new JLabel("Update Room Status");
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
        label.setForeground(Color.white);
        panel.add(label3);

        JLabel label4 = new JLabel("Availablity");
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

        JLabel label5 = new JLabel("Clean Status");
        label5.setBounds(25,216,97,14);
        label5.setFont(new Font("Tahoma",Font.BOLD,14));
        label5.setForeground(Color.white);
        panel.add(label5);

        JTextField textField5 = new JTextField();
        textField5.setBounds(248,216,140,20);
        panel.add(textField5);

        JButton button = new JButton("Update");
        button.setBounds(168,378,89,23);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        panel.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // वैलिडेशन: सभी फील्ड भरे हैं या नहीं चेक करें
                    if (textField3.getText().isEmpty() || textField4.getText().isEmpty() || textField5.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please fill all fields and select an ID.");
                        return; // अपडेट न करें
                    }

                    Vo c = new Vo();
                    String availability = textField4.getText();//es dabe me jo likha ha ussa lo 
                    String statu = textField5.getText();
                    String roomNumber = textField3.getText();

                    // PreparedStatement इस्तेमाल करें (सुरक्षा के लिए)
                    String query = "UPDATE room SET availability = ?, cleaning_status = ? WHERE roomnumber = ?";
                    PreparedStatement pst = c.connection.prepareStatement(query);
                    pst.setString(1, availability);
                    pst.setString(2, statu);
                    pst.setString(3, roomNumber); // अगर roomnumber int है, तो pst.setInt(3, Integer.parseInt(roomNumber));

                    int rowsAffected = pst.executeUpdate();
                    pst.close();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Room updated successfully!");
                        setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "No room found with the given number.");
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
                String selectedItem = k.getSelectedItem();  // यह सही है: k.getSelectedItem() इस्तेमाल करें
                
                try {
                    Vo c = new Vo();
                    
                    // पहली क्वेरी: customer से डेटा (PreparedStatement इस्तेमाल, सही क्वेरी)
                    String q = "select * from customer where number = ?";
                    PreparedStatement pst = c.connection.prepareStatement(q);
                    pst.setString(1, selectedItem);
                    ResultSet resultSet = pst.executeQuery();
                    
                    while (resultSet.next()) {
                        //matalb eski jagah yah dedo matalb usme lakar daldo sir 
                        textField3.setText(resultSet.getString("room"));  // Room number को textField3 में सेट करें
                    }
                    
                    resultSet.close();
                    pst.close();
                    
                    // दूसरी क्वेरी: room से डेटा (PreparedStatement इस्तेमाल)
                    String q2 = "select * from room where roomnumber = ?";
                    PreparedStatement pst2 = c.connection.prepareStatement(q2);
                    pst2.setString(1, textField3.getText());
                    ResultSet resultSet1 = pst2.executeQuery();
                    
                    while (resultSet1.next()) {
                        textField4.setText(resultSet1.getString("availability"));
                        textField5.setText(resultSet1.getString("cleaning_status"));
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
        setSize(950, 450);
        setLayout(null);
        setLocation(400, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // window close hone par exit kare
    }

    public static void main(String[] args) {
        new UpdateRoom(); // Frame ka object create
    }
}
