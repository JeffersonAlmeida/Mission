package com.nasa.mission.validator;

import java.util.List;

import com.nasa.mission.exceptions.MissionException;
import com.nasa.mission.model.Plateau;
import com.nasa.mission.model.Rover;

/**
 * @author Jefferson
 * 
 * This class is responsible for  concentrating all validations
 *
 */
public class InputValidator {
	
	public static void validateInstructions(String instructions) throws MissionException{
		InstructionsValidator.validateInstructions(instructions);
	}
	
	public static boolean validatePlateau(String plateau) throws MissionException {
		return PlateauValidator.validatePlateau(plateau);
	}
	
	public static void validatePosition(Plateau plateau, String position) throws MissionException {
		PositionValidator.validatePosition(plateau, position);
	}
	
	public static void validateEnoughSpace(List<Rover> rovers, Plateau plateau) throws MissionException{
		PlateauValidator.validateEnoughSpace(rovers, plateau);
	}
	
	public static void validatePositionsOnLanding(List<Rover> rovers) throws MissionException{
		PositionValidator.validateAllPositions(rovers);
	}

}
