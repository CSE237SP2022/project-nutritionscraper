# project-nutritionscraper
### Functionality of project 
- PersonalNutrition Info.java takes in a users height, weight, and gender and outputs your lean body mass. The Excerise.java class calculates how many caliries you would burn given your weight, activity level, and duration of activity. The WeightManagement.java takes in a persons basal metablic rate and how much weight they want to lose over a given period time and outputs how many calories you need to each everyday. 

### Daniel user story
-  Iteration 1:
	- Accomplished tasks: Wrote the main web scraping functionality for the NutritionDataScraper class. This includes making the class set up a webdriver instance so that pages can be scraped upon object creation, the ability to read URL's from a text file, and outputting cleanly formatted nutrition data to the console.
	- Tasks for next time: Add the ability for the user to add which information they wish to be output (likely through direct input or text file), and add the csv output formatting.


- Iteration 2: 
	- Accomplished tasks: Split the original webscraping class into two separate classes; one for controlling the web scraper, and one for parsing the output of the webscraper. This required that many methods be broken up into methods which handle smaller tasks. Additionally, this required a complete reworking of the unit tests. In each class' respective branch, there is a unit test file which the functionality of each new method. It should be more obvious to the user what each unit test does now. The overall functionality of the script remains largely the same aside from the methods being broken up. However, one change that should be good for future functionality is that the output of the web scraper is now in the format of a doubly nested array list. This means that the formatted printing is its own method, which means that CSV output should be significantly easier in the next iteration. I also added the ability for the user to select certain nutrients when running the program.
	- Tasks for next time: fix repository structure so that everything works cleanly together (some things are a little finnicky). Coding tasks will consist of adding the diet planning module. This will consist of outputting a diet plan for the user based on their physioligical traits and their preferred foods. This can be formulated as a constrained least squares optimization problem, where the constraints are that each free variable (i.e. the food amount) will be >=0. 
	

### Efimia user story 
- Iteration 1:
	- Accomplished tasks: Created a bash script that compiles and runs the java program from the command line. 
	- Created a new branch in github from the command line to house this bash script while it is still in progress. 
	- Tasks for the next iteration: Get the nurtition info currently printing out in consol to print into a text file that then is opened and acessed by the bash script. Help with JUnit testing for new functionalities. 
- Iteration 2: 
	- Accomplished tasks: Cretaed new class for personal nutrition info that asks for user input about their height, weight, and gender. Created a 	
method to calucalte the lean body mass using the input of the users. Created a method to calculate the basal metabolic rate, using the lean body 
mass calculations. 
	- Task for next iteration: Add a function that update the basal metabloic rate by factoring in activity level of the user.
- Iteration 3: 
	- Accomplished Tasks: Created WeightManagement class and the methods to calculate how many calories needed to lose or gain weight or maintain weight over a given period of time and all helper methods needed for sucessful calculations. 
	
### Gautami user story: 
Iteration 1: 
- Accomplished tasks: Created and wrote the bash script that runs along with the Java program created (NutritionDataScraper). Made a new branch in github that is able to run with the bash script. Managed the project management aspect by creating cards on the project and seeing what tasks have been accomplished and still need to be done. 
- Tasks for the next iteration: Write JUnit tests to ensure all of our functionalities are working as planned. Having all of the nutrition info that we're scraping to printed out in a file to be opened by the bash script. 

Iteration 2: 
- Accomplished tasks: Implemented additional functionality by creating a new Java class to retrieve user inputs about height, weight, and gender and wrote methods to calculate the lean body mass and basal metabolic rate from the inputs to return back to users. These currently don't take in the activity level of the user. To improve from the last iteration, I cleaned up the files to ensure that only the necessary files were included in each of the branches and cleaned up the old methods by breaking them up and splitting into classes. 
- Tasks for the next iteration: Add additional functionality around diet planning so that the user can receive a personalized diet plan with food and biometric inputs

### Constance user story 
Iteration 1:
- Accomplished tasks: 1. Cleaned up the NutritionDataScraper output table, allowing it to exclude key value pairs exceptions in the scraping process. 2. Created and managed project tracking board, for project management and task assignment.   
- Tasks for next time: Write 1. JUnit tests for different functionalities, catching server errors and exceptions. 2. clean up NutritionDataScraper class code, following clean code principles. 

Iteration 2:
- Accomplished tasks (iteration 2): 1. Wrote JUnit tests for PersonalNutritionInfo. 2. Reformatted and applied clean code practices to PersonalNutritionInfo. 3. Cleaned up NutritionDataScraper class code 4. Upkept and majaged project tracking board.   
- Tasks for next time: 1. Explore how LBM(lean body mass) and BMR(basal metabolic rate) can add value to the foodItems in our input.txt file.



### How to compile and run the code
- cd into project-nutritionscraper
- if you want to compile and run PersonalNutritionInfo.java run ./runpersonal.sh
- if you want to compile and run Excerise.java run ./runexercise.sh
- if you want to compile and run WeightManagement.java run ./run.sh




