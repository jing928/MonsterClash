package grp.oozmakappa.monsterclash.model.strategies;

/**
 * @author Chenglong Ma
 */
public class DefaultMode implements Mode {
    private static DefaultMode instance;

    private DefaultMode() {
        // for singleton pattern
    }

    public static DefaultMode getInstance() {
        if (instance == null) {
            instance = new DefaultMode();
        }
        return instance;
    }

    @Override
    public double getAttackPower(double defaultPower) {
        return defaultPower;
    }

    @Override
    public double getArmor(double defaultArmor) {
        return defaultArmor;
    }
}
