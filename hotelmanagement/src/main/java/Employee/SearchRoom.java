package Employee;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SearchRoom extends JFrame implements ActionListener {
    JCheckBox checkBox;
    Choice choice;
    JTable table;
    JButton add, back;

    SearchRoom() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(3, 45, 48));
        panel.setBounds(5, 5, 690, 490);
        panel.setLayout(null);
        add(panel);

        JLabel searchForRoom = new JLabel("Search For Room");
        searchForRoom.setBounds(250, 11, 186, 31);
        searchForRoom.setForeground(Color.WHITE);
        searchForRoom.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(searchForRoom);

        JLabel rbt = new JLabel("Room Bed Type");
        rbt.setBounds(50, 73, 120, 14);
        rbt.setForeground(Color.WHITE);
        rbt.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(rbt);

        JLabel rn = new JLabel("Room Number");
        rn.setBounds(23, 162, 150, 20);
        rn.setForeground(Color.WHITE);
        rn.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(rn);

        JLabel avaiLabel = new JLabel("Availability");
        avaiLabel.setBounds(175, 162, 150, 20);
        avaiLabel.setForeground(Color.WHITE);
        avaiLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(avaiLabel);

        JLabel price = new JLabel("Price");
        price.setBounds(458, 162, 150, 20);
        price.setForeground(Color.WHITE);
        price.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(price);

        JLabel BT = new JLabel("Bed Type");
        BT.setBounds(580, 162, 150, 20);
        BT.setForeground(Color.WHITE);
        BT.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(BT);

        JLabel ST = new JLabel("Clean Status");
        ST.setBounds(306, 162, 150, 20);
        ST.setForeground(Color.WHITE);
        ST.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(ST);

        checkBox = new JCheckBox("Only Display Available");
        checkBox.setBounds(400, 69, 205, 23);
        checkBox.setForeground(Color.WHITE);
        checkBox.setBackground(new Color(3, 45, 48));
        panel.add(checkBox);

        choice = new Choice();
        choice.add("Single Bed");
        choice.add("Double Bed");
        choice.setBounds(170, 70, 120, 20);
        panel.add(choice);

        table = new JTable();
        table.setBounds(0, 187, 700, 150);
        table.setBackground(new Color(3, 45, 48));
        table.setForeground(Color.WHITE);
        panel.add(table);

        // Initial load of all rooms
        loadTableData("select * from room");

        add = new JButton("Search");
        add.setBounds(200, 400, 120, 30);
        add.setBackground(Color.BLACK);
        add.setForeground(Color.WHITE);
        panel.add(add);
        add.addActionListener(this);

        back = new JButton("Back");
        back.setBounds(400, 400, 120, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        panel.add(back);
        back.addActionListener(this);

        setLayout(null);
        setSize(700, 500);
        setLocation(500, 200);
        setVisible(true);
    }

    private void loadTableData(String query) {
        try {
            Vo c = new Vo(); // Assuming Vo is your database connection class
            PreparedStatement pst = c.connection.prepareStatement(query);
            ResultSet resultSet = pst.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            ResultSetMetaData metadata = resultSet.getMetaData();
            int columnCount = metadata.getColumnCount();

            // Add column headers
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

            // Clean up
            resultSet.close();
            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            // Using your original query style, fixed for syntax
            String q = "select * from room where bed_type = '" + choice.getSelectedItem() + "'";
            String a = "select * from room where availability = 'Available' and bed_type = '" + choice.getSelectedItem() + "'";

            String queryToUse;
            if (checkBox.isSelected()) {
                queryToUse = a;
            } else {
                queryToUse = q;
            }

            loadTableData(queryToUse);
        } else if (e.getSource() == back) {
            // Assuming back closes the window or goes back; adjust as needed
            this.dispose();
        }
    }

    public static void main(String[] args) {
        new SearchRoom();
    }
}
