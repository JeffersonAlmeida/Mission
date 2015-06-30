package com.nasa.mission.validator;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

import com.nasa.mission.erros.ValidationErrors;
import com.nasa.mission.exceptions.MissionException;

public class InstructionsValidatorTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testNoValidInstr1() throws MissionException {
		try{
			InstructionsValidator.validateInstructions("LRMLX");
		}catch (MissionException me){
			me.handle();
			assertEquals(ValidationErrors.NOT_VALID_INSTRUCTION, me.getError());
		}
	}
	
	
	@Test
	public void testNoValidInstr2() throws MissionException {
		try{
			InstructionsValidator.validateInstructions("LRM L");
		}catch (MissionException me){
			me.handle();
			assertEquals(ValidationErrors.NOT_VALID_INSTRUCTION, me.getError());
		}
	}
	
	@Test
	public void testNoValidInstr3() throws MissionException {
		try{
			InstructionsValidator.validateInstructions("mmrmLRM");
		}catch (MissionException me){
			me.handle();
			assertEquals(ValidationErrors.NOT_VALID_INSTRUCTION, me.getError());
		}
	}
	
	@Test
	public void testNoValidInstr4() throws MissionException {
		try{
			InstructionsValidator.validateInstructions("@");
		}catch (MissionException me){
			me.handle();
			assertEquals(ValidationErrors.NOT_VALID_INSTRUCTION, me.getError());
		}
	}
	
	@Test
	public void testMissingInstr() throws MissionException {
		try{
			InstructionsValidator.validateInstructions("      ");
		}catch (MissionException me){
			me.handle();
			assertEquals(ValidationErrors.MISSING_INSTRUCTION, me.getError());
		}
	}

}
