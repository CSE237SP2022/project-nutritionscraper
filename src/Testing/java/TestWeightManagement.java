package Testing.java;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import javax.swing.tree.RowMapper;

import org.junit.jupiter.api.Test;

import NutritionScraper.main.java.PersonalNutritionInfo;
import NutritionScraper.main.java.WeightManagement;

class TestWeightManagement {
	

	@Test
	void GainOptionLoseOrGainWeight() {
		WeightManagement wm = new WeightManagement();
		String testInput = "\ng\n";
		Scanner testScanner = new Scanner(testInput);
		wm.inputWeightGoals(testScanner);
		assertEquals("gain", wm.getGoals());
	}
	
	@Test
	void InvalidOptionLoseOrGainWeight() {
		WeightManagement wm = new WeightManagement();
		String testInput = "\n?\n";
		Scanner testScanner = new Scanner(testInput);
		wm.inputWeightGoals(testScanner);
		assertEquals("maintain", wm.getGoals());
	}
	
	@Test
	void NormalInputOverHowManyWeeks() {
		WeightManagement wm = new WeightManagement();
		String testInput = "\n12\n";
		Scanner testScanner = new Scanner(testInput);
		wm.inputOverHowManyWeeks(testScanner);
		assertEquals(12, wm.getOverHowManyWeeks());
	}
	
	@Test
	void NegativeWeeksInputOverHowManyWeeks() {
		WeightManagement wm = new WeightManagement();
		String testInput = "\n-12\n";
		Scanner testScanner = new Scanner(testInput);
		wm.inputOverHowManyWeeks(testScanner);
		assertEquals(0, wm.getOverHowManyWeeks());
	}
	
	@Test
	void InvalideWeeksInputOverHowManyWeeks() {
		WeightManagement wm = new WeightManagement();
		String testInput = "\nwow this is so cool\n";
		Scanner testScanner = new Scanner(testInput);
		wm.inputOverHowManyWeeks(testScanner);
		assertEquals(0, wm.getOverHowManyWeeks());
	}
	
	@Test
	void NormalInputKgs() {
		WeightManagement wm = new WeightManagement();
		String testInput = "\n50\n";
		Scanner testScanner = new Scanner(testInput);
		wm.inputKgs(testScanner);
		assertEquals(50, wm.getKgs());
	}
	
	@Test
	void NegativeInputKgs() {
		WeightManagement wm = new WeightManagement();
		String testInput = "\n-50\n";
		Scanner testScanner = new Scanner(testInput);
		wm.inputKgs(testScanner);
		assertEquals(0, wm.getKgs());
	}
	
	@Test
	void InvalidInputKgs() {
		WeightManagement wm = new WeightManagement();
		String testInput = "\nDougDougDoug\n";
		Scanner testScanner = new Scanner(testInput);
		wm.inputKgs(testScanner);
		assertEquals(0, wm.getKgs());
	}
	
	@Test
	void NormalcalculateThermicEffectiveFood() {
		WeightManagement wm = new WeightManagement();
		assertEquals(0, wm.calculateThermicEffectiveFood());
		
	}
	
	@Test 
	void DoubleCalculateKgPerDay() {
		WeightManagement wm = new WeightManagement();
		wm.setKgs(50);
		wm.setHowManyWeeks(2);
		int value = wm.calculateKgPerDay();
		
		assertEquals(7700*25/7, value);
	}
	
	@Test 
	void ZeroWeekCalculateKgPerDay() {
		WeightManagement wm = new WeightManagement();
		wm.setKgs(50);
		wm.setHowManyWeeks(0);
		int value = wm.calculateKgPerDay();
		
		assertEquals(0, value);
	}
	
	@Test 
	void ZeroKgCalculateKgPerDay() {
		WeightManagement wm = new WeightManagement();
		wm.setKgs(0);
		wm.setHowManyWeeks(3);
		int value = wm.calculateKgPerDay();
		
		assertEquals(0, value);
	}


}
