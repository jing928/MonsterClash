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

    public static void main(String[] args) {
        // TODO: remove this test method
        LinkedList<String> in = new LinkedList<>();
        in.add("a");
        in.add("b");
        System.out.println(in);
//        LinkedList<String> out = add(in, "c");
        LinkedList<String> out = removeLast(in);
        System.out.println(in);
        System.out.println(out);
    }

}
