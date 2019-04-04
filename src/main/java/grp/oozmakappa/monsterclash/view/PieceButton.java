package grp.oozmakappa.monsterclash.view;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;

import javax.swing.*;
import java.awt.*;

import static grp.oozmakappa.monsterclash.utils.Constraints.PIECE_DIAMETER;

/**
 * @author Chenglong Ma
 */
public class PieceButton extends JButton {

    private Piece piece;
    private ImageIcon icon;

    public PieceButton(Piece piece) {
        this.piece = piece;
        setOpaque(false);
        icon = piece.getIcon();
        setContentAreaFilled(false);
        setPreferredSize(new Dimension(PIECE_DIAMETER, PIECE_DIAMETER));
        setBorderPainted(false);
        setAlignmentX(CENTER_ALIGNMENT);
        setAlignmentY(CENTER_ALIGNMENT);
    }

    public Piece getPiece() {
        return piece;
    }

    @Deprecated
    public int getOrder() {
        return piece.getPosition().getOrder();
    }

    protected void paintComponent(Graphics g) {
        Color color = new Color(255, 255, 255, 128);
        if (getModel().isArmed()) {
            color = Color.WHITE;
        }
        g.setColor(color);
        g.fillOval(0, 0, getWidth(), getHeight());
        g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
        super.paintComponent(g);
    }
}
