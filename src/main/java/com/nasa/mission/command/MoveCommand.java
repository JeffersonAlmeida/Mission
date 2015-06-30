package com.nasa.mission.command;

import com.nasa.mission.exceptions.MissionException;
import com.nasa.mission.model.Rover;
import com.nasa.mission.model.state.Context;
import com.nasa.mission.model.state.State;
import com.nasa.mission.model.state.StateFactory;

public class MoveCommand implements ICommand {

	private Rover rover;
	private Context context;

	public MoveCommand(Rover r, Context context) {
		this.rover = r;
		this.context = context;
	}
	
	@Override
	public void execute() {
		State state = StateFactory.build(rover.getPosition().getLetter());
		context.setRover(rover);
		context.setState(state);
		try {
			System.out.println("\tM -> " + rover.position());
			if (context.canIMove())
				state.move(context);
		} catch (MissionException e) {
			e.handle();
		}
		System.out.println("\t     " + rover.position() + "\n");
	}
	
}