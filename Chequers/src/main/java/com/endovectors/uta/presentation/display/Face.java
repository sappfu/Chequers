package com.endovectors.uta.presentation.display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Face extends JPanel
{	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		//Dimension d = getSize ();
		int height = getHeight();
		int width = getWidth();
		
		g.setColor(Color.lightGray);
		g.fillOval(width / 10, height / 10, width - width / 10, height - height / 10);
		g.setColor(Color.RED);
		g.fillRect(width / 4, height / 10 + height / 4, width / 10, height / 60); // left eye
		g.fillRect(width - width / 4, height / 10 + height / 4, width / 10, height / 60); // right eye
		g.fillRect(width / 4, height / 2 + height / 8, width / 2 + width / 10, height/ 75); // mouth
	}
}
