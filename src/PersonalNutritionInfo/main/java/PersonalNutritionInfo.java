package PersonalNutritionInfo.main.java;

import java.util.Scanner;
import java.util.regex.Pattern;

public class PersonalNutritionInfo{
	
	private int weight;
	private int height;
	private String gender;
	private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
	
	public boolean isNumeric(String strNum) {
		if (strNum == null) {
			return false;
		}
		
		return pattern.matcher(strNum).matches();
	}
	
	public void setUp(){
		Scanner apScanner = new Scanner(System.in);
		
		// get weight
		while (true) {
		    System.out.println("What is your weight (in kg)?");
		    String s = apScanner.next();
		    if (isNumeric(s)) {
		    	int weight = (int)Double.parseDouble(s);
		    	if ((weight < 200) && (weight>0)) {
		    		this.weight = weight;
		    		System.out.println("Your weight is " + this.weight + "kg");
		    		break;
		    	}
		    	System.out.println("ERROR Please enter a valid weight");
		    }
		   else {
		        System.out.println("ERROR Please enter a valid weight");
		   }    
		}
		
		// get height
		while (true) {
		    System.out.println("What is your height (in cm)?");
		    String s = apScanner.next();
		    if (isNumeric(s)) {
		    	int height = (int)Double.parseDouble(s);
		    	if ((height < 220) && (height>56)) {
		    		this.height = height;
		    		 System.out.println("Your height is " + this.height + "cm");
		    		break;
		    	}
		    	System.out.println("Error: invalid input. Please enter a valid height \n");
		    }
		   else {
			   System.out.println("Error: invalid input. Please enter a valid height \n");
		   }    
		}
		
		// get gender
		while (true) {
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
				break;
		    }
		    else {
		    	System.out.println("Error: invalid input. Please enter a valid gender.");
		    }
		      
		}
		
		System.out.println("Your LBM is " + leanBodyMass());
	}
	
	public double leanBodyMass(){
		if(this.gender.equals("F")) {
			double LBM = (0.252 * this.weight)+(0.473 * this.height) - 48.3;
			return LBM;
		}else if(this.gender.equals("M")) {
			double LBM = (0.407 * this.weight)+(0.267 * this.height) - 19.2;
			return LBM;
		}
		return 0;
	}
	
	
	
	public static void main(String[] args) {
		PersonalNutritionInfo personalNutritionInfo = new PersonalNutritionInfo();
		personalNutritionInfo.setUp();
	}

}
