package com.endovectors.uta.presentation.button.button_two;

import com.endovectors.uta.presentation.button.ButtonStatesEnum;

import java.awt.Color;

import javax.swing.*;

/**
 * Created by asham_000 on 7/19/2015.
 */
public class ButtonTwo extends JButton {

    public ButtonTwo(String text){
        super(text);
        setBackground(Color.GREEN);
        setBorder(null);
    }

    public void setState(ButtonStatesEnum state){
        switch(state){
            case MENU_STATE:
                this.setText("Continue Game");
                break;
            case PLAY_STATE:
                this.setText("Pause Game");
                break;
        }
    }
}
