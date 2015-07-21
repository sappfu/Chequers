package com.endovectors.uta.presentation.display;

import com.endovectors.uta.presentation.Checkers;
import com.endovectors.uta.presentation.button.ButtonState;
import com.endovectors.uta.presentation.button.ButtonStatesEnum;
import com.endovectors.uta.presentation.button.button_one.ButtonOneMenuStateActionListener;
import com.endovectors.uta.presentation.button.button_three.ButtonThreeMenuStateActionListener;
import com.endovectors.uta.presentation.button.button_two.ButtonTwoMenuStateActionListener;
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

    public GUI(PresentationRequestHandler presentationRequestHandler) {
        super ("Checkers");
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
    }

    public void setButtonState(ButtonStatesEnum state){
        buttonState.setState(state);
    }

}
