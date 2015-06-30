package com.nasa.mission.validator;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.nasa.mission.erros.ValidationErrors;
import com.nasa.mission.exceptions.MissionException;
import com.nasa.mission.model.Plateau;

public class PositionValidatorTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testValidatePosition() throws MissionException {
		int maxH = 4;
		int maxV = 3;
		Plateau plateau = new Plateau(maxH, maxV);
		String position = "1 3 N";
		assertTrue(PositionValidator.validatePosition(plateau, position));
	}
	
	@Test
	public void testValidatePosition2() {
		int maxH = 4;
		int maxV = 3;
		Plateau plateau = new Plateau(maxH, maxV);
		String position = "1 -3 N";
		try {
			PositionValidator.validatePosition(plateau, position);
		} catch (MissionException me) {
			me.handle();
			assertEquals(ValidationErrors.POSITION_Y_TOO_SMALL, me.getError());
		}
	}
	
	@Test
	public void testValidatePosition3() {
		int maxH = 4;
		int maxV = 3;
		Plateau plateau = new Plateau(maxH, maxV);
		String position = "1 -3 N";
		try {
			PositionValidator.validatePosition(plateau, position);
		} catch (MissionException me) {
			me.handle();
			assertEquals(ValidationErrors.POSITION_Y_TOO_SMALL, me.getError());
		}
	}

	@Test
	public void testValidatePositionNumbersPlateauIntInt() {
		int x  = -10;
		int y = 0;
		try {
			PositionValidator.validatePositionNumbers(x, y);
		} catch (MissionException me) {
			me.handle();
			assertEquals(ValidationErrors.POSITION_X_TOO_SMALL, me.getError());
		}
	}
	
	@Test
	public void testValidatePositionNumbersPlateauIntInt2() {
		int x  = -1;
		int y = 0;
		try {
			PositionValidator.validatePositionNumbers(x, y);
		} catch (MissionException me) {
			me.handle();
			assertEquals(ValidationErrors.POSITION_X_TOO_SMALL, me.getError());
		}
	}
	
	@Test
	public void testValidatePositionNumbersPlateauIntInt3() throws MissionException {
		int x  = 1;
		int y = 0;
		int maxH = 1;
		int maxV = 1;
		Plateau plateau = new Plateau(maxH, maxV);
		assertTrue(PositionValidator.validatePositionNumbers(plateau, x, y));
	}
	

	@Test
	public void testValidatePositionNumbersPlateauIntInt4() {
		int x  = 2;
		int y = 0;
		int maxH = 1;
		int maxV = 1;
		Plateau plateau = new Plateau(maxH, maxV);
		try {
			PositionValidator.validatePositionNumbers(plateau, x, y);
		} catch (MissionException me) {
			me.handle();
			assertEquals(ValidationErrors.POSITION_X_TOO_BIG, me.getError());
		}
	}
	
	@Test
	public void testValidatePositionNumbersPlateauIntInt5() {
		int x  = 80;
		int y = 0;
		int maxH = 4;
		int maxV = 3;
		Plateau plateau = new Plateau(maxH, maxV);
		try {
			PositionValidator.validatePositionNumbers(plateau, x, y);
		} catch (MissionException me) {
			me.handle();
			assertEquals(ValidationErrors.POSITION_X_TOO_BIG, me.getError());
		}
	}

	@Test
	public void testValidatePositionNumbersPlateauIntInt6() {
		int x  = 0;
		int y = 88;
		int maxH = 4;
		int maxV = 3;
		Plateau plateau = new Plateau(maxH, maxV);
		try {
			PositionValidator.validatePositionNumbers(plateau, x, y);
		} catch (MissionException me) {
			me.handle();
			assertEquals(ValidationErrors.POSITION_Y_TOO_BIG, me.getError());
		}
	}
	
	@Test
	public void testValidatePositionNumbersPlateauIntInt7() {
		int x  = -1;
		int y = 88;
		try {
			PositionValidator.validatePositionNumbers(x, y);
		} catch (MissionException me) {
			me.handle();
			assertEquals(ValidationErrors.POSITION_X_TOO_SMALL, me.getError());
		}
	}
	
	@Test
	public void testValidatePositionNumbersPlateauIntInt8() {
		int x  = 1;
		int y = -88;
		try {
			PositionValidator.validatePositionNumbers(x, y);
		} catch (MissionException me) {
			me.handle();
			assertEquals(ValidationErrors.POSITION_Y_TOO_SMALL, me.getError());
		}
	}
	
	@Test
	public void testValidatePositionYFormat() {
		String position = "asdf1 N";
		String [] values = position.split(" ");
		try {
			PositionValidator.validateXFormat(values);
		} catch (MissionException me) {
			me.handle();
			assertEquals(ValidationErrors.POSITION_X_MUST_BE_A_NUMBER, me.getError());
		}
	}
	

	@Test
	public void testValidatePositionXFormat1() throws MissionException {
		String position = "1 N";
		String [] values = position.split(" ");
		assertEquals(1, PositionValidator.validateXFormat(values));
	}
	
	@Test
	public void testValidatePositionYFormat1() throws MissionException {
		String position = "1 14";
		String [] values = position.split(" ");
		assertEquals(14, PositionValidator.validateYFormat(values));
	}
	
	@Test
	public void testValidatePositionXFormat() {
		String position = "2 @";
		String [] values = position.split(" ");
		try {
			PositionValidator.validateYFormat(values);
		} catch (MissionException me) {
			me.handle();
			assertEquals(ValidationErrors.POSITION_Y_MUST_BE_A_NUMBER, me.getError());
		}
	}

	
	@Test
	public void testValidateCompassLetter() {
		String str = "1 1";
		String[] values = str.split(" ");
		try {
			PositionValidator.validateCompassLetter(values);
		} catch (MissionException me) {
			me.handle();
			assertEquals(ValidationErrors.MISSING_LETTER, me.getError());
		}
	}
	
	@Test
	public void testValidateCompassLetter2() {
		String str = "1 1 L";
		String[] values = str.split(" ");
		try {
			PositionValidator.validateCompassLetter(values);
		} catch (MissionException me) {
			me.handle();
			assertEquals(ValidationErrors.NOT_VALID_LETTER, me.getError());
		}
	}
	
	@Test
	public void testValidateCompassLetter3() throws MissionException {
		String str = "1 1 e";
		String[] values = str.split(" ");
			boolean r = PositionValidator.validateCompassLetter(values);
		assertTrue(r);
	}

}
