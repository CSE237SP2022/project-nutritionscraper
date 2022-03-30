#!/bin/bash

git checkout main
cd src
mvn clean compile assembly:single
java -cp target/project-nutritionscraper-0.0.1-SNAPSHOT-jar-with-dependencies.jar NutritionScraper.main.java.NutritionScraper $1
cd ..
cd ..
git checkout GautamiAndEfimia


