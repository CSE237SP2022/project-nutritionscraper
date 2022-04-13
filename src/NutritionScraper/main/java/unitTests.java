package NutritionScraper.main.java;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

class unitTests {
	
	/*
	 * THESE TESTS CAN TAKE A WHILE TO RUN
	 * THIS IS DUE TO THE UNIT TEST REINITIALIZING THE WEBSCRAPER AT EVERY TEST
	 */
	
	public NutritionScraper scraper = new NutritionScraper();
	public List<String> urls = textParsing.getUrls("foodListTest.txt");
	
	@Test
	void goToFoodPageTest() {
		
		String url = urls.get(0);
		boolean isAtPage = scraper.goToFoodPage(url);
		assertTrue(isAtPage);
	}
	
	@Test
	void getRawDataTest() {
		//tests if the scraper correctly gets the food name (should imply that all other raw data has been correctly collected)
		String url = urls.get(0);
		boolean isAtPage = scraper.goToFoodPage(url);
		List<String> rawData = scraper.getRawData();
		String foodName = rawData.get(0);
		assertEquals(foodName, "Spinach, raw");
	}
	
	@Test
	void getNutritionDataTest_Calories() {
		//tests if the scraper correctly gets and formats the calorie information
		String url = urls.get(0);
		boolean isAtPage = scraper.goToFoodPage(url);
		Map<String, String> nutritionData = scraper.getNutritionData(url);
		assertTrue(nutritionData.containsKey("Calories"));
		assertEquals(nutritionData.get("Calories"), "23.0");
	}
	
	@Test
	void getNutritionDataTest_Carbs() {
		//tests if the scraper correctly gets and formats the carbohydrate information
		String url = urls.get(0);
		boolean isAtPage = scraper.goToFoodPage(url);
		Map<String, String> nutritionData = scraper.getNutritionData(url);
		assertTrue(nutritionData.containsKey("Total Carbohydrate"));
		assertEquals(nutritionData.get("Total Carbohydrate"), "3.6");
	}
	
	@Test
	void getNutritionDataTest_Fat() {
		//tests if the scraper correctly gets and formats the fat information
		String url = urls.get(0);
		boolean isAtPage = scraper.goToFoodPage(url);
		Map<String, String> nutritionData = scraper.getNutritionData(url);
		assertTrue(nutritionData.containsKey("Total Fat"));
		assertEquals(nutritionData.get("Total Fat"), "0.4");
	}
	
	@Test
	void getNutritionDataTest_Protein() {
		//tests if the scraper correctly gets and formats the extended protein information
		String url = urls.get(0);
		boolean isAtPage = scraper.goToFoodPage(url);
		Map<String, String> nutritionData = scraper.getNutritionData(url);
		assertTrue(nutritionData.containsKey("Tryptophan"));
		assertEquals(nutritionData.get("Tryptophan"), "0.039");
	}
	
	@Test
	void getNutritionDataTest_Vitamins() {
		//tests if the scraper correctly gets and formats the extended vitamin information
		String url = urls.get(0);
		boolean isAtPage = scraper.goToFoodPage(url);
		Map<String, String> nutritionData = scraper.getNutritionData(url);
		assertTrue(nutritionData.containsKey("Retinol"));
		assertEquals(nutritionData.get("Retinol"), "0.0");
	}
	
	@Test
	void getNutritionDataTest_Minerals() {
		//tests if the scraper correctly gets and formats the mineral information
		String url = urls.get(0);
		boolean isAtPage = scraper.goToFoodPage(url);
		Map<String, String> nutritionData = scraper.getNutritionData(url);
		assertTrue(nutritionData.containsKey("Calcium"));
		assertEquals(nutritionData.get("Calcium"), "0.099");
	}
	
	@Test
	void getNutritionDataTest_Sterols() {
		//tests if the scraper correctly gets and formats the extended sterol information
		String url = urls.get(0);
		boolean isAtPage = scraper.goToFoodPage(url);
		Map<String, String> nutritionData = scraper.getNutritionData(url);
		assertTrue(nutritionData.containsKey("Campesterol"));
		assertEquals(nutritionData.get("Campesterol"), "0.0");
	}
	
	@Test
	void getNutritionDataTest_Other() {
		//tests if the scraper correctly gets and formats the other miscellaneous nutrient information
		String url = urls.get(0);
		boolean isAtPage = scraper.goToFoodPage(url);
		Map<String, String> nutritionData = scraper.getNutritionData(url);
		assertTrue(nutritionData.containsKey("Water"));
		assertEquals(nutritionData.get("Water"), "91.4");
	}
	
	@Test
	void getAllFoodDataTest_foodName() {
		//checks if the first row of data is the food name, and checks if the foods are in the correct order
		List<List<String>> foodData = scraper.getAllFoodData(urls);
		List<String> foodNames = foodData.get(0);
		
		String category = foodNames.get(0);
		assertEquals("Food Name", category);
		
		String food1 = foodNames.get(1);
		assertEquals(food1, "Spinach, raw");
		
		String food2 = foodNames.get(2);
		assertEquals(food2, "Rice, white, medium-grain, cooked");
	}
	
	@Test
	void getAllFoodDataTest_Nutrients() {
		//checks if the the nutrient categorization and values are in the correct order & are mapped correctly (one being correct should imply all are correct)
		List<List<String>> foodData = scraper.getAllFoodData(urls);
		List<String> nutrientData = foodData.get(1);
		
		String category = nutrientData.get(0);
		assertEquals("Calories", category);
		
		String food1 = nutrientData.get(1);
		assertEquals(food1, "23.0");
		
		String food2 = nutrientData.get(2);
		assertEquals(food2, "130.0");
	}

}
