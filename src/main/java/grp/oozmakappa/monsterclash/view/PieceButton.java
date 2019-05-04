package grp.oozmakappa.monsterclash.view;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.utils.flyweights.IconFlyweight;

import javax.swing.*;
import java.awt.*;

import static grp.oozmakappa.monsterclash.utils.Constraints.PIECE_DIAMETER;

/**
 * @author Chenglong Ma
 * @Invariant piece != null
 * @Invariant icon != null
 */
public class PieceButton extends JButton {

    private final Piece piece;
    private final IconFlyweight icon;

    public PieceButton(Piece piece) {
        this.piece = piece;
        setOpaque(false);
        setContentAreaFilled(false);
        setPreferredSize(new Dimension(PIECE_DIAMETER, PIECE_DIAMETER));
        setBorderPainted(false);
        setAlignmentX(CENTER_ALIGNMENT);
        setAlignmentY(CENTER_ALIGNMENT);
        icon = piece.getIcon();
    }

    public Piece getPiece() {
        return piece;
    }

    @Deprecated
    public int getOrder() {
        return piece.getPosition().getOrder();
    }

    /**
     * Re-paints the icon of the button
     *
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        // the default background color
        Color color = new Color(255, 255, 255, 128);
        // change the color when the button is clicked.
        if (getModel().isArmed()) {
            color = Color.WHITE;
        }
        g.setColor(color);
        int weight = getWidth(), height = getHeight();
        // draw circle.
        g.fillOval(0, 0, weight, height);
        // zoom the icon
        Image image = icon.getImage();
        g.drawImage(image, 0, 0, weight, height, this);
        super.paintComponent(g);
    }

}
