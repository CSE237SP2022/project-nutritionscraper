#!/bin/bash

git checkout main
cd resources 
TEXTFILE=$(cat foodList.txt)
cd src
javac NutritionScraper.java
java NutritionScraper.java 
read TEXTFILE
cd ..
git checkout GautamiAndEfimia


