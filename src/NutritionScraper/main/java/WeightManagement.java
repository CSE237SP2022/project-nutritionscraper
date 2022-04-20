package NutritionScraper.main.java;

import java.util.Scanner;
import java.util.regex.Pattern;


public class WeightManagement {
	
	private String loseOrGainWeight= " ";
	private int overHowManyWeeks = 0;
	private int kgs = 0;
	private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
	
	public boolean isNumeric(String strNum) {
		if (strNum == null) {
			return false;
		}
		
		return pattern.matcher(strNum).matches();
	}
	
	
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
		String currentInput = newInput.next();
		if(isNumeric(currentInput)) {
			int numericInput = Integer.valueOf(currentInput);
			if(numericInput>= 0) {
				this.overHowManyWeeks = newInput.nextInt();
			}else{
				System.out.print("Your input is invalid expecting non negative value. "
						+ "Setting Value to 0 (default)");
				this.overHowManyWeeks = 0;
			}
		}
		System.out.print("Your input is invalid expecting an integer value. "
				+ "Setting Value to 0 (default)");
			this.overHowManyWeeks =0;
	}
	
	public void inputKgs(Scanner newInput){
		System.out.println("How many kgs do you want to lose or gain? (type 0 for maintain");
		String currentInput = newInput.next();
		if(isNumeric(currentInput)) {
			int numericInput = Integer.valueOf(currentInput);
			if(numericInput>= 0) {
				this.kgs = newInput.nextInt();
			}else{
				System.out.print("Your input is invalid expecting non negative value. "
						+ "Setting Value to 0 (default)");
				this.kgs = 0;
			}
		}
		System.out.print("Your input is invalid expecting an integer value. "
				+ "Setting Value to 0 (default)");
			this.kgs =0;
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
		double kgPerDay = kgPerWeek / 7;
		return (int) (7700*kgPerDay);
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
		
		System.out.print("hello");
	}

}

