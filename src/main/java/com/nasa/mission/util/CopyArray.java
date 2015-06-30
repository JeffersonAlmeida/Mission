package com.nasa.mission.util;

import java.util.ArrayList;
import java.util.List;

import com.nasa.mission.model.Compass;
import com.nasa.mission.model.Instruction;
import com.nasa.mission.model.Position;
import com.nasa.mission.model.Rover;

public class CopyArray {
	public static List<Rover> copy(List<Rover> rovers){
		List<Rover> copy = new ArrayList<Rover>();
		for (Rover r: rovers){
			int x = r.getPosition().getX();
			int y = r.getPosition().getY();
			Compass letter = r.getPosition().getLetter();
			Position pCopy = new Position(x, y, letter);
			List<Instruction> instrCopy = new ArrayList<>();
			for (Instruction l: r.getInstructions())
				instrCopy.add(l);
			Rover rCopy = new Rover(pCopy, instrCopy);
			copy.add(rCopy);
		}
		return copy;
	}
}
