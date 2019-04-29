package grp.oozmakappa.monsterclash.model;

import grp.oozmakappa.monsterclash.model.interfaces.DiceObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chenglong Ma
 */
public final class Dice {
    private static final int MAX_DICE = 6;
    private final List<DiceObserver> observers = new ArrayList<>();
    private static Dice dice;

    private Dice() {
        // cannot be instantiated
    }

    public static synchronized Dice getInstance() {
        if (dice == null) {
            dice = new Dice();
        }
        return dice;
    }

    public void addObserver(DiceObserver observer) {
        observers.add(observer);
    }

    /**
     * Generates next dice value randomly.
     *
     * @return the new dice value
     * @Ensures NumberUtil.between(value, 0, 5)
     */
    public int roll() {
        int value = (int) (Math.random() * MAX_DICE) + 1;
        observers.forEach(o -> o.valueChanged(value));
        return value;
    }

}
