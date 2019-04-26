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

    @Override
    public String toString() {
        if (name().isEmpty()) {
            return "";
        }
        String[] words = name().split("_");
        return String.join(" ", capitalizeFirstLetter(words));
    }

    public ImageIcon getIcon() {
        String filename = ABILITIES_DIR + name().toLowerCase() + ".png";
        return IconUtil.getIcon(filename);
    }

    public ImageIcon getPressedIcon() {
        String filename = ABILITIES_DIR + name().toLowerCase() + "_armed.png";
        return IconUtil.getIcon(filename);
    }
}
