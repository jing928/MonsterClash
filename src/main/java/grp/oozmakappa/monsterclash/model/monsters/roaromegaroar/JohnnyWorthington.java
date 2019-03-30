package grp.oozmakappa.monsterclash.model.monsters.roaromegaroar;

import grp.oozmakappa.monsterclash.model.abstracts.Cell;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;

/**
 * @author Jing Li
 */
public class JohnnyWorthington extends Piece {

    private static double DEFAULTHEALTH = 200;
    private static int DEFAULTATTACKPOWER = 20;
    private static int DEFAULTATTACKRANGE = 5;

    public JohnnyWorthington(String id, Cell startingPos) {
        super(id, startingPos, DEFAULTHEALTH, DEFAULTATTACKPOWER, DEFAULTATTACKRANGE);
    }

}
