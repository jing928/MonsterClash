package grp.oozmakappa.monsterclash.controller;

import grp.oozmakappa.monsterclash.model.Board;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.model.monsters.oozmakappa.JamesPSullivan;
import grp.oozmakappa.monsterclash.model.monsters.oozmakappa.MikeWazowski;
import grp.oozmakappa.monsterclash.model.monsters.oozmakappa.Squishy;
import grp.oozmakappa.monsterclash.model.monsters.roaromegaroar.ChetAlexander;
import grp.oozmakappa.monsterclash.model.monsters.roaromegaroar.JohnnyWorthington;
import grp.oozmakappa.monsterclash.model.monsters.roaromegaroar.RandallBoggs;
import grp.oozmakappa.monsterclash.view.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static grp.oozmakappa.monsterclash.utils.Constraints.*;

/**
 * @author Chenglong Ma
 */
public class MainController {
    // models
    private Board board;
    private List<Piece> pieces;

    // views
    private BoardPanel boardPanel;
    private DiceButton diceButton;
    private JPanel player1Panel;
    private JPanel player2Panel;
    private List<PieceButton> pieceButtons;
    private GameFrame gameFrame;

    // controller
    private DiceController diceController;
    private BoardController boardController;

    private void initBoard() {
        board = new Board(MAX_X, MAX_Y, CORNER_X, CORNER_Y);
    }

    private void initBoardPanel() {
        assert board != null;
        boardPanel = new BoardPanel(board);
    }

    private void initBoardController() {
        assert boardPanel != null && board != null;
        assert pieces != null;
        boardController = new BoardController(boardPanel, board);
        for (Piece piece : pieces) {
            piece.addObserver(boardController);
        }
    }

    private void initDiceButton() {
        assert pieces != null;
        diceButton = new DiceButton();
        diceController = new DiceController(diceButton);
        diceController.addObservers(pieces);
    }

    /**
     * Initializes the {@link Piece}s.
     */
    private void initPieces() {
        assert boardController != null && diceController != null;
        pieces = new ArrayList<>();
        // TODO hardcode
        pieces.add(new JamesPSullivan("James", board.getCell(-6, 4)));
        pieces.add(new MikeWazowski("Mike", board.getCell(-6, 3)));
        pieces.add(new Squishy("Squishy", board.getCell(-6, 2)));

        pieces.add(new ChetAlexander("Chet", board.getCell(6, 4)));
        pieces.add(new JohnnyWorthington("Johnny", board.getCell(6, 3)));
        pieces.add(new RandallBoggs("Randall", board.getCell(6, 2)));

        // TODO: is it okay to add observers here?
        for (Piece piece : pieces) {
            for (CellLabel cellLabel : boardPanel.getCellLabels()) {
                piece.addObserver(cellLabel);
            }
        }
    }

    private void initPieceButtons() {
        assert pieces != null;
        pieceButtons = new ArrayList<>();
        for (Piece piece : pieces) {
            PieceButton button = new PieceButton(piece);
            boardPanel.addPiece(button);
            pieceButtons.add(button);
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
        initBoard();
        initBoardPanel();


        initPieces();
        initPieceButtons();

        initDiceButton();

        initPlayerPanels();
        initBoardController();
        initGameFrame();
        SwingUtilities.invokeLater(gameFrame::display);
    }
}
