package com.teknos.calculadora;

import java.util.Stack;

public class Calculator {

    public static double evaluateExpression(String expression) {
        try {
            return evaluate(expression);
        } catch (Exception e) {
            e.printStackTrace();
            return Double.NaN;
        }
    }

    private static double evaluate(String expression) {
        String[] expressionChars = expression.split("(?=[-+*/])|(?<=[-+*/])");  // Divideix la cadena en caracters i operadors

        Stack<Double> values = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (String expressionChar : expressionChars) { // S'itera per tots els caràcters
            expressionChar = expressionChar.trim();
            if (expressionChar.isEmpty()) {
                continue;
            }

            char firstChar = expressionChar.charAt(0);

            if (Character.isDigit(firstChar) || (expressionChar.length() > 1 && firstChar == '-' && Character.isDigit(expressionChar.charAt(1)))) { // Si el caracter és un digit o un numero començat per - (negatiu) es posa al a pila values
                values.push(Double.parseDouble(expressionChar));
            } else if (isOperator(firstChar)) { // Si el caràcter es una operació es realitza una comprovació de precedencia
                while (!operators.isEmpty() && hasHigherPrecedence(operators.peek(), firstChar)) {
                    double operand2 = values.pop();
                    double operand1 = values.pop();
                    char operator = operators.pop();
                    double result = applyOperator(operator, operand1, operand2); // Es calcula el resultat i s'afegeix a la pila
                    values.push(result);
                }
                operators.push(firstChar);
            } else {
                throw new IllegalArgumentException("Invalid character: " + firstChar);
            }
        }

        while (!operators.isEmpty()) { // Acaba de calcular amb els últims operadors que queden a la pila (els de menys preferència: - i +)
            double operand2 = values.pop();
            double operand1 = values.pop();
            char operator = operators.pop();
            double result = applyOperator(operator, operand1, operand2);
            values.push(result);
        }

        return values.pop();
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static int getOperatorPrecedence(char operator) {
        if (operator == '+' || operator == '-') {
            return 1;
        } else if (operator == '*' || operator == '/') {
            return 2;
        }
        return 0;
    }

    private static boolean hasHigherPrecedence(char op1, char op2) {
        return getOperatorPrecedence(op1) >= getOperatorPrecedence(op2);
    }

    private static double applyOperator(char operator, double operand1, double operand2) {
        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                if (operand2 == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}

