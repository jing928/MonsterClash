package grp.oozmakappa.monsterclash.model.monsters.oozmakappa;

import grp.oozmakappa.monsterclash.model.abstracts.Cell;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.utils.IconUtil;

/**
 * @author Jing Li
 */
public class JamesPSullivan extends Piece {

    private static final double DEFAULTHEALTH = 200;
    private static final int DEFAULTATTACKPOWER = 20;
    private static final int DEFAULTATTACKRANGE = 5;

    public JamesPSullivan(String id, Cell startingPos) {
        super(id, startingPos, DEFAULTHEALTH, DEFAULTATTACKPOWER, DEFAULTATTACKRANGE);
        iconName = IconUtil.JAMES_P_SULLIVAN;
    }

}
