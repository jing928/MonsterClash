package grp.oozmakappa.monsterclash.view;

import grp.oozmakappa.monsterclash.model.command.Command;
import grp.oozmakappa.monsterclash.model.command.CommandManager;
import grp.oozmakappa.monsterclash.model.immutable.ImmutableHistory;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.util.Deque;

/**
 * @author Chenglong Ma
 */
public class HistoryDialog extends JDialog {
    public HistoryDialog() {
        super((Frame) null, true);
        setLayout(new BorderLayout());

        initView();
        setPreferredSize(new Dimension(600, 400));

        pack();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void initView() {
        JTree tree = new JTree(initTree());
        add(tree, BorderLayout.CENTER);
    }

    private DefaultMutableTreeNode initTree() {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode();
        Deque<ImmutableHistory> uni = CommandManager.getInstance().viewUniverses();
        ImmutableHistory prev = null;
        DefaultMutableTreeNode prevNode = null;
        for (ImmutableHistory history : uni) {
            if (prev == null) {
                prev = history;
                for (Command command : prev.getHistory()) {
                    if (prevNode == null) {
                        prevNode = new DefaultMutableTreeNode(command);
                        node.add(prevNode);
                    } else {
                        DefaultMutableTreeNode curr = new DefaultMutableTreeNode(command);
                        prevNode.add(curr);
                        prevNode = curr;
                    }
                }
            }
        }
        return node;
    }
}
