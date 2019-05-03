package grp.oozmakappa.monsterclash.model;

import grp.oozmakappa.monsterclash.utils.IconUtil;

import javax.swing.*;

import static grp.oozmakappa.monsterclash.utils.IconUtil.ABILITIES_DIR;
import static grp.oozmakappa.monsterclash.utils.StringUtil.capitalizeFirstLetter;

/**
 * @author Chenglong Ma
 */
public enum Ability {
    PLAIN_ATTACK,
    SPECIAL_ATTACK,
    SPECIAL_HEALING,
    SPECIAL_MOVE,
    ;

    private static final String DELIMITER = " ";

    /**
     * Parse {@link #toString()} to {@link Ability} instance
     *
     * @param formattedValue the String formatted by {@link #toString()}
     * @return
     * @see #toString()
     */
    public static Ability parse(String formattedValue) {
        String val = String.join("_", formattedValue.split(DELIMITER));
        try {
            return Ability.valueOf(val.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * Overrides super {@link #toString()} used for button text.
     *
     * @return
     */
    @Override
    public String toString() {
        if (name().isEmpty()) {
            return "";
        }
        String[] words = name().split("_");
        return String.join(DELIMITER, capitalizeFirstLetter(words));
    }

    /**
     * Returns the icon for Ability Button.
     *
     * @return
     */
    public ImageIcon getIcon() {
        String filename = ABILITIES_DIR + name().toLowerCase() + ".png";
        return IconUtil.getIcon(filename);
    }

    /**
     * Returns the icon used when button pressed.
     *
     * @return
     */
    public ImageIcon getPressedIcon() {
        String filename = ABILITIES_DIR + name().toLowerCase() + "_armed.png";
        return IconUtil.getIcon(filename);
    }
}
