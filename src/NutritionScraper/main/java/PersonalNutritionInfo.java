package NutritionScraper.main.java;


import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

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
	   else {
		   this.weight = 0;
	       System.out.println("Your input is invalid. Expecting an integer value. "
					+ "Setting Value to 0 (default)");
	   }    
	
	}
	
	public void inputHeight(Scanner apScanner) {

	    System.out.println("What is your height (in cm)?");
	    String s = apScanner.next();
	    if (isNumeric(s)) {
	    	int height = (int)Double.parseDouble(s);
	    	if ((height < 220) && (height>56)) {
	    		this.height = height;
	    		 System.out.println("Your height is " + this.height + "cm");
	  
	    	}
	    	else {
		    	this.height = 0;
		    	System.out.println("Your input is invalid. Setting Value to 0 (default)");
	    	}
	    }
	   else {
		   this.height = 0;
		   System.out.println("Your input is invalid. Expecting an integer value. "
					+ "Setting Value to 0 (default)");
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
	    	String[] possibleGenders = {"F", "M"};
	    	Random ran = new Random();
	    	String random_gender = possibleGenders[ran.nextInt(possibleGenders.length)];
	    	this.gender = random_gender;
	    	System.out.println("Your input is invalid. Your randomly generated gender is " + this.gender);
	    }
		      

	}
	public void setUp(){
		Scanner apScanner = new Scanner(System.in);
		
		// get weight
		inputWeight(apScanner);

		
		// get height
		inputHeight(apScanner);

		
		// get gender
		inputGender(apScanner);

		
		System.out.println("Your LBM is " + leanBodyMass());
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
	
	
	public static void main(String[] args) {
		PersonalNutritionInfo personalNutritionInfo = new PersonalNutritionInfo();
		personalNutritionInfo.setUp();
	}


}
