package com.nasa.mission.command;

import com.nasa.mission.model.Rover;

public class SpinLeftCommand implements ICommand {
    
	private Rover rover;
	
	public SpinLeftCommand(Rover r) {
		this.rover = r;
	}
    
	@Override
	public void execute() {
		System.out.println("\tL -> " + rover.position());
		this.rover.left();
		System.out.println("\t     " + rover.position() + "\n");
	}
}