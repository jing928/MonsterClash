package grp.oozmakappa.monsterclash.model.strategies.modes;

/**
 * @author Chenglong Ma
 */
class DefensiveMode implements Mode {
    private static DefensiveMode instance;

    private DefensiveMode() {
        // for singleton pattern
    }

    static DefensiveMode getInstance() {
        if (instance == null) {
            instance = new DefensiveMode();
        }
        return instance;
    }

    @Override
    public double getAttackPower(double defaultPower) {
        return defaultPower * DEFAULT_ALLAYER;
    }

    @Override
    public double getArmor(double defaultArmor) {
        return defaultArmor * DEFAULT_AMPLIFIER;
    }
}
