package grp.oozmakappa.monsterclash.model.monsters.oozmakappa;

import grp.oozmakappa.monsterclash.model.Team;
import grp.oozmakappa.monsterclash.model.abstracts.Cell;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.utils.IconUtil;

/**
 * @author Jing Li
 * @invariant getTeam().equals(" Team.OozmaKappa ")
 */
public class MikeWazowski extends Piece {

    private static final Team TEAM = Team.OozmaKappa;
    private static final double DEFAULTHEALTH = 100;
    private static final int DEFAULTATTACKPOWER = 10;
    private static final int DEFAULTATTACKRANGE = 3;

    public MikeWazowski(Cell startingPos) {
        super(TEAM, startingPos, DEFAULTHEALTH, DEFAULTATTACKPOWER, DEFAULTATTACKRANGE);
        setIcon(IconUtil.MIKE_WAZOWSKI);
    }

}