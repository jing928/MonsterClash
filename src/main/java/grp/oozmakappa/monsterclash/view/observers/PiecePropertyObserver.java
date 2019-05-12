package grp.oozmakappa.monsterclash.view.observers;

/**
 * @author Chenglong Ma
 */
public interface PiecePropertyObserver {
    void healthChanged(double deltaHealth, boolean shouldNotify);

    void powerChanged(double deltaPower, boolean shouldNotify);

    void rangeChanged(int deltaRange, boolean shouldNotify);

}
