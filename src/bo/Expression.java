package bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Expression {

    private static final int MAX_VALUE = 1;
    private Random random = new Random();

    private List<Object> calcul = new ArrayList<>();
    private String strCalcul;
    private double result;

    public Expression() {
        generateCalcul();
    }

    public void generateCalcul() {

        Operator bin;
        for (int i = 0; i < MAX_VALUE; i++) {
            if (i < 1) {
                calcul.add(getRandom());
            }
            calcul.add(getRandom());
            bin = Operator.randomOperator();
            if (bin == Operator.inverse || bin == Operator.racine) {
                calcul.add(bin);
                calcul.add(Operator.randomBinaryOperator());
            } else {
                calcul.add(bin);
            }
        }
        setCalcul(calcul);
        evaluateCalcul();
        calculToString();
    }

    public void evaluateCalcul() {
        Stack<Double> stack = new Stack<>();
        double a;
        double b;

        for (Object obj : getCalcul()) {
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
        setResult(stack.pop());
    }

    public void calculToString() {
        String tempStr = "";
        List<Object> tempCalcul = getCalcul();

        for (Object obj : tempCalcul) {
            tempStr += obj.toString();
        }
        setStrCalcul(tempStr);
    }

    public double getRandom() {
        return random.nextInt(5);
    }

    public List<Object> getCalcul() {
        return calcul;
    }

    public void setCalcul(List<Object> calcul) {
        this.calcul = calcul;
    }

    public String getStrCalcul() {
        return strCalcul;
    }

    public void setStrCalcul(String strCalcul) {
        this.strCalcul = strCalcul;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Expression{" +
                "random=" + random +
                ", calcul=" + calcul +
                ", strCalcul='" + strCalcul + '\'' +
                ", result=" + result +
                '}';
    }
}
