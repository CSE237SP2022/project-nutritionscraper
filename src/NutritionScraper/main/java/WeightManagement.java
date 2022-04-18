package NutritionScraper.main.java;

import java.util.Scanner;

public class WeightManagement {
	
	private String loseOrGainWeight;
	private int overHowManyWeeks;
	
	public String getGoals() {
		return this.loseOrGainWeight;
	}
	
	public int getOverHowManyWeeks() {
		return this.overHowManyWeeks;
	}
	
	public void inputWeightGoals(Scanner newInput) {
		System.out.println("Do you want to lose weight type 'l' or gain weight type 'g' or maintain weight"
				+ " 'm'?");
		String inputPlaceHolder = newInput.next();
		if(inputPlaceHolder.equals("l")) {
			loseOrGainWeight = "lose";
		} else if(inputPlaceHolder.equals("g")) {
			loseOrGainWeight = "gain";
		} else if(inputPlaceHolder.equals("m")) {
			loseOrGainWeight = "maintain";
		}else {
			System.out.print("INVALID CHOOSE AGAIN");
		}	
	}
	
	public void inputOverHowManyWeeks(Scanner newInput){
		System.out.println("In how many weeks do you want to lose or gainweight or maintain weight?");
		overHowManyWeeks = newInput.nextInt();
	}
	
	public int calculateTEF() {
		PersonalNutritionInfo bmr = new PersonalNutritionInfo();
		 int TEF = (int) (bmr.basalMetabolicRate() * .01);
		 return TEF;
	}
	
	public void setUp(){
		Scanner newInput = new Scanner(System.in);
		inputWeightGoals(newInput);
		inputOverHowManyWeeks(newInput);
	}
	
	public static void main(String[]args) {
		WeightManagement weightManagement = new WeightManagement();
		weightManagement.setUp();
	}

}
