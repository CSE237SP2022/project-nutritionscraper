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
	
	public void setUp() {
		
		//create a chromedriver instance
		WebDriverManager.chromedriver().setup();
		
		//set chrome to headless mode (does not open chrome window)
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");		
		
		//compile the webdriver
		driver = new ChromeDriver(options);
	}
	
	public NutritionScraper() {
		//create a driver instance upon object creation.
		setUp();
	}
	
	public void quitDriver() {
		driver.quit();
	}

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
	
	public Map<String, String> getNutritionData(String url){
		goToFoodPage(url);
		List<String> rawFoodData = getRawData();
		Map<String, String> foodMap = textParsing.mapRawData(rawFoodData);
		return foodMap;	
		}
	
	public String getAllFoodData(List<String> urls) {
		
		//create an empty list of maps to hold each food
		List<Map<String, String>> nutrients = new ArrayList<>();
		
		Set<String> nutrientKeys = new HashSet<>();
		//iterate through the urls
		for (String url : urls) {
			Map<String, String> foodItem = getNutritionData(url);
			
			nutrients.add(foodItem);
			nutrientKeys = foodItem.keySet();
		}
		
		List<List<String>> foodItems = new ArrayList<>();
		for (String key : nutrientKeys) {
			List<String> row = new ArrayList<>();
			row.add(key);
			for (Map<String, String> foodItem : nutrients) {
				row.add(foodItem.get(key));
			}
			foodItems.add(row);
		}
		
		String table = textParsing.tableFormat(foodItems);
		
		return table;
	
	}

	public static void main(String[] args) {
		NutritionScraper scraper = new NutritionScraper();
		String filePath = args[0];
		List<String> urls = textParsing.getUrls(filePath);
		String table = scraper.getAllFoodData(urls);
		System.out.println(table);
		scraper.quitDriver();
	}
}
