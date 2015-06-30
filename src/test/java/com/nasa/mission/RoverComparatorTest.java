package com.nasa.mission;

import static com.nasa.mission.util.RoverComparator.POSITION_X_SORT;
import static com.nasa.mission.util.RoverComparator.POSITION_Y_SORT;
import static com.nasa.mission.util.RoverComparator.getComparator;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.nasa.mission.Factory;
import com.nasa.mission.model.Rover;

public class RoverComparatorTest {

	private List<Rover> rovers;
	
	@Before
	public void setUp() throws Exception {
		this.rovers = new ArrayList<Rover>();
		String p1 = "1 7 N";
		String p2 = "1 2 N";
		String p3 = "2 7 N";
		String p4 = "1 3 N";
		String instructions = "LM";
		Rover r1 = Factory.buildRover(p1, instructions);
		Rover r2 = Factory.buildRover(p2, instructions);
		Rover r3 = Factory.buildRover(p3, instructions);
		Rover r4 = Factory.buildRover(p4, instructions);
		rovers.add(r1);
		rovers.add(r2);
		rovers.add(r3);
		rovers.add(r4);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetComparator() {
		assertEquals(1, rovers.get(0).getPosition().getX());
		assertEquals(7, rovers.get(0).getPosition().getY());
		
		System.out.println("BEFORE\n");
		rovers.forEach((r) -> System.out.println(r));
		
		Collections.sort(rovers, getComparator(POSITION_X_SORT, POSITION_Y_SORT));
		assertEquals(1, rovers.get(0).getPosition().getX());
		assertEquals(2, rovers.get(0).getPosition().getY());
		
		System.out.println("\n\nAFTER\n");
		rovers.forEach((r) -> System.out.println(r));
	}
	
	@Test
	public void testGetComparator2() {
		assertEquals(1, rovers.get(0).getPosition().getX());
		assertEquals(7, rovers.get(0).getPosition().getY());
		
		System.out.println("BEFORE\n");
		rovers.forEach((r) -> System.out.println(r));
		
		Collections.sort(rovers, getComparator(POSITION_X_SORT, POSITION_Y_SORT));
		assertEquals(1, rovers.get(0).getPosition().getX());
		assertEquals(2, rovers.get(0).getPosition().getY());
		
		System.out.println("\n\nAFTER\n");
		rovers.forEach((r) -> System.out.println(r));
	}

}
