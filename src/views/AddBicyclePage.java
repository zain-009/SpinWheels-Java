package views;

import components.CustomButton;
import components.CustomInputField;
import components.CustomRadioButton;
import components.Navbar;
import controllers.BicycleController;
import models.Bicycle;
import javax.swing.*;
import java.awt.*;
import java.util.Enumeration;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

public class AddBicyclePage extends JFrame {
    int userId;
    static ButtonGroup ratingGroup;

    public AddBicyclePage(int userId){
        this.userId = userId;
        ratingGroup = new ButtonGroup();

        setTitle("SpinWheels - List Bicycle");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(new Color(240, 240, 240));
        setLocationRelativeTo(null);
        setLayout(null);
        Image icon = Toolkit.getDefaultToolkit().getImage("favicon.png");
        setIconImage(icon);

        Navbar navbar = new Navbar(this,userId);
        navbar.setBounds(0, 0, 1550, 60);
        add(navbar);

        JPanel listBicyclePanel = new JPanel();
        listBicyclePanel.setBackground(Color.WHITE);
        listBicyclePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        listBicyclePanel.setLayout(new BoxLayout(listBicyclePanel, BoxLayout.Y_AXIS));
        listBicyclePanel.setBounds(510,100,500,600);
        add(listBicyclePanel);

        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        welcomePanel.setBackground(Color.WHITE);
        JLabel welcomeLabel = new JLabel("List Your Bicycle", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        welcomePanel.add(welcomeLabel);
        listBicyclePanel.add(welcomePanel);

        JPanel nameLabelPanel = new JPanel();
        nameLabelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        nameLabelPanel.setBackground(Color.WHITE);
        JLabel nameLabel = new JLabel("Name");
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        nameLabelPanel.add(nameLabel);
        listBicyclePanel.add(nameLabelPanel);

        JPanel nameInputPanel = new JPanel();
        nameInputPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        nameInputPanel.setBackground(Color.WHITE);
        CustomInputField nameField = new CustomInputField();
        nameField.setPreferredSize(new Dimension(450, 40));
        nameInputPanel.add(nameField);
        listBicyclePanel.add(nameInputPanel);

        JPanel yearLabelPanel = new JPanel();
        yearLabelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        yearLabelPanel.setBackground(Color.WHITE);
        JLabel yearLabel = new JLabel("Year");
        nameLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        yearLabelPanel.add(yearLabel);
        listBicyclePanel.add(yearLabelPanel);

        JPanel yearInputPanel = new JPanel();
        yearInputPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        yearInputPanel.setBackground(Color.WHITE);
        CustomInputField yearField = new CustomInputField();
        yearField.setPreferredSize(new Dimension(450, 40));
        yearInputPanel.add(yearField);
        listBicyclePanel.add(yearInputPanel);

        JPanel weightLabelPanel = new JPanel();
        weightLabelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        weightLabelPanel.setBackground(Color.WHITE);
        JLabel weightLabel = new JLabel("Weight ks's");
        nameLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        weightLabelPanel.add(weightLabel);
        listBicyclePanel.add(weightLabelPanel);

        JPanel weightInputPanel = new JPanel();
        weightInputPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        weightInputPanel.setBackground(Color.WHITE);
        CustomInputField weightField = new CustomInputField();
        weightField.setPreferredSize(new Dimension(450, 40));
        weightInputPanel.add(weightField);
        listBicyclePanel.add(weightInputPanel);

        JPanel ratingLabelPanel = new JPanel();
        ratingLabelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        ratingLabelPanel.setBackground(Color.WHITE);
        JLabel ratingLabel = new JLabel("Rating");
        ratingLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        ratingLabelPanel.add(ratingLabel);
        listBicyclePanel.add(ratingLabelPanel);

        JPanel ratingOptionsPanel = conditionPanel();
        listBicyclePanel.add(ratingOptionsPanel);


        JPanel rateLabelPanel = new JPanel();
        rateLabelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        rateLabelPanel.setBackground(Color.WHITE);
        JLabel rateLabel = new JLabel("Hourly Rate");
        nameLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        rateLabelPanel.add(rateLabel);
        listBicyclePanel.add(rateLabelPanel);

        JPanel rateInputPanel = new JPanel();
        rateInputPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        rateInputPanel.setBackground(Color.WHITE);
        CustomInputField rateField = new CustomInputField();
        rateField.setPreferredSize(new Dimension(450, 40));
        rateInputPanel.add(rateField);
        listBicyclePanel.add(rateInputPanel);

        JPanel LocationLabelPanel = new JPanel();
        LocationLabelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        LocationLabelPanel.setBackground(Color.WHITE);
        JLabel locationLabel = new JLabel("Location");
        nameLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        LocationLabelPanel.add(locationLabel);
        listBicyclePanel.add(LocationLabelPanel);

        JPanel locationInputPanel = new JPanel();
        locationInputPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        locationInputPanel.setBackground(Color.WHITE);
        CustomInputField locationField = new CustomInputField();
        locationField.setPreferredSize(new Dimension(450, 40));
        locationInputPanel.add(locationField);
        listBicyclePanel.add(locationInputPanel);

        JPanel signupButtonPanel = new JPanel();
        signupButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        signupButtonPanel.setBackground(Color.WHITE);
        CustomButton listButton = new CustomButton("List");
        CustomButton backButton = new CustomButton("Back");
        signupButtonPanel.add(listButton);
        signupButtonPanel.add(backButton);
        listBicyclePanel.add(signupButtonPanel);

        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dispose();
                new ProfilePage(userId).setVisible(true);
            }
        });

        listButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {

                try {
                    String name = nameField.getText();
                    String year = yearField.getText();
                    String weight = weightField.getText();
                    String rating = getSelectedRating(ratingGroup);
                    String rateString = rateField.getText();
                    String location = locationField.getText();
                    int rate = parseInt(rateString);

                    if (name.isEmpty() || year.isEmpty() || weight.isEmpty() || rating == null || rateString.isEmpty() || location.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please fill all fields!");
                        return;
                    }

                    if (rate < 300) {
                        throw new IllegalArgumentException("Hourly rate must be at least 300.");
                    }

                    Bicycle bicycle = new Bicycle(name, parseInt(year), parseFloat(weight), rating, rate, location, userId);

                    BicycleController bicycleController = new BicycleController();
                    boolean success = bicycleController.insertBicycle(bicycle);

                    if (success) {
                        JOptionPane.showMessageDialog(null, "Bicycle listed successfully!");
                        dispose();
                        new ProfilePage(userId).setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error listing Bicycle!");
                    }

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Please enter valid numeric values for year, weight, and hourly rate.");
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
        });

    }

    private static JPanel conditionPanel() {
        JPanel ratingOptionsPanel = new JPanel();
        ratingOptionsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        ratingOptionsPanel.setBackground(Color.WHITE);

        CustomRadioButton averageButton = new CustomRadioButton("Average");
        CustomRadioButton goodButton = new CustomRadioButton("Good");
        CustomRadioButton excellentButton = new CustomRadioButton("Excellent");
        CustomRadioButton perfectButton = new CustomRadioButton("Perfect");

        ratingGroup.add(averageButton);
        ratingGroup.add(goodButton);
        ratingGroup.add(excellentButton);
        ratingGroup.add(perfectButton);

        ratingOptionsPanel.add(averageButton);
        ratingOptionsPanel.add(goodButton);
        ratingOptionsPanel.add(excellentButton);
        ratingOptionsPanel.add(perfectButton);

        return ratingOptionsPanel;
    }

    public String getSelectedRating(ButtonGroup ratingGroup) {
        Enumeration<AbstractButton> buttons = ratingGroup.getElements();
        while (buttons.hasMoreElements()) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }

}
