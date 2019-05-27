package grp.oozmakappa.monsterclash.model.monsters.roaromegaroar;

import grp.oozmakappa.monsterclash.model.Ability;
import grp.oozmakappa.monsterclash.model.Cell;
import grp.oozmakappa.monsterclash.model.Team;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.utils.IconFactory;

/**
 * @author Jing Li
 * @Invariant getTeam().equals(" Team.RoarOmegaRoar ")
 */
public class ChetAlexander extends Piece {

    private static final Team TEAM = Team.RoarOmegaRoar;
    private static final double DEFAULTHEALTH = 100;
    private static final int DEFAULTATTACKPOWER = 30;
    private static final int DEFAULTATTACKRANGE = 8;
    private static final double DEFAULT_ARMOR = 10;

    public ChetAlexander(Cell startingPos) {
        super(TEAM, startingPos, DEFAULTHEALTH, DEFAULTATTACKPOWER, DEFAULT_ARMOR, DEFAULTATTACKRANGE);
        setIcon(IconFactory.CHET_ALEXANDER);
        addSpecialAbility(Ability.SPECIAL_ATTACK);
//        addSpecialAbility(Ability.SPECIAL_MOVE);
    }

}
