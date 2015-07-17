package com.endovectors.uta.presentation.display;

import javax.swing.*;

/**
 * Created by asham_000 on 7/5/2015.
 */
public class GUI extends JFrame {

    public GUI(DisplayActionListener displayActionListener) {
        JLabel jlabel = new JLabel("Chequers System");
        add(jlabel);
        JButton jbutton = new JButton("Test button");
        jbutton.addActionListener(displayActionListener);
        add(jbutton);

        this.setSize(600, 800);
        setVisible(true);
    }

}
