package com.endovectors.uta.presentation.button.button_one;

import javax.swing.*;

/**
 * Created by asham_000 on 7/19/2015.
 */
public class ButtonOne extends JButton{

    public void setMenuState(){
        this.setText("Start Game");
    }

    public void setGameStartedState(){
        this.setText("End Game");
    }
}
