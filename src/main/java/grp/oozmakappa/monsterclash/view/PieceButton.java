package grp.oozmakappa.monsterclash.view;

import grp.oozmakappa.monsterclash.model.Ability;
import grp.oozmakappa.monsterclash.model.Cell;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.utils.flyweights.IconFlyweight;
import grp.oozmakappa.monsterclash.view.observers.PieceActionObserver;
import grp.oozmakappa.monsterclash.view.observers.PiecePositionObserver;
import grp.oozmakappa.monsterclash.view.observers.PiecePropertyObserver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;

import static grp.oozmakappa.monsterclash.model.Constraints.PIECE_DIAMETER;
import static grp.oozmakappa.monsterclash.utils.IconFactory.toGrey;

/**
 * @author Chenglong Ma
 * @Invariant piece != null
 * @Invariant icon != null
 */
public class PieceButton extends JButton implements PieceActionObserver, PiecePropertyObserver, PiecePositionObserver {

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
        piece.addPositionObserver(this);
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

    @Override
    public void setLocation(Point p) {
        setBounds(p.x, p.y, getWidth(), getHeight());
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
        if (!isEnabled()) {
            image = toGrey(image);
        }
        g.drawImage(image, 0, 0, weight, height, this);
        super.paintComponent(g);
    }

    private void changeBackground(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        repaint();
    }

    @Override
    public void healthChanged(double currValue, double deltaHealth) {
        setEnabled(piece.getHealth() > 0);
    }

    @Override
    public void powerChanged(double currValue, double deltaPower) {
        // do nothing
    }

    @Override
    public void armorChanged(double currValue, double deltaArmor) {
        // do nothing
    }

    @Override
    public void rangeChanged(int currValue, int deltaRange) {
        // do nothing
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
        }
    }

    @Override
    public void afterActing(Piece pieceActed) {
        canPlaced = false;
        changeBackground(DEF_COLOR);
    }

    @Override
    public void beforeMove(Piece pieceToMove) {
        // do nothing
    }

    @Override
    public void afterMove(Piece pieceLocated, boolean shouldNotify) {
        Cell cell = pieceLocated.getPosition();
        Point loc = cell.getLocation();
        setLocation(loc);
        if (pieceLocated.isWin()) {
            String msg = pieceLocated.getTeam() + " Win!!";
            JOptionPane.showMessageDialog(null, msg, "Congrats!", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
