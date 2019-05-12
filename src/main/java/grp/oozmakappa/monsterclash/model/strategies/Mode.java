package grp.oozmakappa.monsterclash.model.strategies;

import grp.oozmakappa.monsterclash.utils.Constraints;

/**
 * @author Chenglong Ma
 */
public interface Mode {
    double DEFAULT_AMPLIFIER = 1.5;
    double DEFAULT_ALLAYER = 0.8;

    /**
     * Returns concrete {@link Mode} by name
     *
     * @param name {@link Constraints#OFFENSIVE_MODE} or {@link Constraints#DEFENSIVE_MODE}
     * @return
     * @Requires name.equals(Constraints.OFFENSIVE_MODE) || name.equals(Constraints.DEFENSIVE_MODE)
     */
    static Mode getMode(String name) {
        switch (name) {
            default:
                return DefaultMode.getInstance();
            case Constraints.OFFENSIVE_MODE:
                return OffensiveMode.getInstance();
            case Constraints.DEFENSIVE_MODE:
                return DefensiveMode.getInstance();
        }
    }

    double getAttackPower(double defaultPower);

    double getArmor(double defaultArmor);
}
