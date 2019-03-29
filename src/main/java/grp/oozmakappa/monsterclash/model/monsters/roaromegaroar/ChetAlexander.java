package grp.oozmakappa.monsterclash.model.monsters.roaromegaroar;

import grp.oozmakappa.monsterclash.model.abstracts.Cell;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;

/**
 * @author Jing Li
 */
public class ChetAlexander extends Piece {

    private static double DEFAULTHEALTH = 100;
    private static int DEFAULTATTACKPOWER = 15;
    private static int DEFAULTATTACKRANGE = 8;

    public ChetAlexander(String id, Cell position, double health, double attackPower, int attackRange) {
        super(id, position, DEFAULTHEALTH, DEFAULTATTACKPOWER, DEFAULTATTACKRANGE);
    }

}
