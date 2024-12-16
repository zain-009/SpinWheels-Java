package views;

import components.CustomButton;
import components.Navbar;
import controllers.UserController;
import models.User;
import javax.swing.*;
import java.awt.*;

public class ProfilePage extends JFrame {
    int userId;

    public ProfilePage(int userId) {
        this.userId = userId;

        setTitle("SpinWheels - Profile");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setBackground(new Color(240, 240, 240));
        setLocationRelativeTo(null);
        setLayout(null);
        Image icon = Toolkit.getDefaultToolkit().getImage("favicon.png");
        setIconImage(icon);

        Navbar navbar = new Navbar(this,userId);
        navbar.setBounds(0, 0, 1550, 60);
        add(navbar);

        JLabel profileLabel = new JLabel("Profile", SwingConstants.CENTER);
        profileLabel.setFont(new Font("SansSerif", Font.BOLD, 32));
        profileLabel.setForeground(new Color(107, 141, 120));
        profileLabel.setBounds(300,80,100,60);
        add(profileLabel);

        JPanel profilePanel = new JPanel();
        profilePanel.setBackground(Color.WHITE);
        profilePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.Y_AXIS));
        profilePanel.setBounds(300,140,900,500);
        add(profilePanel);

        UserController userController = new UserController();
        User user = userController.getUserData(userId);

        if (user != null) {
            JLabel nameLabel = new JLabel("Name", SwingConstants.CENTER);
            nameLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
            profilePanel.add(nameLabel);
            JLabel name = new JLabel(user.getName(), SwingConstants.CENTER);
            name.setFont(new Font("SansSerif", Font.PLAIN, 18));
            profilePanel.add(name);


            profilePanel.add(Box.createRigidArea(new Dimension(0, 10)));


            JLabel emailLabel = new JLabel("Email", SwingConstants.CENTER);
            emailLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
            profilePanel.add(emailLabel);
            JLabel email = new JLabel(user.getEmail(), SwingConstants.CENTER);
            email.setFont(new Font("SansSerif", Font.PLAIN, 18));
            profilePanel.add(email);


            profilePanel.add(Box.createRigidArea(new Dimension(0, 10)));


            JLabel cnicLabel = new JLabel("CNIC", SwingConstants.CENTER);
            cnicLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
            profilePanel.add(cnicLabel);
            JLabel cnic = new JLabel(user.getCnic(), SwingConstants.CENTER);
            cnic.setFont(new Font("SansSerif", Font.PLAIN, 18));
            profilePanel.add(cnic);


            profilePanel.add(Box.createRigidArea(new Dimension(0, 10)));


            JLabel phoneLabel = new JLabel("Phone", SwingConstants.CENTER);
            phoneLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
            profilePanel.add(phoneLabel);
            JLabel phone = new JLabel(user.getPhone(), SwingConstants.CENTER);
            phone.setFont(new Font("SansSerif", Font.PLAIN, 18));
            profilePanel.add(phone);


            profilePanel.add(Box.createRigidArea(new Dimension(0, 10)));


            JLabel isRentingLabel = new JLabel("Renting", SwingConstants.CENTER);
            isRentingLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
            profilePanel.add(isRentingLabel);
            JLabel isRenting = new JLabel(user.getIsRenting() ? "Yes" : "No", SwingConstants.CENTER);
            isRenting.setFont(new Font("SansSerif", Font.PLAIN, 18));
            profilePanel.add(isRenting);


            profilePanel.add(Box.createRigidArea(new Dimension(0, 10)));


            CustomButton loginButton = new CustomButton("Rent your Bicycle");
            profilePanel.add(loginButton);

            profilePanel.add(Box.createRigidArea(new Dimension(0, 10)));

            CustomButton editProfileButton = new CustomButton("      Edit Profile      ");
            profilePanel.add(editProfileButton);

            loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    dispose();
                    new AddBicyclePage(userId).setVisible(true);
                }
            });

            editProfileButton.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    dispose();
                    new EditProfilepage(userId, user).setVisible(true);
                }
            });
        }
    }
}
