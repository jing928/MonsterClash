package grp.oozmakappa.monsterclash.utils;

/**
 * @author Chenglong Ma
 */
public class NumberUtil {
    /**
     * Check if the specified value is between the specified range.
     * <br>
     * NB, the `from` and `to` are inclusive.
     *
     * @param value the value to be checked.
     * @param from  the left boundary value, inclusive.
     * @param to    the right boundary value, inclusive.
     * @return
     */
    public static boolean between(int value, int from, int to) {
        return value >= from && value <= to;
    }
}
