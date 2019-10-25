package bo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Operator {

    addition,
    soustraction,
    multiplication,
    division,
    inverse,
    racine;

    private static final List<Operator> BINARY_OPERATOR_LIST = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int BINARY_OPERATOR_LIST_SIZE = BINARY_OPERATOR_LIST.size();
    private static final Random RANDOM = new Random();

    Operator() {
    }

    public static Operator randomOperator()  {
        return BINARY_OPERATOR_LIST.get(RANDOM.nextInt(BINARY_OPERATOR_LIST_SIZE));
    }

    public static Operator randomBinaryOperator()  {
        return BINARY_OPERATOR_LIST.get(RANDOM.nextInt(BINARY_OPERATOR_LIST_SIZE) - 2);
    }

}
