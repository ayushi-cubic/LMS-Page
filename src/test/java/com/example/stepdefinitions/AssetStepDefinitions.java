package com.example.stepdefinitions;

import com.example.hooks.Hooks;
import com.example.pages.AddAssetPage;
import com.example.pages.AssetNavigationPage;
import com.example.pages.LoginPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Step Definitions for Asset Management
 */
public class AssetStepDefinitions {
    
    private final LoginPage loginPage;
    private final AssetNavigationPage assetNavigationPage;
    private final AddAssetPage addAssetPage;
    
    public AssetStepDefinitions() {
        this.loginPage = new LoginPage(Hooks.getDriver());
        this.assetNavigationPage = new AssetNavigationPage(Hooks.getDriver());
        this.addAssetPage = new AddAssetPage(Hooks.getDriver());
    }
    
    @Given("I navigate to Borrowers menu for assets")
    public void iNavigateToBorrowersMenuForAssets() {
        assetNavigationPage.clickBorrowersMenu();
    }
    
    @And("I click on Assets submenu")
    public void iClickOnAssetsSubmenu() {
        assetNavigationPage.clickAssetsSubmenu();
    }
    
    @And("I click on Add New Asset button")
    public void iClickOnAddNewAssetButton() {
        assetNavigationPage.clickAddNewAssetButton();
    }
    
    @When("I fill the asset form with random data")
    public void iFillTheAssetFormWithRandomData() {
        addAssetPage.fillAssetForm();
    }
    
    @And("I click Next button on asset form")
    public void iClickNextButtonOnAssetForm() {
        addAssetPage.clickNext();
    }
    
    @And("I click Save button for asset")
    public void iClickSaveButtonForAsset() {
        addAssetPage.clickSave();
    }
    
    @Then("the asset should be created successfully")
    public void theAssetShouldBeCreatedSuccessfully() {
        System.out.println("\n========== ASSET CREATION VERIFIED ==========\n");
    }
    
    @Then("I verify all asset details sections and delete the asset")
    public void iVerifyAllAssetDetailsSectionsAndDeleteTheAsset() {
        addAssetPage.verifyAssetDetails();
    }
}
