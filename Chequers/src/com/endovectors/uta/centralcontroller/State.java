package com.endovectors.uta.centralcontroller;

import com.endovectors.uta.checkersai.CheckersBoard;
import com.endovectors.uta.checkersai.Move;

public class State {
	private CheckersBoard board;
	private Move nextMove;
	private boolean grip;

	public State(){
		board = new CheckersBoard();
		nextMove = null;
		grip = false;
	}

	public CheckersBoard getBoard() {
		return board;
	}

	public void setBoard(CheckersBoard board) {
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

