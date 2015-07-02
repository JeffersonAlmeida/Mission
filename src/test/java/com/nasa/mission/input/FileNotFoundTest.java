package com.nasa.mission.input;

import org.junit.After;
import org.junit.Before;
import static org.junit.Assert.assertNull;

public class FileNotFoundTest {
	
	private static final String INPUT = "notfound.txt";	
	
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
		
	}
	
	public void testFileNotFound(){
		MissionInputReader inputReader = new MissionInputReader(INPUT);
		assertNull(inputReader);
	}

}
