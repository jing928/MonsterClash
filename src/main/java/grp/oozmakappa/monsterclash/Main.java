package grp.oozmakappa.monsterclash;

import grp.oozmakappa.monsterclash.controller.MainController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOG = LogManager.getLogger();

    public static void main(String[] args) {
        MainController controller = new MainController();
        controller.run();

    }
}
