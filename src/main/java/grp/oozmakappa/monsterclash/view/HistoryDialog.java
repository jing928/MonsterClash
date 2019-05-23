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
    private final Deque<ImmutableHistory> universes;
    private List<DefaultMutableTreeNode> forest;

    public HistoryDialog() {
        super((Frame) null, true);
        universes = CommandManager.getInstance().getUniverses();
        setLayout(new BorderLayout(2, 2));

        initView();
        setPreferredSize(new Dimension(600, 400));

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

    private void initView() {
        JTree tree = new JTree(initTree());
        // expand tree for easy selection
        for (int i = 0; i < tree.getRowCount(); i++)
            tree.expandRow(i);
        add(tree, BorderLayout.CENTER);
    }

    /**
     * Transfers universes to {@link DefaultMutableTreeNode}
     *
     * @Ensures forest.size() == universes.size()
     */
    private void plantForest() {
        forest = new ArrayList<>();
        for (ImmutableHistory history : universes) {
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

    /**
     * Prunes {@link #forest} to tree
     *
     * @return a pruned tree
     */
    private DefaultMutableTreeNode initTree() {
        if (forest == null) {
            plantForest();
        }
        if (forest.isEmpty()) {
            return null;
        }
        DefaultMutableTreeNode firstTree = forest.get(0);
        if (forest.size() == 1) {
            return firstTree;
        }
        // traverses from the second tree
        for (int i = 1; i < forest.size(); i++) {
            DefaultMutableTreeNode currTree = forest.get(i);
            Enumeration currEnum = currTree.preorderEnumeration();
            DefaultMutableTreeNode prevNode = null;
            // adds the node without shadow to the pruned tree
            while (currEnum.hasMoreElements()) {
                DefaultMutableTreeNode currNode = (DefaultMutableTreeNode) currEnum.nextElement();
                DefaultMutableTreeNode shadow = findShadow(firstTree, currNode);
                if (shadow == null && prevNode != null) {
                    prevNode.add(currNode);
                    break;
                }
                prevNode = shadow;
            }
            // as least ONE shadow node exists
            assert prevNode != null;
        }
        return firstTree;
    }

    /**
     * Finds the first shadow node on pruned tree
     *
     * @param tree the tree to be added
     * @param node the node pointer
     * @return the shadow node on tree if existed.
     */
    private DefaultMutableTreeNode findShadow(DefaultMutableTreeNode tree, DefaultMutableTreeNode node) {
        Enumeration enums = tree.preorderEnumeration();
        DefaultMutableTreeNode shadow = null;
        Command cmd = ((Node) node.getUserObject()).command;
        while (enums.hasMoreElements()) {
            DefaultMutableTreeNode curr = (DefaultMutableTreeNode) enums.nextElement();
            if (((Node) curr.getUserObject()).command == cmd) {
                shadow = curr;
                break;
            }
        }
        return shadow;
    }

    /**
     * A wrapper class for {@link Command}
     */
    static class Node {
        final int versionNum;
        final Command command;

        private Node(int versionNum, Command command) {
            this.versionNum = versionNum;
            this.command = command;
        }

        @Override
        public String toString() {
            return command.toString();
        }
    }
}
