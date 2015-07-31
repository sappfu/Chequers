package com.endovectors.uta.presentation.display;

import com.endovectors.uta.presentation.button.ButtonState;
import com.endovectors.uta.presentation.button.ButtonStatesEnum;
import com.endovectors.uta.presentation.controller.PresentationRequestHandler;
import com.endovectors.uta.processing.CheckersBoard;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

/**
 * Created by asham_000 on 7/5/2015.
 */
public class GUI extends JFrame {

    private static ResourceBundle resources;
    BoardView view;
    ButtonState buttonState;
    JPanel cards;
    CardLayout cl;

    public GUI(PresentationRequestHandler presentationRequestHandler) {
        super ("Checkers");
        /*
        this.setLayout(new GridLayout());
        //GridBagConstraints c = new GridBagConstraints();
        JLabel jlabel = new JLabel("Chequers System");
        add(jlabel);
        view = new BoardView (this, new CheckersBoard());
        buttonState = new ButtonState(presentationRequestHandler);
        getContentPane().add(buttonState);
        getContentPane().add (view);

        this.setSize(600, 800);
        setVisible(true);
        */
        
        //this.setLayout(new CardLayout());
        
        view = new BoardView(this, new CheckersBoard());
        buttonState = new ButtonState(presentationRequestHandler);
        
        JPanel both = new JPanel(new GridLayout(1,0));
        JPanel rightHalf = new JPanel();
        JPanel face = new JPanel();
        face.add(new Face());
        rightHalf.add(buttonState);
        rightHalf.add(view);
        both.add(face);
        both.add(rightHalf);
        cards = new JPanel(new CardLayout());
        cards.add(both, "Both");
        cards.add(face, "Face");
        
        cl = (CardLayout)cards.getLayout();
        cl.show(cards, "Both");
        
        getContentPane().add(cards);
        
        this.setSize(600, 800);
        setVisible(true);
    }

    public void setButtonState(ButtonStatesEnum state){
        buttonState.setState(state);
    }
    
    public BoardView getBoard()
    {
    	return this.view;
    }

    // create method to change display
    // probably not necessary; could do this in board view
    public void changeDisplay(CheckersBoard b)
    {
    	Graphics g = this.getBoard().graph;
    	int marginX = this.getBoard().startX;
    	int marginY = this.getBoard().startY;
    	int incValue = this.getBoard().cellWidth;
    	CheckersBoard board = b;
    	this.getBoard().drawPieces(g, marginX, marginY, incValue, board);
    	
    	cl.show(cards, "Face"); // show face for when it is talking
    	
    	
    	// not sure this is the right way to do it
    	try
    	{
    	    Thread.sleep(5000); // let face "talk" for 5 seconds
    	}
    	catch(InterruptedException ex)
    	{
    	    Thread.currentThread().interrupt();
    	}
    	
    	cl.show(cards, "Both"); // go back to show board and face
    }
}
