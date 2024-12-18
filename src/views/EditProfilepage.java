package views;

import components.CustomButton;
import components.CustomInputField;
import components.Navbar;
import controllers.UserController;
import models.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EditProfilepage extends JFrame {
    int userId;
    User user;

    //Ensure correct data is inserted
    private boolean validateFields(String name, String email, String cnic, String phone, String password) {
        if (email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields are required.");
            return false;
        }
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            JOptionPane.showMessageDialog(null, "Please enter a valid email address.");
            return false;
        }
        if (phone.length() != 11 || !phone.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Please enter a valid phone number.");
            return false;
        }
        if (password.length() < 6) {
            JOptionPane.showMessageDialog(null, "Password must have at least 6 characters.");
            return false;
        }
        return true;
    }

    public EditProfilepage(int userId, User user) {
        this.userId = userId;
        this.user = user;

        setTitle("SpinWheels - Edit Profile");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setBackground(new Color(240, 240, 240));
        setLocationRelativeTo(null);
        setLayout(null);
        Image icon = Toolkit.getDefaultToolkit().getImage("favicon.png");
        setIconImage(icon);

        JPanel signupPanel = new JPanel();
        signupPanel.setBackground(Color.WHITE);
        signupPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        signupPanel.setLayout(new BoxLayout(signupPanel, BoxLayout.Y_AXIS));
        signupPanel.setBounds(510,100,500,600);
        add(signupPanel);

        Navbar navbar = new Navbar(this,userId);
        navbar.setBounds(0, 0, 1550, 60);
        add(navbar);

        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        welcomePanel.setBackground(Color.WHITE);
        JLabel welcomeLabel = new JLabel("Edit Profile", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        welcomePanel.add(welcomeLabel);
        signupPanel.add(welcomePanel);


        JPanel nameLabelPanel = new JPanel();
        nameLabelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        nameLabelPanel.setBackground(Color.WHITE);
        JLabel nameLabel = new JLabel("Name");
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        nameLabelPanel.add(nameLabel);
        signupPanel.add(nameLabelPanel);

        JPanel nameInputPanel = new JPanel();
        nameInputPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        nameInputPanel.setBackground(Color.WHITE);
        CustomInputField nameField = new CustomInputField();
        nameField.setText(user.getName());
        nameField.setPreferredSize(new Dimension(450, 40));
        nameField.setEditable(false);
        nameInputPanel.add(nameField);
        signupPanel.add(nameInputPanel);

        JPanel emailLabelPanel = new JPanel();
        emailLabelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        emailLabelPanel.setBackground(Color.WHITE);
        JLabel emailLabel = new JLabel("Email");
        nameLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        emailLabelPanel.add(emailLabel);
        signupPanel.add(emailLabelPanel);

        JPanel emailInputPanel = new JPanel();
        emailInputPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        emailInputPanel.setBackground(Color.WHITE);
        CustomInputField emailField = new CustomInputField();
        emailField.setText(user.getEmail());
        emailField.setPreferredSize(new Dimension(450, 40));
        emailInputPanel.add(emailField);
        signupPanel.add(emailInputPanel);

        JPanel cnicLabelPanel = new JPanel();
        cnicLabelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        cnicLabelPanel.setBackground(Color.WHITE);
        JLabel cnicLabel = new JLabel("CNIC (36502-*******-*)");
        nameLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        cnicLabelPanel.add(cnicLabel);
        signupPanel.add(cnicLabelPanel);

        JPanel cnicInputPanel = new JPanel();
        cnicInputPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        cnicInputPanel.setBackground(Color.WHITE);
        CustomInputField cnicField = new CustomInputField();
        cnicField.setText(user.getCnic());
        cnicField.setEditable(false);
        cnicField.setPreferredSize(new Dimension(450, 40));
        cnicInputPanel.add(cnicField);
        signupPanel.add(cnicInputPanel);

        JPanel phoneLabelPanel = new JPanel();
        phoneLabelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        phoneLabelPanel.setBackground(Color.WHITE);
        JLabel phoneLabel = new JLabel("Phone");
        nameLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        phoneLabelPanel.add(phoneLabel);
        signupPanel.add(phoneLabelPanel);

        JPanel phoneInputPanel = new JPanel();
        phoneInputPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        phoneInputPanel.setBackground(Color.WHITE);
        CustomInputField phoneField = new CustomInputField();
        phoneField.setText(user.getPhone());
        phoneField.setPreferredSize(new Dimension(450, 40));
        phoneInputPanel.add(phoneField);
        signupPanel.add(phoneInputPanel);

        JPanel passwordLabelPanel = new JPanel();
        passwordLabelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        passwordLabelPanel.setBackground(Color.WHITE);
        JLabel passwordLabel = new JLabel("Password");
        nameLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        passwordLabelPanel.add(passwordLabel);
        signupPanel.add(passwordLabelPanel);

        JPanel passwordInputPanel = new JPanel();
        passwordInputPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        passwordInputPanel.setBackground(Color.WHITE);
        CustomInputField passwordField = new CustomInputField();
        passwordField.setText(user.getPassword());
        passwordField.setPreferredSize(new Dimension(450, 40));
        passwordInputPanel.add(passwordField);
        signupPanel.add(passwordInputPanel);

        JPanel rentingPanel = new JPanel();
        rentingPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        rentingPanel.setBackground(Color.WHITE);
        JCheckBox rentingCheckBox = new JCheckBox("Renting");
        rentingCheckBox.setFocusPainted(false);
        rentingCheckBox.setBackground(Color.WHITE);
        rentingCheckBox.setSelected(user.getIsRenting());
        rentingPanel.add(rentingCheckBox);
        signupPanel.add(rentingPanel);

        JPanel signupButtonPanel = new JPanel();
        signupButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        signupButtonPanel.setBackground(Color.WHITE);
        CustomButton saveButton = new CustomButton("Save");
        CustomButton backButton = new CustomButton("Back");
        signupButtonPanel.add(backButton);
        signupButtonPanel.add(saveButton);
        signupPanel.add(signupButtonPanel);

        saveButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {

                //Get user data from input fields
                String name = nameField.getText();
                String cnic = cnicField.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();
                String password = passwordField.getText();
                boolean isRenting = rentingCheckBox.isSelected();

                if (!validateFields(name, email, cnic, phone, password)) {
                    return;
                }

                //Create user object using fetched data
                User user = new User(name, email, cnic, phone, password, isRenting);

                //Create controller instance and call update method
                UserController userController = new UserController();
                boolean success = userController.update(userId, user);

                if (success) {
                    JOptionPane.showMessageDialog(null, "Details updated successfully!");
                    dispose();
                    new ProfilePage(userId).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Error updating Details!");
                    dispose();
                    new ProfilePage(userId).setVisible(true);
                }
            }
        });

        backButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                dispose();
                new ProfilePage(userId).setVisible(true);
            }
        });

    }
}
