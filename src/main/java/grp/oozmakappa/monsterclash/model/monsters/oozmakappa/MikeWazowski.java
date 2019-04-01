package grp.oozmakappa.monsterclash.model.monsters.oozmakappa;

import grp.oozmakappa.monsterclash.model.abstracts.Cell;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.utils.IconUtil;

/**
 * @author Jing Li
 */
public class MikeWazowski extends Piece {

    public MikeWazowski(String id, Cell startingPos) {
        super(id, startingPos);
        this.health = 100;
        this.attackPower = 10;
        this.attackRange = 3;
        iconName = IconUtil.MIKE_WEZOWSKI;
    }

}