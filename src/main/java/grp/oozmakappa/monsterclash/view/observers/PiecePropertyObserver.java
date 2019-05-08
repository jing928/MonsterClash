package grp.oozmakappa.monsterclash.view.observers;

/**
 * @author Chenglong Ma
 */
public interface PiecePropertyObserver {
    void healthChanged(double deltaHealth);

    void powerChanged(double deltaPower);

    void rangeChanged(int deltaRange);

}
