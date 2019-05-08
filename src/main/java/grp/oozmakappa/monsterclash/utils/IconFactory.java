package grp.oozmakappa.monsterclash.utils;

import grp.oozmakappa.monsterclash.utils.flyweights.IconFlyweight;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static grp.oozmakappa.monsterclash.utils.FileUtil.getSubFiles;
import static java.awt.Image.SCALE_SMOOTH;
import static java.awt.RenderingHints.KEY_INTERPOLATION;
import static java.awt.RenderingHints.VALUE_INTERPOLATION_BILINEAR;

/**
 * @author Chenglong Ma
 */
public class IconFactory {

    // Oozma Kappa
    public static final String JAMES_P_SULLIVAN = "oozmakappa/james_p_sullivan.png";
    public static final String MIKE_WAZOWSKI = "oozmakappa/mike_wazowski.png";
    //region For Pieces
    public static final String SQUISHY = "oozmakappa/squishy.png";
    // Roar Omega Roar
    public static final String CHET_ALEXANDER = "roaromegaroar/chet_alexander.png";
    public static final String JOHNNY_WORTHINGTON = "roaromegaroar/johnny_worthington.png";
    public static final String RANDALL_BOGGS = "roaromegaroar/randall_boggs.png";
    private static final Logger LOG = LogManager.getLogger();
    private static final String MONSTERS_DIR = "img/monsters/";
    private static final String DICE_VALUE_DIR = "img/dice/value/";

    //endregion

    //region For Dice
    private static final String DICE_ROLLING_DIR = "img/dice/rolling/";
    private static IconFactory instance;

    //endregion
    private final Map<String, IconFlyweight> icons;

    private IconFactory() {
        // for singleton pattern
        icons = new HashMap<>();
    }

    public static IconFactory getInstance() {
        if (instance == null) {
            instance = new IconFactory();
        }
        return instance;
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

    /**
     * Resize a image to new scale
     *
     * @param image
     * @param width
     * @param height
     * @return the resized image
     * @Ensures resized.getWidth(null) == width
     * @Ensures resized.getHeight(null) == height
     */
    public static Image resize(final Image image, int width, int height) {
        int realWidth = image.getWidth(null);
        int realHeight = image.getHeight(null);
        if (realWidth == width && realHeight == height) {
            return image;
        }

        final BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        final Graphics2D g2d = resized.createGraphics();
        g2d.setRenderingHint(KEY_INTERPOLATION, VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();
        return resized;
    }

    /**
     * Returns {@link Icon} by file name
     *
     * @param name the icon name
     * @return the icon of monster
     */
    public IconFlyweight getMonsterIcon(String name) {
        assert name != null;
        String path = MONSTERS_DIR + name;
        return getIcon(path);
    }

    /**
     * Returns dice icon
     *
     * @param value     the dice value
     * @param isRolling specify if the dice is in rolling status.
     * @return
     * @Requires NumberUtil.between(value, 0, 5)
     */
    public Icon getDiceIcon(int value, boolean isRolling) {
        String dir = isRolling ? DICE_ROLLING_DIR : DICE_VALUE_DIR;
        String path = dir + value + ".png";
        return getIcon(path).getIcon();
    }

    /**
     * Returns shared {@link IconFlyweight}
     *
     * @param filename the specified icon filename
     * @return the flyweight icon
     * @Ensures <code>icons.containsKey(filename)</code>
     */
    private IconFlyweight getIcon(String filename) {
        IconFlyweight icon = icons.get(filename);
        if (icon == null) {
            icon = new IconImpl(filename);
            icons.put(filename, icon);
            LOG.info("Load new icon from " + filename);
        }
        return icon;
    }

    private static class IconImpl implements IconFlyweight {

        private final String filename;
        private ImageIcon icon;

        private IconImpl(String filename) {
            this.filename = filename;
        }

        @Override
        public ImageIcon getIcon() {
            if (icon == null) {
                // lazy initialization
                File file = FileUtil.getResource(filename);
                icon = new ImageIcon(file.getAbsolutePath());
            }
            return icon;
        }

        @Override
        public ImageIcon getResizedIcon(int width, int height) {
            Image image = getImage();
            image = image.getScaledInstance(width, height, SCALE_SMOOTH);
            return new ImageIcon(image);
        }


        @Override
        public Image getImage() {
            return getIcon().getImage();
        }

        @Override
        public Image getImage(int width, int height) {
            Image image = this.getImage();
            return resize(image, width, height);
        }
    }
}