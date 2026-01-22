package com.example.runner;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PUBLISH_QUIET_PROPERTY_NAME;

/**
 * JUnit 5 Test Runner for Criminal Notice Management Cucumber Tests
 * 
 * This runner executes Criminal Notice Management feature files and generates reports
 * Use -Dcucumber.filter.tags to run specific modules:
 * - Criminal Notice only: -Dcucumber.filter.tags="@NoticeCriminalManagement"
 */
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, 
    value = "pretty, html:target/cucumber-reports/cucumber.html, json:target/cucumber-reports/cucumber.json")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.example.stepdefinitions,com.example.hooks")
@ConfigurationParameter(key = PLUGIN_PUBLISH_QUIET_PROPERTY_NAME, value = "true")
public class NoticeCriminalTestRunner {
    // This class will be empty - JUnit 5 uses annotations to configure the runner
}
