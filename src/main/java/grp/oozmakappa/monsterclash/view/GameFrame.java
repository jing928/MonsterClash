package grp.oozmakappa.monsterclash.view;

import grp.oozmakappa.monsterclash.model.Board;
import grp.oozmakappa.monsterclash.model.Team;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.List;

/**
 * The main view
 *
 * @author Chenglong Ma
 */
public class GameFrame extends JFrame {
    private static final Logger LOG = LogManager.getLogger();
    private JPanel mainPanel;
    // sub views
    private BoardPanel boardPanel;

    public GameFrame() throws HeadlessException {
        super("Monster Clash");
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        initMenuBar();
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        add(mainPanel);
        add(new DiceButton());
        JOptionPane.setRootFrame(this);
    }

    public BoardPanel getBoardPanel() {
        return boardPanel;
    }

    /**
     * @Requires board != null
     */
    public void initBoardPanel(Board board) {
        assert board != null;
        boardPanel = new BoardPanel(board);
        mainPanel.add(boardPanel);
    }

    public void initPlayer1Panel(List<Piece> pieces) {
        JPanel player1Panel = new PlayerPanel(pieces, Team.OozmaKappa);
        mainPanel.add(player1Panel);
    }

    /**
     * @param pieces
     * @Requires mainPanel.getComponentCount() > 0
     */
    public void initPlayer2Panel(List<Piece> pieces) {
        JPanel player2Panel = new PlayerPanel(pieces, Team.RoarOmegaRoar);
        mainPanel.add(player2Panel);
    }

    private void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createFileMenu());
        setJMenuBar(menuBar);
    }

    private JMenu createFileMenu() {
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.add(createHistoryMenuItem());
        fileMenu.add(createExitMenuItem());
        return fileMenu;
    }

    private JMenuItem createHistoryMenuItem() {
        JMenuItem history = new JMenuItem("Show History");
        history.setMnemonic(KeyEvent.VK_H);
        history.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_H, InputEvent.CTRL_MASK
        ));
        history.addActionListener(e -> new HistoryDialog());
        return history;
    }

    private JMenuItem createExitMenuItem() {
        JMenuItem exit = new JMenuItem("Exit");
        exit.setMnemonic(KeyEvent.VK_E);
        exit.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Q, InputEvent.CTRL_MASK
        ));
        exit.addActionListener(e -> System.exit(0));
        return exit;
    }

    /**
     * @Requires mainPanel != null && player1Panel != null && play2Panel != null
     */
    public void display() {
        // finish setup
        setMinimumSize(getSize());

        // centered on screen
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
