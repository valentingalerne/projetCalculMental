package test;

import bo.Operator;
import bo.Expression;

import java.util.Stack;

import static java.lang.Integer.parseInt;

public class calculTest {

    public static void main(String[] args) {

        Stack<Double> stack = new Stack<>();

        Expression e = new Expression();
        System.out.println("Calcul : " + e.getCalcul());

        double a;
        double b;
        double c;
        for (Object obj : e.getCalcul()) {
            if (obj instanceof Double) {
                stack.push((Double) obj);
            } else {
                switch ((Operator) obj) {
                    case addition:
                        a = stack.pop();
                        b = stack.pop();
                        stack.push(a + b);
                        break;
                    case soustraction:
                        a = stack.pop();
                        b = stack.pop();
                        stack.push(a - b);
                        break;
                    case multiplication:
                        a = stack.pop();
                        b = stack.pop();
                        stack.push(a * b);
                        break;
                    case division:
                        a = stack.pop();
                        b = stack.pop();
                        stack.push(a / b);
                        break;
                    case inverse:
                        a = stack.pop();
                        stack.push(1 / a);
                        break;
                    case racine:
                        a = stack.pop();
                        stack.push(Math.sqrt(a));
                        break;
                }
            }
        }

        /*c = Math.round(stack.pop()) * 100;

        stack.push(c / 100);*/

        System.out.println(stack);

    }

    public static boolean isNumeric(String strNum) {
        return strNum.matches("-?\\d+(\\.\\d+)?");
    }
}
