package grp.oozmakappa.monsterclash.model.monsters.oozmakappa;

import grp.oozmakappa.monsterclash.model.Ability;
import grp.oozmakappa.monsterclash.model.Cell;
import grp.oozmakappa.monsterclash.model.Team;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.utils.IconFactory;

/**
 * @author Jing Li
 * @Invariant getTeam().equals(" Team.OozmaKappa ")
 */
public class Squishy extends Piece {

    private static final Team TEAM = Team.OozmaKappa;
    private static final double DEFAULTHEALTH = 100;
    private static final int DEFAULTATTACKPOWER = 15;
    private static final int DEFAULTATTACKRANGE = 8;

    public Squishy(Cell startingPos) {
        super(TEAM, startingPos, DEFAULTHEALTH, DEFAULTATTACKPOWER, DEFAULTATTACKRANGE);
        setIcon(IconFactory.SQUISHY);
        addSpecialAbility(Ability.SPECIAL_ATTACK);
    }

}
