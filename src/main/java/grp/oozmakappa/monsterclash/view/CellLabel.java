package grp.oozmakappa.monsterclash.view;

import grp.oozmakappa.monsterclash.controller.CellListener;
import grp.oozmakappa.monsterclash.model.Cell;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.utils.IconFactory;
import grp.oozmakappa.monsterclash.utils.flyweights.IconFlyweight;
import grp.oozmakappa.monsterclash.view.observers.PiecePositionObserver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;

import static grp.oozmakappa.monsterclash.model.Constraints.CELL_LENGTH;
import static grp.oozmakappa.monsterclash.utils.IconFactory.TRAP_CELL;

/**
 * @author Chenglong Ma
 * @Invariant cell != null
 */
public class CellLabel extends JLabel implements PiecePositionObserver {
    private static final Color COLOR_A = Color.BLUE;
    private static final Color COLOR_B = Color.ORANGE;
    private static final Color COLOR_NEUTRAL = Color.DARK_GRAY;
    private static final Logger LOG = LogManager.getLogger();
    private final Cell cell;
    private final CellListener listener;
    private boolean canPlaced = false;
    private Color currentColor;

    public CellLabel(Cell cell) {
        this.cell = cell;
        setAlignmentX(CENTER_ALIGNMENT);
        setAlignmentY(CENTER_ALIGNMENT);
        if (cell.getRole() == Cell.Role.DISABLE) {
            setEnabled(false);
        }

        // set color
        setDefaultColor();
        setPreferredSize(new Dimension(CELL_LENGTH, CELL_LENGTH));

        // set listener
        listener = new CellListener(this);
        addMouseListener(listener);
    }

    /**
     * Returns true if this cell is allowed to be placed in.
     *
     * @return
     */
    public boolean canPlaced() {
        return canPlaced;
    }

    public Cell getCell() {
        return cell;
    }

    /**
     * Sets the color of this label.
     *
     * @param color
     * @Requires cell.getRole() != Cell.Role.DISABLE
     */
    public void setColor(Color color) {
        if (cell.getRole() != Cell.Role.DISABLE) {
            this.currentColor = color;
            setBackground(color);
        }
    }

    private void setDefaultColor() {
        setOpaque(true);
        switch (cell.getRole()) {

            case OozmaKappa:
                currentColor = COLOR_A;
                break;
            case RoarOmegaRoar:
                currentColor = COLOR_B;
                break;
            case DISABLE:
                currentColor = getBackground();
                break;
            default:
            case NEUTRAL:
                currentColor = COLOR_NEUTRAL;
                break;
        }
        setBackground(currentColor);
    }

    @Override
    public void beforeMove(Piece pieceToMove) {
        if (cell.getRole() == Cell.Role.DISABLE) {
            return;
        }
        // the cell would be lightened if the distance between the cell and
        // the piece equals the next move of the piece.
        if (cell.distance(pieceToMove.getPosition()) == pieceToMove.getNextMove()) {
            setBackground(Color.RED);
            canPlaced = true;
        }
    }

    @Override
    public void afterMove(Piece pieceLocated) {
        setBackground(currentColor);
        canPlaced = false;
        if (cell.equals(pieceLocated.getPosition())) {
            if (listener.affect(cell, pieceLocated)) {
                IconFlyweight icon = IconFactory.getInstance().getIcon(TRAP_CELL);
                setIcon(icon.getResizedIcon(getWidth(), getHeight()));
                vibrate();
            } else {
                setIcon(null);
            }
        }
    }

    /**
     * vibrates the cell
     */
    private void vibrate() {
        new Thread(() -> {
            Point pos = getLocation();
            for (int i = 0; i < 5; i++) {
                int offsetX = 20 - (int) (40 * Math.random());
                int offsetY = 20 - (int) (40 * Math.random());
                setLocation(pos.x + offsetX, pos.y + offsetY);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    //ignore
                }
            }
            setLocation(pos);
        }).start();
    }

}
