package NutritionScraper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;

import java.util.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.lang.StringBuilder;


public class NutritionScraper {
	
	private WebDriver driver;
	
	public void setUp() {
		
		//set the chromedriver directory
		System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
		
		//set chrome to headless mode (does not open chrome window)
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");		
		
		//compile the webdriver
		driver = new ChromeDriver(options);
	}
	
	//method to get the nutrition info for a single food
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
		List<WebElement> raw_nutrition_tables= raw_nutrition_code.findElements(By.className("groupBorder"));
		
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
			else if (nutrient_unit == "mg") {
				nutrient_value = Double.parseDouble(nutrient_value_str)/1E3;
			}
			else if (nutrient_unit == "mcg") {
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
		
		//quit the driver instance after we are finished getting the nutrient information
		
		//return the nutrients hashmap
		return nutrients;
	}
	
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
		StringBuilder table = new StringBuilder();
		for (List<String> row : rows) {
			table.append(String.format(tableFormat, row.toArray(new String[0]))).append("\n");
		}
		
		return table.toString();
		
	}
	
	
	public void getAllFoodData(String filePath) {
		//create an empty arraylist to hold the url strings
		List<String> urls = new ArrayList<String>();
		//get the urls from the file
		try {
			urls = Files.readAllLines(new File(filePath).toPath());
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		List<Map<String, String>> nutrients = new ArrayList<>();
		
		Set<String> nutrientKeys = new HashSet<>();
		//iterate throught the urls
		for (String url : urls) {
			Map<String, String> foodItem = getNutritionData(url);
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
		System.out.println(table); 
		
		driver.quit();
	}
	
		
	public NutritionScraper() {
		//create a driver instance upon object creation.
		setUp();
	}

	public static void main(String[] args) {
		NutritionScraper scraper = new NutritionScraper();
		String url = "https://nutritiondata.self.com/facts/cereal-grains-and-pasta/5718/2";
		
	
		
		String filePath = "resources/foodList.txt";
		scraper.getAllFoodData(filePath);
//		Map<String, Double> nutrients = scraper.getNutritionData(url);
		
//		for (Map.Entry<String, Double> nutrient : nutrients.entrySet()) {
//			System.out.println(nutrient.getKey() + " : " + nutrient.getValue());
//		}
		
		
	}

}
