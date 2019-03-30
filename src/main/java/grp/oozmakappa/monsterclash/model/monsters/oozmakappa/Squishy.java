package grp.oozmakappa.monsterclash.model.monsters.oozmakappa;

import grp.oozmakappa.monsterclash.model.abstracts.Cell;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;

/**
 * @author Jing Li
 */
public class Squishy extends Piece {

    public Squishy(String id, Cell startingPos) {
        super(id, startingPos);
        this.health = 100;
        this.attackPower = 15;
        this.attackRange = 8;
    }

}
