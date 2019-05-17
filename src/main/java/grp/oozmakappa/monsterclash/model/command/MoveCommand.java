package grp.oozmakappa.monsterclash.model.command;

import grp.oozmakappa.monsterclash.model.Cell;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Chenglong Ma
 */
public class MoveCommand implements Command {
    private final Piece piece;
    private final Cell prevPosition, nextPosition;

    private MoveCommand(Piece piece, Cell nextPosition) {
        this.piece = piece;
        this.prevPosition = piece.getPosition();
        this.nextPosition = nextPosition;
    }

    public static void move(Piece piece, Cell newCell) {
        CommandManager manager = CommandManager.getInstance();
        manager.storeAndExecute(new MoveCommand(piece, newCell));
    }

    @Override
    public void execute() {
        piece.setPosition(nextPosition);
        Logger log = LogManager.getLogger();
        log.info("Executed: Move Command");
    }

    @Override
    public void undo() {
        piece.setPosition(prevPosition, false);
//        button.move(prevPosition, prevLocation, false);
        Logger log = LogManager.getLogger();
        log.info("Undid: Move Command");
    }
}
