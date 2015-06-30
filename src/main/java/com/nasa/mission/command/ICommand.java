package com.nasa.mission.command;

/**
 * @author Jefferson
 * 
 * Command Design Pattern
 * Issue requests to objects without knowing anything about the operation being requested.
 *  
 */
public interface ICommand {
	void execute();
}
