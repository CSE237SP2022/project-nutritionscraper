package NutritionScraper.main.java;

import java.util.Scanner;
import java.util.regex.Pattern; 

public class Exercise {
	
	private int duration;  
	private int met; 
	private double weightKgs; 
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
	
	public double getWeightKgs() {
		return this.weightKgs; 
	}
	
	public void inputExerciseDurationInfo (Scanner input) {
		System.out.println("Type in the number of minutes that you exercised for.");
		this.duration = input.nextInt();
	}
	
	public void inputMetbolicEquivalentForTaskInfo (Scanner input) {
		System.out.println("How intense was your activity? Type in '3' if you did a light-intensity activity. Type in '5' for a moderate-intensity activity. Type in '7' for a vigorous-intensity activity.");
		this.met=input.nextInt(); 
	}
	
	public void inputWeightInfo (Scanner input) {
		System.out.println("What is your weight in kgs?"); 
		this.weightKgs = input.nextDouble(); 
	}
	
	public double calculateCaloriesBurned() {
		double caloriesBurned = duration * ((met * 3.5 * weightKgs)/200); 
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
