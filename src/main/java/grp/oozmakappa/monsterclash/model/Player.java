package grp.oozmakappa.monsterclash.model;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;

public class Player {
    private Team team;
    private String name;
    private Piece[] pieces;
    private Piece currentSelectedPiece;

    public Player(String name, Team team) {
        this.name = name;
        this.team = team;
        this.pieces = new Piece[3];
    }

    /**
     * Used for setting the player name
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method should be used when the player is assigning
     * their monsters (before game starts)
     */
    public void setPiece(Piece piece, int pos) {
        this.pieces[pos] = piece;
    }

    /**
     * This method is used when the player is selected on the active
     * board a monster to use
     * It checks that the selected monster is in their "deck" and
     * returns true if it is in the deck
     */
    public boolean setCurPiece(Piece piece) {
        for (Piece curPiece : this.pieces) {
            if (curPiece.equals(piece)) {
                currentSelectedPiece = piece;
                return true;
            }
        }
        return false;
    }

    /**
     * Returns Id
     */

    public Team getTeam() {
        return team;
    }

    /**
     * Returns name
     */

    public String getName() {
        return name;
    }

    /**
     * Returns current selected piece
     */

    public Piece getCurPiece() {
        return currentSelectedPiece;
    }

    /**
     * Returns the player's deck
     */

    public Piece[] getAllPieces() {
        return pieces;
    }
}
