package com.example.runner;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.FILTER_TAGS_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PUBLISH_QUIET_PROPERTY_NAME;

/**
 * JUnit 5 Test Runner for Customer Detail Management Tests Only  
 * Note: Filter tags can be specified via command line using -Dcucumber.filter.tags=@YourTag
 */
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@CustomerDetailManagement")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, 
    value = "pretty, html:target/cucumber-reports/customer-detail-cucumber.html, json:target/cucumber-reports/customer-detail-cucumber.json")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.example.stepdefinitions,com.example.hooks")
@ConfigurationParameter(key = PLUGIN_PUBLISH_QUIET_PROPERTY_NAME, value = "true")
public class CustomerDetailManagementTestRunner {
    // This class will be empty - JUnit 5 uses annotations to configure the runner
}
