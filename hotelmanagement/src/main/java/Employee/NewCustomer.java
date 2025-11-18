package Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NewCustomer extends JFrame implements ActionListener {

    JComboBox<String> comboBox;
    JTextField textFieldNumber, TextName, TextCountry, TextDeposit;
    JRadioButton r1, r2;
    Choice c1;
    JLabel date;
    JButton add, back;

    public NewCustomer() {
        setTitle("New Customer");
        setSize(850, 550);
        setLocation(500, 150);
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 840, 540);
        panel.setLayout(null);
        panel.setBackground(new Color(3, 45, 48));
        add(panel);

        JLabel label = new JLabel("NEW CUSTOMER FORM");
        label.setBounds(118, 11, 260, 53);
        label.setFont(new Font("Tahoma", Font.BOLD, 20));
        label.setForeground(Color.WHITE);
        panel.add(label);

        JLabel labelNumber = new JLabel("Number");
        labelNumber.setBounds(35, 111, 200, 14);
        labelNumber.setForeground(Color.WHITE);
        labelNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel.add(labelNumber);

        textFieldNumber = new JTextField();
        textFieldNumber.setBounds(271, 111, 150, 20);
        panel.add(textFieldNumber);

        JLabel labelIdType = new JLabel("ID Type");
        labelIdType.setBounds(35, 76, 200, 14);
        labelIdType.setForeground(Color.WHITE);
        labelIdType.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel.add(labelIdType);

        comboBox = new JComboBox<>(new String[]{"Passport", "Aadhar Card", "Voter Id", "Driving License"});
        comboBox.setBounds(271, 73, 150, 20);
        comboBox.setForeground(Color.WHITE);
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
        comboBox.setBackground(new Color(3, 45, 48));
        panel.add(comboBox);

        JLabel labelName = new JLabel("Name");
        labelName.setBounds(35, 151, 200, 14);
        labelName.setForeground(Color.WHITE);
        labelName.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel.add(labelName);

        TextName = new JTextField();
        TextName.setBounds(271, 151, 150, 20);
        panel.add(TextName);

        JLabel labelGender = new JLabel("Gender");
        labelGender.setBounds(35, 191, 200, 14);
        labelGender.setForeground(Color.WHITE);
        labelGender.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel.add(labelGender);

        r1 = new JRadioButton("Male");
        r1.setFont(new Font("Tahoma", Font.BOLD, 14));
        r1.setForeground(Color.WHITE);
        r1.setBackground(new Color(3, 45, 48));
        r1.setBounds(271, 191, 80, 20);
        panel.add(r1);

        r2 = new JRadioButton("Female");
        r2.setFont(new Font("Tahoma", Font.BOLD, 14));
        r2.setForeground(Color.WHITE);
        r2.setBackground(new Color(3, 45, 48));
        r2.setBounds(350, 191, 80, 20);
        panel.add(r2);

        ButtonGroup bg = new ButtonGroup();
        bg.add(r1);
        bg.add(r2);

        JLabel labelCountry = new JLabel("Country");
        labelCountry.setBounds(35, 231, 200, 14);
        labelCountry.setForeground(Color.WHITE);
        labelCountry.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel.add(labelCountry);

        TextCountry = new JTextField();
        TextCountry.setBounds(271, 231, 150, 20);
        panel.add(TextCountry);

        JLabel labelRoom = new JLabel("Allocated Room Number");
        labelRoom.setBounds(35, 274, 200, 14);
        labelRoom.setForeground(Color.WHITE);
        labelRoom.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel.add(labelRoom);

        c1 = new Choice();
        populateRooms();

        c1.setBounds(271, 274, 150, 20);
        c1.setFont(new Font("Tahoma", Font.BOLD, 14));
        c1.setBackground(new Color(3, 45, 48));
        c1.setForeground(Color.WHITE);
        panel.add(c1);

        JLabel labelCheckIn = new JLabel("Checked-In:");
        labelCheckIn.setBounds(35, 316, 200, 14);
        labelCheckIn.setForeground(Color.WHITE);
        labelCheckIn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel.add(labelCheckIn);

        // Fixed: Use LocalDateTime for proper date formatting
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = now.format(formatter);
        date = new JLabel(formattedDate);
        date.setBounds(271, 316, 200, 14);
        date.setForeground(Color.WHITE);
        date.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel.add(date);

        JLabel labelDeposit = new JLabel("Deposit");
        labelDeposit.setBounds(35, 359, 200, 14);
        labelDeposit.setForeground(Color.WHITE);
        labelDeposit.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel.add(labelDeposit);

        TextDeposit = new JTextField();
        TextDeposit.setBounds(271, 359, 150, 20);
        panel.add(TextDeposit);

        add = new JButton("ADD");
        add.setBounds(100, 430, 120, 30);
        add.setForeground(Color.WHITE);
        add.setBackground(Color.BLACK);
        add.addActionListener(this);
        panel.add(add);

        back = new JButton("BACK");
        back.setBounds(300, 430, 120, 30);
        back.setForeground(Color.WHITE);
        back.setBackground(Color.BLACK);
        back.addActionListener(this);
        panel.add(back);

        setVisible(true);
    }

    private void populateRooms() {
        c1.removeAll();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            Vo c = new Vo();
            String query = "select * from room where availability = 'Available'";
            pst = c.connection.prepareStatement(query);
            rs = pst.executeQuery();
            
            while (rs.next()) {
                c1.add(rs.getString("roomnumber"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading rooms: " + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            // Validation
            if (comboBox.getSelectedItem() == null ||
                    textFieldNumber.getText().trim().isEmpty() ||
                    TextName.getText().trim().isEmpty() ||
                    TextCountry.getText().trim().isEmpty() ||
                    c1.getSelectedItem() == null ||
                    TextDeposit.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all required fields.");
                return;
            }

            if (!r1.isSelected() && !r2.isSelected()) {
                JOptionPane.showMessageDialog(null, "Select gender");
                return;
            }

            // Fixed: Additional validation for deposit being a number
            try {
                Double.parseDouble(TextDeposit.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Deposit must be a valid number.");
                return;
            }

            // Collect data
            String s1 = (String) comboBox.getSelectedItem();
            String s2 = textFieldNumber.getText();
            String s3 = TextName.getText();
            String s4 = r1.isSelected() ? "MALE" : "FEMALE";
            String s5 = TextCountry.getText();
            String s6 = c1.getSelectedItem();
            String s7 = date.getText();
            String s8 = TextDeposit.getText();

            PreparedStatement pst1 = null;
            PreparedStatement pst = null;
            PreparedStatement checkPst = null;
            ResultSet rs = null;

            try {
                Vo c = new Vo();

                // Fixed: Check room availability to prevent double-booking
                String checkQuery = "SELECT availability FROM room WHERE roomnumber = ?";
                checkPst = c.connection.prepareStatement(checkQuery);
                checkPst.setString(1, s6);
                rs = checkPst.executeQuery();
                if (rs.next() && !"Available".equals(rs.getString("availability"))) {
                    JOptionPane.showMessageDialog(null, "Room is no longer available.");
                    return;
                }

                // Fixed: Use PreparedStatement for UPDATE to prevent SQL injection
                String q1 = "UPDATE room SET availability = 'Occupied' WHERE roomnumber = ?";
                pst1 = c.connection.prepareStatement(q1);
                pst1.setString(1, s6);
                pst1.executeUpdate();

                // Fixed: Use PreparedStatement for INSERT to prevent SQL injection
                String q = "INSERT INTO customer VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                pst = c.connection.prepareStatement(q);
                pst.setString(1, s1);  // ID Type
                pst.setString(2, s2);  // Number
                pst.setString(3, s3);  // Name
                pst.setString(4, s4);  // Gender
                pst.setString(5, s5);  // Country
                pst.setString(6, s6);  // Room Number
                pst.setString(7, s7);  // Check-in Date
                pst.setString(8, s8);  // Deposit
                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "Added successfully");

                // Clear fields
                textFieldNumber.setText("");
                TextName.setText("");
                TextCountry.setText("");
                TextDeposit.setText("");
                r1.setSelected(false);
                r2.setSelected(false);
                comboBox.setSelectedIndex(0);

                populateRooms();

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error adding customer: " + ex.getMessage());
            } 



        
        } else if (e.getSource() == back) {
            this.setVisible(false);
            // Optionally open previous screen here
        }
    }

    public static void main(String[] args) {
        new NewCustomer();
    }
}
