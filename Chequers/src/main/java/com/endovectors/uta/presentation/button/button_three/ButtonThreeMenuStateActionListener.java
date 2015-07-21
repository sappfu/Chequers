package com.endovectors.uta.presentation.button.button_three;

import com.endovectors.uta.presentation.button.ButtonStatesEnum;
import com.endovectors.uta.presentation.controller.PresentationRequestHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by asham_000 on 7/19/2015.
 */
public class ButtonThreeMenuStateActionListener implements ActionListener {

    PresentationRequestHandler presentationRequestHandler;
    ButtonStatesEnum state;

    public ButtonThreeMenuStateActionListener(PresentationRequestHandler presentationRequestHandler){
        this.presentationRequestHandler = presentationRequestHandler;
        state = ButtonStatesEnum.MENU_STATE;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(state) {
            case MENU_STATE:
                System.out.println("Button three pressed in Menu State");
                break;
            case PLAY_STATE:
                System.out.println("Button three pressed in Play State");
                presentationRequestHandler.endGame();
                break;
            case WAIT_STATE:
                presentationRequestHandler.endGame();
                break;
        }
    }

    public void setState(ButtonStatesEnum state){
        this.state = state;
    }
}
