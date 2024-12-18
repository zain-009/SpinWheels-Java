package components;

import javax.swing.*;
import java.awt.*;

public class CustomRadioButton extends JRadioButton {

    public CustomRadioButton(String text) {
        //pass text to super class JRadioButton
        super(text);

        //styling
        setFocusPainted(false);
        setBackground(Color.WHITE);
        setFont(new Font("SansSerif", Font.PLAIN, 14));
    }
}
