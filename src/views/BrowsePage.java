package views;

import components.CustomButton;
import components.Navbar;
import controllers.BicycleController;
import controllers.UserController;
import models.Bicycle;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class BrowsePage extends JFrame {
    private int userId;

    public BrowsePage(int userId) {
        this.userId = userId;

        setTitle("SpinWheels - Browse Bicycles");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        Image icon = Toolkit.getDefaultToolkit().getImage("favicon.png");
        setIconImage(icon);

        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        Navbar navbar = new Navbar(this, userId);
        navbar.setPreferredSize(new Dimension(getWidth(), 60));
        topPanel.add(navbar, BorderLayout.NORTH);

        JLabel titleLabel = new JLabel("Browse");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 32));
        titleLabel.setForeground(new Color(107, 141, 120));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 20, 20, 0));
        topPanel.add(titleLabel, BorderLayout.SOUTH);
        add(topPanel, BorderLayout.NORTH);

        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setBackground(new Color(240, 240, 240));
        cardPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        cardPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JScrollPane scrollPane = new JScrollPane(cardPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.CENTER);

        BicycleController bicycleController = new BicycleController();
        ArrayList<Bicycle> bicycles = bicycleController.getBicycles();

        for (Bicycle bicycle : bicycles) {
            JPanel card = createBikeCard(bicycle);
            cardPanel.add(card);
            cardPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        }

    }

    private JPanel createBikeCard(Bicycle bicycle) {
        JPanel card = new JPanel();
        card.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        card.setPreferredSize(new Dimension(300, 230));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 2));

        JLabel imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(330, 200));
        imageLabel.setIcon(new ImageIcon("m.png"));

        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBackground(Color.WHITE);
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nameLabel = new JLabel("<html><b>Name:</b> " + bicycle.getName() + "</html>");
        JLabel yearLabel = new JLabel("<html><b>Year:</b> " + bicycle.getYear() + "</html>");
        JLabel weightLabel = new JLabel("<html><b>Weight:</b> " + bicycle.getWeight() + "</html>");
        JLabel conditionLabel = new JLabel("<html><b>Condition:</b> " + bicycle.getCondition() + "</html>");
        JLabel rateLabel = new JLabel("<html><b>Hourly Rate:</b> " + bicycle.getHourlyRate() + "</html>");
        JLabel locationLabel = new JLabel("<html><b>Location:</b> " + bicycle.getLocation() + "</html>");

        JLabel[] labels = {nameLabel, yearLabel, weightLabel, conditionLabel, rateLabel, locationLabel};

        for (JLabel label : labels) {
            label.setFont(new Font("SansSerif", Font.PLAIN, 14));
            label.setForeground(new Color(80, 80, 80));
            detailsPanel.add(label);
        }

        detailsPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        if (userId == bicycle.getUserId()) {
            CustomButton removeButton = new CustomButton("Remove");
            detailsPanel.add(removeButton);
            detailsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            removeButton.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    BicycleController bicycleController = new BicycleController();
                    boolean removed = bicycleController.removeBicycle(bicycle, userId);
                    if (removed){
                        dispose();
                        new BrowsePage(userId).setVisible(true);
                        JOptionPane.showMessageDialog(null, "Bicycle removed!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Could not remove bicycle!");
                    }

                }
            });
        }

        if (userId != bicycle.getUserId()) {
            CustomButton bookButton = new CustomButton("Book");
            detailsPanel.add(bookButton);
            bookButton.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    int userId = bicycle.getUserId();
                    UserController userController = new UserController();
                    String phoneNumber = userController.getRenterPhone(userId);
                    JOptionPane.showMessageDialog(null, "Renter Phone Number: " + phoneNumber);
                }
            });
        }

        card.add(imageLabel);
        detailsPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        card.add(detailsPanel);
        return card;
    }
}
