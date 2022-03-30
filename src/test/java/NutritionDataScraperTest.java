package test.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import NutritionScraper.main.java.NutritionScraper;

import java.util.*;

class NutritionDataScraperTest {
	
	public NutritionScraper scraper = new NutritionScraper();
	public List<String> urls = scraper.getUrls("resources/foodList.txt");
	public String nutritionTableString = scraper.getAllFoodData(urls);
	public List<Map<String, String>> nutrientTables = scraper.getNutrientTables();
	public Map<String, String> whiteRice = scraper.getNutritionData("https://nutritiondata.self.com/facts/cereal-grains-and-pasta/5718/2");
	public Map<String, String> spinach = scraper.getNutritionData("https://nutritiondata.self.com/facts/vegetables-and-vegetable-products/2626/2");
	
	@Test
	void ActiveWebDriver() {
		// check if driver instance is created when NutritionScraper object is created (tests setUp method)
		boolean active = scraper.isDriverActive();
		assertTrue(active);
	}
	
	@Test
	void readUrls() {
		// check if urls are read correctly from text file (test file is located at resources/foodList.txt)
		List<String> urls_test = new ArrayList<>();
		urls_test.add("https://nutritiondata.self.com/facts/cereal-grains-and-pasta/5718/2");
		urls_test.add("https://nutritiondata.self.com/facts/vegetables-and-vegetable-products/2626/2");
		assertEquals(urls, urls_test);
	}

	@Test
	void testGrams() {
		// test if getNutritionData gets the correct values for grams
		String calories = whiteRice.get("Calories");
		assertEquals("130.0", calories);
	}
	
	@Test
	void testIU() {
		//test if conversion from IU is correct
		String vitA = whiteRice.get("Vitamin A");
		assertEquals("0.0",vitA);
	}
	
	@Test
	void testMg() {
		//test if conversion from is correct
		String manganese = whiteRice.get("Manganese");
		assertEquals("4.0E-4",manganese);
	}
	
	@Test
	void testMcg() {
		//test if conversion from mcg is correct
		String folate = whiteRice.get("Folate");
		assertEquals("5.8E-5",folate);
	}
	
	@Test
	void testCarbDropDown() {
		//test if getNutritionData correctly expands carb dropdown
		boolean carbDropDown = whiteRice.containsKey("Sucrose");
		assertTrue(carbDropDown);
	}
	
	@Test
	void testFatDropDown() {
		//test if getNutritionData correctly expands fat dropdown
		boolean fatDropDown = whiteRice.containsKey("4:00");
		assertTrue(fatDropDown);
	}
	
	@Test
	void testProteinDropDown() {
		//test if getNutritionData correctly expands protein dropdown
		boolean proteinDropDown = whiteRice.containsKey("Tryptophan");
		assertTrue(proteinDropDown);
	}
	
	@Test
	void testVitaminDropDown() {
		//test if getNutritionData correctly expands vitamin dropdown
		boolean vitaminDropDown = whiteRice.containsKey("Retinol");
		assertTrue(vitaminDropDown);
	}
	
	@Test
	void testSterolDropDown() {
		//test if getNutritionData correctly expands sterol dropdown
		boolean sterolDropDown = whiteRice.containsKey("Campesterol");
		assertTrue(sterolDropDown);
	}
	
	@Test
	void testTableFormat() {
		//test if getAllFoodData gets the correct second food
		String tableFormat = scraper.getTableFormatString();
		String correctTableFormat = "%-22s%-36s%-15s";
		assertEquals(tableFormat, correctTableFormat);
	}
	
	@Test
	void InactiveWebDriver() {
		scraper.quitDriver();
		boolean inactive = scraper.isDriverActive();
		assertFalse(inactive);
	}
	
}
