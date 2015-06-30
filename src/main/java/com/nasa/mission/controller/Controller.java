package com.nasa.mission.controller;

import java.util.List;

import com.nasa.mission.command.CommandFactory;
import com.nasa.mission.command.RemoteControl;
import com.nasa.mission.model.Plateau;
import com.nasa.mission.model.Rover;
import com.nasa.mission.model.state.Context;

public class Controller {
	
	private Plateau plateau;
	private Context context;
	
	public Controller(Plateau plateau) {
		super();
		this.plateau = plateau;
		this.context = new Context(plateau);
	}

	/**
	 * Land all rovers on plateau.
	 * In other words: Add all rovers to the plateaus' list of rovers
	 * @param rovers Rovers List
	 */
	public void landAllRovers(List<Rover> rovers) {
		this.plateau.receiveRovers(rovers);
	}
	
	/**
	 * Iterate over all rovers and execute their instructions sequentially.
	 */
	public void explorePlateau() {
		List<Rover> roversList = plateau.getRovers();
		for (int i = 0; i < roversList.size(); i++){
			Rover r = roversList.get(i);
			System.out.println("Exploring Plateau With " + r + "\n");
			executeInstructionsOn(r);
		}
	}
	
	private void executeInstructionsOn(Rover r) {
		RemoteControl remoteControl = CommandFactory.buildRemoteControl(r, context);
		remoteControl.exploreButtonWasPressed();
	}
	
	/**
	 * Show rovers list
	 */
	public void print() {
		List<Rover> roversList = plateau.getRovers();
		System.out.println("\nOutput :: \n");
		for (int i = 0; i < roversList.size(); i++){
			Rover r = roversList.get(i);
			System.out.println("\t" + r.position());
		}
	}
}
