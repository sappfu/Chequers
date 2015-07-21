package com.endovectors.uta.processing;

public class Player {
	private String color;
	private int pieces;
	
	public Player(String color){
		this.color = color;
		pieces = 12;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getPieces() {
		return pieces;
	}

	public void setPieces(int pieces) {
		this.pieces = pieces;
	}
	
	
}
