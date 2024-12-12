package views;

import components.CustomButton;
import components.CustomInputField;
import controllers.DatabaseController;
import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {

    private boolean validateFields(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields are required.");
            return false;
        }
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            JOptionPane.showMessageDialog(null, "Please enter a valid email address.");
            return false;
        }
        if (password.length() < 6) {
            JOptionPane.showMessageDialog(null, "Please enter a valid password.");
            return false;
        }
        return true;
    }

    public LoginView() {
        setTitle("SpinWheels - Login");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(new Color(240, 240, 240));
        setLocationRelativeTo(null);
        setLayout(null);
        Image icon = Toolkit.getDefaultToolkit().getImage("favicon.png");
        setIconImage(icon);

        JLabel logoLabel = new JLabel("SpinWheels", SwingConstants.CENTER);
        logoLabel.setFont(new Font("SansSerif", Font.BOLD, 32));
        logoLabel.setForeground(new Color(107, 141, 120));
        logoLabel.setBounds(650,185,200,60);
        add(logoLabel);

        JPanel loginPanel = new JPanel();
        loginPanel.setBackground(Color.WHITE);
        loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setBounds(510,270,500,350);
        add(loginPanel);

        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        welcomePanel.setBackground(Color.WHITE);
        JLabel welcomeLabel = new JLabel("Welcome Back", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        welcomePanel.add(welcomeLabel);
        loginPanel.add(welcomePanel);

        JPanel emailLabelPanel = new JPanel();
        emailLabelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        emailLabelPanel.setBackground(Color.WHITE);
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        emailLabelPanel.add(emailLabel);
        loginPanel.add(emailLabelPanel);

        JPanel emailInputPanel = new JPanel();
        emailInputPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        emailInputPanel.setBackground(Color.WHITE);
        CustomInputField emailField = new CustomInputField();
        emailField.setPreferredSize(new Dimension(450, 40));
        emailInputPanel.add(emailField);
        loginPanel.add(emailInputPanel);

        JPanel passwordLabelPanel = new JPanel();
        passwordLabelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        passwordLabelPanel.setBackground(Color.WHITE);
        JLabel passwordLabel = new JLabel("Password");
        emailLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        passwordLabelPanel.add(passwordLabel);
        loginPanel.add(passwordLabelPanel);

        JPanel passwordInputPanel = new JPanel();
        passwordInputPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        passwordInputPanel.setBackground(Color.WHITE);
        CustomInputField passwordField = new CustomInputField();
        passwordField.setPreferredSize(new Dimension(450, 40));
        passwordInputPanel.add(passwordField);
        loginPanel.add(passwordInputPanel);

        JPanel loginButtonPanel = new JPanel();
        loginButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        loginButtonPanel.setBackground(Color.WHITE);
        CustomButton loginButton = new CustomButton("Login");
        loginButtonPanel.add(loginButton);
        loginPanel.add(loginButtonPanel);

        JLabel signupLabel = new JLabel("Don't have an account? Signup");
        signupLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        signupLabel.setForeground(new Color(107, 141, 120));
        signupLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        signupLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginPanel.add(signupLabel);

        signupLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dispose();
                new SignupView().setVisible(true);
            }
        });

        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                String email = emailField.getText();
                String password = passwordField.getText();

                if (!validateFields(email, password)) {
                    return;
                }

                DatabaseController dbHelper = new DatabaseController();
                int userId = dbHelper.authenticateUser(email, password);

                if (userId != 0) {
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                    dispose();
                    new HomePage(userId).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid credentials!.");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginView().setVisible(true));
    }
}
