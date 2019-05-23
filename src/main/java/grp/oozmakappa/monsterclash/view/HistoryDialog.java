package grp.oozmakappa.monsterclash.view;

import grp.oozmakappa.monsterclash.model.command.Command;
import grp.oozmakappa.monsterclash.model.command.CommandManager;
import grp.oozmakappa.monsterclash.model.immutable.ImmutableHistory;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Enumeration;
import java.util.List;

/**
 * @author Chenglong Ma
 */
public class HistoryDialog extends JDialog {
    private List<DefaultMutableTreeNode> forest;

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
        // expand tree for easy selection
        for (int i = 0; i < tree.getRowCount(); i++)
            tree.expandRow(i);
        add(tree, BorderLayout.CENTER);
    }

    private void plantForest() {
        forest = new ArrayList<>();
        Deque<ImmutableHistory> uni = CommandManager.getInstance().getUniverses();
        for (ImmutableHistory history : uni) {
            int versionNum = history.getVersionNum();
            DefaultMutableTreeNode prev = null, tree = null;
            for (Command command : history.getHistory()) {
                if (prev == null) {
                    prev = new DefaultMutableTreeNode(new Node(versionNum, command));
                    tree = prev;
                } else {
                    DefaultMutableTreeNode curr = new DefaultMutableTreeNode(new Node(versionNum, command));
                    prev.add(curr);
                    prev = curr;
                }
            }
            forest.add(tree);
        }
    }

    private DefaultMutableTreeNode initTree() {
        if (forest == null) {
            plantForest();
        }
        if (forest.size() == 1) {
            return forest.get(0);
        }
        DefaultMutableTreeNode firstTree = forest.get(0);
        for (int i = 1; i < forest.size(); i++) {
            DefaultMutableTreeNode secondTree = forest.get(i);
            Enumeration firstEnumeration = firstTree.preorderEnumeration();
            Enumeration secondEnumeration = secondTree.preorderEnumeration();
            DefaultMutableTreeNode prevNode = null;
            while (secondEnumeration.hasMoreElements()) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) secondEnumeration.nextElement();
                Command cmd = ((Node) node.getUserObject()).command;
                DefaultMutableTreeNode parent = findParent(firstEnumeration, cmd);
                if (parent == null && prevNode != null) {
                    prevNode.add(node);
                    break;
                } else {
                    prevNode = parent;
                }
            }
        }
        return firstTree;
    }

    private DefaultMutableTreeNode findParent(Enumeration tree, Object cmd) {
        DefaultMutableTreeNode node = null;
        while (tree.hasMoreElements()) {
            DefaultMutableTreeNode curr = (DefaultMutableTreeNode) tree.nextElement();
            if (((Node) curr.getUserObject()).command == cmd) {
                node = curr;
                break;
            }
        }
        return node;
    }

    static class Node {
        final int versionNum;
        final Command command;

        private Node(int versionNum, Command command) {
            this.versionNum = versionNum;
            this.command = command;
        }
    }
}
