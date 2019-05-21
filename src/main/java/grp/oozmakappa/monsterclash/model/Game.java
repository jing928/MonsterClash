package grp.oozmakappa.monsterclash.model;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.model.monsters.oozmakappa.JamesPSullivan;
import grp.oozmakappa.monsterclash.model.monsters.oozmakappa.MikeWazowski;
import grp.oozmakappa.monsterclash.model.monsters.oozmakappa.Squishy;
import grp.oozmakappa.monsterclash.model.monsters.roaromegaroar.ChetAlexander;
import grp.oozmakappa.monsterclash.model.monsters.roaromegaroar.JohnnyWorthington;
import grp.oozmakappa.monsterclash.model.monsters.roaromegaroar.RandallBoggs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


/**
 * The main model
 *
 * @author Chenglong Ma
 */
public class Game {
    private static final Constraints CONSTRAINTS = Constraints.getInstance();
    private final int maxX, maxY, cornerX, cornerY;
    private Board board;
    private List<Piece> pieces = new ArrayList<>();

    public Game() {
        maxX = CONSTRAINTS.getMaxX();
        maxY = CONSTRAINTS.getMaxY();
        cornerX = CONSTRAINTS.getCornerX();
        cornerY = CONSTRAINTS.getCornerY();
        initBoard();
        initPieces();
    }

    public List<Piece> getPieces() {
        return Collections.unmodifiableList(pieces);
    }

    private void initBoard() {
        board = new Board(maxX, maxY, cornerX, cornerY);
    }

    public Board getBoard() {
        return board;
    }

    /**
     * Initializes the {@link Piece}s.
     */
    private void initPieces() {
        int n = CONSTRAINTS.getNumOfPieces();

        LinkedList<Piece> oozma = new LinkedList<>();
        int maxY = this.maxY;
        oozma.add(new JamesPSullivan(Cell.getCell(-maxX, maxY--)));
        oozma.add(new MikeWazowski(Cell.getCell(-maxX, maxY--)));
        oozma.add(new Squishy(Cell.getCell(-maxX, maxY)));
        maxY = this.maxY;
        LinkedList<Piece> roar = new LinkedList<>();
        roar.add(new ChetAlexander(Cell.getCell(maxX, maxY--)));
        roar.add(new JohnnyWorthington(Cell.getCell(maxX, maxY--)));
        roar.add(new RandallBoggs(Cell.getCell(maxX, maxY)));
        for (int i = 0; i < n; i += 2) {
            pieces.add(oozma.remove());
            pieces.add(roar.remove());
        }
    }
}
