#!/bin/bash

git checkout main
cd src
cd NutritionScraper
javac NutritionScraper.java
java NutritionScraper.java 
cd ..
cd ..
git checkout GautamiAndEfimia


