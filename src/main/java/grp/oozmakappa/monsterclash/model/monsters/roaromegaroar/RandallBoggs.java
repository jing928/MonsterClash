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
public class RandallBoggs extends Piece {

    private static final Team TEAM = Team.RoarOmegaRoar;
    private static final double DEFAULTHEALTH = 100;
    private static final int DEFAULTATTACKPOWER = 10;
    private static final int DEFAULTATTACKRANGE = 3;

    public RandallBoggs(Cell startingPos) {
        super(TEAM, startingPos, DEFAULTHEALTH, DEFAULTATTACKPOWER, DEFAULTATTACKRANGE);
        setIcon(IconFactory.RANDALL_BOGGS);
        addSpecialAbility(Ability.SPECIAL_MOVE);
    }

}
