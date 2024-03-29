package grp.oozmakappa.monsterclash.view;

import grp.oozmakappa.monsterclash.model.Board;
import grp.oozmakappa.monsterclash.model.Cell;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static grp.oozmakappa.monsterclash.model.Constraints.CELL_LENGTH;
import static grp.oozmakappa.monsterclash.utils.Distance.manhattanDistance;

/**
 * @author Chenglong Ma
 * @Invariant cellLabels.size() >= 0
 */
public class BoardPanel extends JLayeredPane {
    private static final Logger LOG = LogManager.getLogger();
    private final Board board;
    private final GridLayout layout;
    private final int boardWidth, boardHeight;
    private List<CellLabel> cellLabels;
    private List<PieceButton> pieceButtons;
    private JPanel cellPanel, piecePanel;
    private boolean updated;
    private double minimumDistance;

    public BoardPanel(Board board) {
        this.board = board;
        int rows = board.getHeight();
        int cols = board.getWidth();
        int gap = 2;
        layout = new GridLayout(rows, cols, gap, gap);
        boardWidth = cols * CELL_LENGTH + gap * (cols + 1);
        boardHeight = rows * CELL_LENGTH + gap * (rows + 1);
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        initializeCellPanel();
        initializePiecePanel();
        setBorder(BorderFactory.createLoweredBevelBorder());
    }

    private void initializeCellPanel() {
        cellPanel = new JPanel(layout);
        cellPanel.setBounds(0, 0, boardWidth, boardHeight);
        initCells();
        add(cellPanel, DEFAULT_LAYER);
    }

    private void initializePiecePanel() {
        piecePanel = new JPanel(null);
        piecePanel.setBounds(0, 0, boardWidth, boardHeight);
        piecePanel.setOpaque(false);
        add(piecePanel, MODAL_LAYER);
    }

    public void addPieceButton(PieceButton pieceButton) {
        piecePanel.add(pieceButton);
        CellLabel cell = getPoint(pieceButton.getPiece());
        pieceButton.setBounds(cell.getX(), cell.getY(), cell.getWidth(), cell.getHeight());
        updated = true;
    }

    private CellLabel getPoint(final Piece piece) {
        List<CellLabel> cellLabels = getCellLabels();
        Optional<CellLabel> opt = cellLabels.stream().filter(c -> c.getCell().equals(piece.getPosition())).findAny();
        return opt.orElse(cellLabels.get(0));
    }

    /**
     * Queries all {@link CellLabel}s in the {@link Board}.
     *
     * @return
     */
    public List<CellLabel> getCellLabels() {
        if (cellLabels == null) {
            // lazy initialization
            cellLabels = new ArrayList<>();
            for (Component component : cellPanel.getComponents()) {
                if (!(component instanceof CellLabel)) {
                    continue;
                }
                CellLabel cellLabel = (CellLabel) component;
                cellLabels.add(cellLabel);
            }
        }
        // returns unmodified list to prevent memory leak
        return Collections.unmodifiableList(cellLabels);
    }

    public List<PieceButton> getPieceButtons() {
        if (pieceButtons == null || updated) {
            pieceButtons = new ArrayList<>();
            for (Component component : piecePanel.getComponents()) {
                if (!(component instanceof PieceButton)) {
                    continue;
                }
                PieceButton button = (PieceButton) component;
                pieceButtons.add(button);
            }
            updated = false;
        }
        return Collections.unmodifiableList(pieceButtons);
    }

    /**
     * Initializes the {@link CellLabel}s for this {@link Board}.
     */
    private void initCells() {
        List<Cell> cells = board.getCells();
        for (int i = 0; i < cells.size(); i++) {
            // The board panel is the creator of cell labels.
            CellLabel cellLabel = new CellLabel(cells.get(i));
            if (i % 2 == 0) {
                cellLabel.setColor(Color.BLACK);
            }
            cellPanel.add(cellLabel);
        }
    }

    private <T extends Component> T getClosestComponent(Component src, T target) {
        T closest = null;
        double dis = manhattanDistance(src.getLocation(), target.getLocation());
        double maxDis = target.getWidth() + target.getHeight();
        // T will be ignored if the minimum distance is larger than
        // the boundary of component
        if (dis < Math.min(maxDis, minimumDistance)) {
            minimumDistance = dis;
            closest = target;
        }
        return closest;
    }

    /**
     * Returns the closest {@link CellLabel} to specified {@link Component}
     *
     * @param srcCell
     * @return the closest {@link CellLabel} if available.
     */
    public CellLabel getClosestCell(Component srcCell) {
        CellLabel closestCell = null;
        minimumDistance = Double.MAX_VALUE;
        for (CellLabel cellLabel : getCellLabels()) {
            if (!cellLabel.canPlaced()) {
                continue;
            }
            CellLabel closest = getClosestComponent(srcCell, cellLabel);
            if (closest != null) {
                closestCell = closest;
            }
        }
        return closestCell;
    }

    public PieceButton getClosestPiece(Component srcPiece) {
        PieceButton closestPiece = null;
        minimumDistance = Double.MAX_VALUE;
        for (PieceButton pieceButton : getPieceButtons()) {
            if (!pieceButton.canPlaced()) {
                continue;
            }
            PieceButton closest = getClosestComponent(srcPiece, pieceButton);
            if (closest != null) {
                closestPiece = closest;
            }
        }
        return closestPiece;
    }

    /**
     * @return if there is any piece button can be placed.
     */
    public boolean hasReachablePiece() {
        return getPieceButtons().stream().anyMatch(PieceButton::canPlaced);
    }
}
