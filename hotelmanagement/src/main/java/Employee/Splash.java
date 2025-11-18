package Employee;

import javax.swing.*;
import java.awt.*;

public class Splash extends JFrame {

    Splash() {
        // Background को blue set करें
        getContentPane().setBackground(Color.BLUE);

        // स्क्रीन साइज चेक करें
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        // विंडो साइज को स्क्रीन के 80% में limit करें
        int windowWidth = (int) (screenWidth * 0.8);
        int windowHeight = (int) (screenHeight * 0.8);

        // इमेज load करें with error handling
        ImageIcon originalIcon = null;
        try {
            originalIcon = new ImageIcon(getClass().getResource("/icon/login.gif"));  // Fixed path
            if (originalIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
                throw new Exception("Image not loaded properly");
            }
        } catch (Exception e) {
            System.out.println("Image not found or failed to load: " + e.getMessage());
            // Fallback: Show text instead of image
            JLabel fallbackLabel = new JLabel("Hotel Management System", SwingConstants.CENTER);
            fallbackLabel.setFont(new Font("Arial", Font.BOLD, 48));
            fallbackLabel.setForeground(Color.WHITE);
            fallbackLabel.setBounds(0, windowHeight / 2 - 50, windowWidth, 100);
            add(fallbackLabel);
            setLayout(null);
            setSize(windowWidth, windowHeight);
            setLocationRelativeTo(null);
            setVisible(true);
            try {
                Thread.sleep(5000);
                setVisible(false);
                dispose();
                new Login();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return;  // Exit early if image fails
        }

        Image originalImage = originalIcon.getImage();

        // इमेज को विंडो में fit करें, aspect ratio maintain करके
        double aspectRatio = (double) originalImage.getWidth(null) / originalImage.getHeight(null);
        int scaledWidth = windowWidth;
        int scaledHeight = (int) (windowWidth / aspectRatio);

        if (scaledHeight > windowHeight) {
            scaledHeight = windowHeight;
            scaledWidth = (int) (windowHeight * aspectRatio);
        }

        Image scaledImage = originalImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        // लेबल बनाएँ और center में रखें
        JLabel label = new JLabel(scaledIcon);
        int x = (windowWidth - scaledWidth) / 2;
        int y = (windowHeight - scaledHeight) / 2;
        label.setBounds(x, y, scaledWidth, scaledHeight);
        add(label);

        setLayout(null);
        setSize(windowWidth, windowHeight);
        setLocationRelativeTo(null);  // विंडो को स्क्रीन के center में रखें

        setVisible(true);

        try {
            Thread.sleep(5000);
            setVisible(false);
            dispose();

            // Splash के बाद Login window open करें
            new Login();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Splash();
    }
}
