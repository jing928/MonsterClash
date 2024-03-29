package grp.oozmakappa.monsterclash.controller;

import grp.oozmakappa.monsterclash.model.Dice;
import grp.oozmakappa.monsterclash.model.Game;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.view.GameFrame;
import grp.oozmakappa.monsterclash.view.MenuDialog;

import javax.swing.*;

/**
 * @author Chenglong Ma
 */
public class MainController {

    private static GameFrame initGameFrame(Game game) {
        GameFrame gameFrame = new GameFrame();
        gameFrame.initPlayer1Panel(game.getPieces());
        gameFrame.initBoardPanel(game.getBoard());
        gameFrame.initPlayer2Panel(game.getPieces());
        gameFrame.pack();
        return gameFrame;
    }

    private static void initControllers(Game model, GameFrame view) {
        assert model != null && view != null;
        Dice dice = Dice.getInstance();
        BoardController boardController = new BoardController(view.getBoardPanel());
        for (Piece piece : model.getPieces()) {
            boardController.addPiece(piece);
            dice.addObserver(piece);
        }
    }

    public static void launch() {
        SwingUtilities.invokeLater(MenuDialog::new);
    }

    public static void startGame() {
        Game game = new Game();
        GameFrame gameFrame = initGameFrame(game);
        initControllers(game, gameFrame);
        SwingUtilities.invokeLater(gameFrame::display);
    }
}
