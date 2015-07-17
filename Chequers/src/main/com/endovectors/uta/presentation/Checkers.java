/* Checkers.java : The main class
 * Copyright (C) 1998-2002  Paulo Pinto
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,
 * Boston, MA 02111-1307, USA.
 */
package com.endovectors.uta.presentation;

import java.util.*;

import java.awt.event.*;
import java.awt.BorderLayout;
import javax.swing.*;

public class Checkers extends JFrame implements ActionListener {

  private static ResourceBundle resources;
  private JMenuBar bar;
  private JMenuItem restartOption;
  private JMenuItem saveOption;
  private JMenuItem loadOption;  
  private JMenuItem exitOption;
  private JCheckBoxMenuItem debugOption;
  private BoardView view;

  
  /**
   * Constructor
   */
  public Checkers () {
    super ("Checkers");

    try {
      resources = ResourceBundle.getBundle("resources.checkers", 
                                            Locale.getDefault());
    } catch (MissingResourceException mre) {
        System.err.println("resources/Checkers.properties not found");
        System.exit(1);
    }

    view = new BoardView (this, new CheckersBoard (), resources);
    getContentPane().add (view, BorderLayout.CENTER);
    
    setDefaultCloseOperation (EXIT_ON_CLOSE);

    JMenu menu = new JMenu (resources.getString("fileLabel"));
    restartOption = new JMenuItem (resources.getString("newLabel"));
    restartOption.addActionListener (this);
    menu.add (restartOption);

    saveOption = new JMenuItem (resources.getString("saveLabel"));
    saveOption.addActionListener (this);
    menu.add (saveOption);

    loadOption = new JMenuItem (resources.getString("openLabel"));
    loadOption.addActionListener (this);
    menu.add (loadOption);    

    menu.addSeparator ();
    exitOption = new JMenuItem (resources.getString("exitLabel"));
    exitOption.addActionListener (this);
    menu.add (exitOption);

    JMenuBar bar = new JMenuBar ();
    bar.add (menu);

    menu = new JMenu (resources.getString("optionsLabel"));
    debugOption = new JCheckBoxMenuItem (resources.getString("debugLabel"));
    debugOption.addActionListener (this);
    menu.add (debugOption);
    bar.add (menu);

    setJMenuBar (bar);
  }

  public static void  main (String args []) {
    Checkers c = new Checkers ();

    c.setSize (300, 300);
    c.setVisible (true);    
  }

  public void actionPerformed (ActionEvent event) {
    JFileChooser dlg;
    
    if (event.getSource () == exitOption)
      System.exit (0);
    else if (event.getSource () == restartOption)
	view.newGame ();
    else if (event.getSource () == saveOption) {
	dlg = new JFileChooser ();
	if (dlg.showSaveDialog (this) == JFileChooser.APPROVE_OPTION) {
            String fileName = dlg.getSelectedFile ().getPath ();
	    view.saveBoard (fileName);
	}
    }
    else if (event.getSource () == loadOption) {
	dlg = new JFileChooser ();
	if (dlg.showOpenDialog (this) == JFileChooser.APPROVE_OPTION) {
	    String fileName = dlg.getSelectedFile ().getPath ();
	    view.loadBoard (fileName);
	}
    }
    else if (event.getSource () == debugOption)
	Debug.setDebug (debugOption.getState ());
  }
}