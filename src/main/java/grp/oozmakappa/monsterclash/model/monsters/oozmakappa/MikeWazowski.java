package grp.oozmakappa.monsterclash.model.monsters.oozmakappa;

import grp.oozmakappa.monsterclash.model.abstracts.Cell;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;

/**
 * @author Jing Li
 */
public class MikeWazowski extends Piece {

    private static double DEFAULTHEALTH = 100;
    private static int DEFAULTATTACKPOWER = 10;
    private static int DEFAULTATTACKRANGE = 3;

    public MikeWazowski(String id, Cell startingPos) {
        super(id, startingPos, DEFAULTHEALTH, DEFAULTATTACKPOWER, DEFAULTATTACKRANGE);
    }

}