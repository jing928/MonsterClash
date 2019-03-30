package grp.oozmakappa.monsterclash.model.monsters.roaromegaroar;

import grp.oozmakappa.monsterclash.model.abstracts.Cell;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;

/**
 * @author Jing Li
 */
public class RandallBoggs extends Piece {

    private static double DEFAULTHEALTH = 100;
    private static int DEFAULTATTACKPOWER = 10;
    private static int DEFAULTATTACKRANGE = 3;

    public RandallBoggs(String id, Cell startingPos) {
        super(id, startingPos, DEFAULTHEALTH, DEFAULTATTACKPOWER, DEFAULTATTACKRANGE);
    }

}
