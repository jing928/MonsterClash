package grp.oozmakappa.monsterclash.controller;

import grp.oozmakappa.monsterclash.model.Dice;
import grp.oozmakappa.monsterclash.model.Game;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.view.GameFrame;

import javax.swing.*;

/**
 * @author Chenglong Ma
 */
public class MainController {

    private GameFrame initGameFrame(Game game) {
        GameFrame gameFrame = new GameFrame();
        gameFrame.initPlayer1Panel(game.getPieces());
        gameFrame.initBoardPanel(game.getBoard());
        gameFrame.initPlayer2Panel(game.getPieces());
        return gameFrame;
    }

    private void initControllers(Game model, GameFrame view) {
        assert model != null && view != null;
        Dice dice = Dice.getInstance();
        BoardController boardController = new BoardController(view.getBoardPanel());
        for (Piece piece : model.getPieces()) {
            boardController.addPiece(piece);
            dice.addObserver(piece);
        }
    }

    public void run() {
        Game game = new Game();
        GameFrame gameFrame = initGameFrame(game);
        initControllers(game, gameFrame);
        SwingUtilities.invokeLater(gameFrame::display);
    }
}
