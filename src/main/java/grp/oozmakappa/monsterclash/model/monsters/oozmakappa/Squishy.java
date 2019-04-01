package grp.oozmakappa.monsterclash.model.monsters.oozmakappa;

import grp.oozmakappa.monsterclash.model.abstracts.Cell;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.utils.IconUtil;

/**
 * @author Jing Li
 */
public class Squishy extends Piece {

    private static final double DEFAULTHEALTH = 100;
    private static final int DEFAULTATTACKPOWER = 15;
    private static final int DEFAULTATTACKRANGE = 8;

    public Squishy(String id, Cell startingPos) {
        super(id, startingPos,DEFAULTHEALTH, DEFAULTATTACKPOWER, DEFAULTATTACKRANGE);
        iconName = IconUtil.SQUISHY;
    }

}
