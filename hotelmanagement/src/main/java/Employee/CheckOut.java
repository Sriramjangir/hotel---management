package Employee;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import javax.swing.*;

public class CheckOut extends JFrame {

    CheckOut() {

        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 790, 390);
        panel.setBackground(new Color(3, 45, 48));
        panel.setLayout(null);
        add(panel);

        JLabel label = new JLabel("Check-Out");
        label.setBounds(100, 20, 100, 30);
        label.setFont(new Font("Tahoma", Font.PLAIN, 20));
        panel.add(label);

        JLabel label2 = new JLabel("Customer ID");
        label2.setBounds(30, 80, 100, 30);
        label2.setFont(new Font("Tahoma", Font.BOLD, 14));
        label2.setForeground(Color.WHITE);
        panel.add(label2);

        Choice k = new Choice();
        k.setBounds(200, 80, 150, 25);
        panel.add(k);

        JLabel roomNum = new JLabel("Room Number");
        roomNum.setBounds(30, 130, 100, 30);
        roomNum.setFont(new Font("Tahoma", Font.BOLD, 14));
        roomNum.setForeground(Color.WHITE);
        panel.add(roomNum);

        JLabel lableRoomnumber = new JLabel();
        lableRoomnumber.setBounds(200, 130, 100, 30);
        lableRoomnumber.setFont(new Font("Tahoma", Font.BOLD, 14));
        lableRoomnumber.setForeground(Color.WHITE);
        panel.add(lableRoomnumber);

        JLabel checkintime = new JLabel("Check In Time");
        checkintime.setBounds(30, 180, 100, 30);
        checkintime.setFont(new Font("Tahoma", Font.BOLD, 14));
        checkintime.setForeground(Color.WHITE);
        panel.add(checkintime);

        // Fixed: Corrected bounds to align with checkintime label
        JLabel labelchecintime = new JLabel();
        labelchecintime.setBounds(200, 180, 100, 30);
        labelchecintime.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelchecintime.setForeground(Color.WHITE);
        panel.add(labelchecintime);

        // Fixed: Corrected typo in label text
        JLabel checkOuttime = new JLabel("Check-Out Time");
        checkOuttime.setBounds(30, 230, 100, 30);
        checkOuttime.setFont(new Font("Tahoma", Font.BOLD, 14));
        checkOuttime.setForeground(Color.WHITE);
        panel.add(checkOuttime);

        Date date = new Date();

        JLabel labelcheckouttime = new JLabel("" + date);
        labelcheckouttime.setBounds(200, 230, 250, 30);
        labelcheckouttime.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelcheckouttime.setForeground(Color.WHITE);
        panel.add(labelcheckouttime);

        try {
            Vo c = new Vo();
            String s = "select * from customer ";
            PreparedStatement ps = c.connection.prepareStatement(s);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                k.add(rs.getString("number"));  // ID (number) को Choice में एड करें
            }

            rs.close();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        JButton checkout = new JButton("Check-Out");
        checkout.setBounds(30, 300, 120, 30);
        checkout.setForeground(Color.WHITE);
        checkout.setBackground(Color.BLACK);
        panel.add(checkout);
        checkout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Vo c = new Vo();
                    // Fixed: Use prepared statements for security and fix spelling in SQL
                    PreparedStatement psDelete = c.connection.prepareStatement("delete from customer where number = ?");
                    psDelete.setString(1, k.getSelectedItem());
                    psDelete.executeUpdate();
                    psDelete.close();

                    PreparedStatement psUpdate = c.connection.prepareStatement("update room set availability = 'Available' where roomnumber = ?");
                    psUpdate.setString(1, lableRoomnumber.getText());
                    psUpdate.executeUpdate();
                    psUpdate.close();

                    // Fixed: Corrected JOptionPane syntax
                    JOptionPane.showMessageDialog(null, "Done");
                    setVisible(false);

                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        JButton check = new JButton("Check");
        check.setBounds(300, 300, 120, 30);
        check.setForeground(Color.WHITE);
        check.setBackground(Color.BLACK);
        panel.add(check);
        // Updated: Added debugging, null checks, prepared statement, and error handling
        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vo c = new Vo();
                try {
                    String selectedId = k.getSelectedItem();
                    System.out.println("Selected ID: " + selectedId);  // Debug: Check selected item
                    
                    if (selectedId == null || selectedId.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please select a Customer ID.");
                        return;
                    }
                    
                    // Use prepared statement for security
                    PreparedStatement ps = c.connection.prepareStatement("select * from customer where number = ?");
                    ps.setString(1, selectedId);
                    ResultSet resultSet = ps.executeQuery();
                    
                    if (resultSet.next()) {
                        lableRoomnumber.setText(resultSet.getString("room"));
                        labelchecintime.setText(resultSet.getString("checkintime"));
                        System.out.println("Data updated: Room=" + lableRoomnumber.getText() + ", CheckIn=" + labelchecintime.getText());  // Debug
                    } else {
                        JOptionPane.showMessageDialog(null, "No data found for selected customer.");
                    }
                    
                    resultSet.close();
                    ps.close();
                } catch (Exception E) {
                    E.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error: " + E.getMessage());
                }
            }
        });

        JButton back = new JButton("Back");
        back.setBounds(170, 300, 120, 30);
        back.setForeground(Color.WHITE);
        back.setBackground(Color.BLACK);
        panel.add(back);
        // Fixed: Added action listener to back button
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);  // Or dispose(); based on your needs
            }
        });

        setLayout(null);
        setSize(800, 400);
        setLocation(500, 210);
        setVisible(true);
    }

    public static void main(String[] args) {
        new CheckOut();
    }
}
