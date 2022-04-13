package NutritionScraper.main.java;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

class personalNutritionInfoUnitTest {
	
	public PersonalNutritionInfo PNI = new PersonalNutritionInfo();
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;
//	String s = "hjehe";
//	boolean a = PNI.isNumeric(s);
	
	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}

	@After
	public void restoreStreams() {
	    System.setOut(originalOut);
	    System.setErr(originalErr);
	}
	
	@Test
	void validNumeric() {
		String s = "1234";
		boolean isNumeric = PNI.isNumeric(s);
		assertTrue(isNumeric);
	}
	
	@Test
	void validNumeric2() {
		String s = "12.34";
		boolean isNumeric = PNI.isNumeric(s);
		assertTrue(isNumeric);
	}
	
	@Test
	void invalidNumeric() {
		String s = "al;sdkj";
		boolean isNumeric = PNI.isNumeric(s);
		assertFalse(isNumeric);
	}
	
	@Test
	void invalidNumeric2() {
		String s = "123k";
		boolean isNumeric = PNI.isNumeric(s);
		assertFalse(isNumeric);
	}
	
	@Test
	void getWeightTest() {
		String testInput = "\n23\n";
		Scanner testScanner = new Scanner(testInput);
		PersonalNutritionInfo testPersonalNutrition = new PersonalNutritionInfo();
		testPersonalNutrition.inputWeight(testScanner);
		assertEquals(23, testPersonalNutrition.getWeight());
	}
	
	@Test
	void getHeightTest() {
		String testInput = "\n167\n";
		Scanner testScanner = new Scanner(testInput);
		PersonalNutritionInfo testPersonalNutrition = new PersonalNutritionInfo();
		testPersonalNutrition.inputHeight(testScanner);
		assertEquals(167, testPersonalNutrition.getHeight());
	}
	
	@Test
	void getGenderTest() {
		String testInput = "\nF\n";
		Scanner testScanner = new Scanner(testInput);
		PersonalNutritionInfo testPersonalNutrition = new PersonalNutritionInfo();
		testPersonalNutrition.inputGender(testScanner);
		assertEquals("F", testPersonalNutrition.getGender());
	}

	@Test
	void calculateLBMFemale() {
		PersonalNutritionInfo testPersonalNutrition = new PersonalNutritionInfo();
		testPersonalNutrition.setHeight(167);
		testPersonalNutrition.setWeight(78);
		testPersonalNutrition.setGender("F");
		double lbm = testPersonalNutrition.leanBodyMass();
		assertEquals(50.35, lbm);
	}
	
	@Test
	void calculateLBMMale() {
		PersonalNutritionInfo testPersonalNutrition = new PersonalNutritionInfo();
		testPersonalNutrition.setHeight(190);
		testPersonalNutrition.setWeight(88);
		testPersonalNutrition.setGender("M");
		double lbm = testPersonalNutrition.leanBodyMass();
		assertEquals(67.35, lbm);
	}
	
	@Test
	void calculateLBMNull() {
		PersonalNutritionInfo testPersonalNutrition = new PersonalNutritionInfo();
		testPersonalNutrition.setHeight(190);
		testPersonalNutrition.setWeight(88);
		double lbm = testPersonalNutrition.leanBodyMass();
		assertEquals(0.00, lbm);
	}

	


}
