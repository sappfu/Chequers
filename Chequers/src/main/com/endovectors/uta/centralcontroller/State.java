package com.endovectors.uta.centralcontroller;


public class State {
	private Board board;
	private Move nextMove;
	private boolean grip;

	public State(){
		board = new Board();
		nextMove = null;
		grip = false;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public Move getNextMove() {
		return nextMove;
	}

	public void setNextMove(Move nextMove) {
		this.nextMove = nextMove;
	}

	public boolean isGrip() {
		return grip;
	}

	public void setGrip(boolean grip) {
		this.grip = grip;
	}
	
	
}

