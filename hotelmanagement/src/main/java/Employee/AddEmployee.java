package Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AddEmployee extends JFrame implements ActionListener {
    JTextField nameText, ageText, salaryText, phoneText, aadharText, emailText;
    JRadioButton radioButtonM, radioButtonF;
    JComboBox<String> comboBox;
    JButton t1, t2;
    ButtonGroup genderGroup;  // To make radio buttons mutually exclusive

    AddEmployee() {
        setLayout(null);
        setSize(900, 500);
        setLocation(60, 160);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Added: Proper close behavior

        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 890, 490);
        panel.setLayout(null);
        panel.setBackground(Color.red);
        add(panel);

        JLabel name = new JLabel("NAME");
        name.setBounds(60, 30, 150, 27);
        name.setFont(new Font("serif", Font.BOLD, 17));
        name.setForeground(Color.WHITE);
        panel.add(name);
        nameText = new JTextField();
        nameText.setBounds(200, 30, 150, 27);
        nameText.setFont(new Font("Tahoma", Font.BOLD, 14));
        nameText.setBackground(Color.WHITE);
        panel.add(nameText);

        JLabel ageLabel = new JLabel("AGE");
        ageLabel.setBounds(60, 80, 150, 27);
        ageLabel.setFont(new Font("serif", Font.BOLD, 17));
        ageLabel.setForeground(Color.WHITE);
        panel.add(ageLabel);
        ageText = new JTextField();
        ageText.setBounds(200, 80, 150, 27);
        ageText.setFont(new Font("Tahoma", Font.BOLD, 14));
        ageText.setBackground(Color.WHITE);
        panel.add(ageText);

        JLabel gender = new JLabel("GENDER");
        gender.setBounds(60, 120, 150, 27);
        gender.setFont(new Font("serif", Font.BOLD, 17));
        gender.setForeground(Color.WHITE);
        panel.add(gender);

        radioButtonM = new JRadioButton("MALE");
        radioButtonM.setBounds(200, 120, 70, 27);
        radioButtonM.setBackground(new Color(3, 45, 48));
        radioButtonM.setFont(new Font("Tahoma", Font.BOLD, 14));
        radioButtonM.setForeground(Color.WHITE);
        panel.add(radioButtonM);

        radioButtonF = new JRadioButton("FEMALE");
        radioButtonF.setBounds(280, 120, 100, 27);
        radioButtonF.setBackground(new Color(3, 45, 48));
        radioButtonF.setFont(new Font("Tahoma", Font.BOLD, 14));
        radioButtonF.setForeground(Color.WHITE);
        panel.add(radioButtonF);

        // Add ButtonGroup for mutual exclusivity
        genderGroup = new ButtonGroup();
        genderGroup.add(radioButtonM);
        genderGroup.add(radioButtonF);

        JLabel job = new JLabel("JOB");
        job.setBounds(60, 170, 150, 27);
        job.setFont(new Font("serif", Font.BOLD, 17));
        job.setForeground(Color.WHITE);
        panel.add(job);

        comboBox = new JComboBox<>(new String[]{"Front Desk", "Housekeeping", "Kitchen Staff", "Room-Service", "Manager", "Accountant", "Chef"});
        comboBox.setBounds(200, 170, 150, 30);
        comboBox.setFont(new Font("Tahoma", Font.BOLD, 14));
        comboBox.setBackground(Color.WHITE);
        panel.add(comboBox);

        JLabel salary = new JLabel("SALARY");
        salary.setBounds(60, 220, 150, 27);
        salary.setFont(new Font("serif", Font.BOLD, 17));
        salary.setForeground(Color.WHITE);
        panel.add(salary);
        salaryText = new JTextField();
        salaryText.setBounds(200, 220, 150, 27);
        salaryText.setFont(new Font("Tahoma", Font.BOLD, 14));
        salaryText.setBackground(Color.WHITE);
        panel.add(salaryText);

        JLabel phone = new JLabel("PHONE NUM");
        phone.setBounds(60, 260, 150, 27);
        phone.setFont(new Font("serif", Font.BOLD, 17));
        phone.setForeground(Color.WHITE);
        panel.add(phone);
        phoneText = new JTextField();
        phoneText.setBounds(200, 260, 150, 27);
        phoneText.setFont(new Font("Tahoma", Font.BOLD, 14));
        phoneText.setBackground(Color.WHITE);
        panel.add(phoneText);

        JLabel adhar = new JLabel("AADHAR");
        adhar.setBounds(60, 290, 150, 27);
        adhar.setFont(new Font("serif", Font.BOLD, 17));
        adhar.setForeground(Color.WHITE);
        panel.add(adhar);
        aadharText = new JTextField();
        aadharText.setBounds(200, 290, 150, 27);
        aadharText.setFont(new Font("Tahoma", Font.BOLD, 14));
        aadharText.setBackground(Color.WHITE);
        panel.add(aadharText);

        JLabel email = new JLabel("E-MAIL");
        email.setBounds(60, 320, 150, 27);
        email.setFont(new Font("serif", Font.BOLD, 17));
        email.setForeground(Color.WHITE);
        panel.add(email);
        emailText = new JTextField();
        emailText.setBounds(200, 320, 150, 27);
        emailText.setFont(new Font("Tahoma", Font.BOLD, 14));
        emailText.setBackground(Color.WHITE);
        panel.add(emailText);

        JLabel aed = new JLabel("ADD EMPLOYEE DETAIL");
        aed.setBounds(400, 10, 450, 27);
        aed.setFont(new Font("Tahoma", Font.BOLD, 31));
        aed.setForeground(Color.WHITE);
        panel.add(aed);

        t1 = new JButton("ADD");
        t1.setBounds(200, 420, 100, 30);
        t1.setBackground(Color.BLACK);
        t1.setForeground(Color.WHITE);
        t1.addActionListener(this);  // Fixed: Add listener for ADD button
        panel.add(t1);

        t2 = new JButton("BACK");
        t2.setBounds(350, 420, 100, 30);
        t2.setBackground(Color.BLACK);
        t2.setForeground(Color.WHITE);
        t2.addActionListener(this);
        panel.add(t2);

        // FIXED: Move setVisible(true) to the END, after all components are added
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == t1) {  // ADD button
            // Validation
            if (nameText.getText().trim().isEmpty() || ageText.getText().trim().isEmpty() ||
                    salaryText.getText().trim().isEmpty() || phoneText.getText().trim().isEmpty() ||
                    aadharText.getText().trim().isEmpty() || emailText.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Fill all the values");
                return;
            }
            if (!radioButtonM.isSelected() && !radioButtonF.isSelected()) {
                JOptionPane.showMessageDialog(null, "Select gender");
                return;
            }

            String name = nameText.getText().trim();
            String age = ageText.getText().trim();
            String salary = salaryText.getText().trim();
            String phoneNum = phoneText.getText().trim();
            String aadhar = aadharText.getText().trim();
            String email = emailText.getText().trim();
            String gender = radioButtonM.isSelected() ? "MALE" : "FEMALE";
            String job = (String) comboBox.getSelectedItem();

            // Numeric validation for age and salary
            try {
                Integer.parseInt(age);  // Age must be integer
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Age must be a number (e.g., 25)!");
                return;
            }
            try {
                Double.parseDouble(salary);  // Salary can be decimal
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Salary must be a number (e.g., 50000 or 50000.50)!");
                return;
            }

            // Fixed SQL query (age and salary without quotes as they are numeric)
            String q = "INSERT INTO kukas(name, age, salary, phonenu, adhar, email, gender, job) " +
                    "VALUES ('" + name + "', " + age + ", " + salary + ", '" + phoneNum + "', '" + aadhar + "', '" + email + "', '" + gender + "', '" + job + "')";
            System.out.println(q);

            Vo c = null;
            Statement stmt = null;


            try {
                c = new Vo();
                stmt = c.connection.createStatement();
                stmt.executeUpdate(q);  // Execute query

                JOptionPane.showMessageDialog(null, "Employee Successfully Added");
                // Clear form
                nameText.setText("");
                ageText.setText("");
                salaryText.setText("");
                phoneText.setText("");
                aadharText.setText("");
                emailText.setText("");
                genderGroup.clearSelection();
                comboBox.setSelectedIndex(0);

            } catch (SQLException K) {
                K.printStackTrace();
                JOptionPane.showMessageDialog(null, "Database Error: " + K.getMessage() + "\n\nCheck table 'kukas' columns and data types.");
            } catch (Exception K) {
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

        } else if (e.getSource() == t2) {  // BACK button
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new AddEmployee();
    }
}
