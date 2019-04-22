package grp.oozmakappa.monsterclash.model;

import grp.oozmakappa.monsterclash.model.interfaces.DiceObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chenglong Ma
 */
public final class Dice {
    private static final int MAX_DICE = 6;
    private static final List<DiceObserver> observers = new ArrayList<>();

    private Dice() {
        // cannot be instantiated
    }

    public static void addObserver(DiceObserver observer) {
        observers.add(observer);
    }

    /**
     * Generates next dice value randomly.
     *
     * @return the new dice value
     * @post NumberUtil.between(value, 0, 5) == true
     */
    public static int roll() {
        int value = (int) (Math.random() * MAX_DICE) + 1;
        observers.forEach(o -> o.valueChanged(value));
        return value;
    }

}
