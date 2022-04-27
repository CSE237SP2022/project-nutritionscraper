package Testing.java;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;


import NutritionScraper.main.java.PersonalNutritionInfo;


public class TestPersonalNutritionInfo {
			
	@Test
	void validNumeric() {
		PersonalNutritionInfo PNI = new PersonalNutritionInfo();
		String s = "1234";
		boolean isNumeric = PNI.isNumeric(s);
		assertTrue(isNumeric);
	}
	
	@Test
	void validNumeric2() {
		PersonalNutritionInfo PNI = new PersonalNutritionInfo();
		String s = "12.34";
		boolean isNumeric = PNI.isNumeric(s);
		assertTrue(isNumeric);
	}
	
	@Test
	void invalidNumeric() {
		PersonalNutritionInfo PNI = new PersonalNutritionInfo();
		String s = "al;sdkj";
		boolean isNumeric = PNI.isNumeric(s);
		assertFalse(isNumeric);
	}
	
	@Test
	void invalidNumeric2() {
		PersonalNutritionInfo PNI = new PersonalNutritionInfo();
		String s = "123k";
		boolean isNumeric = PNI.isNumeric(s);
		assertFalse(isNumeric);
	}
	
	@Test
	void NormalGetWeightTest() {
		String testInput = "\n23\n";
		Scanner testScanner = new Scanner(testInput);
		PersonalNutritionInfo testPersonalNutrition = new PersonalNutritionInfo();
		testPersonalNutrition.inputWeight(testScanner);
		assertEquals(23, testPersonalNutrition.getWeight());
	}
	
	@Test
	void NegativeGetWeightTest() {
		String testInput = "\n-23\n";
		Scanner testScanner = new Scanner(testInput);
		PersonalNutritionInfo testPersonalNutrition = new PersonalNutritionInfo();
		testPersonalNutrition.inputWeight(testScanner);
		assertEquals(0, testPersonalNutrition.getWeight());
	}
	
	@Test
	void InvalidGetWeightTest() {
		String testInput = "\nWeight\n";
		Scanner testScanner = new Scanner(testInput);
		PersonalNutritionInfo testPersonalNutrition = new PersonalNutritionInfo();
		testPersonalNutrition.inputWeight(testScanner);
		assertEquals(0, testPersonalNutrition.getWeight());
	}
	
	
	@Test
	void NormalGetHeightTest() {
		String testInput = "\n167\n";
		Scanner testScanner = new Scanner(testInput);
		PersonalNutritionInfo testPersonalNutrition = new PersonalNutritionInfo();
		testPersonalNutrition.inputHeight(testScanner);
		assertEquals(167, testPersonalNutrition.getHeight());
	}
	
	@Test
	void NegativeGetHeightTest() {
		String testInput = "\n-167\n";
		Scanner testScanner = new Scanner(testInput);
		PersonalNutritionInfo testPersonalNutrition = new PersonalNutritionInfo();
		testPersonalNutrition.inputHeight(testScanner);
		assertEquals(0, testPersonalNutrition.getHeight());
	}
	
	@Test
	void InvalidGetHeightTest() {
		String testInput = "\nHeight\n";
		Scanner testScanner = new Scanner(testInput);
		PersonalNutritionInfo testPersonalNutrition = new PersonalNutritionInfo();
		testPersonalNutrition.inputHeight(testScanner);
		assertEquals(0, testPersonalNutrition.getHeight());
	}
	
	@Test
	void FemaleGetGenderTest() {
		String testInput = "\nF\n";
		Scanner testScanner = new Scanner(testInput);
		PersonalNutritionInfo testPersonalNutrition = new PersonalNutritionInfo();
		testPersonalNutrition.inputGender(testScanner);
		assertEquals("F", testPersonalNutrition.getGender());
	}
	
	@Test
	void MaleGetGenderTest() {
		String testInput = "\nm\n";
		Scanner testScanner = new Scanner(testInput);
		PersonalNutritionInfo testPersonalNutrition = new PersonalNutritionInfo();
		testPersonalNutrition.inputGender(testScanner);
		assertEquals("M", testPersonalNutrition.getGender());
	}
	
	@Test
	void InvalidRandomGetGenderTest() {
		String testInput = "\nasdf\n";
		Scanner testScanner = new Scanner(testInput);
		PersonalNutritionInfo testPersonalNutrition = new PersonalNutritionInfo();
		testPersonalNutrition.inputGender(testScanner);
		assertNotNull(testPersonalNutrition.getGender());
		String currGender = testPersonalNutrition.getGender();
		assertTrue((currGender == "M") || (currGender == "F"));
		
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
	
	@Test 
	void calculateBMIUnderWeight() {
		PersonalNutritionInfo testPersonalNutrition = new PersonalNutritionInfo();
		testPersonalNutrition.setHeight(150);
		testPersonalNutrition.setWeight(36);
		double bmi = testPersonalNutrition.calculateBMI();
		assertEquals(16.0, bmi);
	}

	@Test
	void calculateBMIHealthyWeight() {
		PersonalNutritionInfo testPersonalNutrition = new PersonalNutritionInfo();
		testPersonalNutrition.setHeight(200);
		testPersonalNutrition.setWeight(74);
		double bmi = testPersonalNutrition.calculateBMI();
		assertEquals(18.5, bmi);
	}
	
	@Test
	void calculateBMIOverWeight() {
		PersonalNutritionInfo testPersonalNutrition = new PersonalNutritionInfo();
		testPersonalNutrition.setHeight(150);
		testPersonalNutrition.setWeight(63);
		double bmi = testPersonalNutrition.calculateBMI();
		assertEquals(28, bmi);
	}
	
	@Test
	void calculateBMIObese() {
		PersonalNutritionInfo testPersonalNutrition = new PersonalNutritionInfo();
		testPersonalNutrition.setHeight(150);
		testPersonalNutrition.setWeight(72);
		double bmi = testPersonalNutrition.calculateBMI();
		assertEquals(32, bmi);
	}
	
	@Test
	void calculateIBWLower() {
		PersonalNutritionInfo testPersonalNutrition = new PersonalNutritionInfo();
		testPersonalNutrition.setHeight(150);
		double[] ibw = testPersonalNutrition.calculateIBW();
		assertEquals(41.625, ibw[0]);
	}
	
	@Test
	void calculateIBWUpper() {
		PersonalNutritionInfo testPersonalNutrition = new PersonalNutritionInfo();
		testPersonalNutrition.setHeight(150);
		double[] ibw = testPersonalNutrition.calculateIBW();
		assertEquals(56.025, ibw[1]);
	}
	
	
	
	

}
