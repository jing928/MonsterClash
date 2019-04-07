package grp.oozmakappa.monsterclash.model.monsters.roaromegaroar;

import grp.oozmakappa.monsterclash.model.Team;
import grp.oozmakappa.monsterclash.model.abstracts.Cell;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.utils.IconUtil;

/**
 * @author Jing Li
 */
public class JohnnyWorthington extends Piece {

    private static final Team TEAM = Team.RoarOmegaRoar;
    private static final double DEFAULTHEALTH = 200;
    private static final int DEFAULTATTACKPOWER = 20;
    private static final int DEFAULTATTACKRANGE = 5;

    public JohnnyWorthington(Cell startingPos) {
        super(TEAM, startingPos, DEFAULTHEALTH, DEFAULTATTACKPOWER, DEFAULTATTACKRANGE);
        setIcon(IconUtil.JOHNNY_WORTHINGTON);
    }

}
