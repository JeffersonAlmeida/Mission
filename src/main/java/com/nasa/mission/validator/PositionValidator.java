package com.nasa.mission.validator;

import static com.nasa.mission.util.RoverComparator.POSITION_X_SORT;
import static com.nasa.mission.util.RoverComparator.POSITION_Y_SORT;
import static com.nasa.mission.util.RoverComparator.getComparator;

import java.util.Collections;
import java.util.List;

import com.nasa.mission.erros.ValidationErrors;
import com.nasa.mission.exceptions.MissionException;
import com.nasa.mission.model.Compass;
import com.nasa.mission.model.Plateau;
import com.nasa.mission.model.Position;
import com.nasa.mission.model.Rover;
import com.nasa.mission.util.CopyArray;

public class PositionValidator {
	
	public static boolean validatePosition(Plateau plateau, String position) throws MissionException{
		String [] values = position.split(" ");
		int x = validateXFormat(values);
		int y = validateYFormat(values);
		validatePositionNumbers(x,y);
		validatePositionNumbers(plateau, x,y);
		return validateCompassLetter(values);
	}
	
	public static boolean validatePositionNumbers(int x, int y) throws MissionException {
		if (x < Plateau.HORIZONTAL_MIN){
			String details = "x = " + x + " plateau x = " + Plateau.HORIZONTAL_MIN;
			throw new MissionException(ValidationErrors.POSITION_X_TOO_SMALL, details);
		}
		
		if (y < Plateau.VERTICAL_MIN){
			String details = "y = " + x + " plateau y = " + Plateau.HORIZONTAL_MIN;
			throw new MissionException(ValidationErrors.POSITION_Y_TOO_SMALL, details);
		}
		
		return true;
	}

	public static boolean validatePositionNumbers(Plateau plateau, int x, int y) throws MissionException {
		if ( x > plateau.getHorizontalMax()){
			String details = "x = " + x + " plateau x = " + plateau.getHorizontalMax();
			throw new MissionException(ValidationErrors.POSITION_X_TOO_BIG, details);
		}
		
		if ( y > plateau.getVerticalMax()){
			String details = "y = " + y + " plateau y = " + plateau.getVerticalMax();
			throw new MissionException(ValidationErrors.POSITION_Y_TOO_BIG, details);
		}
		return true;
	}
	
	public static boolean validateCompassLetter(String[] values) throws MissionException {
		String letter = null;
		try {
			letter = String.valueOf(values[2]); 
		}catch(ArrayIndexOutOfBoundsException e){
			throw new MissionException(ValidationErrors.MISSING_LETTER); 
		}
		char compass = letter.toUpperCase().charAt(0);
		if ( compass != 'N' && compass != 'S' && compass != 'E' && compass != 'W'){
			String details = "Wrong Letter: " + compass + " - Options you have: " + Compass.options();
			throw new MissionException(ValidationErrors.NOT_VALID_LETTER, details); 
		}
		return true;
	}


	public static int validateYFormat(String[] values) throws MissionException {
		int y = 5;
		try{
			y = Integer.valueOf(values[1]);
		}catch (NumberFormatException nfe){
			String details = "What has been typed : " + values[1]; 
			throw new MissionException(ValidationErrors.POSITION_Y_MUST_BE_A_NUMBER, details);
		}
		return y;
	}

	public static int validateXFormat(String [] values) throws MissionException {
		int x = 5;
		try{
			x = Integer.valueOf(values[0]);
		}catch (NumberFormatException nfe){
			String details = "What has been typed : " + values[0]; 
			throw new MissionException(ValidationErrors.POSITION_X_MUST_BE_A_NUMBER, details);
		}
		return x;
	}
	
	public static void validateAllPositions(List<Rover> rovers) throws MissionException {
		if (!isLandingPositionsOk(rovers))
			throw new MissionException(ValidationErrors.BUSY_POSTION_ON_LANDING);
	}
	
	private static boolean isLandingPositionsOk(List<Rover> rovers){
		boolean ok = true;
		List<Rover> copy = CopyArray.copy(rovers);
		Collections.sort(copy, getComparator(POSITION_X_SORT, POSITION_Y_SORT));
		if (rovers.size() > 1){
			for (int i=1; i< rovers.size() && ok; i++){
				Position b = rovers.get(i).getPosition();
				Position a = rovers.get(i-1).getPosition();
				ok = (b.getX() == a.getX() && b.getY() == a.getY()) ? false : true;
			}
		}
		return ok;
	}
	
}
