package grp.oozmakappa.monsterclash.model.immutable;

import java.util.LinkedList;

public final class Transformer {

    public static <T> LinkedList<T> add(LinkedList<T> input, T element) {
        LinkedList<T> output = new LinkedList<>(input);
        output.add(element);
        return output;
    }

    public static <T> LinkedList<T> removeLast(LinkedList<T> input) {
        LinkedList<T> output = new LinkedList<>(input);
        output.removeLast();
        return output;
    }

    public static <T> LinkedList<T> duplicate(LinkedList<T> input) {
        return new LinkedList<>(input);
    }

}
