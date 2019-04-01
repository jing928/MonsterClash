package grp.oozmakappa.monsterclash.model.monsters.roaromegaroar;

import grp.oozmakappa.monsterclash.model.abstracts.Cell;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.utils.IconUtil;

/**
 * @author Jing Li
 */
public class ChetAlexander extends Piece {

    public ChetAlexander(String id, Cell startingPos) {
        super(id, startingPos);
        this.health = 100;
        this.attackPower = 15;
        this.attackRange = 8;
        iconName = IconUtil.CHET_ALEXANDER;
    }

}
