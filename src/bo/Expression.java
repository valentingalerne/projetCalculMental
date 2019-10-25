package bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Expression {

    private static final int MAX_VALUE = 2;
    private Random random = new Random();

    private List<Object> calcul = new ArrayList<>();

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

    @Override
    public String toString() {
        return "Expression{" +
                "calcul='" + calcul + '\'' +
                '}';
    }
}
