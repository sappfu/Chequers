package com.endovectors.uta.presentation.controller;


// It appears that this class is not needed


public class Instruction
{
	private String destinations[];
	String text;
	
	public Instruction(String dest, String t)
	{
		String parts[] = dest.split(",");
		for (int i = 0; i < parts.length; i++)
		{
			destinations[i] = parts[i];
		}
		
		text = t;
	}
	
	String[] getDestinations()
	{
		return this.destinations;
	}
	
	String getText()
	{
		return this.text;
	}
	
	void setDestinations(String dest[])
	{
		for (int i = 0; i < dest.length; i++)
		{
			destinations[i] = dest[i];
		}
	}
	
	void setText(String t)
	{
		text = t;
	}
}
