package grp.oozmakappa.monsterclash.model.monsters.roaromegaroar;

import grp.oozmakappa.monsterclash.model.abstracts.Cell;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.utils.IconUtil;

/**
 * @author Jing Li
 */
public class JohnnyWorthington extends Piece {

    public JohnnyWorthington(String id, Cell startingPos) {
        super(id, startingPos);
        this.health = 200;
        this.attackPower = 20;
        this.attackRange = 5;
        iconName = IconUtil.JOHNNY_WORTHINGTON;
    }

}
