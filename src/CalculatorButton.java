import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class CalculatorButton extends JButton {

    private final Color baseColor;
    private final Color pressedColor;

    public CalculatorButton(String text, int x, int y, int width, int height, Color backgroundColor, int fontSize) {
        super(text);
        this.baseColor = backgroundColor;
        this.pressedColor = backgroundColor.brighter();
        setBounds(x, y, width, height);
        setBackground(baseColor);
        setOpaque(true);
        setBorderPainted(true);
        setFont(new Font(Font.MONOSPACED, Font.PLAIN, fontSize + 3));
        setBorder(BorderFactory.createLineBorder(Color.black, 3));
        setForeground(Color.white);

        getModel().addChangeListener(e -> {
            if (getModel().isArmed() && getModel().isPressed()) {
                setBackground(pressedColor);
            } else {
                setBackground(baseColor);
            }
        });
    }
}