package NutritionScraper.main.java;


import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.lang.Math;

public class PersonalNutritionInfo{
	
	private int weight = 0;
	private int height = 0;
	private String gender = null;
	private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
	
	public boolean isNumeric(String strNum) {
		if (strNum == null) {
			return false;
		}
		
		return pattern.matcher(strNum).matches();
	}
	
	public int getWeight() {
		return this.weight;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public String getGender() {
		return this.gender;
	}
	
	public int setWeight(int weight) {
		this.weight = weight;
		return this.weight;
	}
	
	public int setHeight(int height) {
		this.height = height;
		return this.height;
	}
	
	public String setGender(String gender) {
		this.gender = gender;
		return this.gender;
	}
	
	public void inputWeight(Scanner apScanner) {

	    System.out.println("What is your weight (in kg)?");
	    String s = apScanner.next();
	    if (isNumeric(s)) {
	    	int weight = (int)Double.parseDouble(s);
	    	checksWeightInput(weight);
	    }
	   else {
		   this.weight = 0;
	       System.out.println("Your input is invalid. Expecting an integer value. "
					+ "Setting Value to 0 (default)");
	   }    
	
	}

	private void checksWeightInput(int weight) {
		if ((weight < 200) && (weight>0)) {
			this.weight = weight;
			System.out.println("Your weight is " + this.weight + "kg");

		}
		else {
			this.weight = 0;
			System.out.println("Your input is invalid. Expecting non negative integer value. "
					+ "Setting Value to 0 (default)");
		}
	}
	
	public void inputHeight(Scanner apScanner) {

	    System.out.println("What is your height (in cm)?");
	    String s = apScanner.next();
	    if (isNumeric(s)) {
	    	int height = (int)Double.parseDouble(s);
	    	checkHeightValue(height);
	    }
	   else {
		   this.height = 0;
		   System.out.println("Your input is invalid. Expecting an integer value. "
					+ "Setting Value to 0 (default)");
	   }    
	
	}

	private void checkHeightValue(int height) {
		if ((height < 220) && (height>56)) {
			this.height = height;
			 System.out.println("Your height is " + this.height + "cm");
  
		}
		else {
			this.height = 0;
			System.out.println("Your input is invalid. Setting Value to 0 (default)");
		}
	}
	
	public void inputGender(Scanner apScanner) {

	    System.out.println("What is your gender? Enter M for male or F for female");
	    String s = apScanner.next();
	
	    if (s.equals("M")|| s.equals("F") || s.equals("m") || s.equals("f")) {
	    	if (s.equals("m")) {
	    		this.gender = "M";
	    	}
	    	else if (s.equals("f")) {
	    		this.gender = "F";
	    	}
	    	else {
	    		this.gender = s;
	    	}
			System.out.println("Your gender is " + this.gender);
	    }
	    else {
	    	randomizesGender();
	    	System.out.println("Your input is invalid. Your randomly generated gender is " + this.gender);
	    }
		      

	}

	private void randomizesGender() {
		String[] possibleGenders = {"F", "M"};
		Random ran = new Random();
		String random_gender = possibleGenders[ran.nextInt(possibleGenders.length)];
		this.gender = random_gender;
	}
	public void setUp(){
		Scanner apScanner = new Scanner(System.in);
		inputWeight(apScanner);
		inputHeight(apScanner);
		inputGender(apScanner);

		System.out.println("Your LBM is " + leanBodyMass());
		System.out.println("Your BMR is " + basalMetabolicRate()); 
		
		double[] ibwRange = calculateIBW();
		calculateWeightRanges(ibwRange[0], ibwRange[1]);
		double bmi = calculateBMI();
		
	}
	
	public double leanBodyMass(){
		if (this.gender == null) {
			return 0.00;
		}
		if(this.gender.equals("F")) {
			double LBM = (0.252 * this.weight)+(0.473 * this.height) - 48.3;
			return Math.round(LBM * 100.0)/100.0;
		
		}else if(this.gender.equals("M")) {
			double LBM = (0.407 * this.weight)+(0.267 * this.height) - 19.2;
			return Math.round(LBM * 100.0)/100.0;
		}
		return 0.00;
	}
	
	public int basalMetabolicRate(){
		double lbm = leanBodyMass();
		int bmr = (int) (370 + (21.6 * lbm));
		return bmr;
	}


	public PersonalNutritionInfo() {
		
	}
	
	public double calculateBMI() {
		double bmi = (double)(this.weight/Math.pow((double)this.height/100,2));
		if (bmi < 18.5) {
			System.out.println("Your BMI is " + String.format("%.2f",bmi) + " which is classified as underweight");
		}
		else if (bmi >= 18.5 & bmi <=24.9) {
			System.out.println("Your BMI is " + String.format("%.2f",bmi) + " which is classified as healthy");
		}
		else if (bmi >= 25.0 & bmi <=29.9) {
			System.out.println("Your BMI is " + String.format("%.2f",bmi) + " which is classified as overweight");
		}
		else {
			System.out.println("Your BMI is " + String.format("%.2f",bmi) + " which is classified as obese");
		}
		return bmi;
	}
	
	public double[] calculateIBW() {
		double[] ibwRange = new double[2];
		double lowerRange = 18.5*Math.pow((double)this.height/100,2);
		double upperRange = 24.9*Math.pow((double)this.height/100,2);
		ibwRange[0] = lowerRange;
		ibwRange[1] = upperRange;
		System.out.print("The healthy weight range for your body type is between " + String.format("%.2f",lowerRange) + " kgs and " +  String.format("%.2f",upperRange) + " kgs.");
		calculateWeightRanges(lowerRange, upperRange);
		return ibwRange;
	}

	private void calculateWeightRanges(double lowerRange, double upperRange) {
		double weightPercent;
		if (this.weight > upperRange) {
			weightPercent = 100*(this.weight/upperRange);
			System.out.println("Your body weight is " +  String.format("%.2f",weightPercent) + "% over the upper limit of the healthy range");
		}
		if (this.weight < lowerRange) {
			weightPercent = 100*(this.weight/lowerRange);
			System.out.println("Your body weight is " + String.format("%.2f",weightPercent) + "% under the lower limit of the healthy range");
		}
		else {
			System.out.println("You are within the healthy body weight range");
		}
	}
	
	
	
	public static void main(String[] args) {
		PersonalNutritionInfo personalNutritionInfo = new PersonalNutritionInfo();
		personalNutritionInfo.setUp();
	}


}
