package grp.oozmakappa.monsterclash.view;

import grp.oozmakappa.monsterclash.controller.SettingListener;
import grp.oozmakappa.monsterclash.model.Constraints;
import grp.oozmakappa.monsterclash.model.Team;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.util.Arrays;

import static grp.oozmakappa.monsterclash.model.rules.AbstractRuleFactory.RULE_A;
import static grp.oozmakappa.monsterclash.model.rules.AbstractRuleFactory.RULE_B;

public class MenuDialog extends JDialog {
    public static final String START_COMMAND = "Start";
    public static final String EXIT_COMMAND = "Exit";
    private final Border padding;
    private final JPanel boardSetting, gameSetting;
    private final SettingListener listener;
    private JFormattedTextField xVal, yVal, corXVal, corYVal, time;


    public MenuDialog() {
        super((Frame) null, true);
        padding = BorderFactory.createEmptyBorder(20, 20, 10, 20);
        setLayout(new BorderLayout(2, 2));
        listener = new SettingListener(this);
        GridLayout gridLayout = new GridLayout(6, 2, 5, 5);
        boardSetting = new JPanel(gridLayout);
        initBoardSetting();
        gameSetting = new JPanel(gridLayout);
        initGameSetting();
        addTabbedPane();
        addButtons();
        // setup
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private static void add(JPanel panel, Component... components) {
        Arrays.stream(components).forEach(panel::add);
    }

    private JComboBox<Integer> getNumberCombo() {
        Integer[] nums = new Integer[]{Constraints.NUM_OF_PIECES, 4, 2};
        JComboBox<Integer> cbb = new JComboBox<>(nums);
        cbb.setSelectedIndex(0);
        cbb.addItemListener(listener);
        return cbb;
    }

    private JFormattedTextField getFormattedField(int defaultValue) {
        NumberFormatter formatter = new NumberFormatter();
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);
        JFormattedTextField res = new JFormattedTextField(formatter);
        res.setValue(defaultValue);
        return res;
    }

    private void initBoardSetting() {
        boardSetting.setBorder(padding);
        JLabel numOfPiece = getResizedJLabel("Number of Pieces: ");
        JComboBox<Integer> cbb = getNumberCombo();
        JLabel x = getResizedJLabel("Max X: ");
        xVal = getFormattedField(Constraints.MAX_X);
        JLabel y = getResizedJLabel("Max Y: ");
        yVal = getFormattedField(Constraints.MAX_Y);
        JLabel cornerX = getResizedJLabel("Corner X: ");
        corXVal = getFormattedField(Constraints.CORNER_X);
        JLabel cornerY = getResizedJLabel("Corner Y: ");
        corYVal = getFormattedField(Constraints.CORNER_Y);
        add(boardSetting, numOfPiece, cbb);
        add(boardSetting, x, xVal);
        add(boardSetting, y, yVal);
        add(boardSetting, cornerX, corXVal);
        add(boardSetting, cornerY, corYVal);
    }

    private JLabel getResizedJLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(label.getFont().deriveFont(14f));
        return label;
    }

    private void initGameSetting() {
        gameSetting.setBorder(padding);
        JLabel obstacles = getResizedJLabel("Enable Obstacles: ");
        JCheckBox checkBox = new JCheckBox("", true);
        checkBox.addItemListener(listener);

        JLabel timeOut = getResizedJLabel("Time out (ms): ");
        time = getFormattedField(Constraints.TIME_OUT);

        add(gameSetting, obstacles, checkBox);
        addOptions("Rule: ", RULE_A, RULE_B);
        add(gameSetting, timeOut, time);
        addOptions("Initial Team: ", (Object[]) Team.values());
    }

    /**
     * @param text
     * @param options
     * @Requires options.length >= 1
     */
    private void addOptions(String text, Object... options) {
        JLabel optText = getResizedJLabel(text);
        ButtonGroup group = new ButtonGroup();
        boolean selected = true;
        for (Object option : options) {
            JRadioButton opt = new JRadioButton(option.toString(), selected);
            opt.addActionListener(listener);
            group.add(opt);
            add(gameSetting, selected ? optText : new JLabel(), opt);
            selected = false;
        }
    }

    private void addTabbedPane() {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Game Setting", gameSetting);
        tabbedPane.addTab("Board Setting", boardSetting);
        add(tabbedPane, BorderLayout.CENTER);
    }

    private void addButtons() {
        Border padding = BorderFactory.createEmptyBorder(5, 50, 5, 50);
        JPanel panel = new JPanel();
        panel.setBorder(padding);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        JButton start = new JButton(START_COMMAND);
        start.addActionListener(listener);
        JButton exit = new JButton(EXIT_COMMAND);
        exit.addActionListener(listener);
        Component mid = Box.createHorizontalGlue();
        add(panel, start, mid, exit);
        add(panel, BorderLayout.SOUTH);
    }

    public int getMaxX() {
        return (int) xVal.getValue();
    }

    public int getMaxY() {
        return (int) yVal.getValue();
    }

    public int getCornerX() {
        return (int) corXVal.getValue();
    }

    public int getCornerY() {
        return (int) corYVal.getValue();
    }

    public int getTimeOut() {
        return (int) time.getValue();
    }
}
