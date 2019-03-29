package grp.oozmakappa.monsterclash.model;

/**
 * @author Chenglong Ma
 */
public final class Dice {

    private static final int MAX_DICE = 6;

    /**
     * Generates next dice value randomly.
     *
     * @return the new dice value.
     */
    public static int roll() {
        return (int) (Math.random() * MAX_DICE) + 1;
    }
}
