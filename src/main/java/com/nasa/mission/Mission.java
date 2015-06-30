package com.nasa.mission;

import java.util.List;
import com.nasa.mission.controller.Controller;
import com.nasa.mission.input.MissionInputReader;
import com.nasa.mission.model.Plateau;
import com.nasa.mission.model.Rover;

public class Mission {
	
	private List<Rover> rovers;
	private Controller controller;
	private MissionInputReader missionInputReader;
	
	public Mission(){
		missionInputReader = new MissionInputReader();
		Plateau plateau = missionInputReader.getPlateau();
		this.rovers = missionInputReader.getRovers();
		this.controller = new Controller(plateau);
	}

	/**
	 * Deploy Your Mission
	 * Change input file before deploying
	 * file located in ../inputs/input.txt
	 */
	public void deploy() {
		if (missionInputReader.goAhead()){
			controller.landAllRovers(rovers);
			controller.explorePlateau();
			controller.print();
		}
	}
}
