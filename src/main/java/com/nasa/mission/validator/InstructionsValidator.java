package com.nasa.mission.validator;

import com.nasa.mission.erros.ValidationErrors;
import com.nasa.mission.exceptions.MissionException;

public class InstructionsValidator {
	
	public static boolean validateInstructions(String instructions) throws MissionException{
		instructions = instructions.toUpperCase().trim();
		if (!instructions.isEmpty())
			checkInstructions(instructions);
		else
			throw new MissionException(ValidationErrors.MISSING_INSTRUCTION);
		
		return true;
	}

	private static void checkInstructions(String instructions) throws MissionException {
		char [] instr = instructions.toCharArray();
		for ( int i = 0 ; i < instr.length ; i++){
			char c = instr[i] ;
			if (c != 'L' && c != 'R' && c != 'M'){
				String details = "What has been typed: " + c;
				MissionException me = new MissionException(ValidationErrors.NOT_VALID_INSTRUCTION);
				me.setDetails(details);
				throw me;
			}
		}
	}
}
