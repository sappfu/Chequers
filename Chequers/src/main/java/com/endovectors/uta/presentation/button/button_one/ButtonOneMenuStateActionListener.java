package com.endovectors.uta.presentation.button.button_one;

import com.endovectors.uta.presentation.button.ButtonStatesEnum;
import com.endovectors.uta.presentation.controller.PresentationRequestHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by asham_000 on 7/5/2015.
 */
public class ButtonOneMenuStateActionListener implements ActionListener{

    PresentationRequestHandler presentationRequestHandler;
    ButtonStatesEnum state;

    public ButtonOneMenuStateActionListener(PresentationRequestHandler presentationRequestHandler){
        this.presentationRequestHandler = presentationRequestHandler;
        state = ButtonStatesEnum.MENU_STATE;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(state) {
            case MENU_STATE:
                System.out.println("Button One pressed in Menu State");
                presentationRequestHandler.startGame();

                break;
            case PLAY_STATE:
                System.out.println("Button One pressed in Play State");
                presentationRequestHandler.endTurn();
                break;
        }
    }

    public void setState(ButtonStatesEnum state){
        this.state = state;
    }
}
