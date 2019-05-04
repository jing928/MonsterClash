package grp.oozmakappa.monsterclash.utils.flyweights;

import javax.swing.*;
import java.awt.*;

/**
 * @author Chenglong Ma
 */
public interface IconFlyweight {
    ImageIcon getIcon();

    ImageIcon getResizedIcon(int width, int height);

    Image getImage();

    Image getImage(int width, int height);
}
