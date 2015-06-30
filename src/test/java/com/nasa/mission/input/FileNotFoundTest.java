package com.nasa.mission.input;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.nasa.mission.input.MissionInputReader;
import com.nasa.mission.model.Rover;

public class FileNotFoundTest {
	
	private static final String INPUT = "../missionmvn/inputs/notfound.txt";	
	private MissionInputReader inputReader;
	
	@Before
	public void setUp() throws Exception {
		inputReader = new MissionInputReader(INPUT);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReadFileAndCreateObjects() {
		assertNotNull(inputReader);
		List<Rover> rovers = inputReader.getRovers();
		assertNull(inputReader.getPlateau());
		assertNotNull(rovers);
		assertTrue(rovers.isEmpty());
	}

}
