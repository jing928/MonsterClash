package grp.oozmakappa.monsterclash.model;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;

public class Player {
	private String id;
	private String name;
	private Piece pieces[];
	private Piece currentSelectedPiece;
	
	public Player(String id, String name) {
		this.id = id;
		this.name = name;
		this.pieces = new Piece[3];
	}
	
	/**
	 * Used for setting the player name
	 */
	
	public boolean setName(String name) {
		this.name = name;
		return true;
	}
	/**
	 * This method should be used when the player is assigning
	 * their monsters (before game starts)
	 */
	public boolean setPiece(Piece piece, int pos) {
		this.pieces[pos] = piece;
		return true;
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
	
	public String getId() {
		return id;
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
	
}
