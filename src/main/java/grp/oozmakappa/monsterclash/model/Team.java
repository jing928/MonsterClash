package grp.oozmakappa.monsterclash.model;

public enum Team {
    OozmaKappa,
    RoarOmegaRoar;

    public static Team getRivalTeam(Team team) {
        return team == OozmaKappa ? RoarOmegaRoar : OozmaKappa;
    }
}
