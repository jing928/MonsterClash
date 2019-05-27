package grp.oozmakappa.monsterclash.view.observers;

/**
 * @author Chenglong Ma
 */
public interface PiecePropertyObserver {
    void healthChanged(double currValue, double deltaHealth, double prevValue);

    void powerChanged(double currValue, double deltaPower);

    void armorChanged(double currValue, double deltaArmor);

    void rangeChanged(int currValue, int deltaRange);

}
