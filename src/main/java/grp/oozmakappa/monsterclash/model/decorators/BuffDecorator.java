package grp.oozmakappa.monsterclash.model.decorators;

import grp.oozmakappa.monsterclash.model.interfaces.CellEffect;

/**
 * @author Chenglong Ma
 */
abstract class BuffDecorator extends AbstractDecorator {
    BuffDecorator(CellEffect toDecorated) {
        super(toDecorated);
    }
}
