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
	
	public void setKgs(int kg) {
		this.kgs = kg;
		
	}
	
	public void setHowManyWeeks(int weeks) {
		this.overHowManyWeeks = weeks;
	}
	
	public void setLoseOrGainWeight(String goal) {
		this.loseOrGainWeight = goal;
	}
	

	
	public void inputWeightGoals(Scanner newInput) { //done
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
			this.loseOrGainWeight = "maintain";
			System.out.print("Your input is invalid. Weight goals automatically set to maintain. ");
		}	
	}
	
	public void inputOverHowManyWeeks(Scanner newInput){ //done
		System.out.println("In how many weeks do you want to lose or gainweight or maintain weight?");
		String currentInput = newInput.next();
		if(isNumeric(currentInput)) {
			int numericInput = Integer.valueOf(currentInput);
			if(numericInput>= 0) {
				this.overHowManyWeeks = numericInput;
			}else{
				System.out.print("Your input is invalid expecting non negative value. "
						+ "Setting Value to 0 (default)");
				this.overHowManyWeeks = 0;
			}
		}
		else {
		System.out.print("Your input is invalid expecting an integer value. "
				+ "Setting Value to 0 (default)");
			this.overHowManyWeeks =0;
		}
	}
	
	public void inputKgs(Scanner newInput){ //done
		System.out.println("How many kgs do you want to lose or gain? (type 0 for maintain");
		String currentInput = newInput.next();
		if(isNumeric(currentInput)) {
			int numericInput = Integer.valueOf(currentInput);
			if(numericInput>= 0) {
				this.kgs = numericInput;
			}else{
				System.out.print("Your input is invalid expecting non negative value. "
						+ "Setting Value to 0 (default)");
				this.kgs = 0;
			}
		}
		else {
		System.out.print("Your input is invalid expecting an integer value. "
				+ "Setting Value to 0 (default)");
			this.kgs =0;

		}
	}
	
	public int calculateThermicEffectiveFood(PersonalNutritionInfo pni) { //done
		int TEF = (int) (pni.basalMetabolicRate() * .01);
		return TEF;
	}
	
	public int calculateTotalDailyExpenditure(PersonalNutritionInfo pni){ //done
		int TEF = calculateThermicEffectiveFood(pni);
		int TDEE = pni.basalMetabolicRate() + TEF + 500; //replace 500 with calories burned from activity level
		return TDEE;
	}
	
	public int calculateKgPerDay(){ //done
		if (this.overHowManyWeeks == 0){
			System.out.println("Denominator whose value is 0 gives 0 as final result. OverHowManyWeeks is zero.");
			return 0;
		}
		double kgPerWeek = this.kgs / this.overHowManyWeeks;
		double kgPerDay = kgPerWeek / 7;
		return (int) (7700*kgPerDay);
	}
	
	public int calculateLoseWeight(PersonalNutritionInfo pni){ //done
		int TDEE = calculateTotalDailyExpenditure(pni);
		int kgPerDay = calculateKgPerDay();
		System.out.println(TDEE + " " + kgPerDay);
		int calPerDay = TDEE - kgPerDay;
		if(calPerDay > 0){
			return calPerDay;
		}
		System.out.println("Choose a less ambitious goal");
		return -1;
	}
	
	public int calculateGainWeight(PersonalNutritionInfo pni){
		int TDEE = calculateTotalDailyExpenditure(pni);
		int kgPerDay = calculateKgPerDay();
		int calPerDay = TDEE + kgPerDay;
		return calPerDay;
	}
	
	public int caloriesAfterWeightManagement(PersonalNutritionInfo pni){
		if(this.loseOrGainWeight.equals("lose")){
			return calculateLoseWeight(pni);
		} else if(this.loseOrGainWeight.equals("gain")){
			return calculateGainWeight(pni);
		}
		
		return calculateTotalDailyExpenditure(pni);
	}
	
	public void setUp(PersonalNutritionInfo pni){
		System.out.println("Our records say that your gender is " + pni.getGender() + "; your height is " + pni.getHeight() + "cm; your weight is " + pni.getWeight() + "kg.");
		System.out.println("");
		
		setKgs(pni.getWeight());
		
		Scanner newInput = new Scanner(System.in);
		inputWeightGoals(newInput);
		inputOverHowManyWeeks(newInput);
		inputKgs(newInput);
		
		System.out.print("Your caloric need for each day based on your goals is " 
		+ caloriesAfterWeightManagement(pni));
	}
	
	public static void main(String[]args) {
		WeightManagement weightManagement = new WeightManagement();
		PersonalNutritionInfo pni = new PersonalNutritionInfo();
		pni.setGender("F");
		pni.setHeight(175);
		pni.setWeight(60);
		weightManagement.setUp(pni);
		
		
	}

}

