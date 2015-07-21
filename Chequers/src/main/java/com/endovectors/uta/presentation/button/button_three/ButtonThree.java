package com.endovectors.uta.presentation.button.button_three;

import com.endovectors.uta.presentation.button.ButtonStatesEnum;

import javax.swing.*;

/**
 * Created by asham_000 on 7/19/2015.
 */
public class ButtonThree extends JButton {

    public ButtonThree(String text){
        super(text);
    }

    public void setState(ButtonStatesEnum state){
        switch(state){
            case MENU_STATE:
                this.setText("Demo Mode");
                break;
            case PLAY_STATE:
                this.setText("End Game");
                break;
            case WAIT_STATE:
                break;
        }
    }
}
