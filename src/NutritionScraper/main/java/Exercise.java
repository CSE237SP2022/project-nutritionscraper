package NutritionScraper.main.java;

import java.util.Scanner;
import java.util.regex.Pattern; 

public class Exercise {
	

	private int duration=0;  
	private int met=0; 
	private int weightKgs=0; 
	private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
	
	public boolean isNumeric(String strNum) {
		if (strNum == null) {
			return false;
		}
		
		return pattern.matcher(strNum).matches();
	}
	
	public int getDuration() {
		return this.duration; 
	}
	
	public int getMet() {
		return this.met; 
	}

	public int getWeightKgs() {
		return this.weightKgs; 
	}
	
	public void setMet(int met) {
		this.met = met;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public void setWeight(int weight) {
		this.weightKgs = weight;
	}
	
	public void inputExerciseDurationInfo (Scanner input) {
		System.out.println("Type in the number of minutes that you exercised for.");
		String currentInput = input.next(); 
		if(isNumeric(currentInput)) {
			int numericInput = (int) Math.round(Double.valueOf(currentInput));
			if(numericInput>= 0) {
				this.duration = numericInput;
			}else{
				System.out.println("Your input is invalid expecting non negative integer value. "
						+ "Setting Value to 0 (default)");
				this.duration = 0;
			}
		}
		else{
			System.out.println("Your input is invalid expecting an integer value. "
					+ "Setting Value to 0 (default)");
				this.duration =0;
		}
	}
	
	public void inputMetbolicEquivalentForTaskInfo (Scanner input) {
		System.out.println("How intense was your activity? Type in '3' if you did a light-intensity activity. Type in '5' for a moderate-intensity activity. Type in '7' for a vigorous-intensity activity.");
		String currentInput = input.next(); 
		if(isNumeric(currentInput)) {
			int numericInput = (int) Math.round(Double.valueOf(currentInput));
			if(numericInput==3 || numericInput==5 || numericInput==7 ) {
				this.met = numericInput;
			}else{
				System.out.println("Your input is invalid. EXpecting non negative integer value: 3, 5, or 9 "
						+ "Setting Value to 0 (default)");
				this.met = 0;
			}
		}
		else{
			System.out.println("Your input is invalid. Expecting an integer value. "
					+ "Setting Value to 0 (default)");
				this.met =0;
		}
	}
	
	public void inputWeightInfo (Scanner input) {
		System.out.println("What is your weight in kgs?"); 
		String currentInput = input.next(); 
		if(isNumeric(currentInput)) {
			int numericInput = (int) Math.round(Double.valueOf(currentInput));
			if(numericInput>= 0) {
				this.weightKgs = numericInput;
			}else{
				System.out.println("Your input is invalid. Expecting non negative integer value. "
						+ "Setting Value to 0 (default)");
				this.weightKgs = 0;
			}
		}
		else{
			System.out.println("Your input is invalid. Expecting an integer value. "
					+ "Setting Value to 0 (default)");
				this.weightKgs =0;
		}

	}
	
	public double calculateCaloriesBurned() {
		double caloriesBurned = duration * ((met * 3.5 * weightKgs)/200.0); 
		caloriesBurned = Math.round(caloriesBurned * 100.0)/100.0;
		return caloriesBurned; 
	}
	
	public void setUp() {
		Scanner input = new Scanner (System.in); 
		inputExerciseDurationInfo (input); 
		inputMetbolicEquivalentForTaskInfo (input); 
		inputWeightInfo (input); 
		
		System.out.println("The amount of calories you burned in your workout is " + calculateCaloriesBurned()); 
	}
	
	public static void main (String[]args) {
		Exercise exercise = new Exercise(); 
		exercise.setUp();
	}
}
