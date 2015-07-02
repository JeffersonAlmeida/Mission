package com.nasa.mission.input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import com.nasa.mission.Factory;
import com.nasa.mission.erros.ValidationErrors;
import com.nasa.mission.exceptions.MissionException;
import com.nasa.mission.model.Plateau;
import com.nasa.mission.model.Rover;
import com.nasa.mission.util.Constants;
import com.nasa.mission.validator.InputValidator;

/**
 * @author Jefferson
 *
 *For performance reasons, this class is responsible for
 *creating objects while making  inputs validations.
 */
public class MissionInputReader {

	private BufferedReader br;
	private File inputFile;
	private Plateau plateau;
	private List<Rover> rovers;
	private boolean goAhead;
	private int lineCount;
	
	public MissionInputReader(String input) {
		this.rovers = new ArrayList<Rover>();
		this.goAhead = true;
		inputFile = new File(ClassLoader.getSystemResource(input).getPath());
		lineCount = 1;
		try {
			FileReader fr = new FileReader(inputFile); 
			br = new BufferedReader(fr); 
			createAndValidateInputs();
		} catch (FileNotFoundException e) {
			new MissionException(ValidationErrors.FILE_NOT_FOUND).handle();;
		} 
	}
	
	public MissionInputReader() {
		this(Constants.INPUT_FILE);
	}
	
	private void createAndValidateInputs(){
		readFileAndCreateObjects();
		if (goAhead){
			try {
				InputValidator.validatePositionsOnLanding(rovers);
				InputValidator.validateEnoughSpace(rovers, plateau);
			} catch (MissionException e) {
				e.handle();
				goAhead = false;
			}
		}
	}
	
	private void readFileAndCreateObjects(){
		try {
			readInputs();
		}catch (MissionException e) {
			e.handle();
			goAhead = false;
		}catch ( IOException ioe){
			ioe.printStackTrace();  
			goAhead = false;
		}
		
	}
	
	private void readInputs() throws IOException, MissionException{
		try{
			String line = br.lines().findFirst().get();
			lineCount++;
			if ( validatePlateuInput( line )){
			    this.plateau = Factory.buildPlateau( line );
				readRoversInputs();
			} 
		}catch(NoSuchElementException nsee){
			throw new MissionException(ValidationErrors.FILE_MAY_BE_EMPTY);
		}finally {
			br.close();
		}
	}

	private void readRoversInputs() {
		Iterator<String> i = br.lines().iterator();
		if (i.hasNext()){
			readRoversInput(i);
		}else{
			MissionException me = new MissionException(ValidationErrors.MISSING_POSITION);
			me.setDetails("Check input file in line number 2");
			me.handle();
			goAhead = false;
		}
		
	}

	private void readRoversInput(Iterator<String> i) {
		while ( i.hasNext() ){
			try {
				String position = readPosition(i);
				String instructions = readInstructions(i);
				Rover r = Factory.buildRover(position, instructions);
				this.rovers.add(r);
			} catch (MissionException e) {
				e.setDetails("Check input file in line number: " + lineCount); 
				e.handle();
				goAhead = false;
				break;
			}
		}
	}
	
	private String readPosition(Iterator<String> i) throws MissionException{
		String position = "";
		if (i.hasNext()){
			position = i.next();
			lineCount++;
			InputValidator.validatePosition(plateau, position);
		}else{
			throw new MissionException(ValidationErrors.MISSING_POSITION);
		}
		return position;
	}

	private String readInstructions(Iterator<String> i) throws MissionException {
		String instructions = "";
		if (i.hasNext()){
		    instructions = i.next();
		    lineCount++;
			InputValidator.validateInstructions(instructions);
		}else {
			throw new MissionException(ValidationErrors.MISSING_INSTRUCTION);
		}
		return instructions;
	}
	
	private boolean validatePlateuInput(String p) {
		try {
			return InputValidator.validatePlateau(p);
		} catch (MissionException e) {
			e.setDetails("Check input file in line number: " + lineCount); 
			e.handle();
			goAhead = false;
		}
		return false;
	}

	public Plateau getPlateau() {
		return plateau;
	}

	public List<Rover> getRovers() {
		return rovers;
	}
	
	public boolean goAhead() {
		return goAhead;
	}

}

