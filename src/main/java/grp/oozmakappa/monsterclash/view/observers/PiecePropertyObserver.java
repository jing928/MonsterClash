package grp.oozmakappa.monsterclash.view.observers;

/**
 * @author Chenglong Ma
 */
public interface PiecePropertyObserver {
    void healthChanged(double deltaHealth, boolean isUndoing);

    void powerChanged(double deltaPower, boolean isUndoing);

    void rangeChanged(int deltaRange, boolean isUndoing);

}
