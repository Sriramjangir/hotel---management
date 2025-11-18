package Employee;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class PickUp extends JFrame {
    private JTable table;  // Table instance variable for access in ActionListener

    PickUp() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(3, 45, 48));
        panel.setBounds(5, 5, 790, 590);
        panel.setLayout(null);
        add(panel);

        JLabel pus = new JLabel("Pick Up Service");
        pus.setBounds(90, 11, 170, 25);
        pus.setForeground(Color.WHITE);
        pus.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(pus);

        JLabel toc = new JLabel("Type of Car");
        toc.setBounds(32, 97, 89, 14);
        toc.setForeground(Color.WHITE);
        toc.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel.add(toc);

        Choice k = new Choice();
        k.setBounds(123, 94, 150, 25);
        panel.add(k);

        // Dropdown (Choice) me car names la rahe hain
        try {
            Vo c = new Vo();
            String query = "SELECT car_nameko FROM drivers";
            PreparedStatement pst = c.connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                k.add(rs.getString("car_nameko"));
            }

            rs.close();
            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // JTable setup
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 150, 780, 250); // 👈 Table thoda upar
        table.setBackground(new Color(3, 45, 48));
        table.setForeground(Color.WHITE);
        panel.add(scrollPane);

        // Initially load all drivers
        loadTableData("SELECT * FROM drivers");

        // Display Button (ab table ke niche)
        JButton button = new JButton("Display");
        button.setBounds(200, 430, 120, 30); // 👈 table ke niche
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        panel.add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Vo c = new Vo();
                    // 👇 Directly using k.getSelectedItem() inside query
                    String query = "SELECT * FROM drivers WHERE car_nameko = '" + k.getSelectedItem() + "'";
                    PreparedStatement pst = c.connection.prepareStatement(query);
                    ResultSet rs = pst.executeQuery();

                    DefaultTableModel model = new DefaultTableModel();
                    ResultSetMetaData metadata = rs.getMetaData();
                    int columnCount = metadata.getColumnCount();

                    // Add column headers
                    for (int i = 1; i <= columnCount; i++) {
                        model.addColumn(metadata.getColumnName(i));
                    }

                    // Add data rows
                    while (rs.next()) {
                        Object[] row = new Object[columnCount];
                        for (int i = 1; i <= columnCount; i++) {
                            row[i - 1] = rs.getObject(i);
                        }
                        model.addRow(row);
                    }

                    table.setModel(model);
                    rs.close();
                    pst.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Back Button (bhi table ke niche)
        JButton back = new JButton("Back");
        back.setBounds(400, 430, 120, 30); // 👈 Display ke side me
        back.setBackground(Color.WHITE);
        back.setForeground(Color.BLACK);
        panel.add(back);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent d) {
                setVisible(false);
                dispose();
            }
        });

        // Frame setup
        setLayout(null);
        setSize(900, 600);
        setLocation(500, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Helper method to load all data
    private void loadTableData(String query) {
        try {
            Vo c = new Vo();
            PreparedStatement pst = c.connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            ResultSetMetaData metadata = rs.getMetaData();
            int columnCount = metadata.getColumnCount();

            // Add column headers
            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(metadata.getColumnName(i));
            }

            // Add rows
            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                model.addRow(row);
            }

            table.setModel(model);

            rs.close();
            pst.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
         new PickUp();
    }
}
