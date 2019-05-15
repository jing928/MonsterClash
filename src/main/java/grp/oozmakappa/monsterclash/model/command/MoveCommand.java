package grp.oozmakappa.monsterclash.model.command;

import grp.oozmakappa.monsterclash.model.Cell;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.view.PieceButton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;

/**
 * @author Chenglong Ma
 */
public class MoveCommand implements Command {
    private final PieceButton button;
    private final Piece piece;
    private final Cell prevPosition, nextPosition;
    private final Point prevLocation, nextLocation;

    public MoveCommand(PieceButton button, Cell nextPosition, Point nextLocation, Point prevLocation) {
        this.button = button;
        piece = button.getPiece();
        this.prevPosition = piece.getPosition();
        this.nextPosition = nextPosition;
        this.prevLocation = prevLocation;
        this.nextLocation = nextLocation;
    }

    @Override
    public void execute() {
        button.move(nextPosition, nextLocation);
        Logger log = LogManager.getLogger();
        log.info("Executed: Move Command");
    }

    @Override
    public void undo() {
        button.move(prevPosition, prevLocation, false);
        Logger log = LogManager.getLogger();
        log.info("Undid: Move Command");
    }
}
