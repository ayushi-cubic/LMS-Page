package com.example.runner;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.core.options.Constants.FILTER_TAGS_PROPERTY_NAME;
import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.core.options.Constants.PLUGIN_PROPERTY_NAME;

/**
 * JUnit 5 Test Runner for Criminal Case Creation Tests Only
 */
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.example.stepdefinitions,com.example.hooks")
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@CriminalCaseCreation")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, 
    value = "pretty, html:target/cucumber-reports/criminal-case-creation.html, json:target/cucumber-reports/criminal-case-creation.json")
public class CriminalCaseCreationTestRunner {
    // This class will be empty, the annotations define the runner configuration
}
