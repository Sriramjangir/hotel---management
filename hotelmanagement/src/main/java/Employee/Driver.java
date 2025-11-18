package Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Driver extends JFrame implements ActionListener {
    JTextField nameText, ageText, carCText, carNText, locNText;
    JComboBox<String> comboBox, comboBox1;
    JButton add, back;
    Statement stmt;  // Local in method is better, but field ok

    Driver() {
        System.out.println("Driver constructor started...");  // FIXED: Debug print

        setLayout(null);
        setSize(900, 500);
        setLocation(20, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel and components setup
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 890, 490);
        panel.setBackground(new Color(3, 45, 48));
        panel.setLayout(null);
        add(panel);

        JLabel label = new JLabel("ADD DRIVERS");
        label.setBounds(194, 10, 200, 22);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Tahoma", Font.BOLD, 22));
        panel.add(label);

        JLabel name = new JLabel("NAME");
        name.setBounds(64, 70, 102, 22);
        name.setFont(new Font("Tahoma", Font.BOLD, 14));
        name.setForeground(Color.WHITE);
        panel.add(name);

        nameText = new JTextField();
        nameText.setBounds(174, 70, 156, 20);
        nameText.setForeground(Color.WHITE);
        nameText.setFont(new Font("Tahoma", Font.BOLD, 14));
        nameText.setBackground(new Color(16, 108, 115));
        panel.add(nameText);

        JLabel age = new JLabel("AGE");
        age.setBounds(64, 110, 102, 22);
        age.setFont(new Font("Tahoma", Font.BOLD, 14));
        age.setForeground(Color.WHITE);
        panel.add(age);
        ageText = new JTextField();
        ageText.setBounds(174, 110, 156, 20);
        ageText.setForeground(Color.WHITE);
        ageText.setFont(new Font("Tahoma", Font.BOLD, 14));
        ageText.setBackground(new Color(16, 108, 115));
        panel.add(ageText);

        JLabel gender = new JLabel("GENDER");
        gender.setBounds(64, 150, 102, 22);
        gender.setFont(new Font("Tahoma", Font.BOLD, 14));
        gender.setForeground(Color.WHITE);
        panel.add(gender);
        comboBox = new JComboBox<>(new String[]{"MALE", "FEMALE"});
        comboBox.setBounds(176, 150, 154, 20);
        comboBox.setForeground(Color.WHITE);
        comboBox.setFont(new Font("Tahoma", Font.BOLD, 14));
        comboBox.setBackground(new Color(16, 108, 115));
        panel.add(comboBox);

        JLabel carC = new JLabel("CAR COMPANY");
        carC.setBounds(64, 190, 102, 22);
        carC.setFont(new Font("Tahoma", Font.BOLD, 14));
        carC.setForeground(Color.WHITE);
        panel.add(carC);
        carCText = new JTextField();
        carCText.setBounds(174, 190, 156, 20);
        carCText.setForeground(Color.WHITE);
        carCText.setFont(new Font("Tahoma", Font.BOLD, 14));
        carCText.setBackground(new Color(16, 108, 115));
        panel.add(carCText);

        JLabel carN = new JLabel("CAR NAME");
        carN.setBounds(64, 230, 112, 22);
        carN.setFont(new Font("Tahoma", Font.BOLD, 14));
        carN.setForeground(Color.WHITE);
        panel.add(carN);
        carNText = new JTextField();
        carNText.setBounds(174, 230, 156, 20);
        carNText.setForeground(Color.WHITE);
        carNText.setFont(new Font("Tahoma", Font.BOLD, 14));
        carNText.setBackground(new Color(16, 108, 115));
        panel.add(carNText);

        JLabel available = new JLabel("AVAILABLE");
        available.setBounds(64, 270, 102, 22);
        available.setFont(new Font("Tahoma", Font.BOLD, 14));
        available.setForeground(Color.WHITE);
        panel.add(available);
        comboBox1 = new JComboBox<>(new String[]{"YES", "NO"});
        comboBox1.setBounds(176, 270, 154, 20);
        comboBox1.setForeground(Color.WHITE);
        comboBox1.setFont(new Font("Tahoma", Font.BOLD, 14));
        comboBox1.setBackground(new Color(16, 108, 115));
        panel.add(comboBox1);

        JLabel loc = new JLabel("LOCATION");
        loc.setBounds(64, 310, 102, 22);
        loc.setFont(new Font("Tahoma", Font.BOLD, 14));
        loc.setForeground(Color.WHITE);
        panel.add(loc);
        locNText = new JTextField();
        locNText.setBounds(174, 310, 156, 20);
        locNText.setForeground(Color.WHITE);
        locNText.setFont(new Font("Tahoma", Font.BOLD, 14));
        locNText.setBackground(new Color(16, 108, 115));
        panel.add(locNText);

        add = new JButton("ADD");
        add.setBounds(64, 380, 111, 33);
        add.setForeground(Color.WHITE);
        add.setBackground(Color.BLACK);
        add.addActionListener(this);
        panel.add(add);

        back = new JButton("BACK");
        back.setBounds(200, 380, 111, 33);
        back.setForeground(Color.WHITE);
        back.setBackground(Color.BLACK);
        back.addActionListener(this);
        panel.add(back);

        // FIXED: setVisible(true) को अंत में रखा – अब GUI दिखेगा!
        System.out.println("All components added. Setting visible...");  // Debug
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent J) {
        if (J.getSource() == add) {
            if (nameText.getText().trim().isEmpty() || ageText.getText().trim().isEmpty() ||
                    carCText.getText().trim().isEmpty() || carNText.getText().trim().isEmpty() ||
                    locNText.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Fill all the fields!");
                return;
            }

            String gender = (String) comboBox.getSelectedItem();
            String available = (String) comboBox1.getSelectedItem();

            String name = nameText.getText().trim();
            String age = ageText.getText().trim();
            String carC = carCText.getText().trim();
            String carN = carNText.getText().trim();
            String locn = locNText.getText().trim();

            try {
                Integer.parseInt(age);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Age must be a number (e.g., 25)!");
                return;
            }

            String q = "INSERT INTO drivers (namji, ageji, genderjo, car_companyhi, car_nameko, availableoo, locationyu) " +
                    "VALUES ('" + name + "', " + age + ", '" + gender + "', '" + carC + "', '" + carN + "', '" + available + "', '" + locn + "')";
            System.out.println("Executing Query: " + q);

            Vo c = null;
            Statement stmt = null;

            try {
                c = new Vo();
                stmt = c.connection.createStatement();
                stmt.executeUpdate(q);

                JOptionPane.showMessageDialog(null, "Driver Successfully Added");
                nameText.setText("");
                ageText.setText("");
                carCText.setText("");
                carNText.setText("");
                locNText.setText("");
                comboBox.setSelectedIndex(0);
                comboBox1.setSelectedIndex(0);

            } catch (SQLException K) {
                K.printStackTrace();
                JOptionPane.showMessageDialog(null, "Database Error: " + K.getMessage() + "\n\nCheck table 'drivers' columns and data types.");
            } catch (Exception K) {
                K.printStackTrace();
                JOptionPane.showMessageDialog(null, "Unexpected error: " + K.getMessage());
            } finally {
                try {
                    if (stmt != null) stmt.close();
                    
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } else if (J.getSource() == back) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Driver();
    }
}
