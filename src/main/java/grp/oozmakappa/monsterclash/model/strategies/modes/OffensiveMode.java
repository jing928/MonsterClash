package grp.oozmakappa.monsterclash.model.strategies.modes;

/**
 * @author Chenglong Ma
 */
class OffensiveMode implements Mode {
    private static OffensiveMode instance;

    private OffensiveMode() {
        // for singleton pattern
    }

    static OffensiveMode getInstance() {
        if (instance == null) {
            instance = new OffensiveMode();
        }
        return instance;
    }

    @Override
    public double getAttackPower(double defaultPower) {
        return defaultPower * DEFAULT_AMPLIFIER;
    }

    @Override
    public double getArmor(double defaultArmor) {
        return defaultArmor * DEFAULT_ALLAYER;
    }
}
