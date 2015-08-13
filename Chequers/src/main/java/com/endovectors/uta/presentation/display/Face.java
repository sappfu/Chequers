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
		
		int height = getHeight();
		int width = getWidth();
		
		g.setColor(Color.YELLOW);
		g.fillOval(width / 10, height / 10, width - width / 10, height - height / 10);
		g.setColor(Color.WHITE);
		
		//g.fillRect(width / 4, height / 10 + height / 4, width / 10, height / 60); // left eye
		g.fillOval(width / 4, height / 10 + height / 4, width / 20, height / 20);
		//g.fillRect(width - width / 4, height / 10 + height / 4, width / 10, height / 60); // right eye
		g.fillOval(width - width / 4, height / 10 + height / 4, width / 20, height / 20);
		
		g.setColor(Color.BLACK);
		
		
		g.fillOval(width / 4, height / 10 + height / 4, width / 40, height / 40); // left pupil
		g.fillOval(width - width / 4, height / 10 + height / 4, width / 40, height / 40); // right pupil
		
		//g.fillRect(width / 4, height / 2 + height / 8, width / 2 + width / 10, height/ 75); // mouth
		//g.setColor(Color.BLACK);
		g.fillArc(width / 4, height / 2 + height / 8, width / 2 + width / 10, height / 5, 0, -180); // mouth
	}
}
