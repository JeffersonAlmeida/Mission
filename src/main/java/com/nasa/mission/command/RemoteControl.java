package com.nasa.mission.command;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jefferson
 * 
 * Command Design Pattern
 * RemoteControl Encapsulate a request (Execute Instructions) as an object
 * 
 */
public class RemoteControl {
	
	private List<ICommand> commands;
	
	public RemoteControl() {
		this.commands = new ArrayList<ICommand>();
	}
	
	/**
	 * Add a command in the commands list
	 * 
	 * @param c
	 */
	public void addCommand(ICommand c){
		this.commands.add(c);
	}
	
	/**
	 * Execute all commands
	 */
	public void exploreButtonWasPressed(){
		this.commands.forEach((command) -> command.execute());
	}
	
}
