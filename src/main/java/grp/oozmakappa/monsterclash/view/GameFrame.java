package grp.oozmakappa.monsterclash.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Chenglong Ma
 */
public class GameFrame extends JFrame {
    private static final Logger LOG = LogManager.getLogger();
    private JPanel mainPanel;

    public GameFrame() throws HeadlessException {
        super("Monster Clash");
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        add(mainPanel);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                LOG.info("Current Position: " + mouseEvent.getPoint());
            }
        });
    }

    public void display() {
        // finish setup
        pack();
        setMinimumSize(getSize());

        // centered on screen
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void addPlayerPanel(JPanel playerPanel) {
        mainPanel.add(playerPanel);
    }

    public void addBoardPanel(BoardPanel boardPanel) {
        mainPanel.add(boardPanel);
    }

    public void addDiceButton(DiceButton diceButton) {
        add(diceButton);
    }

    //    public GameFrame(Board board) {
//        super("Monster Clash");
////        JSplitPane splitPane = new JSplitPane();
//
//        BoardPanel boardPanel = new BoardPanel(board);
//        boardPanel.setBorder(BorderFactory.createLoweredBevelBorder());
//
//        // add segments
////        add(boardPanel, BorderLayout.CENTER);
//        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
//        add(boardPanel);
//        add(new DiceButton());
////        JPanel dicePan = new JPanel(new BorderLayout());
////        dicePan.add(new DiceButton(), BorderLayout.CENTER);
////        add(dicePan, BorderLayout.SOUTH);
//
//
//        // finish setup
//        pack();
//        setMinimumSize(getSize());
//
//        // centered on screen
//        setLocationRelativeTo(null);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setVisible(true);
//
//    }
}
