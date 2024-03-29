package grp.oozmakappa.monsterclash.model.command;

import grp.oozmakappa.monsterclash.model.Cell;
import grp.oozmakappa.monsterclash.model.Constraints;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;

/**
 * @author Chenglong Ma
 */
public class MoveCommand extends AbstractCommand {
    private final Piece piece;
    private final Cell prevPosition, nextPosition;

    private MoveCommand(Piece piece, Cell nextPosition) {
        super("Move - " + piece.getName());
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
        LOG.info("Executed: Move Command");
    }

    @Override
    public void undo() {
        Constraints constraints = Constraints.getInstance();
        boolean enabled = constraints.isEnableObstacle();
        constraints.enableObstacle(false);
        piece.setPosition(prevPosition);
        constraints.enableObstacle(enabled);
        LOG.info("Undid: Move Command");
    }
}
