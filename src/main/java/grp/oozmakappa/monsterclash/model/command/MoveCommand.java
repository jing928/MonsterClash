package grp.oozmakappa.monsterclash.model.command;

import grp.oozmakappa.monsterclash.model.Cell;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.view.PieceButton;

import java.awt.*;

/**
 * @author Chenglong Ma
 */
public class MoveCommand implements Command {
    private final PieceButton button;
    private final Piece piece;
    private final Cell prevPosition, nextPosition;
    private final Point prevLocation, nextLocation;

    public MoveCommand(PieceButton button, Point prevLocation, Cell nextCell, Point nextLocation) {
        this.button = button;
        piece = button.getPiece();
        this.prevPosition = piece.getPosition();
        this.nextPosition = nextCell;
        this.prevLocation = prevLocation;
        this.nextLocation = nextLocation;
    }

    @Override
    public void execute() {
        piece.move(nextPosition);
        button.setLocation(nextLocation);
    }

    @Override
    public void undo() {
        piece.setShouldNotify(false);
        piece.move(prevPosition);
        piece.setShouldNotify(true);
        button.setLocation(prevLocation);
    }
}
