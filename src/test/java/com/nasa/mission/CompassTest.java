package com.nasa.mission;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.nasa.mission.model.Compass;

public class CompassTest {

	private Compass n, e, s, w;
	
	@Before
	public void setUp() throws Exception {
		n = Compass.N;
		e = Compass.E;
		s = Compass.S;
		w = Compass.W;
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testRight() {
		assertEquals(Compass.E, n.right());
		assertEquals(Compass.S, e.right());
		assertEquals(Compass.W, s.right());
		assertEquals(Compass.N, w.right());
	}

	@Test
	public void testLeft() {
		assertEquals(Compass.W, n.left());
		assertEquals(Compass.N, e.left());
		assertEquals(Compass.E, s.left());
		assertEquals(Compass.S, w.left());
	}

}
