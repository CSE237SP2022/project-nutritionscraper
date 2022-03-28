#!/bin/bash


cd src/NutritionScraper
javac NutritionScraper.java
java NutritionScraper.java 
TEXTFILE=cat $1
read TEXTFILE


