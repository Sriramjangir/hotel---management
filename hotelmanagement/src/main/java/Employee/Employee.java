package Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Employee extends JFrame {

    Employee() {

        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 990, 590);
        panel.setBackground(new Color(3, 45, 48));
        panel.setLayout(null);
        add(panel);

        JTable table = new JTable();
        table.setForeground(Color.WHITE);
        table.setBackground(new Color(3, 45, 48));
        table.setFillsViewportHeight(true);

        // JScrollPane for scrollable table
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 34, 980, 450);
        panel.add(scrollPane);

        try {
            Vo c = new Vo();
            String q = "SELECT * FROM kukas";
            PreparedStatement pst = c.connection.prepareStatement(q);
            ResultSet resultSet = pst.executeQuery();
            ResultSetMetaData metadata = resultSet.getMetaData();
            int columnCount = metadata.getColumnCount();

            DefaultTableModel model = new DefaultTableModel();

            // Add column names
            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(metadata.getColumnName(i));
            }

            // Add rows
            while (resultSet.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = resultSet.getObject(i);
                }
                model.addRow(row);
            }

            table.setModel(model);  // ✅ Set model to JTable

            // Close resources (optional)
            resultSet.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


        JButton button = new JButton("Back");
        button.setBounds(350,500,120,30);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        panel.add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });








































        setLayout(null);
        setLocation(100, 0);
        setSize(1000, 600);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Employee();
    }
}
