package com.endovectors.uta.presentation.controller;

public class MessageResult
{
	private int move;
	//private String prompt;
	
	public MessageResult(int m/*, String p*/)
	{
		move = m;
		//prompt = p;
	}
	
	int getMove()
	{
		return this.move;
	}
	
	/*String getPrompt()
	{
		return this.prompt;
	}*/
	
	void setMove(int m)
	{
		this.move = m;
	}
	
	/*void setPrompt(String p)
	{
		this.prompt = p;
	}*/
}
