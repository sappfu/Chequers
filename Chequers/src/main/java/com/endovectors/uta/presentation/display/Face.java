package com.endovectors.uta.presentation.display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Face extends JPanel
{	
	public Face()
	{
		repaint();
	}
	
	public void paintComponent(Graphics g)
	{
		Dimension d = getSize ();
		
		g.setColor(Color.lightGray);
		g.fillOval(d.width / 2, d.height / 2, d.width, d.height);
		g.setColor(Color.RED);
		g.fillRect(d.width / 2 - d.width / 3, d.height / 2 + d.height / 2, d.width / 5, d.height / 12); // left eye
		g.fillRect(d.width / 2 + d.width / 3, d.height / 2 + d.height / 2, d.width / 5, d.height / 12); // right eye
		g.drawRect(d.width, d.height - d.height / 3, d.width / 2, d.height/ 12); // mouth
	}
}
