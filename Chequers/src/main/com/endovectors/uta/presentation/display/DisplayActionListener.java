package com.endovectors.uta.presentation.display;

import com.endovectors.uta.presentation.controller.PresentationRequestHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by asham_000 on 7/5/2015.
 */
public class DisplayActionListener implements ActionListener{

    PresentationRequestHandler presentationRequestHandler;

    public DisplayActionListener(PresentationRequestHandler presentationRequestHandler){
        this.presentationRequestHandler = presentationRequestHandler;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        presentationRequestHandler.buttonClicked(e);
    }
}
