package grp.oozmakappa.monsterclash.controller;

import grp.oozmakappa.monsterclash.model.command.Command;
import grp.oozmakappa.monsterclash.model.command.CommandManager;
import grp.oozmakappa.monsterclash.view.HistoryDialog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Chenglong Ma
 */
public class HistoryListener implements ActionListener, TreeSelectionListener {

    private static final Logger LOG = LogManager.getLogger();
    private final HistoryDialog dialog;
    private HistoryDialog.Node selectedNode;

    public HistoryListener(HistoryDialog dialog) {

        this.dialog = dialog;
    }

    /**
     * @param e
     * @Requires selectedNode != null
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        switch (action) {
            case HistoryDialog.TIME_TRAVEL:
                CommandManager cmdManager = CommandManager.getInstance();
                int verNum = selectedNode.getVersionNum();
                Command cmd = selectedNode.getCommand();
                cmdManager.timeTravel(verNum, cmd);
                break;
            case HistoryDialog.CANCEL:
                break;
        }
        dialog.dispose();
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
        selectedNode = (HistoryDialog.Node) node.getUserObject();
        dialog.enableTravelButton(selectedNode != null);
    }
}
