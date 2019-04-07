package grp.oozmakappa.monsterclash.controller;

import grp.oozmakappa.monsterclash.model.Board;
import grp.oozmakappa.monsterclash.model.Dice;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.model.monsters.oozmakappa.JamesPSullivan;
import grp.oozmakappa.monsterclash.model.monsters.oozmakappa.MikeWazowski;
import grp.oozmakappa.monsterclash.model.monsters.oozmakappa.Squishy;
import grp.oozmakappa.monsterclash.model.monsters.roaromegaroar.ChetAlexander;
import grp.oozmakappa.monsterclash.model.monsters.roaromegaroar.JohnnyWorthington;
import grp.oozmakappa.monsterclash.model.monsters.roaromegaroar.RandallBoggs;
import grp.oozmakappa.monsterclash.view.BoardPanel;
import grp.oozmakappa.monsterclash.view.DiceButton;
import grp.oozmakappa.monsterclash.view.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static grp.oozmakappa.monsterclash.utils.Constraints.*;

/**
 * @author Chenglong Ma
 */
public class MainController {

    private Board board;

    // views
    private BoardPanel boardPanel;
    private DiceButton diceButton;
    private JPanel player1Panel;
    private JPanel player2Panel;
    private GameFrame gameFrame;

    // controller
    private DiceController diceController;
    private BoardController boardController;

    private void initBoard() {
        board = new Board(MAX_X, MAX_Y, CORNER_X, CORNER_Y);
    }

    /**
     * @pre the {@link Board} should be instantiated first.
     */
    private void initBoardPanel() {
        assert board != null;
        boardPanel = new BoardPanel(board);
    }

    private void initDice() {
        diceButton = new DiceButton();
        diceController = new DiceController(diceButton);
    }

    /**
     * Initializes the {@link Piece}s.
     *
     * @pre The {@link BoardController} cannot be null
     */
    private void initPieces() {
        assert boardController != null;
        List<Piece> pieces = new ArrayList<>();
        // TODO hardcode
        pieces.add(new JamesPSullivan(board.getCell(-MAX_X, 4)));
        pieces.add(new MikeWazowski(board.getCell(-MAX_X, 3)));
        pieces.add(new Squishy(board.getCell(-MAX_X, 2)));

        pieces.add(new ChetAlexander(board.getCell(MAX_X, 4)));
        pieces.add(new JohnnyWorthington(board.getCell(MAX_X, 3)));
        pieces.add(new RandallBoggs(board.getCell(MAX_X, 2)));

        for (Piece piece : pieces) {
            boardController.addPiece(piece);
            Dice.addObserver(piece);
        }
    }

    private void initPlayerPanels() {
        // TODO: hardcode
        player1Panel = new JPanel();
        player1Panel.setBackground(Color.RED);
        player1Panel.setPreferredSize(new Dimension(300, 60));

        player2Panel = new JPanel();
        player2Panel.setBackground(Color.BLUE);
        player2Panel.setPreferredSize(new Dimension(300, 60));
    }

    /**
     * @pre the components in {@link GameFrame} should be created first.
     */
    private void initGameFrame() {
        assert player1Panel != null;
        assert player2Panel != null;
        assert boardPanel != null;
        assert diceButton != null;
        gameFrame = new GameFrame();
        gameFrame.addPlayerPanel(player1Panel);
        gameFrame.addBoardPanel(boardPanel);
        gameFrame.addPlayerPanel(player2Panel);
        gameFrame.addDiceButton(diceButton);
    }

    public void run() {
        initDice();

        initBoard();
        initBoardPanel();
        boardController = new BoardController(boardPanel);

        initPieces();

        initPlayerPanels();
        initGameFrame();
        SwingUtilities.invokeLater(gameFrame::display);
    }
}
