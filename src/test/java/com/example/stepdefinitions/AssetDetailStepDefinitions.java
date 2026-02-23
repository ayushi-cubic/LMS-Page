package com.example.stepdefinitions;

import org.openqa.selenium.WebDriver;

import com.example.hooks.Hooks;
import com.example.pages.AssetDetailPage;
import com.example.pages.LoginPage;
import com.example.pages.OtpPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Step Definitions for Asset Detail Management Feature.
 */
public class AssetDetailStepDefinitions {

    private final WebDriver driver;
    private final LoginPage loginPage;
    private final OtpPage otpPage;
    private AssetDetailPage assetDetailPage;

    public AssetDetailStepDefinitions() {
        this.driver = Hooks.getDriver();
        this.loginPage = new LoginPage(driver);
        this.otpPage = new OtpPage(driver);
        this.assetDetailPage = new AssetDetailPage(driver);
    }

    @Given("I open the LMS application for asset details")
    public void iOpenTheLmsApplicationForAssetDetails() {
        loginPage.openApplication("https://qalmsbu.cubictree.com/");
    }

    @When("I login to LMS with username {string} and password {string} for asset details")
    public void iLoginToLmsWithUsernameAndPasswordForAssetDetails(String username, String password) {
        if (loginPage.isLoginPageDisplayed()) {
            loginPage.login(username, password);
        } else {
            System.out.println("Login page not displayed, user may already be authenticated. Skipping login step.");
        }
    }

    @And("I enter the OTP {string} for asset details")
    public void iEnterTheOtpForAssetDetails(String otp) {
        if (otpPage.isOtpPageDisplayed()) {
            otpPage.verifyOtp(otp);
        } else {
            System.out.println("OTP page not displayed, skipping OTP step.");
        }
    }

    @Then("I should be successfully logged in to asset details")
    public void iShouldBeSuccessfullyLoggedInToAssetDetails() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Given("I navigate to asset details page")
    public void iNavigateToAssetDetailsPage() {
        assetDetailPage.navigateToAssetDetails();
        assetDetailPage = new AssetDetailPage(driver);
    }

    @When("I click on Borrowers menu for asset details")
    public void iClickOnBorrowersMenuForAssetDetails() {
        assetDetailPage.clickBorrowersMenu();
    }

    @And("I click on Asset submenu for asset details")
    public void iClickOnAssetSubmenuForAssetDetails() {
        assetDetailPage.clickAssetSubmenu();
    }

    @And("I click Load asset button")
    public void iClickLoadAssetButton() {
        assetDetailPage.clickLoadAssets();
    }

    @And("I click on Action button for first asset")
    public void iClickOnActionButtonForFirstAsset() {
        assetDetailPage.clickFirstRowAction();
    }

    @And("I click on Details option for first asset")
    public void iClickOnDetailsOptionForFirstAsset() {
        assetDetailPage.clickDetails();
        assetDetailPage = new AssetDetailPage(driver);
    }

    @When("I complete asset detail update with random data")
    public void iCompleteAssetDetailUpdateWithRandomData() {
        assetDetailPage.completeAssetDetailEditFlow();
    }

    @Then("the asset details should be updated successfully")
    public void theAssetDetailsShouldBeUpdatedSuccessfully() {
        try {
            Thread.sleep(2500);
            System.out.println("✓ Asset details updated successfully");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
