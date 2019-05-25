package grp.oozmakappa.monsterclash.model;

import grp.oozmakappa.monsterclash.model.interfaces.DiceObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chenglong Ma
 * @Invariant NumberUtil.between(value, 1, 6)
 */
public final class Dice {
    private static final int MAX_DICE = 6;
    private static Dice dice;
    private final List<DiceObserver> observers = new ArrayList<>();
    private boolean canRoll = true;
    private int value;

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
     * @Ensures NumberUtil.between(value, 1, 6)
     */
    public void roll() {
        int value = (int) (Math.random() * MAX_DICE) + 1;
        setValue(value);
        canRoll = false;
    }

    public boolean canRoll() {
        return canRoll;
    }

    public void setCanRoll(boolean canRoll) {
        this.canRoll = canRoll;
    }

    public int getValue() {
        return value;
    }

    /**
     * @param value
     * @Requires NumberUtil.between(value, 1, 6)
     */
    public void setValue(final int value) {
        this.value = value;
        observers.forEach(o -> o.valueChanged(value));
    }
}
