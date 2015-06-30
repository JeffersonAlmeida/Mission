package com.nasa.mission.validator;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.nasa.mission.Factory;
import com.nasa.mission.erros.ValidationErrors;
import com.nasa.mission.exceptions.MissionException;
import com.nasa.mission.model.Rover;

public class InputValidatorTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testValidatePositionsOnLanding() {
		List<Rover> rovers = new ArrayList<Rover>();
		rovers.add(Factory.buildRover("1 2 N", "LM"));
		rovers.add(Factory.buildRover("1 2 N", "LM"));
		try {
			InputValidator.validatePositionsOnLanding(rovers);
		} catch (MissionException e) {
			e.handle();
			assertEquals(e.getError(), ValidationErrors.BUSY_POSTION_ON_LANDING);
			String s = "you must reconsider rovers' positions. They will be landed in the same position and may crash.";
			assertEquals(s, e.getMessage());
			
		}
	}

}
