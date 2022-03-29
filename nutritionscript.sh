#!/bin/bash

git checkout main
cd resources 
TEXTFILE=$(cat foodList.txt)
cd ..
cd src
cd NutritionScraper
javac NutritionScraper.java
java NutritionScraper.java 
read TEXTFILE
cd ..
cd ..
git checkout GautamiAndEfimia


