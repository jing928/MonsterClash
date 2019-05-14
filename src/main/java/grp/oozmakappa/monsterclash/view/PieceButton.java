package grp.oozmakappa.monsterclash.view;

import grp.oozmakappa.monsterclash.model.Ability;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.utils.flyweights.IconFlyweight;
import grp.oozmakappa.monsterclash.view.observers.PieceActionObserver;
import grp.oozmakappa.monsterclash.view.observers.PiecePropertyObserver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;

import static grp.oozmakappa.monsterclash.model.Constraints.PIECE_DIAMETER;

/**
 * @author Chenglong Ma
 * @Invariant piece != null
 * @Invariant icon != null
 */
public class PieceButton extends JButton implements PieceActionObserver, PiecePropertyObserver {

    private static final Logger LOG = LogManager.getLogger();
    private static final Color DEF_COLOR = new Color(255, 255, 255, 128);
    private final Piece piece;
    private final IconFlyweight icon;
    private Color backgroundColor = DEF_COLOR;
    private boolean canPlaced = false;

    public PieceButton(Piece piece) {
        this.piece = piece;
        piece.addPropertyObserver(this);
        piece.addActionObserver(this);
        setOpaque(false);
        setContentAreaFilled(false);
        setPreferredSize(new Dimension(PIECE_DIAMETER, PIECE_DIAMETER));
        setBorderPainted(false);
        setAlignmentX(CENTER_ALIGNMENT);
        setAlignmentY(CENTER_ALIGNMENT);
        icon = piece.getIcon();
    }

    public boolean canPlaced() {
        return canPlaced;
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
        Color color = backgroundColor;
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

    private void changeBackground(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        repaint();
    }

    @Override
    public void healthChanged(double deltaHealth) {
        notifyChange(deltaHealth, "health");
        setEnabled(piece.getHealth() > 0);
    }

    @Override
    public void powerChanged(double deltaPower) {
        notifyChange(deltaPower, "attack power");
    }

    @Override
    public void rangeChanged(int deltaRange) {
        notifyChange(deltaRange, "range");
    }

    @Override
    public void beforeActing(Piece pieceToAct) {
        boolean teammate = piece.getTeam() == pieceToAct.getTeam();
        // notify teammates if piece's ability is healing
        boolean notifyTeammate = pieceToAct.getCurrAbility() == Ability.SPECIAL_HEALING == teammate;
        Color notifiedColor = teammate ? Color.GREEN : Color.MAGENTA;
        boolean canReach = piece.distance(pieceToAct) <= pieceToAct.getCurrentReachableRange();
        // the button would be lightened if the distance between the button and
        // the piece equals the attack range of the piece.
        if (notifyTeammate && canReach) {
            canPlaced = true;
            changeBackground(notifiedColor);
            LOG.info("Reachable piece: " + piece);
        }
    }

    @Override
    public void afterActing(Piece pieceActed) {
        canPlaced = false;
        changeBackground(DEF_COLOR);
    }
}
