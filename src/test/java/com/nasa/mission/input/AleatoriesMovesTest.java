package com.nasa.mission.input;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.nasa.mission.controller.Controller;
import com.nasa.mission.model.Compass;
import com.nasa.mission.model.Plateau;
import com.nasa.mission.model.Position;
import com.nasa.mission.model.Rover;

public class AleatoriesMovesTest {
	
	private static final String INPUT = "aleatoriesMoves.txt";	
	private MissionInputReader inputReader;
	private List<Rover> rovers;
	private Controller controller;
	private Plateau plateau;
	
	@Before
	public void setUp() throws Exception {
		inputReader = new MissionInputReader(INPUT);
		this.plateau = inputReader.getPlateau();
		this.controller = new Controller(plateau); 
		rovers = inputReader.getRovers();
		controller.landAllRovers(rovers);
		controller.explorePlateau();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInput() {
		controller.print();
		Rover r1 = rovers.get(0);
		assertNotNull(r1);
		assertEquals(new Position(0, 0, Compass.N).toString(), r1.getPosition().toString());
		Rover r2 = rovers.get(1);
		assertNotNull(r2);
		assertEquals(new Position(0, 1, Compass.N).toString(), r2.getPosition().toString());
		Rover r3 = rovers.get(2);
		assertNotNull(r3);
		assertEquals(new Position(0, 3, Compass.N).toString(), r3.getPosition().toString());
		Rover r4 = rovers.get(3);
		assertNotNull(r4);
		assertEquals(new Position(2, 4, Compass.E).toString(), r4.getPosition().toString());
		Rover r5 = rovers.get(4);
		assertNotNull(r5);
		assertEquals(new Position(0, 5, Compass.N).toString(), r5.getPosition().toString());
	}

}
