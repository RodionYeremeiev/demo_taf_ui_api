#!/bin/bash

# Exit on error
set -e

# Clean and run tests
echo "Running tests for all modules..."
mvn clean verify -Dheadless=false

# Prepare merged results folder
MERGED_DIR="merged-results"
echo "Removing merged-results folder from previous run..."
rm -rf $MERGED_DIR
echo "Preparing merged-results folder..."
mkdir -p $MERGED_DIR

# Copy results from each module
echo "Collecting Allure results..."
cp -r ui-tests/target/allure-results/* $MERGED_DIR/ 2>/dev/null || echo "No UI results found"
cp -r api-tests/target/allure-results/* $MERGED_DIR/ 2>/dev/null || echo "No API results found"

# Generate HTML report
echo "Generating Allure HTML report..."
allure generate $MERGED_DIR --clean -o merged-report

# Open report
echo "Opening Allure report..."
allure open merged-report