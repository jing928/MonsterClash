package grp.oozmakappa.monsterclash.model.command;

import grp.oozmakappa.monsterclash.controller.PieceListener;
import grp.oozmakappa.monsterclash.controller.states.PieceButtonState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StateChangeCommand implements Command {
    private final PieceListener pieceListener;
    private final PieceButtonState newState;
    private final PieceButtonState prevState;

    private StateChangeCommand(PieceListener pieceListener, PieceButtonState newState) {
        this.pieceListener = pieceListener;
        this.newState = newState;
        this.prevState = pieceListener.getState();
    }

    public static void setState(PieceListener ctrl, PieceButtonState nextState) {
        CommandManager manager = CommandManager.getInstance();
        manager.storeAndExecute(new StateChangeCommand(ctrl, nextState));
    }

    @Override
    public void execute() {
        pieceListener.setState(newState);
        Logger log = LogManager.getLogger();
        log.info("Executed: State Change Command");
    }

    @Override
    public void undo() {
        pieceListener.undoState(prevState);
        Logger log = LogManager.getLogger();
        log.info("Undid: State Change Command");
    }

}
