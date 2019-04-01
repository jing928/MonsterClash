package grp.oozmakappa.monsterclash.model.monsters.oozmakappa;

import grp.oozmakappa.monsterclash.model.abstracts.Cell;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.utils.IconUtil;

/**
 * @author Jing Li
 */
public class MikeWazowski extends Piece {

    private static final double DEFAULTHEALTH = 100;
    private static final int DEFAULTATTACKPOWER = 10;
    private static final int DEFAULTATTACKRANGE = 3;

    public MikeWazowski(String id, Cell startingPos) {
        super(id, startingPos, DEFAULTHEALTH, DEFAULTATTACKPOWER, DEFAULTATTACKRANGE);
        setIcon(IconUtil.MIKE_WEZOWSKI);
    }

}