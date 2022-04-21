package Testing.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import NutritionScraper.main.java.Exercise;

import java.util.Scanner;
class TestExercise {

	@Test
	void NormalInputExerciseDurationInfo() {
		Exercise e = new Exercise();
		String testInput = "\n10\n";
		Scanner testScanner = new Scanner(testInput);
		e.inputExerciseDurationInfo(testScanner);
		assertEquals(10, e.getDuration());
	}
	
	@Test
	void DoubleInputExerciseDurationInfo() {
		Exercise e = new Exercise();
		String testInput = "\n10.5\n";
		Scanner testScanner = new Scanner(testInput);
		e.inputExerciseDurationInfo(testScanner);
		assertEquals(11, e.getDuration());
	}
	
	@Test
	void NegativeInputExerciseDurationInfo() {
		Exercise e = new Exercise();
		String testInput = "\n-10.5\n";
		Scanner testScanner = new Scanner(testInput);
		e.inputExerciseDurationInfo(testScanner);
		assertEquals(0, e.getDuration());
	}
	
	@Test
	void InvalidInputExerciseDurationInfo() {
		Exercise e = new Exercise();
		String testInput = "\nHello\n";
		Scanner testScanner = new Scanner(testInput);
		e.inputExerciseDurationInfo(testScanner);
		assertEquals(0, e.getDuration());
	}
	
	
	@Test 
	void NormalInputWeightInfo(){
		Exercise e = new Exercise();
		String testInput = "\n100\n";
		Scanner testScanner = new Scanner(testInput);
		e.inputWeightInfo(testScanner);;
		assertEquals(100, e.getWeightKgs());
	}
	
	@Test 
	void DoubleInputWeightInfo(){
		Exercise e = new Exercise();
		String testInput = "\n100.002\n";
		Scanner testScanner = new Scanner(testInput);
		e.inputWeightInfo(testScanner);;
		assertEquals(100, e.getWeightKgs());
	}
	
	@Test 
	void NegativeInputWeightInfo(){
		Exercise e = new Exercise();
		String testInput = "\n-10000000\n";
		Scanner testScanner = new Scanner(testInput);
		e.inputWeightInfo(testScanner);;
		assertEquals(0, e.getWeightKgs());
	}
	
	@Test 
	void InvalidInputWeightInfo(){
		Exercise e = new Exercise();
		String testInput = "\nShook\n";
		Scanner testScanner = new Scanner(testInput);
		e.inputWeightInfo(testScanner);;
		assertEquals(0, e.getWeightKgs());
	}
	
	@Test
	void NormalInputMetbolicEquivalentForTaskInfo() {
		Exercise e = new Exercise();
		String testInput = "\n3\n";
		Scanner testScanner = new Scanner(testInput);
		e.inputMetbolicEquivalentForTaskInfo(testScanner);;
		assertEquals(3, e.getMet());
	}
	
	@Test
	void InvalidInputMetbolicEquivalentForTaskInfo() {
		Exercise e = new Exercise();
		String testInput = "\n1\n";
		Scanner testScanner = new Scanner(testInput);
		e.inputMetbolicEquivalentForTaskInfo(testScanner);;
		assertEquals(0, e.getMet());
	}
	
	@Test
	void Invalid2InputMetbolicEquivalentForTaskInfo() {
		Exercise e = new Exercise();
		String testInput = "\nasdf\n";
		Scanner testScanner = new Scanner(testInput);
		e.inputMetbolicEquivalentForTaskInfo(testScanner);;
		assertEquals(0, e.getMet());
	}
	
	@Test
	void NormalCalculateCaloriesBurned() {
		Exercise e = new Exercise();
		e.setDuration(200);
		e.setWeight(60);
		e.setMet(3);
		assertEquals(630, e.calculateCaloriesBurned());
	}
	
	@Test
	void Normal2CalculateCaloriesBurned() {
		Exercise e = new Exercise();
		e.setDuration(111);
		e.setWeight(66);
		e.setMet(3);
		assertEquals(384.62, e.calculateCaloriesBurned());
	}
	
	@Test
	void InvalidCalculateCaloriesBurned() {
		Exercise e = new Exercise();
		e.setDuration(111);
		e.setWeight(66);
		e.setMet(0);
		assertEquals(0, e.calculateCaloriesBurned());
	}

}
