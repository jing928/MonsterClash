package grp.oozmakappa.monsterclash.controller;

import grp.oozmakappa.monsterclash.model.Board;
import grp.oozmakappa.monsterclash.model.Dice;
import grp.oozmakappa.monsterclash.model.Game;
import grp.oozmakappa.monsterclash.model.Team;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.view.GameFrame;

import javax.swing.*;

/**
 * @author Chenglong Ma
 */
public class MainController {

    /**
     * @param board
     * @return
     */
    private GameFrame initGameFrame(Board board) {
        assert board != null;
        GameFrame gameFrame = new GameFrame();
        gameFrame.initPlayer1Panel();
        gameFrame.initBoardPanel(board);
        gameFrame.initPlayer2Panel();
        return gameFrame;
    }

    private void initControllers(Game model, GameFrame view) {
        assert model != null && view != null;
        Dice dice = Dice.getInstance();
        // Default start team is Oozma Kappa
        BoardController boardController = new BoardController(view.getBoardPanel(), Team.OozmaKappa);
        for (Piece piece : model.getPieces()) {
            boardController.addPiece(piece);
            dice.addObserver(piece);
        }
        dice.addObserver(boardController);
    }

    public void run() {
        Game game = new Game();
        GameFrame gameFrame = initGameFrame(game.getBoard());
        initControllers(game, gameFrame);
        SwingUtilities.invokeLater(gameFrame::display);
    }
}
