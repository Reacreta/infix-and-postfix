public class InfixToPostfix {

    // Method to convert infix to postfix
    public static String convertInfixToPostfix(String infix) {
        char[] operators = new char[infix.length()];
        int operatorTop = -1;
        StringBuilder postfix = new StringBuilder();

        for (char ch : infix.toCharArray()) {
            if (Character.isLetter(ch)) {
                postfix.append(ch).append(' ');
            } else if (ch == '(') {
                operators[++operatorTop] = ch;
            } else if (ch == ')') {
                while (operatorTop >= 0 && operators[operatorTop] != '(') {
                    postfix.append(operators[operatorTop--]).append(' ');
                }
                operatorTop--; // Pop '('
            } else if (isOperator(ch)) {
                while (operatorTop >= 0 && precedence(operators[operatorTop]) >= precedence(ch)) {
                    postfix.append(operators[operatorTop--]).append(' ');
                }
                operators[++operatorTop] = ch; 
            }
        }

        // Append any remaining operators
        while (operatorTop >= 0) {
            postfix.append(operators[operatorTop--]).append(' ');
        }

        return postfix.toString().trim();
    }

    // Method to evaluate postfix expression
    public static int evaluatePostfix(String postfix, int[] variableValues) {
        int[] operandStack = new int[postfix.length()];
        int operandTop = -1;

        for (char ch : postfix.toCharArray()) {
            if (Character.isLetter(ch)) {
                operandStack[++operandTop] = variableValues[ch - 'A'];
            } else if (isOperator(ch)) {
                int operand2 = operandStack[operandTop--];
                int operand1 = operandStack[operandTop--];
                int result = performOperation(operand1, operand2, ch);
                operandStack[++operandTop] = result;
            }
        }
        return operandStack[operandTop];
    }

    // Helper method to check if a character is an operator
    private static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    // Helper method to determine operator precedence
    private static int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return -1;
        }
    }

    // Helper method to perform basic arithmetic operations
    private static int performOperation(int operand1, int operand2, char operator) {
        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}