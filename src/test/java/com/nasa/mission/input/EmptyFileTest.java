package com.nasa.mission.input;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.nasa.mission.input.MissionInputReader;
import com.nasa.mission.model.Rover;

public class EmptyFileTest {
	
	private static final String INPUT = "..//missionmvn//inputs//inputEmpty.txt";	
	private MissionInputReader inputReader;
	
	@Before
	public void setUp() throws Exception {
		inputReader = new MissionInputReader(INPUT);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInputReader() {
		assertNotNull(inputReader);
	}

	@Test
	public void testReadFileAndCreateObjects() {
		List<Rover> rovers = inputReader.getRovers();
		assertNull(inputReader.getPlateau());
		assertNotNull(rovers);
		assertTrue(rovers.isEmpty());
	}

	@Test
	public void testMayIContinue() {
		assertFalse(inputReader.goAhead());
	}
	
}
