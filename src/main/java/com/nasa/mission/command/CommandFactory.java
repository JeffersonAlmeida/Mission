package com.nasa.mission.command;

import java.util.Iterator;
import java.util.List;
import com.nasa.mission.model.Instruction;
import com.nasa.mission.model.Rover;
import com.nasa.mission.model.state.Context;

public class CommandFactory {
	
	/**
	 * Build a RemoteControl object 
	 * Abstracted As: The Invoker - asks the command to carry out the request
	 * 
	 * Creates Concretes Commands objects and sets their receiver.
	 * 
	 * @param rover: A rover object
	 * @param context Context object used in State Design Pattern
	 * @return Returns a RemoteControl object 
	 */
	public static RemoteControl buildRemoteControl(Rover rover, Context context){
		List<Instruction> instructions = rover.getInstructions();
		Iterator<Instruction> it = instructions.iterator();
		RemoteControl remoteControl = new RemoteControl();
		while (it.hasNext()){
			Instruction i = it.next();
			switch (i) {
			case L:
				remoteControl.addCommand(new SpinLeftCommand(rover));
				break;

			case R:
				remoteControl.addCommand(new SpinRightCommand(rover));
				break;

			case M:
				remoteControl.addCommand(new MoveCommand(rover, context));
				break;

			default:
				break;
			}
		}
		return remoteControl;
	}
	
}
