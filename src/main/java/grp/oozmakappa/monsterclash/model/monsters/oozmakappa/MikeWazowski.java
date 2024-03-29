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
public class MikeWazowski extends Piece {

    private static final Team TEAM = Team.OozmaKappa;
    private static final double DEFAULTHEALTH = 100;
    private static final int DEFAULTATTACKPOWER = 40;
    private static final int DEFAULTATTACKRANGE = 3;
    private static final double DEFAULT_ARMOR = 15;

    public MikeWazowski(Cell startingPos) {
        super(TEAM, startingPos, DEFAULTHEALTH, DEFAULTATTACKPOWER, DEFAULT_ARMOR, DEFAULTATTACKRANGE);
        setIcon(IconFactory.MIKE_WAZOWSKI);
        addSpecialAbility(Ability.SPECIAL_HEALING);
//        addSpecialAbility(Ability.SPECIAL_MOVE);
    }

}