package NutritionScraper.main.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class textParsingTest {

	@Test
	void noDataToGrams() {
		String value = "~";
		String unit = " ";
		String converted = textParsing.convertToGrams(value, unit);
		assertEquals("0.0", converted);
	}
	
	@Test
	void mgToGrams() {
		String value = "1000";
		String unit = "mg";
		String converted = textParsing.convertToGrams(value, unit);
		assertEquals("1.0", converted);
	}
	
	@Test
	void mcgToGrams() {
		String value = "1000000";
		String unit = "mcg";
		String converted = textParsing.convertToGrams(value, unit);
		assertEquals("1.0", converted);
	}
	
	@Test
	void gramsUnitToGrams() {
		String value = "1";
		String unit = "g";
		String converted = textParsing.convertToGrams(value, unit);
		assertEquals("1.0", converted);
	}
	
	@Test
	void iuToIU() {
		String value = "1";
		String unit = "IU";
		String converted = textParsing.convertToGrams(value, unit);
		assertEquals("1.0", converted);
	}
	
	@Test
	void renameCarbs() {
		String initialName = "From Carbohydrate";
		String converted = textParsing.renameNutrients(initialName);
		assertEquals("Calories From Carbohydrate", converted);
	}
	
	@Test
	void renameFat() {
		String initialName = "From Fat";
		String converted = textParsing.renameNutrients(initialName);
		assertEquals("Calories From Fat", converted);
	}
	
	@Test
	void renameProtein() {
		String initialName = "From Protein";
		String converted = textParsing.renameNutrients(initialName);
		assertEquals("Calories From Protein", converted);
	}
	
	@Test
	void renameAlcohol() {
		String initialName = "From Alcohol";
		String converted = textParsing.renameNutrients(initialName);
		assertEquals("Calories From Alcohol", converted);
	}


}
