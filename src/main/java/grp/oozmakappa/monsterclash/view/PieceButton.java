package grp.oozmakappa.monsterclash.view;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.utils.flyweights.IconFlyweight;
import grp.oozmakappa.monsterclash.view.observers.PieceActionObserver;
import grp.oozmakappa.monsterclash.view.observers.PiecePropertyObserver;

import javax.swing.*;
import java.awt.*;

import static grp.oozmakappa.monsterclash.utils.Constraints.PIECE_DIAMETER;

/**
 * @author Chenglong Ma
 * @Invariant piece != null
 * @Invariant icon != null
 */
public class PieceButton extends JButton implements PieceActionObserver, PiecePropertyObserver {

    private final Piece piece;
    private final IconFlyweight icon;

    public PieceButton(Piece piece) {
        this.piece = piece;
        piece.addPropertyObserver(this);
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

    /**
     * Shows message box
     *
     * @param deltaValue
     * @param propertyName
     * @Requires deltaValue != 0
     */
    private void notifyChange(double deltaValue, String propertyName) {
        assert deltaValue != 0D;
        String msg = String.format("You %s %.2f %s!",
                deltaValue > 0 ? "gained" : "lost",
                Math.abs(deltaValue),
                propertyName);
        GameFrame.showMessage(this, msg, deltaValue > 0);
    }

    @Override
    public void healthChanged(double deltaHealth) {
        notifyChange(deltaHealth, "health");
    }

    @Override
    public void powerChanged(double deltaPower) {
        notifyChange(deltaPower, "attack power");
    }

    @Override
    public void rangeChanged(int deltaRange) {
        notifyChange(deltaRange, "attack range");
    }

    @Override
    public void beforeActing(Piece pieceToAct) {
        // the cell would be lightened if the distance between the cell and
        // the piece equals the next move of the piece.
        if (piece.distance(pieceToAct) == pieceToAct.getAttackRange()) {
            setBackground(Color.YELLOW);
        }
    }
}
