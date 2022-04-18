package NutritionScraper.main.java;

import java.util.Scanner;

public class WeightManagement {
	
	private String loseOrGainWeight;
	private int overHowManyWeeks;
	private int kgs;
	
	public String getGoals() {
		return this.loseOrGainWeight;
	}
	
	public int getOverHowManyWeeks() {
		return this.overHowManyWeeks;
	}
	
	public int getKgs(){
		return this.kgs;
	}
	
	public void inputWeightGoals(Scanner newInput) {
		System.out.println("Do you want to lose weight type 'l' or gain weight type 'g' or maintain weight"
				+ " 'm'?");
		String inputPlaceHolder = newInput.next();
		if(inputPlaceHolder.equals("l")) {
			this.loseOrGainWeight = "lose";
		} else if(inputPlaceHolder.equals("g")) {
			this.loseOrGainWeight = "gain";
		} else if(inputPlaceHolder.equals("m")) {
			this.loseOrGainWeight = "maintain";
		}else {
			System.out.print("INVALID CHOOSE AGAIN");
		}	
	}
	
	public void inputOverHowManyWeeks(Scanner newInput){
		System.out.println("In how many weeks do you want to lose or gainweight or maintain weight?");
		this.overHowManyWeeks = newInput.nextInt();
	}
	
	public void inputKgs(Scanner newInput){
		System.out.println("How many kgs do you want to lose or gain? (type 0 for maintain");
		this.kgs = newInput.nextInt();
	}
	
	public int calculateThermicEffectiveFood() {
		PersonalNutritionInfo bmr = new PersonalNutritionInfo();
		int TEF = (int) (bmr.basalMetabolicRate() * .01);
		return TEF;
	}
	
	public int calculateTotalDailyExpenditure(){
		PersonalNutritionInfo bmr = new PersonalNutritionInfo();
		int TEF = calculateThermicEffectiveFood();
		int TDEE = bmr.basalMetabolicRate() + TEF + 500; //replace 500 with calories burned from activity level
		return TDEE;
	}
	
	public int calculateKgPerDay(){
		int kgPerWeek = this.kgs / this.overHowManyWeeks;
		int kgPerDay = kgPerWeek / 7;
		return 7700*kgPerDay;
	}
	
	public int calculateLoseWeight(){
		int TDEE = calculateTotalDailyExpenditure();
		int kgPerDay = calculateKgPerDay();
		int calPerDay = TDEE - kgPerDay;
		if(calPerDay > 0){
			return calPerDay;
		}
		System.out.println("Choose a less ambitious goal");
		return -1;
	}
	
	public int calculateGainWeight(){
		int TDEE = calculateTotalDailyExpenditure();
		int kgPerDay = calculateKgPerDay();
		int calPerDay = TDEE + kgPerDay;
		return calPerDay;
	}
	
	public int caloriesAfterWeightManagement(){
		if(this.loseOrGainWeight.equals("lose")){
			return calculateLoseWeight();
		} else if(this.loseOrGainWeight.equals("gain")){
			return calculateGainWeight();
		}
		
		return calculateTotalDailyExpenditure();
	}
	
	public void setUp(){
		Scanner newInput = new Scanner(System.in);
		inputWeightGoals(newInput);
		inputOverHowManyWeeks(newInput);
		inputKgs(newInput);
		
		System.out.print("Your caloric need for each day based on your goals is " 
		+ caloriesAfterWeightManagement());
	}
	
	public static void main(String[]args) {
		WeightManagement weightManagement = new WeightManagement();
		weightManagement.setUp();
	}

}
