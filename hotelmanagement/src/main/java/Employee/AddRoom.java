package Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class AddRoom extends JFrame implements ActionListener {
    JTextField t2, t4;
    JComboBox t3, t5, t6;
    JButton b1, b2;

    AddRoom() {

        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 875, 490);
        panel.setBackground(new Color(3, 45, 48));
        panel.setLayout(null);
        add(panel);

        JLabel l1 = new JLabel("Add Rooms");
        l1.setBounds(194, 10, 160, 22);
        l1.setFont(new Font("Tahoma", Font.ITALIC, 22));
        panel.add(l1);

        JLabel l2 = new JLabel("Room Number");
        l2.setBounds(64, 70, 152, 22);
        l2.setFont(new Font("Tahoma", Font.BOLD, 14));
        l2.setForeground(Color.WHITE);
        panel.add(l2);

        t2 = new JTextField();
        t2.setBounds(200, 70, 156, 20);
        t2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        t2.setForeground(Color.WHITE);
        t2.setBackground(new Color(16, 108, 115));
        panel.add(t2);

        JLabel l3 = new JLabel("Availability");
        l3.setBounds(64, 110, 152, 22);
        l3.setFont(new Font("Tahoma", Font.BOLD, 14));
        l3.setForeground(Color.WHITE);
        panel.add(l3);

        t3 = new JComboBox(new String[]{"Available", "Occupied"});
        t3.setBounds(200, 110, 156, 20);
        t3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        t3.setForeground(Color.WHITE);
        t3.setBackground(new Color(16, 108, 115));
        panel.add(t3);

        JLabel l4 = new JLabel("Price");
        l4.setBounds(64, 170, 152, 22);
        l4.setFont(new Font("Tahoma", Font.BOLD, 14));
        l4.setForeground(Color.WHITE);
        panel.add(l4);

        t4 = new JTextField();
        t4.setBounds(200, 170, 156, 20);
        t4.setFont(new Font("Tahoma", Font.PLAIN, 14));
        t4.setForeground(Color.WHITE);
        t4.setBackground(new Color(16, 108, 115));
        panel.add(t4);

        JLabel l5 = new JLabel("Cleaning Status");
        l5.setBounds(64, 190, 152, 22);
        l5.setFont(new Font("Tahoma", Font.BOLD, 14));
        l5.setForeground(Color.WHITE);
        panel.add(l5);

        t5 = new JComboBox(new String[]{"Cleaned", "Dirty"});
        t5.setBounds(200, 190, 156, 20);
        t5.setFont(new Font("Tahoma", Font.PLAIN, 14));
        t5.setForeground(Color.WHITE);
        t5.setBackground(new Color(16, 108, 115));
        panel.add(t5);

        JLabel l6 = new JLabel("Bed Type");
        l6.setBounds(64, 230, 152, 22);
        l6.setFont(new Font("Tahoma", Font.BOLD, 14));
        l6.setForeground(Color.WHITE);
        panel.add(l6);

        t6 = new JComboBox(new String[]{"Single Bed", "Double Bed"});
        t6.setBounds(200, 230, 156, 20);
        t6.setFont(new Font("Tahoma", Font.PLAIN, 14));
        t6.setForeground(Color.WHITE);
        t6.setBackground(new Color(16, 108, 115));
        panel.add(t6);

        b1 = new JButton("Add");
        b1.setBounds(64, 321, 111, 33);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        panel.add(b1);

        b2 = new JButton("Back");
        b2.setBounds(198, 321, 111, 33);
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        panel.add(b2);

        // Frame settings
        setLocation(50, 50);
        setLayout(null);
        setSize(880, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == b1){
            // Basic validation
            if (t2.getText().trim().isEmpty() || t4.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill Room Number and Price fields.");
                return;
            }

            Vo c = null;  // Vo instance declare karo
            Statement stmt = null;  // Statement declare karo (Yeh main fix hai)

            try{
                c = new Vo();  // Vo create karo
                String room = t2.getText().trim();
                String ava = (String) t3.getSelectedItem();
                String status = (String) t5.getSelectedItem();
                String price = t4.getText().trim();
                String type = (String) t6.getSelectedItem();

                // Price numeric validation (kyunki price integer/decimal honi chahiye)
                try {
                    Double.parseDouble(price);  // Number check
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Price must be a number (e.g., 1500 or 1500.50)!");
                    return;
                }

                // Updated Query: Price numeric (no quotes, jaise aapne kaha integer hai)
                String q = "INSERT INTO room (roomnumber, availability, cleaning_status, price, bed_type) " +
                        "VALUES ('" + room + "', '" + ava + "', '" + status + "', " + price + ", '" + type + "')";




                // Debug: Query print
                System.out.println("Executing Query: " + q);

                // Statement create aur execute (proper declaration)
                stmt = c.connection.createStatement();
                stmt.executeUpdate(q);//es time query chalegi sir   // Database mein value insert hogi

                JOptionPane.showMessageDialog(null, "Room Successfully Added");
                // Clear fields
                t2.setText("");
                t4.setText("");
                t3.setSelectedIndex(0);
                t5.setSelectedIndex(0);
                t6.setSelectedIndex(0);

            } catch (SQLException K){
                K.printStackTrace();
                JOptionPane.showMessageDialog(null, "Database Error: " + K.getMessage() + "\n\nCheck table 'room' columns and price type (DECIMAL/INT).");
            } catch (Exception K){
                K.printStackTrace();
                JOptionPane.showMessageDialog(null, "Unexpected error: " + K.getMessage());
            } finally {
                // Resources close (safe practice)
                try {
                    if (stmt != null) stmt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        else if(e.getSource() == b2){
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new AddRoom();
    }
}
