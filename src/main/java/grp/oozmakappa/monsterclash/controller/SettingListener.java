package grp.oozmakappa.monsterclash.controller;

import grp.oozmakappa.monsterclash.model.Constraints;
import grp.oozmakappa.monsterclash.model.Team;
import grp.oozmakappa.monsterclash.model.command.TurnChangeCommand;
import grp.oozmakappa.monsterclash.utils.NumberUtil;
import grp.oozmakappa.monsterclash.view.MenuDialog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import static grp.oozmakappa.monsterclash.model.Team.OOZMA_KAPPA;
import static grp.oozmakappa.monsterclash.model.Team.ROAR_OMEGA_ROAR;
import static grp.oozmakappa.monsterclash.model.rules.AbstractRuleFactory.RULE_A;
import static grp.oozmakappa.monsterclash.model.rules.AbstractRuleFactory.RULE_B;
import static grp.oozmakappa.monsterclash.view.MenuDialog.EXIT_COMMAND;
import static grp.oozmakappa.monsterclash.view.MenuDialog.START_COMMAND;

/**
 * @author Chenglong Ma
 */
public class SettingListener implements ItemListener, ActionListener {
    private static final Logger LOG = LogManager.getLogger();
    private final Constraints constraints = Constraints.getInstance();
    private final MenuDialog dialog;

    public SettingListener(MenuDialog dialog) {
        this.dialog = dialog;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        Object src = e.getSource();
        boolean selected = e.getStateChange() == ItemEvent.SELECTED;
        if (src instanceof JCheckBox) {
            constraints.enableObstacle(selected);
        }
        if (src instanceof JComboBox && selected) {
            constraints.setNumOfPieces((int) e.getItem());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd) {
            case START_COMMAND:
                loadSetting();
                TurnChangeCommand.changeTurn();
                MainController.startGame();
                dialog.dispose();
                return;
            case EXIT_COMMAND:
                System.exit(0);
                return;
            case RULE_A:
            case RULE_B:
                constraints.setCurrentRule(cmd);
                return;
            case OOZMA_KAPPA:
            case ROAR_OMEGA_ROAR:
                Team initTeam = Team.valueOf(cmd);
                constraints.setInitialTeam(initTeam);
                return;
        }
    }

    private void loadSetting() {
        int numOfPieces = constraints.getNumOfPieces();
        int maxX = dialog.getMaxX();
        assert maxX != 0;
        constraints.setMaxX(maxX);
        int maxY = dialog.getMaxY();
        assert 2 * maxY > numOfPieces;
        constraints.setMaxY(maxY);
        int corX = dialog.getCornerX();
        assert NumberUtil.between(corX, 0, maxX);
        constraints.setCornerX(corX);
        int corY = dialog.getCornerY();
        assert NumberUtil.between(corY, 0, maxY);
        constraints.setCornerY(corY);
        int time = dialog.getTimeOut();
        constraints.setTimeOut(time);
    }
}
