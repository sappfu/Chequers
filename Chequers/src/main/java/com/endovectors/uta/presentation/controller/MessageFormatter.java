package com.endovectors.uta.presentation.controller;

import com.endovectors.uta.presentation.controller.Instruction;

/**
 * Created by asham_000 on 7/5/2015.
 */
public class MessageFormatter
{
	private MessageResult result = null;
	//private Instruction instruction = null;
	// add more?
	
	public MessageFormatter(MessageResult m/*, Instruction inst*/)
	{
		result = m;
		//instruction = inst;
		
		result.setMove(m.getMove());
		//result.setPrompt(m.getPrompt());
		
		//instruction.setDestinations(inst.getDestinations());
		//instruction.setText(inst.getText());
	}
	
	/*public MessageFormatter(Instruction inst)
	{
		instruction = inst;
		
		instruction.setDestinations(inst.getDestinations());
		instruction.setText(inst.getText());
	}*/
	
	public MessageResult getResult()
	{
		return this.result;
	}
	
	/*public Instruction getInstruction()
	{
		return this.instruction;
	}*/
	/*
	public void format()
	{
		// what should be added?
	}*/
	
	public static void main(String args[])
	{
		
	}
}
