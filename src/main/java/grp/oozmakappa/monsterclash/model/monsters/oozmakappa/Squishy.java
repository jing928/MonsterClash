package grp.oozmakappa.monsterclash.model.monsters.oozmakappa;

import grp.oozmakappa.monsterclash.model.abstracts.Cell;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;

/**
 * @author Jing Li
 */
public class Squishy extends Piece {

    public Squishy(String id, Cell startingPos) {
        super(id, startingPos,100, 15, 8);
    }

}
