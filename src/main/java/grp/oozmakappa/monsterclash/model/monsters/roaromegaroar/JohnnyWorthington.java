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
public class JohnnyWorthington extends Piece {

    private static final Team TEAM = Team.RoarOmegaRoar;
    private static final double DEFAULTHEALTH = 200;
    private static final int DEFAULTATTACKPOWER = 40;
    private static final int DEFAULTATTACKRANGE = 5;
    private static final double DEFAULT_ARMOR = 14;

    public JohnnyWorthington(Cell startingPos) {
        super(TEAM, startingPos, DEFAULTHEALTH, DEFAULTATTACKPOWER, DEFAULT_ARMOR, DEFAULTATTACKRANGE);
        setIcon(IconFactory.JOHNNY_WORTHINGTON);
        addSpecialAbility(Ability.SPECIAL_HEALING);
//        addSpecialAbility(Ability.SPECIAL_MOVE);
    }

}
