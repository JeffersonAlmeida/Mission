package com.nasa.mission.command;

import com.nasa.mission.model.Rover;

public class SpinRightCommand implements ICommand {

	private Rover rover;
	
	public SpinRightCommand(Rover r) {
		this.rover = r;
	}
	
	@Override
	public void execute() {
		System.out.println("\tR -> " + rover.position());
		rover.right();
		System.out.println("\t     " + rover.position() + "\n");
	}
	
}