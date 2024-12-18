package components;
import views.BrowsePage;
import views.HomePage;
import views.ProfilePage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Navbar extends JPanel {

    public Navbar(JFrame parentFrame, int userId) {

        setLayout(new BorderLayout());
        setBackground(new Color(139, 177, 152));
        setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));

        JLabel logo = new JLabel("SpinWheels");
        logo.setFont(new Font("Poppins", Font.BOLD, 24));
        logo.setForeground(Color.WHITE);

        JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        menuPanel.setOpaque(false);

        JLabel homeLink = new JLabel("Home");
        homeLink.setFont(new Font("Poppins", Font.BOLD, 20));
        homeLink.setForeground(Color.WHITE);
        homeLink.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
        menuPanel.add(homeLink);

        JLabel browseLink = new JLabel("Browse");
        browseLink.setFont(new Font("Poppins", Font.BOLD, 20));
        browseLink.setForeground(Color.WHITE);
        browseLink.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
        menuPanel.add(browseLink);

        JLabel profileLink = new JLabel("\uD83D\uDC64");
        profileLink.setFont(new Font("Poppins", Font.PLAIN, 20));
        profileLink.setForeground(Color.WHITE);
        profileLink.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 20));
        menuPanel.add(profileLink);

        homeLink.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                //dispose the current frame or screen
                parentFrame.dispose();
                new HomePage(userId).setVisible(true);
            }
        });

        browseLink.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                parentFrame.dispose();
                new BrowsePage(userId).setVisible(true);
            }
        });

        profileLink.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                parentFrame.dispose();
                new ProfilePage(userId).setVisible(true);
            }
        });

        add(logo, BorderLayout.WEST);
        add(menuPanel, BorderLayout.EAST);
    }
}
