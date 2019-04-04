package grp.oozmakappa.monsterclash.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;

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

}
