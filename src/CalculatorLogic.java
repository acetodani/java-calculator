import java.math.BigDecimal;

public class CalculatorLogic {

    double currentValue;
    double storedValue;
    String operator;
    double lastOperand;
    String lastOperator;
    boolean startNewNumber;

    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return a / b;
    }

    public double modulo(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return BigDecimal.valueOf(a)
                .remainder(BigDecimal.valueOf(b))
                .doubleValue();
    }

    public double calculate(double a, double b, String op) {
        switch (op) {
            case "+":
                return add(a, b);
            case "-":
                return subtract(a, b);
            case "x":
                return multiply(a, b);
            case "÷":
                return divide(a, b);
            case "%":
                return modulo(a, b);
            default:
                return b;
        }
    }

}
