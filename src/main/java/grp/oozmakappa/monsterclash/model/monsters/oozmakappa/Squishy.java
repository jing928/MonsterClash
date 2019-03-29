package grp.oozmakappa.monsterclash.model.monsters.oozmakappa;

import grp.oozmakappa.monsterclash.model.abstracts.Cell;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;

/**
 * @author Jing Li
 */
public class Squishy extends Piece {

    private static double DEFAULTHEALTH = 100;
    private static int DEFAULTATTACKPOWER = 15;
    private static int DEFAULTATTACKRANGE = 8;

    public Squishy(String id, Cell startingPos) {
        super(id, startingPos,DEFAULTHEALTH, DEFAULTATTACKPOWER, DEFAULTATTACKRANGE);
    }

}
