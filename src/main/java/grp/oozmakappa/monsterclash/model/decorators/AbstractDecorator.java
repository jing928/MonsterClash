package grp.oozmakappa.monsterclash.model.decorators;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.model.interfaces.CellEffect;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chenglong Ma
 */
public abstract class AbstractDecorator implements CellEffect {
    // 0 and 1 are randomly selected.
    private static final int BUFF = 0;
    private static final int DEBUFF = 1;
    private final CellEffect toDecorated;

    AbstractDecorator(CellEffect toDecorated) {
        this.toDecorated = toDecorated;
    }

    private static BuffDecorator getBuffDecorator(CellEffect toDecorated) {
        List<BuffDecorator> decorators = new ArrayList<>();
        decorators.add(new HealthBuff(toDecorated));
        decorators.add(new PowerBuff(toDecorated));
        // TODO: add more
        int size = decorators.size();
        return decorators.get((int) (size * Math.random()));
    }

    private static DebuffDecorator getDebuffDecorator(CellEffect toDecorated) {
        List<DebuffDecorator> decorators = new ArrayList<>();
        decorators.add(new HealthDebuff(toDecorated));
        decorators.add(new RangeDebuff(toDecorated));
        // TODO: add more
        int size = decorators.size();
        return decorators.get((int) (size * Math.random()));
    }

    public static CellEffect getDecorator(CellEffect toDecorated) {
        int random = (int) (10 * Math.random());
        switch (random) {

            default:
                return null;
            case BUFF:
                return getBuffDecorator(toDecorated);
            case DEBUFF:
                return getDebuffDecorator(toDecorated);
        }
    }

    @Override
    public void affect(Piece piece) {
        // forward operation
        if (toDecorated != null) {
            toDecorated.affect(piece);
        }
    }
}
