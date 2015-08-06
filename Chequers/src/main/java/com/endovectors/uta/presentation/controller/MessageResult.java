package main.java.com.endovectors.uta.presentation.controller;

public class MessageResult
{
	private String move;
	private String prompt;
	
	public MessageResult(String m, String p)
	{
		move = m;
		prompt = p;
	}
	
	String getMove()
	{
		return this.move;
	}
	
	String getPrompt()
	{
		return this.prompt;
	}
	
	void setMove(String m)
	{
		this.move = m;
	}
	
	void setPrompt(String p)
	{
		this.prompt = p;
	} 
}
