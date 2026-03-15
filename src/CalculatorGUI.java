import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class CalculatorGUI {

    public static final int WINDOW_HEIGHT = 500;
    public static final int WINDOW_WIDTH = 300;
    public static final int PANEL_HEIGHT = 80;
    public static final int BUTTON_HEIGHT = 55;
    public static final int BUTTON_WIDTH = 55;
    private static final int DISPLAY_FONT_SIZE_LARGE = 30;
    private static final int DISPLAY_FONT_SIZE_MEDIUM = 26;
    private static final int DISPLAY_FONT_SIZE_SMALL = 22;
    private static final int DISPLAY_FONT_SIZE_MIN = 18;
    private static final Color NUMBER_BUTTON_COLOR = new Color(72, 71, 68);
    private static final Color UTILITY_BUTTON_COLOR = new Color(115, 113, 113);
    private static final Color OPERATION_BUTTON_COLOR = new Color(255, 139, 0);

    public CalculatorGUI() {
        JFrame window = new JFrame("Calculator");
        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        window.getContentPane().setBackground(new Color(33, 31, 29));
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLayout(null);
        CalculatorLogic logic = new CalculatorLogic();

        JTextField textPanel = new JTextField();
        textPanel.setBounds(35, 20, 235, PANEL_HEIGHT);
        textPanel.setBackground(new Color(33, 31, 29));
        textPanel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 30));
        textPanel.setEditable(false);
        textPanel.setForeground(Color.white);
        textPanel.setHorizontalAlignment(JTextField.RIGHT);
        textPanel.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateDisplayFontSize(textPanel);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateDisplayFontSize(textPanel);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateDisplayFontSize(textPanel);
            }
        });
        updateDisplayFontSize(textPanel);

        CalculatorButton btn1 = new CalculatorButton("1", 35, 190, BUTTON_WIDTH, BUTTON_HEIGHT,
                NUMBER_BUTTON_COLOR, 15);
        addAppendListener(btn1, textPanel, "1");

        CalculatorButton btn2 = new CalculatorButton("2", 95, 190, BUTTON_WIDTH, BUTTON_HEIGHT,
                NUMBER_BUTTON_COLOR, 15);
        addAppendListener(btn2, textPanel, "2");

        CalculatorButton btn3 = new CalculatorButton("3", 155, 190, BUTTON_WIDTH, BUTTON_HEIGHT,
                NUMBER_BUTTON_COLOR, 15);
        addAppendListener(btn3, textPanel, "3");

        CalculatorButton btn4 = new CalculatorButton("4", 35, 260, BUTTON_WIDTH, BUTTON_HEIGHT,
                NUMBER_BUTTON_COLOR, 15);
        addAppendListener(btn4, textPanel, "4");

        CalculatorButton btn5 = new CalculatorButton("5", 95, 260, BUTTON_WIDTH, BUTTON_HEIGHT,
                NUMBER_BUTTON_COLOR, 15);
        addAppendListener(btn5, textPanel, "5");

        CalculatorButton btn6 = new CalculatorButton("6", 155, 260, BUTTON_WIDTH, BUTTON_HEIGHT,
                NUMBER_BUTTON_COLOR, 15);
        addAppendListener(btn6, textPanel, "6");

        CalculatorButton btn7 = new CalculatorButton("7", 35, 330, BUTTON_WIDTH, BUTTON_HEIGHT,
                NUMBER_BUTTON_COLOR, 15);
        addAppendListener(btn7, textPanel, "7");

        CalculatorButton btn8 = new CalculatorButton("8", 95, 330, BUTTON_WIDTH, BUTTON_HEIGHT,
                NUMBER_BUTTON_COLOR, 15);
        addAppendListener(btn8, textPanel, "8");

        CalculatorButton btn9 = new CalculatorButton("9", 155, 330, BUTTON_WIDTH, BUTTON_HEIGHT,
                NUMBER_BUTTON_COLOR, 15);
        addAppendListener(btn9, textPanel, "9");

        CalculatorButton negativeOperationBtn = new CalculatorButton("+/−", 35, 400, BUTTON_WIDTH, BUTTON_HEIGHT,
                NUMBER_BUTTON_COLOR, 15);
        negativeOperationBtn.addActionListener(e -> toggleSign(textPanel, logic));

        CalculatorButton btn0 = new CalculatorButton("0", 95, 400, BUTTON_WIDTH, BUTTON_HEIGHT,
                NUMBER_BUTTON_COLOR, 15);
        addAppendListener(btn0, textPanel, "0");

        CalculatorButton decimalBtn = new CalculatorButton(".", 155, 400, BUTTON_WIDTH, BUTTON_HEIGHT,
                NUMBER_BUTTON_COLOR, 15);
        addAppendListener(decimalBtn, textPanel, ".");

        CalculatorButton acBtn = new CalculatorButton("AC", 95, 120, BUTTON_WIDTH, BUTTON_HEIGHT,
                UTILITY_BUTTON_COLOR, 15);

        acBtn.addActionListener(e -> {
            textPanel.setText("");
            logic.storedValue = 0;
            logic.currentValue = 0;
            logic.operator = null;
            logic.lastOperand = 0;
            logic.lastOperator = null;
        });

        CalculatorButton deleteBtn = new CalculatorButton("Del", 35, 120, BUTTON_WIDTH, BUTTON_HEIGHT,
                UTILITY_BUTTON_COLOR, 15);

        deleteBtn.addActionListener(e -> {
            String currentText = textPanel.getText();
            if (!currentText.isEmpty()) {
                String updatedText = currentText.substring(0, currentText.length() - 1);
                textPanel.setText(updatedText);

                if (logic.operator != null && !updatedText.contains(logic.operator)) {
                    logic.operator = null;
                }
            }
        });

        CalculatorButton moduloOpBtn = new CalculatorButton("%", 155, 120, BUTTON_WIDTH, BUTTON_HEIGHT,
                UTILITY_BUTTON_COLOR, 15);
        addPercentListener(moduloOpBtn, textPanel, logic);

        CalculatorButton divBtn = new CalculatorButton("÷", 215, 120, BUTTON_WIDTH, BUTTON_HEIGHT,
                UTILITY_BUTTON_COLOR, 20);
        addOperatorListener(divBtn, textPanel, logic, "÷");

        CalculatorButton multiplyBtn = new CalculatorButton("x", 215, 190, BUTTON_WIDTH, BUTTON_HEIGHT,
                OPERATION_BUTTON_COLOR, 15);
        addOperatorListener(multiplyBtn, textPanel, logic, "x");

        CalculatorButton subtractBtn = new CalculatorButton("-", 215, 260, BUTTON_WIDTH, BUTTON_HEIGHT,
                OPERATION_BUTTON_COLOR, 15);
        addOperatorListener(subtractBtn, textPanel, logic, "-");

        CalculatorButton addBtn = new CalculatorButton("+", 215, 330, BUTTON_WIDTH, BUTTON_HEIGHT,
                OPERATION_BUTTON_COLOR, 15);
        addOperatorListener(addBtn, textPanel, logic, "+");

        CalculatorButton equalsBtn = new CalculatorButton("=", 215, 400, BUTTON_WIDTH, BUTTON_HEIGHT,
                OPERATION_BUTTON_COLOR, 15);

        equalsBtn.addActionListener(e -> {
            String currentText = textPanel.getText();
            if (currentText.isEmpty()) {
                return;
            }

            try {
                if (logic.operator != null) {
                    int operatorIndex = findOperatorIndex(currentText, logic.operator);
                    if (operatorIndex < 0 || operatorIndex == currentText.length() - 1) {
                        return;
                    }

                    double firstValue = Double.parseDouble(currentText.substring(0, operatorIndex));
                    double currentValue = Double
                            .parseDouble(currentText.substring(operatorIndex + logic.operator.length()));
                    double result = logic.calculate(firstValue, currentValue, logic.operator);

                    logic.currentValue = result;
                    logic.storedValue = result;
                    logic.lastOperand = currentValue;
                    logic.lastOperator = logic.operator;
                    logic.operator = null;
                    textPanel.setText(formatResult(result));
                    return;
                }

                if (logic.lastOperator != null) {
                    double currentValue = Double.parseDouble(currentText);
                    double result = logic.calculate(currentValue, logic.lastOperand, logic.lastOperator);
                    logic.currentValue = result;
                    logic.storedValue = result;
                    textPanel.setText(formatResult(result));
                }
            } catch (RuntimeException ex) {
                textPanel.setText("Error");
                logic.storedValue = 0;
                logic.currentValue = 0;
                logic.operator = null;
                logic.lastOperand = 0;
                logic.lastOperator = null;
            }
        });

        addComponents(
                window,
                textPanel,
                acBtn,
                deleteBtn,
                moduloOpBtn,
                btn1,
                btn2,
                btn3,
                btn4,
                btn5,
                btn0,
                negativeOperationBtn,
                decimalBtn,
                btn6,
                btn7,
                btn8,
                btn9,
                divBtn,
                multiplyBtn,
                subtractBtn,
                addBtn,
                equalsBtn);

        window.setVisible(true);
    }

    private void addAppendListener(CalculatorButton button, JTextField textPanel, String value) {
        button.addActionListener(e -> textPanel.setText(textPanel.getText() + value));
    }

    private void addOperatorListener(CalculatorButton button, JTextField textPanel, CalculatorLogic logic,
            String operator) {
        button.addActionListener(e -> {
            String currentText = textPanel.getText();
            if (currentText.isEmpty()) {
                return;
            }

            if (logic.operator != null) {
                int operatorIndex = findOperatorIndex(currentText, logic.operator);
                if (operatorIndex == currentText.length() - 1) {
                    textPanel.setText(currentText.substring(0, operatorIndex) + operator);
                    logic.operator = operator;
                }
                return;
            }

            logic.storedValue = Double.parseDouble(currentText);
            logic.operator = operator;
            logic.lastOperator = null;
            textPanel.setText(currentText + operator);
        });
    }

    private void addPercentListener(CalculatorButton button, JTextField textPanel, CalculatorLogic logic) {
        button.addActionListener(e -> {
            String currentText = textPanel.getText();
            if (currentText.isEmpty()) {
                return;
            }

            try {
                if (logic.operator == null) {
                    double value = Double.parseDouble(currentText);
                    double percentValue = value / 100.0;
                    textPanel.setText(formatResult(percentValue));
                    logic.currentValue = percentValue;
                    return;
                }

                int operatorIndex = findOperatorIndex(currentText, logic.operator);
                if (operatorIndex < 0 || operatorIndex == currentText.length() - 1) {
                    return;
                }

                double firstValue = Double.parseDouble(currentText.substring(0, operatorIndex));
                double secondValue = Double.parseDouble(currentText.substring(operatorIndex + logic.operator.length()));

                double percentValue;
                if ("+".equals(logic.operator) || "-".equals(logic.operator)) {
                    percentValue = firstValue * secondValue / 100.0;
                } else {
                    percentValue = secondValue / 100.0;
                }

                textPanel.setText(formatResult(firstValue) + logic.operator + formatResult(percentValue));
            } catch (RuntimeException ex) {
                textPanel.setText("Error");
                logic.storedValue = 0;
                logic.currentValue = 0;
                logic.operator = null;
                logic.lastOperand = 0;
                logic.lastOperator = null;
            }
        });
    }

    private void addComponents(JFrame window, Component... components) {
        for (Component component : components) {
            window.add(component);
        }
    }

    private void toggleSign(JTextField textPanel, CalculatorLogic logic) {
        String currentText = textPanel.getText();
        if (currentText.isEmpty()) {
            return;
        }

        if (logic.operator == null) {
            if (currentText.startsWith("-")) {
                textPanel.setText(currentText.substring(1));
            } else {
                textPanel.setText("-" + currentText);
            }
            return;
        }

        int operatorIndex = findOperatorIndex(currentText, logic.operator);
        if (operatorIndex < 0) {
            return;
        }

        String prefix = currentText.substring(0, operatorIndex + logic.operator.length());
        String suffix = currentText.substring(operatorIndex + logic.operator.length());

        if (suffix.startsWith("-")) {
            textPanel.setText(prefix + suffix.substring(1));
        } else {
            textPanel.setText(prefix + "-" + suffix);
        }
    }

    private int findOperatorIndex(String text, String operator) {
        int startIndex = text.startsWith("-") ? 1 : 0;
        return text.indexOf(operator, startIndex);
    }

    private void updateDisplayFontSize(JTextField textPanel) {
        int textLength = textPanel.getText().length();
        int fontSize;

        if (textLength <= 8) {
            fontSize = DISPLAY_FONT_SIZE_LARGE;
        } else if (textLength <= 12) {
            fontSize = DISPLAY_FONT_SIZE_MEDIUM;
        } else if (textLength <= 16) {
            fontSize = DISPLAY_FONT_SIZE_SMALL;
        } else {
            fontSize = DISPLAY_FONT_SIZE_MIN;
        }

        textPanel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, fontSize));
    }

    private String formatResult(double result) {
        BigDecimal formatted = BigDecimal.valueOf(result)
                .setScale(10, RoundingMode.HALF_UP)
                .stripTrailingZeros();
        return formatted.toPlainString();
    }
}