#!/bin/bash

git checkout main
java -cp target/project-nutritionscraper-0.0.1-SNAPSHOT-jar-with-dependencies.jar NutritionScraper.main.java.NutritionScraper $1
cd ..
cd ..


