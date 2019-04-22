package grp.oozmakappa.monsterclash.view;

import grp.oozmakappa.monsterclash.controller.CellListener;
import grp.oozmakappa.monsterclash.model.abstracts.Cell;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.view.interfaces.PieceObserver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;

import static grp.oozmakappa.monsterclash.utils.Constraints.CELL_LENGTH;

/**
 * @author Chenglong Ma
 * @invariant cell != null
 */
public class CellLabel extends JLabel implements PieceObserver {
    private static final Color COLOR_A = Color.ORANGE;
    private static final Color COLOR_B = Color.BLUE;
    private static final Color COLOR_NEUTRAL = Color.DARK_GRAY;
    private static final Logger LOG = LogManager.getLogger();
    private final Cell cell;
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
        addMouseListener(new CellListener(this));
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
     * @pre cell.getRole() != Cell.Role.DISABLE
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

            case TEAM_A:
                currentColor = COLOR_A;
                break;
            case TEAM_B:
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

    /**
     * @param pieceToMove
     */
    @Override
    public void positionChanging(Piece pieceToMove) {
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
    public void positionChanged() {
        setBackground(currentColor);
        canPlaced = false;
    }
}
