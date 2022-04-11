package NutritionScraper.main.java;

import java.util.Scanner;

public class PersonalNutritionInfo{
	
	private int weight;
	private int height;
	private String gender;
	
	public void setUp(){
		Scanner apScanner = new Scanner(System.in);
		System.out.println("Enter weight to the nearest kg");
		this.weight  = apScanner.nextInt();
		System.out.println(this.weight);
		
		System.out.println("Enter hegiht in cm");
		this.height = apScanner.nextInt();
		System.out.println(this.height);
		
		System.out.println("Enter m for male or f for female");
		this.gender = apScanner.next();
		System.out.println(this.gender);
		
	}
	
	public double basalMetabolicRate() {
		double lbm = leanBodyMass();
		if(lbm != 0) {
			double calories = 370 + (21.6*lbm);
			return calories;
		}
		return 0;
	}
	
	public double leanBodyMass(){
		if(this.gender.equals("f")) {
			double lbm = (0.252 * this.weight)+(0.473 * this.height) - 48.3;
			return lbm;
		}else if(this.gender.equals("m")) {
			double lbm = (0.407 * this.weight)+(0.267 * this.height) - 19.2;
			return lbm;
		}
		return 0;
	}
	
	
	
	public static void main(String[] args) {
		PersonalNutritionInfo personalNutritionInfo = new PersonalNutritionInfo();
		personalNutritionInfo.setUp();
		personalNutritionInfo.basalMetabolicRate();
	}

}
