package NutritionScraper.main.java;

import java.util.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.lang.StringBuilder;


public class textParsing {
	
	public static String convertToGrams(String nutrientValueStr, String nutrientUnit) {
		double nutrientValue;
		//convert the nutrient amount into grams from the given unit if applicable
		if (nutrientValueStr.equals("~")) {
			nutrientValue = 0.0;
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
	
	public static String renameNutrients(String nutrientName) {
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
	
	public static Map<String, String> mapRawData(List<String> rawData) {
			
		Map<String, String> nutrients = new LinkedHashMap<>();
		
		nutrients.put("Food Name", rawData.get(0));
		rawData.remove(0);
		
		for (String nutrient : rawData) {
				
			//split the nutrient text into individual values
			String[] nutrient_text_parsed = nutrient.split("\n");
			
			//
			String nutrient_name = renameNutrients(nutrient_text_parsed[0]);
			
			
			if (nutrient_name.contains(":")) {
				continue;
			}
		
			String nutrient_value = convertToGrams(nutrient_text_parsed[1], nutrient_text_parsed[2]);
			//put the nutrient name and value into the hashmap
			nutrients.put(nutrient_name, nutrient_value);
		}
		
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
	
	public static List<String> getUrls(String filePath){
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

}
