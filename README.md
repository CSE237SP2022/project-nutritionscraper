# project-nutritionscraper
### Daniel user story
- Accomplished tasks: Wrote the main web scraping functionality for the NutritionDataScraper class. This includes making the class set up a webdriver instance so that pages can be scraped upon object creation, the ability to read URL's from a text file, and outputting cleanly formatted nutrition data to the console. 
- Tasks for next time: Add the ability for the user to add which information they wish to be output (likely through direct input or text file), and add the csv output formatting. 

### Efimia user story 
- Accomplished tasks: Created a bash script that compiles and runs the java program from the command line. 
- Created a new branch in github from the command line to house this bash script while it is still in progress. 
- Tasks for the next iteration: Get the nurtition info currently printing out in consol to print into a text file that then is opened and acessed by the bash script. Help with JUnit testing for new functionalities. 

### Gautami user story: 
- Accomplished tasks: Created and wrote the bash script that runs along with the Java program created (NutritionDataScraper). Made a new branch in github that is able to run with the bash script. Managed the project management aspect by creating cards on the project and seeing what tasks have been accomplished and still need to be done. 
- Tasks for the next iteration: Write JUnit tests to ensure all of our functionalities are working as planned. Having all of the nutrition info that we're scraping to printed out in a file to be opened by the bash script. 

### Constance user story 
- Accomplished tasks: 1. Cleaned up the NutritionDataScraper output table, allowing it to exclude key value pairs exceptions in the scraping process. 2. Created and managed project tracking board, for project management and task assignment.   
- Tasks for next time: Write 1. JUnit tests for different functionalities, catching server errors and exceptions. 2. clean up NutritionDataScraper class code, following clean code principles. 



### How to compile the code
- Download maven. This can be done by executing the command brew install maven
- navigate to the project project directory (/project-nutritionscraper)
- execute the command "mvn clean compile assembly:single"

### How to run the program
- First open the foodList.txt file located at /project-nutritionscraper. This file contains a list of foods from https://nutritiondata.self.com/. Add any desired foods into by entering the URL of the food on a new line. Save and close the file when done.
- Execute the bash script with the filepath of the foodList.txt file as the first argument (e.g. bash nutritionscript.sh foodList.txt ). If the program keeps outputting "Failed to get nutrition data. Trying again.", try exiting the program and re-running it. This error occurs due to pop-up ads on the website.
