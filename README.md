# project-nutritionscraper
### Daniel user story
-  Iteration 1:
	- Accomplished tasks: Wrote the main web scraping functionality for the NutritionDataScraper class. This includes making the class set up a webdriver instance so that pages can be scraped upon object creation, the ability to read URL's from a text file, and outputting cleanly formatted nutrition data to the console.
	- Tasks for next time: Add the ability for the user to add which information they wish to be output (likely through direct input or text file), and add the csv output formatting.


- Iteration 2: 
	- Accomplished tasks: Split the original webscraping class into two separate classes; one for controlling the web scraper, and one for parsing the output of the webscraper. This required that many methods be broken up into methods which handle smaller tasks. Additionally, this required a complete reworking of the unit tests. In each class' respective branch, there is a unit test file which the functionality of each new method. It should be more obvious to the user what each unit test does now. The overall functionality of the script remains largely the same aside from the methods being broken up. However, one change that should be good for future functionality is that the output of the web scraper is now in the format of a doubly nested array list. This means that the formatted printing is its own method, which means that CSV output should be significantly easier in the next iteration.
	

### Efimia user story 
- Accomplished tasks: Created a bash script that compiles and runs the java program from the command line. 
- Created a new branch in github from the command line to house this bash script while it is still in progress. 
- Tasks for the next iteration: Get the nurtition info currently printing out in consol to print into a text file that then is opened and acessed by the bash script. Help with JUnit testing for new functionalities. 

### Gautami user story: 
- Accomplished tasks: Created and wrote the bash script that runs along with the Java program created (NutritionDataScraper). Made a new branch in github that is able to run with the bash script. Managed the project management aspect by creating cards on the project and seeing what tasks have been accomplished and still need to be done. 
- Tasks for the next iteration: Write JUnit tests to ensure all of our functionalities are working as planned. Having all of the nutrition info that we're scraping to printed out in a file to be opened by the bash script. 

### Constance user story 
- Accomplished tasks (iteration 2): 1. Wrote JUnit tests for PersonalNutritionInfo. 2. Reformatted and applied clean code practices to PersonalNutritionInfo. 3. Cleaned up NutritionDataScraper class code 4. Upkept and majaged project tracking board.   
- Tasks for next time: 1. Explore how LBM(lean body mass) and BMR(basal metabolic rate) can add value to the foodItems in our input.txt file.



### How to compile the code
- Download maven. This can be done by executing the command brew install maven
- navigate to the project project directory (/project-nutritionscraper)
- execute the command "mvn clean compile assembly:single"

### How to run the program
- First open the foodList.txt file located at /project-nutritionscraper. This file contains a list of foods from https://nutritiondata.self.com/. Add any desired foods into by entering the URL of the food on a new line. Save and close the file when done.
- Execute the bash script with the filepath of the foodList.txt file as the first argument (e.g. bash nutritionscript.sh foodList.txt ). If the program keeps outputting "Failed to get nutrition data. Trying again.", try exiting the program and re-running it. This error occurs due to pop-up ads on the website.
