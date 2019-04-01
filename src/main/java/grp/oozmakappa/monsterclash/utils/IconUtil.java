package grp.oozmakappa.monsterclash.utils;

import javax.swing.*;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import static grp.oozmakappa.monsterclash.utils.FileUtil.getSubFiles;

/**
 * @author Chenglong Ma
 */
public class IconUtil {

    //region For Pieces
    // Oozma Kappa
    public static final String JAMES_P_SULLIVAN = "oozmakappa/james_p_sullivan.png";
    public static final String MIKE_WEZOWSKI = "oozmakappa/mike_wazowski.png";
    public static final String SQUISHY = "oozmakappa/squishy.png";
    // Roar Omega Roar
    public static final String CHET_ALEXANDER = "roaromegaroar/chet_alexander.png";
    public static final String JOHNNY_WORTHINGTON = "roaromegaroar/johnny_worthington.png";
    public static final String RANDALL_BOGGS = "roaromegaroar/randall_boggs.png";

    private static final String MONSTERS_DIR = "img/monsters/";
    //endregion

    //region For Dice
    private static final String DICE_VALUE_DIR = "img/dice/value/";
    public static final List<Icon> DICE_ICONS = getIcons(DICE_VALUE_DIR);
    private static final String DICE_ROLLING_DIR = "img/dice/rolling/";
    public static final List<Icon> DICE_ROLLING_ICONS = getIcons(DICE_ROLLING_DIR);
    //endregion

    /**
     * Returns {@link Icon} by file name
     *
     * @param name
     * @return
     */
    public static ImageIcon getMonsterIcon(String name) {
        String path = MONSTERS_DIR + name;
        File file = FileUtil.getResource(path);
        return new ImageIcon(file.getAbsolutePath());
    }

    /**
     * Returns {@link Icon} set under specified dir.
     *
     * @param dir
     * @return The {@link Icon}s under specified dir.
     */
    public static List<Icon> getIcons(String dir) {
        return getSubFiles(dir).stream()
                .map(ImageIcon::new).collect(Collectors.toList());
    }
}
