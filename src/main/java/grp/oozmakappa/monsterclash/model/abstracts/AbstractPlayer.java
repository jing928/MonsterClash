package grp.oozmakappa.monsterclash.model.abstracts;

public abstract class AbstractPlayer {
	String id;
	String name;
	Piece piece[];
	Piece currentSelectedPiece;
	
	public AbstractPlayer(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	/**
	 * Used for setting the player name
	 */
	
	boolean setName(String name) {
		this.name = name;
		return true;
	}
	/**
	 * This method should be used when the player is assigning
	 * their monsters (before game starts)
	 */
	boolean setPiece(Piece piece, int pos) {
		this.piece[pos] = piece;
		return true;
	}
	/**
	 * This method is used when the player is selected on the active
	 * board a monster to use
	 * It checks that the selected monster is in their "deck" and
	 * returns true if it is in the deck
	 */
	boolean setCurPiece(Piece piece) {
		for (Piece curPiece : this.piece) {
			if (curPiece.equals(piece)) {
				currentSelectedPiece = piece;
				return true;
			}
		}
		return false;
	}
	
}
