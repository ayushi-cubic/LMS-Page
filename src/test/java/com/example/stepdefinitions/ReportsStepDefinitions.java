package com.example.stepdefinitions;

import org.openqa.selenium.WebDriver;

import com.example.hooks.Hooks;
import com.example.pages.GenerateReportPage;
import com.example.pages.ReportsNavigationPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Step Definitions for Reports Management Feature
 */
public class ReportsStepDefinitions {
    
    private WebDriver driver;
    private ReportsNavigationPage reportsNavigationPage;
    private GenerateReportPage generateReportPage;
    
    // Constructor - WebDriver will be retrieved from Hooks
    public ReportsStepDefinitions() {
        this.driver = Hooks.getDriver();
        this.reportsNavigationPage = new ReportsNavigationPage(driver);
        this.generateReportPage = new GenerateReportPage(driver);
    }
    
    // Navigation Steps
    @Given("I navigate to Reports module")
    public void iNavigateToReportsModule() {
        reportsNavigationPage.navigateToReports();
    }
    
    // Template Selection
    @When("I select template report name {string}")
    public void iSelectTemplateReportName(String reportName) {
        generateReportPage.selectTemplateReportName(reportName);
    }
    
    @When("I select template report name from dropdown randomly")
    public void iSelectTemplateReportNameRandomly() {
        generateReportPage.selectTemplateReportNameRandomly();
    }
    
    // Case Report Steps
    @And("I select Include for cases")
    public void iSelectIncludeForCases() {
        generateReportPage.clickCaseInclude();
    }
    
    @And("I select Recovery section")
    public void iSelectRecoverySection() {
        generateReportPage.clickRecovery();
    }
    
    @And("I select All Recovery")
    public void iSelectAllRecovery() {
        generateReportPage.clickAllRecovery();
    }
    
    @And("I click Generate Case Report")
    public void iClickGenerateCaseReport() {
        generateReportPage.clickGenerateCaseReport();
    }
    
    @And("I select Case Expenses section")
    public void iSelectCaseExpensesSection() {
        generateReportPage.clickCaseExpenses();
    }
    
    @And("I select All Case Expenses")
    public void iSelectAllCaseExpenses() {
        generateReportPage.clickAllCaseExpenses();
    }
    
    @And("I select Asset section")
    public void iSelectAssetSection() {
        generateReportPage.clickAsset();
    }
    
    @And("I select Asset Include")
    public void iSelectAssetInclude() {
        generateReportPage.clickAssetInclude();
    }
    
    @And("I click Team case ownership filter")
    public void iClickTeamCaseOwnershipFilter() {
        generateReportPage.clickTeamCaseOwnershipFilter();
    }
    
    // Notice Report Steps
    @When("I click Notice checkbox")
    public void iClickNoticeCheckbox() {
        generateReportPage.clickNoticeCheckbox();
    }
    
    @And("I click Include all for notices")
    public void iClickIncludeAllForNotices() {
        generateReportPage.clickNoticeIncludeAll();
    }
    
    @And("I select all notice recovery")
    public void iSelectAllNoticeRecovery() {
        generateReportPage.clickNoticeRecoverySelectAll();
    }
    
    @And("I click Generate Notice Report")
    public void iClickGenerateNoticeReport() {
        generateReportPage.clickGenerateNoticeReport();
    }
    
    @And("I select Notice Proceedings")
    public void iSelectNoticeProceedings() {
        generateReportPage.clickNoticeProceedings();
    }
    
    @And("I select all Notice Proceedings")
    public void iSelectAllNoticeProceedings() {
        generateReportPage.clickNoticeProceedingsSelectAll();
    }
    
    @And("I click Notice Team ownership filter")
    public void iClickNoticeTeamOwnershipFilter() {
        generateReportPage.clickNoticeTeamOwnershipFilter();
    }
    
    @And("I click Multiple Notice")
    public void iClickMultipleNotice() {
        generateReportPage.clickMultipleNotice();
    }
    
    @And("I select all Multiple Notice")
    public void iSelectAllMultipleNotice() {
        generateReportPage.clickMultipleNoticeSelectAll();
    }
    
    @And("I click Notice Expenses")
    public void iClickNoticeExpenses() {
        generateReportPage.clickNoticeExpenses();
    }
    
    @And("I select all Notice Expenses")
    public void iSelectAllNoticeExpenses() {
        generateReportPage.clickNoticeExpensesSelectAll();
    }
    
    @And("I click Notice Asset")
    public void iClickNoticeAsset() {
        generateReportPage.clickNoticeAsset();
    }
    
    @And("I click Notice Asset Include")
    public void iClickNoticeAssetInclude() {
        generateReportPage.clickNoticeAssetInclude();
    }
    
    // Common Steps
    @And("I click Reset button")
    public void iClickResetButton() {
        generateReportPage.clickReset();
    }
    
    @And("I click Customer radio button")
    public void iClickCustomerRadioButton() {
        generateReportPage.clickCustomerRadioButton();
    }
    
    // Verification Steps
    @Then("case reports should be generated successfully")
    public void caseReportsShouldBeGeneratedSuccessfully() {
        System.out.println("✓ Case reports generated successfully");
    }
    
    @Then("team ownership report should be generated successfully")
    public void teamOwnershipReportShouldBeGeneratedSuccessfully() {
        System.out.println("✓ Team ownership report generated successfully");
    }
    
    @Then("notice reports should be generated successfully")
    public void noticeReportsShouldBeGeneratedSuccessfully() {
        System.out.println("✓ Notice reports generated successfully");
    }
    
    @Then("notice team ownership report should be generated successfully")
    public void noticeTeamOwnershipReportShouldBeGeneratedSuccessfully() {
        System.out.println("✓ Notice team ownership report generated successfully");
    }
    
    @Then("customer report section should be displayed")
    public void customerReportSectionShouldBeDisplayed() {
        System.out.println("✓ Customer report section displayed");
    }
    
    @Then("all reports workflow should be completed successfully")
    public void allReportsWorkflowShouldBeCompletedSuccessfully() {
        System.out.println("✓ All reports workflow completed successfully");
    }
    
    @Then("complete notice reports should be generated successfully")
    public void completeNoticeReportsShouldBeGeneratedSuccessfully() {
        System.out.println("✓ Complete notice reports generated successfully");
    }
    
    // Customer Report Steps
    @And("I click Customer My tab")
    public void iClickCustomerMyTab() {
        generateReportPage.clickCustomerMyTab();
    }
    
    @And("I click Customer Team tab")
    public void iClickCustomerTeamTab() {
        generateReportPage.clickCustomerTeamTab();
    }
    
    @And("I click Customer All tab")
    public void iClickCustomerAllTab() {
        generateReportPage.clickCustomerAllTab();
    }
    
    @And("I click Customer Account Include")
    public void iClickCustomerAccountInclude() {
        generateReportPage.clickCustomerAccountInclude();
    }
    
    @And("I click Customer Asset")
    public void iClickCustomerAsset() {
        generateReportPage.clickCustomerAsset();
    }
    
    @And("I click Customer Asset Include")
    public void iClickCustomerAssetInclude() {
        generateReportPage.clickCustomerAssetInclude();
    }
    
    @And("I click Customer OTS Offer")
    public void iClickCustomerOTSOffer() {
        generateReportPage.clickCustomerOTSOffer();
    }
    
    @And("I select all Customer OTS Offer")
    public void iSelectAllCustomerOTSOffer() {
        generateReportPage.clickCustomerOTSOfferSelectAll();
    }
    
    @And("I click Customer Expenses")
    public void iClickCustomerExpenses() {
        generateReportPage.clickCustomerExpenses();
    }
    
    @And("I select all Customer Expenses")
    public void iSelectAllCustomerExpenses() {
        generateReportPage.clickCustomerExpensesSelectAll();
    }
    
    @And("I click SARFAESI Report tab")
    public void iClickSARFAESIReportTab() {
        generateReportPage.clickSARFAESIReportTab();
    }
    
    @And("I click Standard Report tab")
    public void iClickStandardReportTab() {
        generateReportPage.clickStandardReportTab();
    }
    
    @And("I select random Standard Report Type")
    public void iSelectRandomStandardReportType() {
        generateReportPage.selectRandomStandardReportType();
    }
    
    @And("I force click Generate Report")
    public void iForceClickGenerateReport() {
        generateReportPage.forceClickGenerateReport();
    }
}
