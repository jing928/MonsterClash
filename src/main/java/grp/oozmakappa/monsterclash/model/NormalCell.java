package grp.oozmakappa.monsterclash.model;

import grp.oozmakappa.monsterclash.model.abstracts.Cell;

/**
 * @author Chenglong Ma
 */
public final class NormalCell extends Cell {

    NormalCell(int x, int y) {
        super(x, y);
    }

    NormalCell(int x, int y, int order) {
        super(x, y, order);
    }

    NormalCell(int x, int y, int order, Role role) {
        super(x, y, order, role);
    }
}
