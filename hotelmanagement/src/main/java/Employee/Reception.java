package Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Reception extends JFrame {

    Reception() {

        setLayout(null);
        setSize(1950, 1090);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.RED);

        // Main panel (right side)
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(280, 0, 1238, 800);
        panel.setBackground(new Color(3, 45, 48));
        add(panel);

        ImageIcon i111 = new ImageIcon(getClass().getResource("/icon/cccc.gif"));
        Image i3 = i111.getImage().getScaledInstance(200, 195, Image.SCALE_DEFAULT);
        ImageIcon imageIcon111 = new ImageIcon(i3);
        JLabel label11 = new JLabel(imageIcon111);
        label11.setBounds(700, 200, 200, 190);

        JLabel label = new JLabel("This is Reception Panel");
        label.setForeground(Color.WHITE);
        label.setBounds(50, 0, 200, 30);
        panel.add(label);

        // Side panel (left)
        JPanel panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setBounds(5, 5, 270, 800); // made same height for balance
        panel1.setBackground(new Color(2, 4, 15, 6)); // ✅ valid RGB

        // Equal spacing: All buttons at y=30, 70, 110, 150, 190, 230, 270, 310, 350, 390 (gap of 40 between each)
        JButton btnNCF = new JButton("new Customer Form");
        btnNCF.setBounds(30, 30, 200, 30);
        btnNCF.setBackground(Color.BLACK);
        btnNCF.setForeground(Color.WHITE);
        panel1.add(btnNCF);
        btnNCF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new NewCustomer();
                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        JButton btnRoom = new JButton("Room");
        btnRoom.setBounds(30, 70, 200, 30);
        btnRoom.setBackground(Color.BLACK);
        btnRoom.setForeground(Color.WHITE);
        panel1.add(btnRoom);
        btnRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Room();
                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        JButton btnDep = new JButton("Departement");
        btnDep.setBounds(30, 110, 200, 30);
        btnDep.setBackground(Color.BLACK);
        btnDep.setForeground(Color.WHITE);
        panel1.add(btnDep);
        btnDep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Department();
                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        JButton btnCI = new JButton("Customer Info");
        btnCI.setBounds(30, 150, 200, 30);
        btnCI.setBackground(Color.BLACK);
        btnCI.setForeground(Color.WHITE);
        panel1.add(btnCI);
        btnCI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Customerinfo();
                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        JButton btnMI = new JButton("Manager Info");
        btnMI.setBounds(30, 190, 200, 30);
        btnMI.setBackground(Color.BLACK);
        btnMI.setForeground(Color.WHITE);
        panel1.add(btnMI);
        btnMI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new ManagerInfo();
                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        JButton btnCO = new JButton("Check Out");
        btnCO.setBounds(30, 230, 200, 30);
        btnCO.setBackground(Color.BLACK);
        btnCO.setForeground(Color.WHITE);
        panel1.add(btnCO);
        btnCO.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new CheckOut();
                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        JButton btnUC = new JButton("Update Check-In Details");
        btnUC.setBounds(30, 270, 200, 30);
        btnUC.setBackground(Color.BLACK);
        btnUC.setForeground(Color.WHITE);
        panel1.add(btnUC);
        btnUC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new UpdateCheck();
                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        JButton btnURS = new JButton("Update Room Status");
        btnURS.setBounds(30, 310, 200, 30);
        btnURS.setBackground(Color.BLACK);
        btnURS.setForeground(Color.WHITE);
        panel1.add(btnURS);
        btnURS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new UpdateRoom();
                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        JButton btnPUS = new JButton("Pick up Service");
        btnPUS.setBounds(30, 350, 200, 30);
        btnPUS.setBackground(Color.BLACK);
        btnPUS.setForeground(Color.WHITE);
        panel1.add(btnPUS);
        btnPUS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new PickUp();
                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        JButton btnSR = new JButton("new Search Room");
        btnSR.setBounds(30, 390, 200, 30);
        btnSR.setBackground(Color.BLACK);
        btnSR.setForeground(Color.WHITE);
        panel1.add(btnSR);
        btnSR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new SearchRoom();
                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        JButton button = new JButton("Back");
        button.setForeground(Color.WHITE);
        button.setBounds(200, 500, 70, 30);
        button.setBackground(Color.BLACK);
        panel.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Dashboard();
            }
        });

        // Add panels (add side panel after main panel for proper layering)
        add(panel);
        add(panel1);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Reception();
    }
}
