package grp.oozmakappa.monsterclash.model.monsters.oozmakappa;

import grp.oozmakappa.monsterclash.model.abstracts.Cell;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;

/**
 * @author Jing Li
 */
public class JamesPSullivan extends Piece {

    public JamesPSullivan(String id, Cell startingPos) {
        super(id, startingPos);
        this.health = 200;
        this.attackPower = 20;
        this.attackRange = 5;
    }

}
