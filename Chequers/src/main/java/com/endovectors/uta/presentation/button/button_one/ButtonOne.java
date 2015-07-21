package com.endovectors.uta.presentation.button.button_one;

import com.endovectors.uta.presentation.button.ButtonStatesEnum;

import javax.swing.*;

/**
 * Created by asham_000 on 7/19/2015.
 */
public class ButtonOne extends JButton{

    public ButtonOne(String text){
        super(text);
    }

    public void setState(ButtonStatesEnum state){
        switch(state){
            case MENU_STATE:
                this.setText("Start Game");
                break;
            case PLAY_STATE:
                this.setText("End Turn");
                break;
            case WAIT_STATE:
                this.setText("Please Wait");
        }
    }
}
