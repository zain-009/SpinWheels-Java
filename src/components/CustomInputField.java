package components;

import javax.swing.*;
import java.awt.*;

public class CustomInputField extends JTextField {

    public CustomInputField() {
        setFont(new Font("SansSerif", Font.PLAIN, 14));
        setBounds(0,0,150,10);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
    }
}
