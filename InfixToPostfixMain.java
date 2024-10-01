import java.util.Scanner;

public class InfixToPostfixMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input infix expression
        System.out.println("Enter an infix expression (use variables A to E): ");
        String infixExpression = scanner.nextLine().replaceAll("\\s+", ""); // Remove spaces

        // Get variable values for A to E using an array
        int[] variableValues = getVariableValues(scanner);

        // Convert infix to postfix
        String postfixExpression = InfixToPostfix.convertInfixToPostfix(infixExpression);
        System.out.println("\nPostfix Notation: " + postfixExpression);

        // Evaluate postfix expression
        int result = InfixToPostfix.evaluatePostfix(postfixExpression, variableValues);
        System.out.println("\nGenerated Result: " + result);

        scanner.close();
    }

    // Method to get values for variables A to E
    private static int[] getVariableValues(Scanner scanner) {
        int[] variableValues = new int[5]; // Array to store values for A to E
        char[] variables = {'A', 'B', 'C', 'D', 'E'};
        
        for (int i = 0; i < 5; i++) {
            System.out.println("Enter value for " + variables[i] + ": ");
            variableValues[i] = scanner.nextInt();
        }
        return variableValues;
    }
}