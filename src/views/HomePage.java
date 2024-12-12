package views;
import components.Navbar;
import javax.swing.*;
import java.awt.*;

public class HomePage extends JFrame {
    int userId;

    public HomePage(int userId) {
        this.userId = userId;
        setTitle("SpinWheels - Home");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        Image icon = Toolkit.getDefaultToolkit().getImage("favicon.png");
        setIconImage(icon);

        setLayout(new BorderLayout());

        Navbar navbar = new Navbar(this,userId);
        add(navbar, BorderLayout.NORTH);

        JPanel heroSection = new JPanel(new BorderLayout());
        heroSection.setBackground(new Color(245, 245, 245));

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(null);

        JLabel title = new JLabel("Explore the City with SpinWheels");
        title.setFont(new Font("Poppins", Font.BOLD, 32));
        title.setForeground(new Color(33, 37, 41));

        JLabel subtitle = new JLabel("<html>Rent premium bicycles for your adventures.<br>" +
                "Affordable, flexible, and designed for every rider.</html>");
        subtitle.setFont(new Font("Poppins", Font.PLAIN, 18));
        subtitle.setForeground(new Color(108, 117, 125));

        JButton browseButton = new JButton("Browse Bicycles");
        browseButton.setBackground(new Color(139, 177, 152));
        browseButton.setForeground(Color.WHITE);
        browseButton.setFocusPainted(false);
        browseButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        browseButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        textPanel.add(title);
        textPanel.add(Box.createVerticalStrut(15));
        textPanel.add(subtitle);
        textPanel.add(Box.createVerticalStrut(20));
        textPanel.add(browseButton);

        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(new ImageIcon("bicycle1.jpg"));
        imageLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        heroSection.add(textPanel, BorderLayout.WEST);
        heroSection.add(imageLabel,BorderLayout.CENTER);

        textPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        add(heroSection, BorderLayout.CENTER);

        setVisible(true);

        browseButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dispose();
                new BrowsePage(userId).setVisible(true);
            }
        });
    }
}
