package grp.oozmakappa.monsterclash.model.monsters.roaromegaroar;

import grp.oozmakappa.monsterclash.model.abstracts.Cell;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;

/**
 * @author Jing Li
 */
public class JohnnyWorthington extends Piece {

    public JohnnyWorthington(String id, Cell startingPos) {
        super(id, startingPos);
        this.health = 200;
        this.attackPower = 20;
        this.attackRange = 5;
    }

}
