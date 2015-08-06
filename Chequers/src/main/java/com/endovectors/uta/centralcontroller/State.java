package com.endovectors.uta.centralcontroller;


import com.endovectors.uta.persistence.FileStorage;
import com.endovectors.uta.processing.CheckersBoard;
import com.endovectors.uta.processing.Move;
import com.endovectors.uta.processing.MoveInterface;

import java.util.ArrayList;
import java.util.Observable;

public class State extends Observable {

	private CheckersBoard board;
	private ArrayList<MoveInterface> nextMoves;
	private boolean grip;
	FileStorage fileStorage;

	public State(){
		board = new CheckersBoard();
		grip = false;
		fileStorage = new FileStorage();
		this.addObserver(fileStorage);
	}

	public CheckersBoard getBoard() {
		return board;
	}

	public void setBoard(CheckersBoard board) {
		this.board = board;
		//TODO override toString method of board
		setChanged();
		notifyObservers(this.board.toString());
	}

	public ArrayList<MoveInterface> getNextMoves() {
		return nextMoves;
	}

	public void setNextMoves(ArrayList<MoveInterface> nextMoves) {
		this.nextMoves = nextMoves;
		for (MoveInterface move : this.nextMoves){
			setChanged();
			notifyObservers(move.toString());
		}
	}

	public boolean isGrip() {
		return grip;
	}

	public void setGrip(boolean grip) {
		this.grip = grip;
		setChanged();
		notifyObservers("" + grip);
	}

	public void stopWriter(){
		fileStorage.closeFile();
	}
	
	
}
