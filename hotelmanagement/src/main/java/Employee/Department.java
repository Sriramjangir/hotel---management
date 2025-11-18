package Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Department extends JFrame {

    Department() {

        // Frame Settings
        setUndecorated(true);
        setLayout(null);
        setSize(700, 500);
        setLocation(550, 150);

        // Panel Setup
        JPanel panel = new JPanel();
        panel.setBackground(new Color(3, 45, 48));
        panel.setBounds(0, 0, 700, 500);
        panel.setLayout(null);
        add(panel);

        // JTable
        JTable table = new JTable();
        table.setBackground(new Color(4, 45, 48));
        table.setForeground(Color.white);
        table.setFillsViewportHeight(true);

        // ScrollPane for Table
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 40, 690, 350);
        panel.add(scrollPane);

        // Fetch Data from Database
        try {
            Vo c = new Vo();  // Vo class should provide connection and statement
            String q = "SELECT * FROM departement";  // Correct spelling? or 'department'?
            PreparedStatement pst = c.connection.prepareStatement(q);
            ResultSet resultSet = pst.executeQuery();

            DefaultTableModel model = new DefaultTableModel();

            ResultSetMetaData metadata = resultSet.getMetaData();
            int columnCount = metadata.getColumnCount();

            // Add column headers


            //resultSet.getObject(1) ➝ id की पहली row ➝ 1
            //
            //resultSet.getObject(2) ➝ name की पहली row ➝ "Ravi"
            //
            //resultSet.getObject(3) ➝ salary की पहली row ➝ 25000
           // row[0] = resultSet.getObject(1);  // id
           // row[1] = resultSet.getObject(2);  // name
           // row[2] = resultSet.getObject(3);
            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(metadata.getColumnName(i));
            }

            // Add rows to the model
            while (resultSet.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = resultSet.getObject(i);
                }
                model.addRow(row);
            }

            // Set model to table
            table.setModel(model);

            // Clean up (optional)
            resultSet.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // BACK Button
        JButton back = new JButton("BACK");
        back.setBounds(400, 410, 120, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        panel.add(back);

        back.addActionListener(e -> setVisible(false));

        // Labels (optional)
        JLabel label = new JLabel("Department");
        label.setBounds(10, 11, 150, 14);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(label);

        JLabel label2 = new JLabel("Salary");
        label2.setBounds(350, 11, 150, 14);
        label2.setForeground(Color.WHITE);
        label2.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(label2);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Department();
    }
}
