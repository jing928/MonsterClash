package grp.oozmakappa.monsterclash.model.monsters.roaromegaroar;

import grp.oozmakappa.monsterclash.model.abstracts.Cell;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;

/**
 * @author Jing Li
 */
public class RandallBoggs extends Piece {

    public RandallBoggs(String id, Cell startingPos) {
        super(id, startingPos);
        this.health = 100;
        this.attackPower = 10;
        this.attackRange = 3;
    }

}
