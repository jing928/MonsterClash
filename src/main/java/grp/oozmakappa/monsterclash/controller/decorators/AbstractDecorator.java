package grp.oozmakappa.monsterclash.controller.decorators;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chenglong Ma
 */
public abstract class AbstractDecorator implements EffectDecorator {
    protected final EffectDecorator toDecorated;

    protected AbstractDecorator(EffectDecorator toDecorated) {
        this.toDecorated = toDecorated;
    }

    private static BuffDecorator getBuffDecorator(EffectDecorator toDecorated) {
        List<BuffDecorator> decorators = new ArrayList<>();
        decorators.add(new HealthBuff(toDecorated));
        decorators.add(new PowerBuff(toDecorated));
        // TODO: add more
        int size = decorators.size();
        return decorators.get((int) (size * Math.random()));
    }

    private static DebuffDecorator getDebuffDecorator(EffectDecorator toDecorated) {
        List<DebuffDecorator> decorators = new ArrayList<>();
        decorators.add(new HealthDebuff(toDecorated));
        decorators.add(new RangeDebuff(toDecorated));
        // TODO: add more
        int size = decorators.size();
        return decorators.get((int) (size * Math.random()));
    }

    public static EffectDecorator getDecorator(EffectDecorator toDecorated) {
        int random = (int) (10 * Math.random());
        switch (random) {

            default:
                return null;
            // 0 and 1 are randomly selected.
            case 0:
                return getBuffDecorator(toDecorated);
            case 1:
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

    protected abstract void showMessage(String message);
}
