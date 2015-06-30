package com.nasa.mission.validator;

import java.util.List;

import com.nasa.mission.erros.ValidationErrors;
import com.nasa.mission.exceptions.MissionException;
import com.nasa.mission.model.Plateau;
import com.nasa.mission.model.Rover;

public class PlateauValidator {
	
	public static boolean validatePlateau(String plateau) throws MissionException{
		String [] values = plateau.split(" ");
		int x = validateXFormat(values);
		int y = validateYFormat(values);
		return validatePlateauSize(x, y);
	}

	public static int validateYFormat(String[] values) throws MissionException {
		int y = 5;
		try{
			y = Integer.valueOf(values[1]);
		}catch (NumberFormatException nfe){
			throw new MissionException(ValidationErrors.PLATEAU_Y_MUST_BE_A_NUMBER);
		}catch(ArrayIndexOutOfBoundsException e){
			throw new MissionException(ValidationErrors.MISSING_PLATEAU_PARAMETER);
		}
		return y;
	}

	public static int validateXFormat(String [] values) throws MissionException {
		int x = 5;
		try{
			x = Integer.valueOf(values[0]);
		}catch(ArrayIndexOutOfBoundsException e){
			throw new MissionException(ValidationErrors.MISSING_PLATEAU_PARAMETER);
		}catch (NumberFormatException nfe){
			throw new MissionException(ValidationErrors.PLATEAU_X_MUST_BE_A_NUMBER);
		}
		return x;
	}

	public static boolean validatePlateauSize(int x, int y) throws MissionException {
		if (x <= Plateau.HORIZONTAL_MIN){
			throw new MissionException(ValidationErrors.PLATEAU_X_TOO_SMALL);
		}
		
		if (x > Plateau.HORIZONTAL_MAX){
			throw new MissionException(ValidationErrors.PLATEAU_X_TOO_BIG);
		}
		
		if (y <= Plateau.VERTICAL_MIN){
			throw new MissionException(ValidationErrors.PLATEAU_Y_TOO_SMALL);
		}
		
		if (y > Plateau.VERTICAL_MAX){
			throw new MissionException(ValidationErrors.PLATEAU_Y_TOO_BIG);
		}
		
		return true;
	}
	
	public static void validateEnoughSpace(List<Rover> rovers, Plateau plateau) throws MissionException{
		int roversIWantToSend = rovers.size();
		if (!enoughSpaceFor(roversIWantToSend, plateau))
			throw new MissionException(ValidationErrors.NOT_ENOUGH_SPACE);
	}

	private static boolean enoughSpaceFor(int roversIWantToSend, Plateau plateau) {
		return (roversIWantToSend < plateau.capacity()) ? true: false;
	}
}
