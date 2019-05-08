package grp.oozmakappa.monsterclash.view;

import grp.oozmakappa.monsterclash.model.Board;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;

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
    private JPanel player1Panel;
    private JPanel player2Panel;

    public GameFrame() throws HeadlessException {
        super("Monster Clash");
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        add(mainPanel);
        add(new DiceButton());
    }

    /**
     * Shows message dialog
     *
     * @param parent
     * @param message the message to show
     * @param good    is the message good or bad?
     */
    public static void showMessage(Component parent, String message, boolean good) {
        String title = good ? "Congrats!" : "Sorry...";
        int type = good ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.WARNING_MESSAGE;
        JOptionPane.showMessageDialog(parent, message, title, type);
    }

    public BoardPanel getBoardPanel() {
        return boardPanel;
    }

    public JPanel getPlayer1Panel() {
        return player1Panel;
    }

    public JPanel getPlayer2Panel() {
        return player2Panel;
    }

    /**
     * @Requires board != null
     */
    public void initBoardPanel(Board board) {
        assert board != null;
        boardPanel = new BoardPanel(board);
        mainPanel.add(boardPanel);
    }

    @Deprecated
    public void initPlayer1Panel() {
        // TODO: hardcode
        player1Panel = new JPanel();
        player1Panel.setBackground(Color.RED);
        player1Panel.setPreferredSize(new Dimension(300, 60));
        mainPanel.add(player1Panel);
    }

    /**
     * @Requires mainPanel.getComponentCount() > 0
     */
    @Deprecated
    public void initPlayer2Panel() {
        player2Panel = new JPanel();
        player2Panel.setBackground(Color.BLUE);
        player2Panel.setPreferredSize(new Dimension(300, 60));
        mainPanel.add(player2Panel);
    }

    /**
     * @Requires mainPanel != null && player1Panel != null && play2Panel != null
     */
    public void display() {
        // finish setup
        pack();
        setMinimumSize(getSize());

        // centered on screen
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
