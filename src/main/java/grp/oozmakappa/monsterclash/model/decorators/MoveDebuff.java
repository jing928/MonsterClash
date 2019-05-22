package grp.oozmakappa.monsterclash.model.decorators;

import grp.oozmakappa.monsterclash.model.Cell;
import grp.oozmakappa.monsterclash.model.Constraints;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.model.command.MoveCommand;
import grp.oozmakappa.monsterclash.model.interfaces.CellEffect;

/**
 * @author Chenglong Ma
 */
public class MoveDebuff extends DebuffDecorator {
    private static final int MAX_X = Constraints.MAX_X;
    private static final int MAX_Y = Constraints.MAX_Y + 1;

    MoveDebuff(CellEffect toDecorated) {
        super(toDecorated);
    }

    private Cell nextCell() {
        int x = (int) (Math.random() * MAX_X);
        int y = (int) (Math.random() * MAX_Y);
        return Cell.getCell(x, y);
    }

    @Override
    public boolean affect(Piece piece) {
        Cell cell = nextCell();
        MoveCommand.move(piece, cell);
        LOG.info("To new position: " + cell);
        return super.affect(piece);
    }
}
