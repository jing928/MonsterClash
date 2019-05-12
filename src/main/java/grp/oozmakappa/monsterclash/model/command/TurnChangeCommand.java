//package grp.oozmakappa.monsterclash.model.command;
//
//import grp.oozmakappa.monsterclash.controller.BoardController;
//import grp.oozmakappa.monsterclash.model.Team;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//public class TurnChangeCommand implements Command {
//    private final BoardController boardController;
//    private final Team prevTeam;
//
//    public TurnChangeCommand(BoardController boardController) {
//        this.boardController = boardController;
//        Team currTeam = boardController.getCurrTeam();
//        // Default team is Oozma Kappa
//        this.prevTeam = currTeam == null ? Team.OozmaKappa : currTeam;
//    }
//
//    @Override
//    public void execute() {
//        changeTurn();
//    }
//
//    @Override
//    public void undo() {
//        boardController.setCurrTeam(prevTeam);
//        boardController.setCanMove(false);
//        Logger log = LogManager.getLogger();
//        log.info("Current turn: " + prevTeam);
//    }
//
//    private void changeTurn() {
//        Team currTeam = boardController.getCurrTeam();
//        if (currTeam == null) {
//            currTeam = Team.OozmaKappa;
//        } else {
//            currTeam = currTeam == Team.OozmaKappa
//                    ? Team.RoarOmegaRoar
//                    : Team.OozmaKappa;
//        }
//        boardController.setCurrTeam(currTeam);
//        boardController.setCanMove(false);
//        Logger log = LogManager.getLogger();
//        log.info("Current turn: " + currTeam);
//    }
//
//}
