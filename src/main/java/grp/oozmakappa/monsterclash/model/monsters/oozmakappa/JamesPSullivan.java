package grp.oozmakappa.monsterclash.model.monsters.oozmakappa;

import grp.oozmakappa.monsterclash.model.Team;
import grp.oozmakappa.monsterclash.model.abstracts.Cell;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.utils.IconUtil;

/**
 * @author Jing Li
 */
public class JamesPSullivan extends Piece {

	private static final Team TEAM = Team.OozmaKappa;
    private static final double DEFAULTHEALTH = 200;
    private static final int DEFAULTATTACKPOWER = 20;
    private static final int DEFAULTATTACKRANGE = 5;

    public JamesPSullivan(Cell startingPos) {
        super(TEAM, startingPos, DEFAULTHEALTH, DEFAULTATTACKPOWER, DEFAULTATTACKRANGE);
        setIcon(IconUtil.JAMES_P_SULLIVAN);
    }

}
