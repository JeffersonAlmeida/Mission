package com.nasa.mission.validator;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.nasa.mission.erros.ValidationErrors;
import com.nasa.mission.exceptions.MissionException;
import com.nasa.mission.input.MissionInputReader;
import com.nasa.mission.model.Plateau;
import com.nasa.mission.model.Rover;
import com.nasa.mission.util.Constants;

public class PlateauValidatorTest {

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testEnoughSpace() {
		String file = "notEnoughSpace.txt";
		String f = Constants.INPUTS + file;
		MissionInputReader missionInputReader = new MissionInputReader(f);
		Plateau plateau = missionInputReader.getPlateau();
		List<Rover> rovers = missionInputReader.getRovers();
		assertNotNull(plateau);
		assertNotNull(rovers);
	}

	@Test
	public void testValidatePlateau() {
		String str = "1324XX";
		try{
			 PlateauValidator.validatePlateau(str);
		}catch (MissionException me){
			me.handle();
			assertEquals(ValidationErrors.PLATEAU_X_MUST_BE_A_NUMBER, me.getError());
		}
	}
	
	@Test
	public void testValidatePlateau2() throws MissionException {
		String str = "1 1";
		assertTrue(PlateauValidator.validatePlateau(str));
	}

	@Test
	public void testValidateYFormat1(){
		String str = "1 X";
		String [] values = str.split(" ");
		try{
			PlateauValidator.validateYFormat(values);
		}catch (MissionException me){
			me.handle();
			assertEquals(ValidationErrors.PLATEAU_Y_MUST_BE_A_NUMBER, me.getError());
		}
	}
	
	@Test
	public void testValidateYFormat2(){
		String str = "1";
		String [] values = str.split(" ");
		try{
			PlateauValidator.validateYFormat(values);
		}catch (MissionException me){
			me.handle();
			assertEquals(ValidationErrors.MISSING_PLATEAU_PARAMETER, me.getError());
		}
	}
	
	@Test
	public void testValidateYFormat3(){
		String str = "";
		String [] values = str.split(" ");
		try{
			PlateauValidator.validateYFormat(values);
		}catch (MissionException me){
			me.handle();
			assertEquals(ValidationErrors.MISSING_PLATEAU_PARAMETER, me.getError());
		}
	}
	
	@Test
	public void testValidateYFormat4(){
		String str = "             ";
		String [] values = str.split(" ");
		try{
			PlateauValidator.validateYFormat(values);
		}catch (MissionException me){
			me.handle();
			assertEquals(ValidationErrors.MISSING_PLATEAU_PARAMETER, me.getError());
			assertNotSame("test", ValidationErrors.PLATEAU_Y_MUST_BE_A_NUMBER,  me.getError());
		}
	}
	
	@Test
	public void testValidateYFormat5(){
		String str = "-1 7";
		String [] values = str.split(" ");
		int y = 0;
		try{
			y = PlateauValidator.validateYFormat(values);
		}catch (MissionException me){
			me.handle();
			assertEquals(ValidationErrors.MISSING_PLATEAU_PARAMETER, me.getError());
			assertNotSame("test", ValidationErrors.PLATEAU_Y_MUST_BE_A_NUMBER,  me.getError());
		}
		
		assertEquals(7, y);
	}
	
	@Test
	public void testValidateYFormat6(){
		String str = "-1 3";
		String [] values = str.split(" ");
		int y = 0;
		try{
			y = PlateauValidator.validateYFormat(values);
		}catch (MissionException me){
			me.handle();
			assertEquals(ValidationErrors.MISSING_PLATEAU_PARAMETER, me.getError());
			assertNotSame("test", ValidationErrors.PLATEAU_Y_MUST_BE_A_NUMBER,  me.getError());
		}
		
		assertNotSame(5, y);
	}

	@Test
	public void testValidateXFormat1() {
		String str = "@ @";
		String [] values = str.split(" ");
		try{
			PlateauValidator.validateXFormat(values);
		}catch (MissionException me){
			me.handle();
			assertEquals(ValidationErrors.PLATEAU_X_MUST_BE_A_NUMBER, me.getError());
		}
	}
	
	@Test
	public void testValidateXFormat2() {
		String str = "1 @";
		String [] values = str.split(" ");
		try{
			PlateauValidator.validateXFormat(values);
		}catch (MissionException me){
			me.handle();
			assertEquals(ValidationErrors.PLATEAU_X_MUST_BE_A_NUMBER, me.getError());
		}
	}
	
	@Test
	public void testValidateXFormat3() throws MissionException {
		String str = "1 2";
		String [] values = str.split(" ");
		int x = 2;
		x = PlateauValidator.validateXFormat(values);
		assertEquals(1, x);
	}
	
	@Test
	public void testValidateXFormat4() throws MissionException {
		String str = "         ";
		String [] values = str.split(" ");
		try{
			PlateauValidator.validateXFormat(values);
		}catch (MissionException me){
			me.handle();
			assertEquals(ValidationErrors.MISSING_PLATEAU_PARAMETER, me.getError());
		}
	}


	@Test
	public void testValidatePlateauSize() {
		int x = 0;
		int y = 0;
		try{
			PlateauValidator.validatePlateauSize(x, y);
		}catch (MissionException me){
			me.handle();
			assertEquals(ValidationErrors.PLATEAU_X_TOO_SMALL, me.getError());
		}
	}
	
	
	@Test
	public void testValidatePlateauSize2() {
		int x = 4;
		int y = 1;
		boolean r = false;
		try{
			 r = PlateauValidator.validatePlateauSize(x, y);
		}catch (MissionException me){
			me.handle();
			assertEquals(ValidationErrors.MISSING_PLATEAU_PARAMETER, me.getError());
		}
		assertTrue(r);
	}

	
	@Test
	public void testValidatePlateauSize3() {
		int x = 1;
		int y = -5;
		boolean r = false;
		try{
			r = PlateauValidator.validatePlateauSize(x, y);
		}catch (MissionException me){
			me.handle();
			assertEquals(ValidationErrors.PLATEAU_Y_TOO_SMALL, me.getError());
		}
		assertFalse(r);
	}
	
	@Test
	public void testValidatePlateauSize4() {
		int x = 110000;
		int y = -5;
		boolean r = false;
		try{
			r = PlateauValidator.validatePlateauSize(x, y);
		}catch (MissionException me){
			me.handle();
			assertEquals(ValidationErrors.PLATEAU_X_TOO_BIG, me.getError());
		}
		
		assertFalse(r);
	}
	
	@Test
	public void testValidatePlateauSize5() {
		int x = 1;
		int y = 1000000;
		boolean r = false;
		try{
			r = PlateauValidator.validatePlateauSize(x, y);
		}catch (MissionException me){
			me.handle();
			assertEquals(ValidationErrors.PLATEAU_Y_TOO_BIG, me.getError());
		}
		assertFalse(r);
	}
	
	@Test
	public void testValidatePlateauSpace() {
		int x = 1;
		int y = 1000000;
		boolean r = false;
		try{
			r = PlateauValidator.validatePlateauSize(x, y);
		}catch (MissionException me){
			me.handle();
			assertEquals(ValidationErrors.PLATEAU_Y_TOO_BIG, me.getError());
		}
		assertFalse(r);
	}
}
