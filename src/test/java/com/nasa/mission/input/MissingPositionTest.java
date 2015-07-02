package com.nasa.mission.input;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MissingPositionTest {
	
	private static final String INPUT = "missingPositionInput.txt";	
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
		assertNotNull(inputReader.getRovers());
		assertEquals(0, inputReader.getRovers().size());
		assertTrue(inputReader.getRovers().isEmpty());
	}

	
}
