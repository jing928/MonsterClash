package grp.oozmakappa.monsterclash.model.monsters.oozmakappa;

import grp.oozmakappa.monsterclash.model.abstracts.Cell;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;

/**
 * @author Jing Li
 */
public class JamesPSullivan extends Piece {

    private static double DEFAULTHEALTH = 200;
    private static int DEFAULTATTACKPOWER = 20;
    private static int DEFAULTATTACKRANGE = 5;

    public JamesPSullivan(String id, Cell startingPos) {
        super(id, startingPos, DEFAULTHEALTH, DEFAULTATTACKPOWER, DEFAULTATTACKRANGE);
    }

}
