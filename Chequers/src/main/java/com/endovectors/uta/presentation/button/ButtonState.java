package com.endovectors.uta.presentation.button;

import com.endovectors.uta.presentation.button.button_one.ButtonOne;
import com.endovectors.uta.presentation.button.button_one.ButtonOneMenuStateActionListener;
import com.endovectors.uta.presentation.button.button_three.ButtonThree;
import com.endovectors.uta.presentation.button.button_three.ButtonThreeMenuStateActionListener;
import com.endovectors.uta.presentation.button.button_two.ButtonTwo;
import com.endovectors.uta.presentation.button.button_two.ButtonTwoMenuStateActionListener;
import com.endovectors.uta.presentation.controller.PresentationRequestHandler;

import javax.swing.*;

/**
 * Created by asham_000 on 7/19/2015.
 */
public class ButtonState extends JPanel {

    ButtonStatesEnum state = ButtonStatesEnum.MENU_STATE;
    ButtonOne buttonOne;
    ButtonOneMenuStateActionListener buttonOneMenuStateActionListener;
    ButtonTwo buttonTwo;
    ButtonTwoMenuStateActionListener buttonTwoMenuStateActionListener;
    ButtonThree buttonThree;
    ButtonThreeMenuStateActionListener buttonThreeMenuStateActionListener;
    ButtonDecoder buttonDecoder;

    public ButtonState(PresentationRequestHandler presentationRequestHandler){
        this.buttonOne = new ButtonOne("Start Game");
        this.buttonTwo = new ButtonTwo("Continue Game");
        this.buttonThree = new ButtonThree("Demo Mode");
        this.buttonOneMenuStateActionListener = new ButtonOneMenuStateActionListener(presentationRequestHandler);
        buttonOne.addActionListener(buttonOneMenuStateActionListener);
        this.add(buttonOne);
        this.buttonTwoMenuStateActionListener = new ButtonTwoMenuStateActionListener(presentationRequestHandler);
        buttonTwo.addActionListener(buttonTwoMenuStateActionListener);
        this.add(buttonTwo);
        this.buttonThreeMenuStateActionListener = new ButtonThreeMenuStateActionListener(presentationRequestHandler);
        buttonThree.addActionListener(buttonThreeMenuStateActionListener);
        this.add(buttonThree);
        buttonDecoder = new ButtonDecoder(this.buttonOne, this.buttonTwo, this.buttonThree);
    }

    public void setState(ButtonStatesEnum state){
        this.state = state;
        buttonOne.setState(state);
        buttonTwo.setState(state);
        buttonThree.setState(state);
        buttonOneMenuStateActionListener.setState(state);
        buttonTwoMenuStateActionListener.setState(state);
        buttonThreeMenuStateActionListener.setState(state);
    }
}
