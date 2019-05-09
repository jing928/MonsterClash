package grp.oozmakappa.monsterclash.model.decorators;

import grp.oozmakappa.monsterclash.model.interfaces.CellEffect;

/**
 * @author Chenglong Ma
 */
abstract class DebuffDecorator extends AbstractDecorator {
    DebuffDecorator(CellEffect toDecorated) {
        super(toDecorated);
    }
}
