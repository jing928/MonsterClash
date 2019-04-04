package grp.oozmakappa.monsterclash.view;

import grp.oozmakappa.monsterclash.model.Board;
import grp.oozmakappa.monsterclash.model.abstracts.Cell;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static grp.oozmakappa.monsterclash.utils.Constraints.BOARD_HEIGHT;
import static grp.oozmakappa.monsterclash.utils.Constraints.BOARD_WIDTH;
import static grp.oozmakappa.monsterclash.utils.Distance.manhattanDistance;

/**
 * @author Chenglong Ma
 */
public class BoardPanel extends JLayeredPane {
    private static final Logger LOG = LogManager.getLogger();
    private final Board board;
    private final GridLayout layout;
    private List<CellLabel> cellLabels;
    private JPanel cellPanel, piecePanel;

    public BoardPanel(Board board) {
        this.board = board;
        int x = board.getWidth();
        int y = board.getHeight();
        int gap = 2;
        layout = new GridLayout(x, y, gap, gap);
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        initializeCellPanel();
        initializePiecePanel();
        setBorder(BorderFactory.createLoweredBevelBorder());
    }

    private void initializeCellPanel() {
        cellPanel = new JPanel(layout);
        cellPanel.setBounds(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
        initCells();
        add(cellPanel, DEFAULT_LAYER);
    }

    private void initializePiecePanel() {
        piecePanel = new JPanel(layout);
        piecePanel.setBounds(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
        piecePanel.setOpaque(false);
        initPieces();
        add(piecePanel, MODAL_LAYER);
    }

    public void addPieceButton(PieceButton pieceButton) {
        int order = pieceButton.getOrder();
        piecePanel.remove(order);
        piecePanel.add(pieceButton, order);
    }

    /**
     * Queries all {@link CellLabel}s in the {@link Board}.
     *
     * @return
     */
    public List<CellLabel> getCellLabels() {
        if (cellLabels == null) {
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

    /**
     * Initializes the piece for {@link #piecePanel}.
     * <br>
     * Using transparent {@link JLabel} as placeholder for {@link PieceButton}.
     */
    private void initPieces() {
        for (int i = 0; i < cellPanel.getComponentCount(); i++) {
            JLabel placeHolder = new JLabel();
            placeHolder.setOpaque(false);
            piecePanel.add(placeHolder);
        }
    }

    /**
     * Returns the closest {@link CellLabel} to specified {@link Component}
     *
     * @param component
     * @return the closest {@link CellLabel} if available.
     */
    public CellLabel getClosestCell(Component component) {
        CellLabel closestCell = null;
        double minDis = Double.MAX_VALUE;
        for (CellLabel cellLabel : getCellLabels()) {
            if (!cellLabel.canPlaced()) {
                continue;
            }
            double dis = manhattanDistance(component.getLocation(), cellLabel.getLocation());
            double maxDis = cellLabel.getWidth() + cellLabel.getHeight();
            // the cell will be ignored if the minimum distance is larger than
            // the boundary of component
            if (dis < Math.min(maxDis, minDis)) {
                minDis = dis;
                closestCell = cellLabel;
            }
        }
        return closestCell;
    }

}
