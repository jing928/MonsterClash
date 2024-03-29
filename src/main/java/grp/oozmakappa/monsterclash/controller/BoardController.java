package grp.oozmakappa.monsterclash.controller;

import grp.oozmakappa.monsterclash.model.Cell;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.view.BoardPanel;
import grp.oozmakappa.monsterclash.view.CellLabel;
import grp.oozmakappa.monsterclash.view.GameFrame;
import grp.oozmakappa.monsterclash.view.PieceButton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.event.MouseAdapter;

/**
 * @author Chenglong Ma
 */
public class BoardController extends MouseAdapter {
    private static final Logger LOG = LogManager.getLogger();
    private final BoardPanel boardPanel;

    public BoardController(BoardPanel boardPanel) {
        this.boardPanel = boardPanel;
        updateCellsLocation();
    }

    /**
     * Updates {@link Cell}s location
     * This can be only called after {@link GameFrame#pack()}
     */
    private void updateCellsLocation() {
        boardPanel.getCellLabels().forEach(c -> c.getCell().setLocation(c.getLocation()));
    }

    /**
     * Adds new {@link PieceButton} into {@link BoardPanel}
     *
     * @param piece
     */
    public void addPiece(Piece piece) {
        PieceButton button = new PieceButton(piece);
        // 1.1 add action observers
        for (PieceButton existing : boardPanel.getPieceButtons()) {
            Piece existingPiece = existing.getPiece();
            piece.addActionObserver(existing);
            existingPiece.addActionObserver(button);
        }
        // 1.2 add position observers
        for (CellLabel cellLabel : boardPanel.getCellLabels()) {
            piece.addPositionObserver(cellLabel);
        }
        // 2. set listener
        PieceListener listener = new PieceListener(boardPanel);
        button.addMouseListener(listener);
        // 3. add to board panel
        boardPanel.addPieceButton(button);
    }

}
