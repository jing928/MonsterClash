package grp.oozmakappa.monsterclash.model.decorators;

import grp.oozmakappa.monsterclash.model.interfaces.CellEffect;

/**
 * @author Chenglong Ma
 */
public abstract class BuffDecorator extends AbstractDecorator {
    protected BuffDecorator(CellEffect toDecorated) {
        super(toDecorated);
    }
}
