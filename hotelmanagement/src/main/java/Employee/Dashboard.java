package Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame implements ActionListener {
    JButton add, rec;

    Dashboard() {
        super("HOTEL MANAGEMENT SYSTEM");

        setLayout(null); 
        rec = new JButton("RECEPTION");
        rec.setBounds(425, 400, 140, 30);
        rec.setFont(new Font("Tahoma", Font.BOLD, 15));
        rec.setBackground(new Color(255, 98, 0));
        rec.setForeground(Color.WHITE);
        rec.addActionListener(this);
        add(rec);

        add = new JButton("ADMIN");
        add.setBounds(880, 400, 140, 30);
        add.setFont(new Font("Tahoma", Font.BOLD, 15));
        add.setBackground(new Color(255, 98, 0));
        add.setForeground(Color.WHITE);
        add.addActionListener(this);
        add(add);

        
        ImageIcon bossIcon = new ImageIcon(getClass().getResource("/icon/boss.png"));
        Image bossImg = bossIcon.getImage().getScaledInstance(200, 195, Image.SCALE_SMOOTH);
        JLabel bossLbl = new JLabel(new ImageIcon(bossImg));
        bossLbl.setBounds(850, 200, 200, 195);
        add(bossLbl);

        ImageIcon recIcon = new ImageIcon(getClass().getResource("/icon/Reception.png"));
        Image recImg = recIcon.getImage().getScaledInstance(200, 195, Image.SCALE_SMOOTH);
        JLabel recLbl = new JLabel(new ImageIcon(recImg));
        recLbl.setBounds(400, 200, 200, 195);
        add(recLbl);

        // ---------------------- BACKGROUND IMAGE AT BOTTOM -------------------------
        ImageIcon bg = new ImageIcon(getClass().getResource("/icon/ram.jpg"));
        Image bgImg = bg.getImage().getScaledInstance(1950, 1090, Image.SCALE_SMOOTH);
        JLabel background = new JLabel(new ImageIcon(bgImg));
        background.setBounds(0, 0, 1950, 1090);

        // Put background LAST so it stays behind
        getContentPane().add(background);

        setSize(1950, 1090);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rec) {
            new Reception();
            setVisible(false);
        } else if (e.getSource() == add) {
            new AdminLogin2();
        }
    }

    public static void main(String[] args) {
        new Dashboard();
    }
}
