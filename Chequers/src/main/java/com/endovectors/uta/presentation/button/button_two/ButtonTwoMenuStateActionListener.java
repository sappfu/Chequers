package com.endovectors.uta.presentation.button.button_two;

import com.endovectors.uta.presentation.button.ButtonStatesEnum;
import com.endovectors.uta.presentation.controller.PresentationRequestHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by asham_000 on 7/19/2015.
 */
public class ButtonTwoMenuStateActionListener implements ActionListener {

    PresentationRequestHandler presentationRequestHandler;
    ButtonStatesEnum state;

    public ButtonTwoMenuStateActionListener(PresentationRequestHandler presentationRequestHandler) {
        this.presentationRequestHandler = presentationRequestHandler;
        state = ButtonStatesEnum.MENU_STATE;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(state) {
            case MENU_STATE:
                System.out.println("Button Two pressed in Menu State");
                presentationRequestHandler.continueGame();
                break;
            case PLAY_STATE:
                System.out.println("Button Two pressed in Play State");
                break;
            case WAIT_STATE:
                break;
        }
    }

    public void setState(ButtonStatesEnum state){
        this.state = state;
    }
}
