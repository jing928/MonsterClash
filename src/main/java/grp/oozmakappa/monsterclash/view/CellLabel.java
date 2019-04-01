package grp.oozmakappa.monsterclash.view;

import grp.oozmakappa.monsterclash.controller.CellListener;
import grp.oozmakappa.monsterclash.model.abstracts.Cell;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.view.interfaces.PieceObserver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;

import static grp.oozmakappa.monsterclash.utils.Constraints.CELL_X;

/**
 * @author Chenglong Ma
 */
public class CellLabel extends JLabel implements PieceObserver {
    private static final Color COLOR_A = Color.ORANGE;
    private static final Color COLOR_B = Color.BLUE;
    private static final Color COLOR_NEUTRAL = Color.DARK_GRAY;
    private final Logger LOG = LogManager.getLogger();
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
        setPreferredSize(new Dimension(CELL_X, CELL_X));

        // set listener
        addMouseListener(new CellListener(this));
        this.cell.setLocation(getLocation());
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

    @Override
    public void positionChanging(Piece pieceToMove) {
        // TODO: is this appropriate?
        if (cell.getRole() == Cell.Role.DISABLE) {
            return;
        }
        if (cell.distance(pieceToMove.getPosition()) == pieceToMove.getNextMove()) {
//            LOG.info("Available positions: " + cell.getX() + ", " + cell.getY());
            setBackground(Color.RED);
            canPlaced = true;
            LOG.info("Cell location: " + getLocation());
        }
    }

    @Override
    public void positionChanged(Piece pieceMoved, Point newPosition) {
        setBackground(currentColor);
        double offset = Math.sqrt(getWidth() * getWidth() + getHeight() * getHeight());
        if (canPlaced) {
            LOG.info("Distance: " + getLocation().distance(newPosition));
            LOG.info("Offset: " + offset);
            LOG.info("new Position: " + newPosition);
        }
        canPlaced &= getLocation().distance(newPosition) < offset;
        if (canPlaced) {
            cell.setLocation(getLocation());
            pieceMoved.setPosition(cell);
        }
        canPlaced = false;
    }

    @Override
    public void positionVerified(Piece pieceVerified, Point nextPosition) {
        // do nothing
    }

    public Cell getCell() {
        return cell;
    }

    public void setColor(Color color) {
        if (cell.getRole() != Cell.Role.DISABLE) {
            this.currentColor = color;
            setBackground(color);
        }
    }
}
