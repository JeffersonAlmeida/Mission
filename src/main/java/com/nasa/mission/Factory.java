package com.nasa.mission;

import java.util.ArrayList;
import java.util.List;
import com.nasa.mission.model.Compass;
import com.nasa.mission.model.Instruction;
import com.nasa.mission.model.Plateau;
import com.nasa.mission.model.Position;
import com.nasa.mission.model.Rover;

public class Factory {

	/**
	 * @param plateau - String representing a plateau size = "x y"
	 * @return Returns a Plateau Object
	 */
	public static Plateau buildPlateau(String plateau){
		String[] plateauString = plateau.split(" ");
		int horizontalMax = Integer.parseInt(plateauString[0]);
		int verticalMax = Integer.parseInt(plateauString[1]);
		return new Plateau(horizontalMax, verticalMax);
	}
	
	/**
	 * @param position - String representing a Rovers' Position (x y compassLetter)
	 * @param instructions -  String representing Rovers' Instructions (R|L|M)
	 * @return Returns a Rover Object
	 * @see Instruction, Position, Compass
	 */
	public static Rover buildRover(String position, String instructions){
		String[] positionString = position.split(" ");
		int x = Integer.parseInt(positionString[0]);
		int y = Integer.parseInt(positionString[1]);
		Compass letter = buildCompass(positionString[2]);
		Position p = new Position(x, y, letter);
		List<Instruction> lista = buildInstructions(instructions);
		return new Rover(p, lista);
	}
	
	private static Compass buildCompass(String letter){
		Compass compassLetter = null;
		switch (letter) {
		case "N":
			compassLetter= Compass.N;
			break;
			
		case "S":
			compassLetter= Compass.S;
			break;
		
		case "E":
			compassLetter= Compass.E;
			break;
			
		case "W":
			compassLetter= Compass.W;
			break;
			
		default:
			break;
		}
		
		return compassLetter;
	}
	
	private static List<Instruction> buildInstructions(String instructions){
		List<Instruction> list = new ArrayList<Instruction>();
	    for (char c : instructions.toCharArray()){
	    	switch (c) {
			case 'L':
				list.add(Instruction.L);
				break;
		
			case 'R':
				list.add(Instruction.R);
				break;
			
			case 'M':
				list.add(Instruction.M);
				break;

			default:
				break;
			}
	    }
		return list;
	}
}
