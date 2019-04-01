package grp.oozmakappa.monsterclash.model.monsters.oozmakappa;

import grp.oozmakappa.monsterclash.model.abstracts.Cell;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.utils.IconUtil;

/**
 * @author Jing Li
 */
public class JamesPSullivan extends Piece {

    public JamesPSullivan(String id, Cell startingPos) {
        super(id, startingPos);
        this.health = 200;
        this.attackPower = 20;
        this.attackRange = 5;
        iconName = IconUtil.JAMES_P_SULLIVAN;
    }

    // TODO magic number
    public JamesPSullivan(String id, Cell position, Object other) {
        super(id, 200, position, 20, 5);
    }
}
