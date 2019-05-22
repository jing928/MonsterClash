package grp.oozmakappa.monsterclash.model;

public enum Team {
    OozmaKappa,
    RoarOmegaRoar;

    public static final String OOZMA_KAPPA = "OozmaKappa";
    public static final String ROAR_OMEGA_ROAR = "RoarOmegaRoar";

    public static Team getRivalTeam(Team team) {
        return team == OozmaKappa ? RoarOmegaRoar : OozmaKappa;
    }
}
