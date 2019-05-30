package grp.oozmakappa.monsterclash.view;

import grp.oozmakappa.monsterclash.controller.HistoryListener;
import grp.oozmakappa.monsterclash.model.command.Command;
import grp.oozmakappa.monsterclash.model.command.CommandManager;
import grp.oozmakappa.monsterclash.model.immutable.ImmutableHistory;
import grp.oozmakappa.monsterclash.utils.IconFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Enumeration;
import java.util.List;

import static grp.oozmakappa.monsterclash.utils.IconFactory.TREE_NODE;

/**
 * @author Chenglong Ma
 */
public class HistoryDialog extends JDialog {
    public static final String TIME_TRAVEL = "Time Travel";
    public static final String CANCEL = "Cancel";
    private static final Logger LOG = LogManager.getLogger();
    private final Deque<ImmutableHistory> universes;
    private final HistoryListener listener;
    private final JScrollPane scrollPane = new JScrollPane();
    private List<DefaultMutableTreeNode> forest;
    private JButton start;

    public HistoryDialog() {
        super((Frame) null, true);
        setTitle("History");
        universes = CommandManager.getInstance().getUniverses();
        listener = new HistoryListener(this);
        setLayout(new BorderLayout(2, 2));

        initView();
        setPreferredSize(new Dimension(1400, 1000));

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private static void setUI(JTree tree) {
        Icon icon = IconFactory.getInstance().getIcon(TREE_NODE).getIcon();
        DefaultTreeCellRenderer render = (DefaultTreeCellRenderer) (tree.getCellRenderer());
        render.setLeafIcon(icon);
        render.setClosedIcon(icon);
        render.setOpenIcon(icon);
    }

    private void initView() {
        JTree tree = new JTree(initTree());
        tree.setAutoscrolls(true);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        setUI(tree);
        tree.addTreeSelectionListener(listener);
        // expand tree for easy selection
        for (int i = 0; i < tree.getRowCount(); i++)
            tree.expandRow(i);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);
        scrollPane.setViewportView(tree);
        addButtons();
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

    private void addButtons() {
        JPanel panel = new JPanel();
        Border padding = BorderFactory.createEmptyBorder(5, 50, 5, 50);
        panel.setBorder(padding);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        start = new JButton(TIME_TRAVEL);
        start.setEnabled(false);
        start.addActionListener(listener);
        JButton cancel = new JButton(CANCEL);
        cancel.addActionListener(listener);
        panel.add(start);
        panel.add(Box.createHorizontalGlue());
        panel.add(cancel);
        add(panel, BorderLayout.SOUTH);
    }

    public void enableTravelButton(boolean enabled) {
        start.setEnabled(enabled);
    }

    /**
     * A wrapper class for {@link Command}
     */
    public static class Node {
        private final int versionNum;
        private final Command command;

        private Node(int versionNum, Command command) {
            this.versionNum = versionNum;
            this.command = command;
        }

        public Command getCommand() {
            return command;
        }

        @Override
        public String toString() {
            return String.format("%s v_%d.0", command, versionNum);
        }

        public int getVersionNum() {
            return versionNum;
        }
    }
}
