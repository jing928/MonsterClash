package grp.oozmakappa.monsterclash.model.monsters.roaromegaroar;

import grp.oozmakappa.monsterclash.model.abstracts.Cell;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;

/**
 * @author Jing Li
 */
public class ChetAlexander extends Piece {

    private static final double DEFAULTHEALTH = 100;
    private static final int DEFAULTATTACKPOWER = 15;
    private static final int DEFAULTATTACKRANGE = 8;

    public ChetAlexander(String id, Cell startingPos) {
        super(id, startingPos, DEFAULTHEALTH, DEFAULTATTACKPOWER, DEFAULTATTACKRANGE);
        iconName = IconUtil.CHET_ALEXANDER;
    }

}
