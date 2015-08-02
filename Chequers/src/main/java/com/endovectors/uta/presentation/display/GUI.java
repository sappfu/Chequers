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
        
        this.setLayout(new GridLayout());
        
        
        
        view = new BoardView(this, new CheckersBoard());
        //add(view);
        buttonState = new ButtonState(presentationRequestHandler);
        //add(buttonState);
        
        JPanel both = new JPanel(new GridLayout(1,2));        
        
        JPanel face = new JPanel();
        face.add(new Face());
        
        JPanel rightHalf = new JPanel(new BorderLayout());
        rightHalf.add(buttonState, BorderLayout.NORTH);
        rightHalf.add(view);
        both.add(new Face());
        both.add(rightHalf);
        
        cl = new CardLayout();
        cards = new JPanel(cl);
        cards.add(both, "Both");
        cards.add(face, "Face");
        
        cl.show(cards, "Both");
        
        //getContentPane().add(cards);
        add(cards);
        
        this.setSize(800, 800);
        setVisible(true);
        
    }

    public void setButtonState(ButtonStatesEnum state){
        buttonState.setState(state);
    }
    
    public BoardView getBoard()
    {
    	return this.view;
    }
    
    public void changeBoard(CheckersBoard b)
    {
    	Graphics g = this.getBoard().graph; // not sure this is right
    	int marginX = this.getBoard().startX;
    	int marginY = this.getBoard().startY;
    	int incValue = this.getBoard().cellWidth;
    	CheckersBoard board = b;
    	this.getBoard().drawPieces(g, marginX, marginY, incValue, board);
    }
    
    public void changeToFace()
    {
    	cl.show(cards, "Face");
    }
    
    public void changeToBoth()
    {
    	cl.show(cards, "Both");
    }
}
