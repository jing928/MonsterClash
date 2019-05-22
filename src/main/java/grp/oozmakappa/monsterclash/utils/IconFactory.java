package grp.oozmakappa.monsterclash.utils;

import grp.oozmakappa.monsterclash.model.Constraints;
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
    /**
     * Acknowledgement:
     * Icons made by {
     * Lorc, http://lorcblog.blogspot.com,
     * Delapouite, http://delapouite.com,
     * }
     */
    public static final String ABILITIES_DIR = "img/abilities/";
    public static final String MODE_DIR = "img/mode/";
    //region For trap cell
    public static final String TRAP_CELL = "img/cell/trap.png";

    //endregion
    private static final Logger LOG = LogManager.getLogger();
    //region For Dice
    private static final String MONSTERS_DIR = "img/monsters/";

    //endregion
    private static final String DICE_VALUE_DIR = "img/dice/value/";
    //endregion
    //region For piece abilities
    private static final String DICE_ROLLING_DIR = "img/dice/rolling/";
    //endregion
    private static IconFactory instance;
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
     * Converts a given Image into a BufferedImage
     *
     * @param img The Image to be converted
     * @return The converted BufferedImage
     */
    public static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }

    public static Image toGrey(Image image) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        BufferedImage bimg = toBufferedImage(image);
        BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int rgb = bimg.getRGB(i, j);
                grayImage.setRGB(i, j, rgb);
            }
        }
        return grayImage;
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
     * Returns the {@link Icon} of mode
     *
     * @param mode the mode name
     * @return
     * @see Constraints#OFFENSIVE_MODE
     * @see Constraints#DEFENSIVE_MODE
     */
    public Icon getModeIcon(String mode) {
        String path = MODE_DIR + mode + ".png";
        return getIcon(path).getIcon();
    }

    /**
     * Returns shared {@link IconFlyweight}
     *
     * @param filename the specified icon filename
     * @return the flyweight icon
     * @Ensures <code>icons.containsKey(filename)</code>
     */
    public IconFlyweight getIcon(String filename) {
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
            ImageIcon icon = getIcon();
            if (icon.getIconWidth() == width && icon.getIconHeight() == height) {
                return icon;
            }
            Image image = icon.getImage();
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
