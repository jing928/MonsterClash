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

/**
 * @author Chenglong Ma
 */
public class BoardPanel extends JLayeredPane {
    private static final Logger LOG = LogManager.getLogger();
    private final Board board;
    private final List<CellLabel> cellLabels = new ArrayList<>();
    private final List<JLabel> pieces = new ArrayList<>();
    private final GridLayout layout;
    private JPanel cellPanel, piecePanel;

    // TODO： assign Model in Views
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

    private void initializePiecePanel2() {
        // set piece panel
        piecePanel = new JPanel(new GridBagLayout());
        piecePanel.setBounds(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
        piecePanel.setOpaque(false);
        add(piecePanel, MODAL_LAYER);
    }

    public void addPiece(PieceButton pieceButton) {
        int order = pieceButton.getOrder();
        piecePanel.remove(order);
        piecePanel.add(pieceButton, order);
    }

    public void addPiece2(PieceButton pieceButton) {
        int width = cellPanel.getWidth() / board.getWidth() - 10;
        int height = cellPanel.getHeight() / board.getHeight() - 10;
        pieceButton.setPreferredSize(new Dimension(width, height));
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = pieceButton.getCellX() + board.getMaxX();
        c.gridy = pieceButton.getCellY() + board.getMaxY();
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.BOTH;
        LOG.info("Location: " + c.gridx + ", " + c.gridy);
        piecePanel.add(pieceButton, c);
//        int order = pieceButton.getOrder();
//        Point point = cellPanel.getComponent(order).getLocation();
//        pieceButton.setBounds(point.x, point.y, width, height);
//        add(pieceButton, MODAL_LAYER);
    }

    public List<CellLabel> getCellLabels() {
        return Collections.unmodifiableList(cellLabels);
    }

    private void initCells() {
        List<Cell> cells = board.getCells();
        for (int i = 0; i < cells.size(); i++) {
            CellLabel cellLabel = new CellLabel(cells.get(i));
            if (i % 2 == 0) {
                cellLabel.setColor(Color.BLACK);
            }
            // TODO: to be checked.
            cellLabels.add(cellLabel);
            cellPanel.add(cellLabel);
        }
    }

    private void initPieces() {
        for (int i = 0; i < cellLabels.size(); i++) {
            JLabel placeHolder = new JLabel();
            placeHolder.setOpaque(false);
            piecePanel.add(placeHolder);
            pieces.add(placeHolder);
        }
    }

//    @Override
//    public void positionChanging(Piece pieceToMove) {
//
//    }
//
//    @Override
//    public void positionChanged(Piece pieceMoved, Point newPosition) {
////        double minDis = Double.MAX_VALUE;
////        CellLabel targetCell = null;
////        for (CellLabel cell : cellLabels) {
////            double dis = cell.getLocation().distance(newPosition);
////            if (dis < minDis) {
////                minDis = dis;
////                targetCell = cell;
////            }
////        }
////        LOG.info("Target Location: " + Objects.requireNonNull(targetCell).getLocation());
////        pieceMoved.setPosition(Objects.requireNonNull(targetCell).getCell());
//
//    }
}
