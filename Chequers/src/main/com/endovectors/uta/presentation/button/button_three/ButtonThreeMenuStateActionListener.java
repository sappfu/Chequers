package com.endovectors.uta.presentation.button.button_three;

import com.endovectors.uta.presentation.controller.PresentationRequestHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by asham_000 on 7/19/2015.
 */
public class ButtonThreeMenuStateActionListener implements ActionListener {

    PresentationRequestHandler presentationRequestHandler;

    public ButtonThreeMenuStateActionListener(PresentationRequestHandler presentationRequestHandler){
        this.presentationRequestHandler = presentationRequestHandler;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Button3 pressed");
    }
}
