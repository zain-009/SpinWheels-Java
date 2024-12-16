package components;

import javax.swing.*;
import java.awt.*;

public class CustomRadioButton extends JRadioButton {

    public CustomRadioButton(String text) {
        super(text);

        setFocusPainted(false);
        setBackground(Color.WHITE);
        setFont(new Font("SansSerif", Font.PLAIN, 14));
        setIcon(new UnselectedIcon());
        setSelectedIcon(new SelectedIcon());
    }

    private static class UnselectedIcon implements Icon {
        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(Color.LIGHT_GRAY);
            g2.fillOval(x, y, getIconWidth(), getIconHeight());

            g2.setColor(Color.WHITE);
            g2.fillOval(x + 2, y + 2, getIconWidth() - 4, getIconHeight() - 4);
        }

        @Override
        public int getIconWidth() {
            return 16;
        }

        @Override
        public int getIconHeight() {
            return 16;
        }
    }

    private static class SelectedIcon implements Icon {
        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(new Color(107, 141, 120));
            g2.fillOval(x, y, getIconWidth(), getIconHeight());

            g2.setColor(Color.WHITE);
            g2.fillOval(x + 2, y + 2, getIconWidth() - 4, getIconHeight() - 4);

            g2.setColor(new Color(107, 141, 120));
            g2.fillOval(x + 5, y + 5, getIconWidth() - 10, getIconHeight() - 10);
        }

        @Override
        public int getIconWidth() {
            return 16;
        }

        @Override
        public int getIconHeight() {
            return 16;
        }
    }
}
