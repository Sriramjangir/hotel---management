package Employee;

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
import javax.swing.table.DefaultTableModel;

public class Customerinfo extends JFrame {

    Customerinfo() {

        // 🔹 Panel बनाना
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 1200, 800); // पूरे फ्रेम के बराबर
        panel.setBackground(new Color(3, 45, 48));
        panel.setLayout(null);
        add(panel);

        // 🔹 Header Labels
        

        // 🔹 Table और ScrollPane
        JTable table = new JTable();
        table.setBackground(new Color(3, 45, 48));
        table.setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 40, 1160, 450); // छोटा किया
        panel.add(scrollPane);

        // 🔹 Database से Data निकालना
        try {
            Vo c = new Vo(); // आपकी Database connection class
            String q = "SELECT * FROM Customer";
            PreparedStatement pst = c.connection.prepareStatement(q);
            ResultSet resultSet = pst.executeQuery();

            DefaultTableModel model = new DefaultTableModel();

            ResultSetMetaData metadata = resultSet.getMetaData();
            int columnCount = metadata.getColumnCount();

            for (int j = 1; j <= columnCount; j++) {
                model.addColumn(metadata.getColumnName(j));
            }

            while (resultSet.next()) {
                Object[] row = new Object[columnCount];
                for (int j = 1; j <= columnCount; j++) {
                    row[j - 1] = resultSet.getObject(j);
                }
                model.addRow(row);
            }

            table.setModel(model);
            resultSet.close();
            pst.close();
            c.connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // 🔹 Back बटन
        JButton backButton = new JButton("Back");
        backButton.setBounds(550, 520, 100, 30); // अब clearly दिखेगा
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Tahoma", Font.BOLD, 13));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        panel.add(backButton);

        // 🔹 Frame Settings
        setLayout(null);
        setSize(1200, 800);
        setLocation(200, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Customerinfo();
    }
}
