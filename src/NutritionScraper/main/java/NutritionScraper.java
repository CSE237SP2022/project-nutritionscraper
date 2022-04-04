package NutritionScraper.main.java;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.opentelemetry.exporter.logging.SystemOutLogExporter;

import java.util.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.lang.StringBuilder;

public class NutritionScraper {
	
	private WebDriver driver;
	private boolean driverActive;
	private static String tableFormatString;
	private static List<Map<String, String>> nutrientTables; 
	

	public void setUp() {
		
		//create a chromedriver instance
		WebDriverManager.chromedriver().setup();
		
		//set chrome to headless mode (does not open chrome window)
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");		
		
		//compile the webdriver
		driver = new ChromeDriver(options);
		
		driverActive = true;
	}
	
	/**
	 * Checks status of our chrome driver, is it active or not
	 * @param void
	 * @return boolean. True --> is active. False --> isn't active
	 */
	public boolean isDriverActive() {
		return driverActive;
	}
	
	/**
	 * 
	 * @param
	 * @return
	 */
	public void setNutrientTables(List<Map<String, String>> nutrTables) {
		nutrientTables = nutrTables;
	}
	
	
	/**
	 * 
	 * @param
	 * @return
	 */
	public static List<Map<String, String>> getNutrientTables() {
		return nutrientTables;
	}
	
	/**
	 * 
	 * @param
	 * @return
	 */
	public static void setTableFormatString(String tableFormat) {
		tableFormatString = tableFormat;
	}
	
	/**
	 * 
	 * @param
	 * @return
	 */
	public String getTableFormatString() {
		return tableFormatString;
	}
	
<<<<<<< HEAD
	/*
	 * This method navigates the to a given food url and selects
	 * 100g as the serving size (which is a standard option across 
	 * all listed foods), and selects the extended nutrition facts 
	 * 
	 */
	
	public void goToFoodPage(String url) {
		boolean success = false;
		
		//keep trying to get the nutrition data until no errors occur
		while(!success) {
			
			try {
				//get the website code
				driver.get(url);
				
				//select 100g as the serving size
				Select serving_size_100 = new Select(driver.findElement(By.name("serving")));
				serving_size_100.selectByValue("100.0");
				
				//Expand dropdown tabs containing additional nutrition data
				//reversing the list fixes errors where certain dropdown tabs cannot be clicked
				List<WebElement> expand_nutrition_data = driver.findElements(By.className("expand_collapse"));
				Collections.reverse(expand_nutrition_data);
				for (WebElement tab : expand_nutrition_data) {
					tab.click();
				}
				
				//successfully obtained the website data; break the loop
				success = true;
			}
			
			catch(Exception e){
				//Continue trying to get the website data
				System.out.println("Failed to get nutrition data. Trying again.");
				continue;
			}
		}
		
	}
	
	public List<String> getRawData() {
		
		String food_name = driver.findElement(By.className("facts-heading")).getText();
		
		//find the web element containing the nutrition information tables
		WebElement raw_nutrition_code = driver.findElement(By.id("NutritionInformationSlide"));
		
		//find the tables containing the nutrition information
		List<WebElement> raw_nutrition_tables = raw_nutrition_code.findElements(By.className("groupBorder"));
		
		//initialize empty list to hold the raw nutrient data from the tables
		List<WebElement> raw_nutrition_data = new ArrayList<WebElement>();
		
		//put all nutrients listed in the the raw tables into a single list;
		for (WebElement table : raw_nutrition_tables) {
			
			//split the table into individual web elements for each nutritent
			List<WebElement> nutrients = table.findElements(By.className("clearer"));
			
			//add the nutrient web elements to the raw_nutrient_data list
			raw_nutrition_data.addAll(nutrients);
		}
		
		List<String> rawScraperData = new ArrayList<String>();
		
		rawScraperData.add(food_name);
		
		for (WebElement nutrient : raw_nutrition_data) {
			String raw_nutrient_text = nutrient.getText();
			rawScraperData.add(raw_nutrient_text);
		}
		
		return rawScraperData;
	}
	
	public String convertToGrams(String nutrientValueStr, String nutrientUnit) {
		double nutrientValue;
		
		//convert the nutrient amount into grams from the given unit if applicable
		if (nutrientUnit.equals("~")) {
			nutrientValue = 0;
		} 
		else if (nutrientUnit.equals("mg")) {
			nutrientValue = Double.parseDouble(nutrientValueStr)/1E3;
		}
		else if (nutrientUnit.equals("mcg")) {
			nutrientValue = Double.parseDouble(nutrientValueStr)/1E6;
		}
		else {
			nutrientValue = Double.parseDouble(nutrientValueStr);
		}
		
		return Double.toString(nutrientValue);
	}
	
	public String renameNutrients(String nutrientName) {
		nutrientName = nutrientName.trim();
		
		if (nutrientName.equals("From Carbohydrate")) {
			return "Calories From Carbohydrate";
		}
		else if (nutrientName.equals("From Fat")) {
			 return "Calories From Fat";
		}
		else if (nutrientName.equals("From Protein")) {
			return "Calories From Protein";
		}
		else if (nutrientName.equals("From Alcohol")) {
			return "Calories From Alcohol";
		}
		else {
			return nutrientName;
		}
	}
	
	public Map<String, String> mapRawData(List<String> rawData) {
		
		Map<String, String> nutrients = new HashMap<>();
		
		nutrients.put("Food Name", rawData.get(0));
		rawData.remove(0);
		
		for (String nutrient : rawData) {
			
			//get the web text containing the nutrient information
				
				//split the nutrient text into individual values
				String[] nutrient_text_parsed = nutrient.split("\n");
				
				String nutrient_name = renameNutrients(nutrient_text_parsed[0]);				
			
				String nutrient_value = convertToGrams(nutrient_text_parsed[1], nutrient_text_parsed[2]);
				//put the nutrient name and value into the hashmap
				nutrients.put(nutrient_name, nutrient_value);
		}
		
		//return the nutrients hashmap
		return nutrients;
	}
	
	public Map<String, String> getNutritionData(String url){
		goToFoodPage(url);
		List<String> rawFoodData = getRawData();
		Map<String, String> foodMap = mapRawData(rawFoodData);
		return foodMap;	
	}
	
/*
	public Map<String, String> getNutritionData(String url) {
		
		boolean success = false;
		
		//keep trying to get the nutrition data until no errors occur
		while(!success) {
			
			try {
				//get the website code
				driver.get(url);
				
				//select 100g as the serving size
				Select serving_size_100 = new Select(driver.findElement(By.name("serving")));
				serving_size_100.selectByValue("100.0");
				
				//Expand dropdown tabs containing additional nutrition data
				//reversing the list fixes errors where certain dropdown tabs cannot be clicked
				List<WebElement> expand_nutrition_data = driver.findElements(By.className("expand_collapse"));
				Collections.reverse(expand_nutrition_data);
				for (WebElement tab : expand_nutrition_data) {
					tab.click();
				}
				
				//successfully obtained the website data; break the loop
				success = true;
			}
			
			catch(Exception e){
				//Continue trying to get the website data
				System.out.println("Failed to get nutrition data. Trying again.");
				continue;
			}
		}
		
		String food_name = driver.findElement(By.className("facts-heading")).getText();
		
		//find the web element containing the nutrition information tables
		WebElement raw_nutrition_code = driver.findElement(By.id("NutritionInformationSlide"));
		
		//find the tables containing the nutrition information
		List<WebElement> raw_nutrition_tables = raw_nutrition_code.findElements(By.className("groupBorder"));
		
		//initialize empty list to hold the raw nutrient data from the tables
		List<WebElement> raw_nutrition_data = new ArrayList<WebElement>();
		
		//put all nutrients listed in the the raw tables into a single list;
		for (WebElement table : raw_nutrition_tables) {
			
			//split the table into individual web elements for each nutritent
			List<WebElement> nutrients = table.findElements(By.className("clearer"));
			
			//add the nutrient web elements to the raw_nutrient_data list
			raw_nutrition_data.addAll(nutrients);
		}
		
		//create an empty hashmap to hold the nutrient name and value
		Map<String, String> nutrients = new HashMap<>();
		
		nutrients.put("Food Name", food_name);
		//iterate through the nutrient web elements
		for (WebElement nutrient : raw_nutrition_data) {
			
			//get the web text containing the nutrient information
			String nutrient_text = nutrient.getText();
			
			//split the nutrient text into individual values
			String[] nutrient_text_parsed = nutrient_text.split("\n");
			
			//Put the nutrient name, value, and units into variables, trim leading/trailing whitespace from nutrient names
			String nutrient_name = nutrient_text_parsed[0].trim();
			String nutrient_value_str = nutrient_text_parsed[1];
			String nutrient_unit = nutrient_text_parsed[2];
			
			//create an empty double to hold the nutrient value for conversion from string to double
			double nutrient_value;
			
			//convert the nutrient amount into grams from the given unit if applicable
			if (nutrient_value_str.equals("~")) {
				nutrient_value = 0;
			} 
			else if (nutrient_unit.equals("mg")) {
				nutrient_value = Double.parseDouble(nutrient_value_str)/1E3;
			}
			else if (nutrient_unit.equals("mcg")) {
				nutrient_value = Double.parseDouble(nutrient_value_str)/1E6;
			}
			else {
				nutrient_value = Double.parseDouble(nutrient_value_str);
			}
			
			//reformat the nutrient name string (only applies to calories from certain macronutrients)
			if (nutrient_name.equals("From Carbohydrate")) {
				nutrient_name = "Calories From Carbohydrate";
			}
			if (nutrient_name.equals("From Fat")) {
				nutrient_name = "Calories From Fat";
			}
			if (nutrient_name.equals("From Protein")) {
				nutrient_name = "Calories From Protein";
			}
			if (nutrient_name.equals("From Alcohol")) {
				nutrient_name = "Calories From Alcohol";
			}
			
			String nutrient_value_string = Double.toString(nutrient_value);
			//put the nutrient name and value into the hashmap
			nutrients.put(nutrient_name, nutrient_value_string);
		}
		
		//return the nutrients hashmap
		return nutrients;
	}
	*/
	
	
	/**
	 * 
	 * @param
	 * @return
	 */
	public static void convert() {
		
	}
	
	/**
	 * 
	 * @param
	 * @return
	 */
	public static void nameReformatting() {
		
	}
	
	/**
	 * Format all the food items and their respective nutrient value into a table
	 * @param List<List<String>> rows
	 * @return String table
	 */
	public static String tableFormat(List<List<String>> rows) {
		int[] maxColLength = new int[rows.get(0).size()];
		for (List<String> row: rows) {
			for (int i=0; i<row.size(); i++) {
				maxColLength[i] = Math.max(maxColLength[i], row.get(i).length());
			}
		}
		
		StringBuilder tableFormatBuilder = new StringBuilder();
		for (int colLen : maxColLength) {
			tableFormatBuilder.append("%-").append(colLen+3).append("s");
		}
		String tableFormat = tableFormatBuilder.toString();
		setTableFormatString(tableFormat);
		
		StringBuilder table = new StringBuilder();
		for (List<String> row : rows) {
			table.append(String.format(tableFormat, row.toArray(new String[0]))).append("\n");
		}
		
		return table.toString();	
	}
	
	/**
	 * Gets a list of urls to visit
	 * @param String filePath
	 * @return List<String> urls
	 */
	public List<String> getUrls(String filePath){
		//create an empty arraylist to hold the url strings
		List<String> urls = new ArrayList<String>();
		//get the urls from the file
		try {
			urls = Files.readAllLines(new File(filePath).toPath());
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return urls;
	}
	
	/**
	 * 
	 * @param
	 * @return
	 */
	public String getAllFoodData(List<String> urls) {
		
		//create an empty list of maps to hold each food
		List<Map<String, String>> nutrients = new ArrayList<>();
		
		Set<String> nutrientKeys = new HashSet<>();
		//iterate throught the urls
		for (String url : urls) {
//			Map<String, String> foodItem = getNutritionData(url);
			Map<String, String> foodItem = cleanUpFoodItem(getNutritionData(url));
			
			nutrients.add(foodItem);
			nutrientKeys = foodItem.keySet();
		}
		
		List<List<String>> foodItems = new ArrayList<>();
		for (String key : nutrientKeys) {
			List<String> row = new ArrayList<>();
			row.add(key);
			Map<String, String> foodItem_ = new HashMap<>();
			for (Map<String, String> foodItem : nutrients) {
				row.add(foodItem.get(key));
			}
			foodItems.add(row);
		}
		
		
		String table = tableFormat(foodItems);
		
		return table;
	
	}
	
	/**
	 * Cleans up the foodItem map by removing problematic key value pairs that don't represent a nutrient and its value
	 * @param Map<String, String> foodItem
	 * @return Map<String, String> foodItem
	 */
	public Map<String, String> cleanUpFoodItem(Map<String, String> foodItem){
		Iterator<Map.Entry<String, String>> iterator = foodItem.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, String> entry = iterator.next();
			String currNutrient = entry.getKey();
			String cleanedUpNutrient = currNutrient.replaceAll("[^a-zA-Z]", "");
			if (currNutrient.length() - cleanedUpNutrient.length() > 1) {
				iterator.remove();
			}
		}
		return foodItem;
	}
	
	/**
	 * Setting up the scraper
	 * @param void
	 * @return void
	 */
	public NutritionScraper() {
		//create a driver instance upon object creation.
		setUp();
	}
	
	/**
	 * Quit driver 
	 * @param void
	 * @return void
	 */
	public void quitDriver() {
		driver.quit();
		driverActive = false;
	}

	public static void main(String[] args) {
		NutritionScraper scraper = new NutritionScraper();
		
		String[] _args = {"/Users/haiyili/git/project-nutritionscraper/foodList.txt"};
		
		
		String filePath = _args[0];
		List<String> urls = scraper.getUrls(filePath);
		String table = scraper.getAllFoodData(urls);
		System.out.println(table);
		scraper.quitDriver();
	}
}
