package views;
import components.CustomButton;
import components.CustomInputField;
import controllers.UserController;
import models.User;
import javax.swing.*;
import java.awt.*;

public class SignupView extends JFrame {

    //refactored
    private boolean validateFields(String name, String email, String cnic, String phone, String password) {
        if (name.isEmpty() || email.isEmpty() || cnic.isEmpty() || phone.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields are required.");
            return false;
        }
        if (!name.matches("[a-zA-Z\\s]+")) {
            JOptionPane.showMessageDialog(null, "Please enter a valid name.");
            return false;
        }
        if (cnic.length() != 15 || !cnic.matches("\\d{5}-\\d{7}-\\d")) {
            JOptionPane.showMessageDialog(null, "Please enter a valid CNIC in the format XXXXX-XXXXXXX-X.");
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

    public SignupView() {
        setTitle("SpinWheels - Signup");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setBackground(new Color(240, 240, 240));
        setLocationRelativeTo(null);
        setLayout(null);
        Image icon = Toolkit.getDefaultToolkit().getImage("favicon.png");
        setIconImage(icon);

        JLabel logoLabel = new JLabel("SpinWheels", SwingConstants.CENTER);
        logoLabel.setFont(new Font("SansSerif", Font.BOLD, 32));
        logoLabel.setForeground(new Color(107, 141, 120));
        logoLabel.setBounds(650,50,200,60);
        add(logoLabel);

        JPanel signupPanel = new JPanel();
        signupPanel.setBackground(Color.WHITE);
        signupPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        signupPanel.setLayout(new BoxLayout(signupPanel, BoxLayout.Y_AXIS));
        signupPanel.setBounds(510,130,500,600);
        add(signupPanel);

        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        welcomePanel.setBackground(Color.WHITE);
        JLabel welcomeLabel = new JLabel("Signup", SwingConstants.CENTER);
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
        nameField.setPreferredSize(new Dimension(450, 40));
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
        passwordField.setPreferredSize(new Dimension(450, 40));
        passwordInputPanel.add(passwordField);
        signupPanel.add(passwordInputPanel);

        JPanel signupButtonPanel = new JPanel();
        signupButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        signupButtonPanel.setBackground(Color.WHITE);
        CustomButton signupButton = new CustomButton("Signup");
        signupButtonPanel.add(signupButton);
        signupPanel.add(signupButtonPanel);


        JLabel loginLabel = new JLabel("Already have an account? Login");
        loginLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        loginLabel.setForeground(new Color(107, 141, 120));
        loginLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        loginLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signupPanel.add(loginLabel);


        loginLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dispose();
                new LoginView().setVisible(true);
            }
        });

        signupButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {

                //Get user data from input fields
                String name = nameField.getText();
                String cnic = cnicField.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();
                String password = passwordField.getText();
                boolean isRenting = false;

                if (!validateFields(name, email, cnic, phone, password)) {
                    return;
                }

                //Create user object using fetched data
                User newUser = new User(name, email, cnic, phone, password, isRenting);

                //Create controller instance and call create method
                UserController userController = new UserController();
                boolean success = userController.createUser(newUser);

                if (success) {
                    JOptionPane.showMessageDialog(null, "Account created successfully!");
                    dispose();
                    new LoginView().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Error creating Account!.");
                }
            }
        });


    }

}
