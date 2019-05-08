package grp.oozmakappa.monsterclash.model.decorators;

import grp.oozmakappa.monsterclash.model.interfaces.CellEffect;

/**
 * @author Chenglong Ma
 */
public abstract class DebuffDecorator extends AbstractDecorator {
    protected DebuffDecorator(CellEffect toDecorated) {
        super(toDecorated);
    }
}
