# Java Calculator

A simple desktop calculator built with Java Swing, inspired by the iOS calculator layout.

## Features

- **Basic Arithmetic**: Addition, Subtraction, Multiplication, Division
- **Modulo**: Remainder operation between two numbers
- **Percentage**: Context-aware percentage calculation (relative for `+`/`-`, absolute for `×`/`÷`)
- **Sign Toggle** (`+/−`): Flip the sign of the current number or the second operand
- **Decimal Support**: Enter decimal numbers with the `.` button
- **Delete**: Remove the last entered character
- **All Clear** (`AC`): Reset the calculator to its initial state
- **Repeat Last Operation**: Pressing `=` repeatedly re-applies the last operator and operand
- **Adaptive Display**: Font size automatically shrinks as the displayed number gets longer
- **Error Handling**: Displays `Error` on division by zero or other invalid operations

## Project Structure

```
src/
├── Main.java             # Entry point — launches the calculator window
├── CalculatorGUI.java    # Swing UI layout and all button event handlers
├── CalculatorLogic.java  # Core arithmetic operations
└── CalculatorButton.java # Custom JButton with press/highlight styling
```

## Requirements

- Java 8 or higher
- No external dependencies — uses the standard Java SE library only

## Running the Application

Compile and run from the `src` directory:

```bash
cd src
javac *.java
java Main
```

## Screenshots

The calculator features a dark theme with orange operator buttons, matching the look of a modern iOS-style calculator.

| Button Color | Usage |
|---|---|
| Dark gray | Number digits (`0`–`9`), decimal, sign toggle |
| Medium gray | Utility buttons (`Del`, `AC`, `%`) and division |
| Orange | Operator buttons (`×`, `−`, `+`, `=`) |

## License

This project is open source. Feel free to use, modify, and distribute it.
