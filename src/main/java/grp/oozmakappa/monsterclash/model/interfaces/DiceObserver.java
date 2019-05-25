package grp.oozmakappa.monsterclash.model.interfaces;

/**
 * @author Chenglong Ma
 */
public interface DiceObserver {
    /**
     * Executes when {@link grp.oozmakappa.monsterclash.model.Dice}
     * generates new value.
     *
     * @param value the new dice value.
     * @Requires NumberUtil.between(value, 1, 6)
     */
    void valueChanged(int value);
}
