package grp.oozmakappa.monsterclash.model.command;

import grp.oozmakappa.monsterclash.controller.PieceListener;
import grp.oozmakappa.monsterclash.controller.states.PieceButtonState;

public class StateChangeCommand implements Command {
    private final PieceListener pieceListener;
    private final PieceButtonState newState;
    private final PieceButtonState prevState;

    public StateChangeCommand(PieceListener pieceListener, PieceButtonState newState) {
        this.pieceListener = pieceListener;
        this.newState = newState;
        this.prevState = pieceListener.getState();
    }

    @Override
    public void execute() {
        pieceListener.setState(newState);
    }

    @Override
    public void undo() {
        pieceListener.setState(prevState);
    }

}
