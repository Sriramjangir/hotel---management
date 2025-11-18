package Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;  // FIXED: TableModel के लिए
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;  // FIXED: MetaData के लिए

public class Room extends JFrame {
    JTable table;
    JButton back;

    Room() {
        setLayout(null);
        setLocation(300, 100);
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 890, 590);
        panel.setBackground(new Color(3, 45, 48));
        panel.setLayout(null);
        add(panel);

        table = new JTable();
        table.setBounds(10, 40, 500, 400);
        table.setBackground(new Color(3,45,48));
        table.setForeground(Color.WHITE);
        panel.add(table);

        try{
            Vo c = new Vo();
            String q = "select * from room";
            PreparedStatement pst = c.connection.prepareStatement(q);
            ResultSet resultSet = pst.executeQuery(); // FIXED: excute → execute

            DefaultTableModel model = new DefaultTableModel();
            ResultSetMetaData metaData = resultSet.getMetaData();//resul data se aye data ki jankari lena

            int columnCount = metaData.getColumnCount();

            // कॉलम नेम्स ऐड
            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(metaData.getColumnName(i));
            }

            // डेटा ऐड
            while (resultSet.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i-1] = resultSet.getObject(i);
                }
                model.addRow(row);
            }

            table.setModel(model);  // FIXED: Model सेट किया

            // Resources close
            resultSet.close();

        }catch (Exception e){
            e.printStackTrace();
        }




        back = new JButton("BACK");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(200,500,120,30);
        panel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(e.getSource()==back){
                    setVisible(false);
                }
            }
        });


        JLabel availability = new JLabel("Availability");
        availability.setBounds(119,15,80,19);
        availability.setForeground(Color.WHITE);
        availability.setFont(new Font("Tahoma",Font.BOLD,12));
        panel.add(availability);

        JLabel Clean = new JLabel("Clean-Status");
        Clean.setBounds(216,15,150,19);
        Clean.setForeground(Color.WHITE);
        Clean.setFont(new Font("Tahoma",Font.BOLD,12));
        panel.add(Clean);



        JLabel prize = new JLabel("Price");
        prize.setBounds(330,15,80,19);
        prize.setForeground(Color.WHITE);
        prize.setFont(new Font("Tahoma",Font.BOLD,12));
        panel.add(prize);



        JLabel BedType = new JLabel("Bed-Type");
        BedType.setBounds(470,15,80,19);
        BedType.setForeground(Color.WHITE);
        BedType.setFont(new Font("Tahoma",Font.BOLD,12));
        panel.add(BedType);


        JLabel roomnu = new JLabel("Room Nu");
        roomnu.setBounds(30,15,80,19);
        roomnu.setForeground(Color.WHITE);
        roomnu.setFont(new Font("Tahoma",Font.BOLD,12));
        panel.add(roomnu);


















        setVisible(true);
        System.out.println("Room window opened!");
    }

    public static void main(String[] args) {
        new Room();
    }
}
