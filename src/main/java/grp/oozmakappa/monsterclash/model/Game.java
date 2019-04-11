package grp.oozmakappa.monsterclash.model;

import grp.oozmakappa.monsterclash.controller.BoardController;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.model.monsters.oozmakappa.JamesPSullivan;
import grp.oozmakappa.monsterclash.model.monsters.oozmakappa.MikeWazowski;
import grp.oozmakappa.monsterclash.model.monsters.oozmakappa.Squishy;
import grp.oozmakappa.monsterclash.model.monsters.roaromegaroar.ChetAlexander;
import grp.oozmakappa.monsterclash.model.monsters.roaromegaroar.JohnnyWorthington;
import grp.oozmakappa.monsterclash.model.monsters.roaromegaroar.RandallBoggs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static grp.oozmakappa.monsterclash.utils.Constraints.*;

/**
 * The main model
 *
 * @author Chenglong Ma
 */
public class Game {
    private Board board;
    private List<Piece> pieces = new ArrayList<>();

    public Game() {
        initBoard();
        initPieces();
    }

    public List<Piece> getPieces() {
        return Collections.unmodifiableList(pieces);
    }

    private void initBoard() {
        board = new Board(MAX_X, MAX_Y, CORNER_X, CORNER_Y);
    }

    public Board getBoard() {
        return board;
    }

    /**
     * Initializes the {@link Piece}s.
     *
     * @pre The {@link BoardController} cannot be null
     */
    private void initPieces() {
        // TODO hardcode
        pieces.add(new JamesPSullivan(board.getCell(-MAX_X, 4)));
        pieces.add(new MikeWazowski(board.getCell(-MAX_X, 3)));
        pieces.add(new Squishy(board.getCell(-MAX_X, 2)));

        pieces.add(new ChetAlexander(board.getCell(MAX_X, 4)));
        pieces.add(new JohnnyWorthington(board.getCell(MAX_X, 3)));
        pieces.add(new RandallBoggs(board.getCell(MAX_X, 2)));
    }
}
