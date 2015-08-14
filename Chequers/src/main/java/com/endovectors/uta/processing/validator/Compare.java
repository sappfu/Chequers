package com.endovectors.uta.processing.validator;

import com.endovectors.uta.processing.CheckersBoard;
import com.endovectors.uta.processing.List;
import com.endovectors.uta.processing.Move;

import java.util.ArrayList;
import java.util.Enumeration;

/**
 * Created by asham_000 on 7/5/2015.
 */
public class Compare implements CompareInterface{

    private CheckersBoard previousBoard;

    public Compare() {
        previousBoard = new CheckersBoard();
    }

    public Compare(CheckersBoard board) {
        this.previousBoard = board;
    }

    public boolean compare(CheckersBoard board) {
    	return compareMoveToList(this.checkBoard(board), board);
    }

    private Move checkBoard(CheckersBoard currentBoard) {
        Move move = new Move();
        byte[] currentBoardArray = currentBoard.getPieces();
        byte[] oldBoardArray = previousBoard.getPieces();
        for (int i = 0; i < 32; i++) {
            if (oldBoardArray[i] == 0 && currentBoardArray[i] == 2 || currentBoardArray[i] == 3) {
                move.setTo(i);
            }
            if ((oldBoardArray[i] == 2 || oldBoardArray[i] == 3) && currentBoardArray[i] == 0) {
                move.setFrom(i);
            }
        }
        return move;
    }

    private boolean compareMoveToList(Move move, CheckersBoard board){
        boolean resultTo = false;
        boolean resultFrom = false;
        previousBoard.setCurrentPlayer(2);
        List allMoves = previousBoard.legalMoves();
        while (!allMoves.isEmpty()) {
            List moves = (List) allMoves.pop_front();
            Move temp;
            Enumeration enu = moves.elements();
            while (enu.hasMoreElements()) {
                temp = (Move) enu.nextElement();
                if (temp.getFrom() == move.getFrom())
                    resultFrom = true;
                if (temp.getTo() == move.getTo())
                    resultTo = true;
            }
        }
        if (resultTo && resultFrom)
        	previousBoard = board;
        return resultTo&&resultFrom;
    }
    
    public CheckersBoard checkKings(CheckersBoard board)
    {
    	byte[] currentBoard = board.getPieces();
    	byte[] oldBoard = previousBoard.getPieces();
    	CheckersBoard checkersBoard = new CheckersBoard();
        for (int i = 0; i < 32; i++)
    	{
    		if (currentBoard[i] == CheckersBoard.WHITE && i > 27)
    			currentBoard[i] = CheckersBoard.WHITE_KING;
    		if (oldBoard[i] == CheckersBoard.WHITE && i > 27)
    			oldBoard[i] = CheckersBoard.WHITE_KING;
    		if (currentBoard[i] == CheckersBoard.BLACK && i < 4)
    			currentBoard[i] = CheckersBoard.BLACK_KING;
    		if (oldBoard[i] == CheckersBoard.BLACK && i < 4)
    			oldBoard[i] = CheckersBoard.BLACK_KING;
    		if (oldBoard[i] == CheckersBoard.WHITE_KING && currentBoard[i] == CheckersBoard.WHITE)
    			currentBoard[i]++;
    		if (oldBoard[i] == CheckersBoard.BLACK_KING && currentBoard[i] == CheckersBoard.BLACK)
    			currentBoard[i]++;
    		if (oldBoard[i] == CheckersBoard.WHITE_KING && currentBoard[i] == 0)
    		{
    			for (int j = 0; j < 32; j++)
    			{
    				if (j == i)
    					continue;
    				else
    					if (oldBoard[j] == 0 && currentBoard[j] == CheckersBoard.WHITE)
    					{
    						currentBoard[j]++;
    						break;
    					}
    			}
    		}
    		/*if (oldBoard[i] == CheckersBoard.BLACK_KING && currentBoard[i] == 0)
    		{
    			for (int j = 0; j < 32; j++)
    			{
    				if (j == i)
    					continue;
    				else
    					if (oldBoard[j] == 0 && currentBoard[j] == CheckersBoard.BLACK)
    					{
    						currentBoard[j]++;
    						break;
    					}
    			}
    		}*/
    	}
    	checkersBoard.setPieces(currentBoard);
    	//previousBoard = board;
    	return checkersBoard;
    }
}