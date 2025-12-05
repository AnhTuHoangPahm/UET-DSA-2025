package Algorithm.Other;

import DataStruct.Stack.MyStack;

import java.io.IOException;
import java.io.StringReader;

public class TwoStack {
    public TwoStack() {
        System.out.println("Dijkstra's Two Stack algorithm");
        System.out.println("------------------------------");
        System.out.println("""
                Insert expression.
                Rules: put parenthesis around any sub expression.
                Example: ((2*(3+4))+2) instead of (2*(3+4)+2)
                ---------------------------------------------"""
        );
    }

    public double Calculate(String expression) {
        MyStack<Double> values = new MyStack<>(2);
        MyStack<String> operators = new MyStack<>(1);

        StringReader reader = new StringReader(expression);

        for (int i = 0; i < expression.length(); i++) {
            try {
                String c = String.valueOf((char) reader.read());
                if (c.equals("(")) /*Do Nothing*/ ;
                else if (c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/"))
                    operators.push(c);
                else if (c.equals(")")) {
                    String operator = operators.pop();
                    switch (operator) {
                        case "+" -> values.push(values.pop() + values.pop());
                        case "-" -> values.push(values.pop() - values.pop());
                        case "*" -> values.push(values.pop() * values.pop());
                        case "/" -> {
                            try {
                                double denominator = values.pop();
                                double numerator = values.pop();
                                if (numerator == 0.0) {
                                    throw new ArithmeticException("Divide by ZERO!");
                                }
                                values.push(numerator / denominator);
                            } catch (ArithmeticException e) {
                                System.out.println(e.getMessage()); // not get printed out during test bruh??
                            }
                        }
                    }
                } else
                    values.push(Double.parseDouble(c));
            } catch (IOException e) {
                System.err.println(e.getMessage());
                break;
            }
        }
        return values.pop();
    }
}
