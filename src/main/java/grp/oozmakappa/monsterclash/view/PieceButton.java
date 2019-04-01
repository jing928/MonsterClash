package grp.oozmakappa.monsterclash.view;

import grp.oozmakappa.monsterclash.controller.PieceListener;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.view.interfaces.PieceObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

import static grp.oozmakappa.monsterclash.utils.Constraints.PIECE_DIAMETER;

/**
 * @author Chenglong Ma
 */
public class PieceButton extends JButton implements PieceObserver {
    private Piece piece;
    private ImageIcon icon;
    private Point currentLocation;

    public PieceButton(Piece piece) {
        this.piece = piece;
        this.piece.addObserver(this);
        MouseAdapter listener = new PieceListener(this, piece);
        addMouseListener(listener);
        addMouseMotionListener(listener);
        setOpaque(false);
        icon = piece.getIcon();
        setContentAreaFilled(false);
        setPreferredSize(new Dimension(PIECE_DIAMETER, PIECE_DIAMETER));
        setBorderPainted(false);
        setAlignmentX(CENTER_ALIGNMENT);
        setAlignmentY(CENTER_ALIGNMENT);
    }

    public int getCellX() {
        return piece.getX();
    }

    public int getCellY() {
        return piece.getY();
    }

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

    @Override
    public void positionChanging(Piece pieceToMove) {
        // do nothing
    }

    @Override
    public void positionChanged(Piece pieceMoved, Point newPosition) {
        // do nothing
    }

    @Override
    public void positionVerified(Piece pieceVerified, Point nextPosition) {
        if (pieceVerified.getTargetDistance(piece) == 0) {
            this.piece = pieceVerified;
            setLocation(nextPosition);
        }
    }
}
