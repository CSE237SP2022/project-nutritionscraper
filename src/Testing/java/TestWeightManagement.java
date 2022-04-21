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
	void NormalFemaleCalculateThermicEffectiveFood() {
		WeightManagement wm = new WeightManagement();
		PersonalNutritionInfo pni = new PersonalNutritionInfo();
		pni.setGender("F");
		pni.setHeight(175);
		pni.setWeight(60);
		
		assertEquals(14, wm.calculateThermicEffectiveFood(pni));

	}
	
	@Test
	void InvalidCalculateThermicEffectiveFood() {
		WeightManagement wm = new WeightManagement();
		PersonalNutritionInfo pni = new PersonalNutritionInfo();

		assertEquals(3, wm.calculateThermicEffectiveFood(pni));

	}
	
	@Test
	void NormalCalculateTotalDailyExpenditure() {
		WeightManagement wm = new WeightManagement();
		PersonalNutritionInfo pni = new PersonalNutritionInfo();
		pni.setGender("F");
		pni.setHeight(175);
		pni.setWeight(60);
		
		assertEquals(1955, wm.calculateTotalDailyExpenditure(pni));
	}
	
	@Test
	void InvalidCalculateTotalDailyExpenditure() {
		WeightManagement wm = new WeightManagement();
		PersonalNutritionInfo pni = new PersonalNutritionInfo();
		
		assertEquals(873, wm.calculateTotalDailyExpenditure(pni));
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

	@Test
	void NormalCalculateLoseWeight() {
		WeightManagement wm = new WeightManagement();
		PersonalNutritionInfo pni = new PersonalNutritionInfo();
		wm.setHowManyWeeks(40);
		wm.setKgs(60);
		pni.setGender("F");
		pni.setHeight(175);
		pni.setWeight(60);
		assertEquals(855, wm.calculateLoseWeight(pni));
	}
	
	@Test
	void InvalidCalculateLoseWeight() {
		WeightManagement wm = new WeightManagement();
		wm.setHowManyWeeks(12);
		wm.setKgs(60);
		PersonalNutritionInfo pni = new PersonalNutritionInfo();
		pni.setGender("F");
		pni.setHeight(175);
		pni.setWeight(60);
		assertEquals(-1, wm.calculateLoseWeight(pni));
	}
	
	@Test
	void NormalCalculateGainWeight() {
		WeightManagement wm = new WeightManagement();
		wm.setHowManyWeeks(12);
		wm.setKgs(60);
		PersonalNutritionInfo pni = new PersonalNutritionInfo();
		pni.setGender("F");
		pni.setHeight(175);
		pni.setWeight(60);
		assertEquals(7455, wm.calculateGainWeight(pni));
	}
	
	@Test 
	void NormalLoseCaloriesAfterWeightManagement() {
		WeightManagement wm = new WeightManagement();
		wm.setHowManyWeeks(50);
		wm.setKgs(60);
		wm.setLoseOrGainWeight("lose");
		PersonalNutritionInfo pni = new PersonalNutritionInfo();
		pni.setGender("F");
		pni.setHeight(175);
		pni.setWeight(100);
		assertEquals(1075, wm.caloriesAfterWeightManagement(pni));
	}
	
	@Test 
	void NormalMaintainCaloriesAfterWeightManagement() {
		WeightManagement wm = new WeightManagement();
		wm.setHowManyWeeks(2);
		wm.setKgs(60);
		wm.setLoseOrGainWeight("maintain");
		PersonalNutritionInfo pni = new PersonalNutritionInfo();
		pni.setGender("F");
		pni.setHeight(175);
		pni.setWeight(60);
		assertEquals(1955, wm.caloriesAfterWeightManagement(pni));
	}
	
	@Test 
	void NormalGainCaloriesAfterWeightManagement() {
		WeightManagement wm = new WeightManagement();
		wm.setHowManyWeeks(50);
		wm.setKgs(60);
		wm.setLoseOrGainWeight("gain");
		PersonalNutritionInfo pni = new PersonalNutritionInfo();
		pni.setGender("F");
		pni.setHeight(175);
		pni.setWeight(60);
		assertEquals(3055, wm.caloriesAfterWeightManagement(pni));
	}

}
