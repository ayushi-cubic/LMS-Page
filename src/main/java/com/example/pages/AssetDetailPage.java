package com.example.pages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.example.utils.RandomDataGenerator;
import com.example.utils.WaitHelper;

/**
 * Page Object Model for Asset Detail Page
 * Handles navigation and edit flow for first asset details record.
 */
public class AssetDetailPage {

    private static final DateTimeFormatter APP_DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MMM-yyyy");

    private final WebDriver driver;
    private final WaitHelper waitHelper;
    private final Random random;

    private final By borrowersMenu = By.xpath("/html/body/div[3]/div/div/div/ul/li[2]/a");
    private final By assetSubmenu = By.xpath("/html/body/div[2]/div/div/div/ul/li[2]/ul/li[4]/a");
    private final By loadAssetsButton = By.xpath("//*[@id='LoadAssets']");
    private final By actionButton = By.xpath("//*[@id='assetsListingContainer']/div[1]/table/tbody/tr[1]/td[8]/div/a/i");
    private final By detailsButton = By.xpath("//*[@id='assetsListingContainer']/div[1]/table/tbody/tr[1]/td[8]/div/ul/li[3]/a");

    private final By sectionAction = By.xpath("//*[@id='flush-collapse14']/div/div/div/div[1]/div");
    private final By sectionEdit = By.xpath("//*[@id='flush-collapse14']/div/div/div/div[1]/div/ul/li[2]/a");

    private final By natureDropdown = By.xpath("//*[@id='Nature']");
    private final By assetTypeDropdown = By.xpath("//*[@id='AssetType']");
    private final By categoryDropdown = By.xpath("//*[@id='CategoryId']");

    private final By areaField = By.xpath("//*[@id='area']");
    private final By carBrandField = By.xpath("//*[@id='CarBrand']");
    private final By northsideField = By.xpath("//*[@id='Northside']");
    private final By siuthsideField = By.xpath("//*[@id='siuthside']");
    private final By eastsideField = By.xpath("//*[@id='Eastside']");
    private final By westsideField = By.xpath("//*[@id='Westside']");
    private final By capitalField = By.xpath("//*[@id='Capital']");
    private final By commodityField = By.xpath("//*[@id='Commodity']");
    private final By inCaratField = By.xpath("//*[@id='InCarat']");

    private final By nameField = By.xpath("//*[@id='Name']");
    private final By accountNumberSelect2 = By.xpath("//*[@id='edit-asset']/div/div[3]/div[1]/span[1]/span[1]/span");
    private final By descriptionField = By.xpath("//*[@id='Description']");
    private final By currentHolderField = By.xpath("//*[@id='CurrentHolder']");
    private final By holdingStatusDropdown = By.xpath("//*[@id='HoldingStatus']");
    private final By estimatedValueField = By.xpath("//*[@id='EstimatedValue']");
    private final By chargeTypeDropdown = By.xpath("//*[@id='AssetChargeTypeId']");
    private final By periodField = By.xpath("//*[@id='Period']");
    private final By addressField = By.xpath("//*[@id='Address']");
    private final By dateOfNoticePossessionField = By.xpath("//*[@id='DateofPublicNoticeofPossession']");
    private final By disputeCollateralField = By.xpath("//*[@id='DisputeCollateral']");
    private final By cersaiNumberField = By.xpath("//*[@id='CERSAINumber']");
    private final By assetIdField = By.xpath("//*[@id='AssetId']");
    private final By lockOverLockCasesDropdown = By.xpath("//*[@id='LockOverLockCases']");
    private final By authorisedOfficerNameField = By.xpath("//*[@id='AuthorisedOfficerName']");
    private final By possessionStatusDropdown = By.xpath("//*[@id='PossessionStatus_1']");

    private final By eastField = By.xpath("//*[@id='East']");
    private final By westField = By.xpath("//*[@id='West']");
    private final By southField = By.xpath("//*[@id='South']");
    private final By northField = By.xpath("//*[@id='North']");

    private final By documentTitleField = By.xpath("//*[@id='edit-asset']/div/div[10]/div[2]/div/input");
    private final By documentDescriptionField = By.xpath("//*[@id='edit-asset']/div/div[10]/div[3]/div/textarea");
    private final By saveButton = By.xpath("//*[@id='asseteditsubmitbutton']");

    private final By addressAccordion = By.xpath("//*[@id='accordionFlushExample']/div[3]/div[1]/div");
    private final By addressAddNewButton = By.xpath("//*[@id='AssetAddressContainer']/div/div[1]/div[2]/a");
    private final By addressTypeDropdown = By.xpath("//*[@id='AddressTypeIds']");
    private final By customerAddress1Field = By.xpath("//*[@id='customeraddress']");
    private final By customerAddress2Field = By.xpath("//*[@id='customeraddress2']");
    private final By customerAddress3Field = By.xpath("//*[@id='customeraddress3']");
    private final By customerAddress4Field = By.xpath("//*[@id='customeraddress4']");
    private final By stateDropdown = By.xpath("//*[@id='StateIds']");
    private final By locationDropdown = By.xpath("//*[@id='LocationIds']");
    private final By zipcodeField = By.xpath("//*[@id='Zipcode']");
    private final By addAssetAddressButton = By.xpath("//*[@id='add']");
    private final By addressSaveButton = By.xpath("//*[@id='AddressButtonSave']");

    private final By valuationAccordion = By.xpath("//*[@id='accordionFlushExample']/div[5]/div[1]/div");
    private final By valuationAddNewButton = By.xpath("//*[@id='AssetValuationContainer']/div/div[1]/div[2]/a");
    private final By valuationAssetDropdown = By.xpath("//*[@id='AssetIdName']");
    private final By estMarketValueField = By.xpath("//*[@id='EstMarketValue']");
    private final By realizableValueField = By.xpath("//*[@id='RealizableValue']");
    private final By distressSaleValueField = By.xpath("//*[@id='DistressSaleValue']");
    private final By valuationMethodDropdown = By.xpath("//*[@id='ValuationMethodId']");
    private final By valuationDateField = By.xpath("//*[@id='ValuationDate']");
    private final By valuerDropdown = By.xpath("//*[@id='ValuerId']");
    private final By approverNameField = By.xpath("//*[@id='ApproverName']");
    private final By dateOfApprovalField = By.xpath("//*[@id='DateofAproval']");
    private final By valuationTitleField = By.xpath("//*[@id='edit-notice']/form/div/div[5]/div[2]/input");
    private final By valuationDescriptionField = By.xpath("//*[@id='edit-notice']/form/div/div[5]/div[3]/textarea");
    private final By valuationSaveButton = By.xpath("//*[@id='AddEditValuationButton']");

    private final By biddersAccordion = By.xpath("//*[@id='accordionFlushExample']/div[6]/div[1]/div");
    private final By biddersAddNewButton = By.xpath("//*[@id='AssetBidderContainer']/div/div[1]/div[2]/a");
    private final By plannedAuctionDateField = By.xpath("//*[@id='PlannedAuctionDate']");
    private final By submissionDateField = By.xpath("//*[@id='SubmissionDate']");
    private final By bidderEmdAmountField = By.xpath("//*[@id='EMDAmount']");
    private final By bidderNameField = By.xpath("//*[@id='BidderName']");
    private final By bidderBidAmountField = By.xpath("//*[@id='BidAmount']");
    private final By bidderPanField = By.xpath("//*[@id='PAN']");
    private final By bidderMobileField = By.xpath("//*[@id='MobileNo']");
    private final By bidderEmailField = By.xpath("//*[@id='EmailId']");
    private final By bidderAddressField = By.xpath("//*[@id='Address']");
    private final By addBidderButton = By.xpath("//*[@id='AddBinder']");
    private final By biddersSaveButton = By.xpath("//*[@id='edit-bidders']/form/div/div/div[7]/input");

    private final By auctionAccordion = By.xpath("//*[@id='accordionFlushExample']/div[7]/div[1]/div");
    private final By auctionAddNewButton = By.xpath("//*[@id='AssetAuctionContainer']/div/div[1]/div[2]/a");
    private final By auctionRefNoField = By.xpath("//*[@id='AuctionRefNo']");
    private final By auctionDateField = By.xpath("//*[@id='AuctionDate']");
    private final By auctionTypeField = By.xpath("//*[@id='AuctionType']");
    private final By auctionAssetDropdown = By.xpath("//*[@id='AssetIdUniq']");
    private final By saleStatusDropdown = By.xpath("//*[@id='SaleStatusId']");
    private final By purchaserField = By.xpath("//*[@id='Purchaser']");
    private final By finalPaymentDateField = By.xpath("//*[@id='FinalPaymentDate']");
    private final By auctionLocationField = By.xpath("//*[@id='AuctionLocation']");
    private final By marketValueField = By.xpath("//*[@id='MarketValue']");
    private final By reserveValueField = By.xpath("//*[@id='ReserveValue']");
    private final By emdDateField = By.xpath("//*[@id='EMDDate']");
    private final By emdPerField = By.xpath("//*[@id='EMDPer']");
    private final By acceptedBidAmountField = By.xpath("//*[@id='AcceptedBidAmount']");
    private final By auctioningAgencyField = By.xpath("//*[@id='AuctioningAgency']");
    private final By auctionPaymentDetailsField = By.xpath("//*[@id='Remark']");
    private final By bidConfirmationLetterDateField = By.xpath("//*[@id='BidConfirmationLetterDate']");
    private final By auctionAuthorisedOfficerNameField = By.xpath("//*[@id='AuthorisedOfficerName']");
    private final By biderNameField = By.xpath("//*[@id='BiderName']");
    private final By biderAmountField = By.xpath("//*[@id='BidAmount']");
    private final By panNumberField = By.xpath("//*[@id='PanNumber']");
    private final By auctionEmdAmountField = By.xpath("//*[@id='EMDAmount']");
    private final By auctionDescriptionField = By.xpath("//*[@id='Description']");
    private final By auctionSaveButton = By.xpath("//*[@id='AddEditAuctionButton']");

    @SuppressWarnings("this-escape")
    public AssetDetailPage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
        this.random = new Random();
        PageFactory.initElements(driver, this);
    }

    public void navigateToAssetDetails() {
        clickBorrowersMenu();
        clickAssetSubmenu();
        clickLoadAssets();
        clickFirstRowAction();
        clickDetails();
    }

    public void clickBorrowersMenu() {
        clickByLocatorWithFallbacks(new By[] {
            borrowersMenu,
            By.xpath("//a[normalize-space()='Borrowers']")
        }, "Borrowers menu");
        waitHelper.hardWait(1200);
    }

    public void clickAssetSubmenu() {
        clickByLocatorWithFallbacks(new By[] {
            By.xpath("/html/body/div[3]/div/div/div/ul/li[2]/ul/li[4]/a"),
            assetSubmenu,
            By.xpath("//li[contains(@class,'menu-item')]//a[normalize-space()='Asset']")
        }, "Asset submenu");
        waitHelper.hardWait(1800);
    }

    public void clickLoadAssets() {
        waitHelper.clickWithWait(loadAssetsButton);
        waitHelper.hardWait(2500);
    }

    public void clickFirstRowAction() {
        waitHelper.clickWithWait(actionButton);
        waitHelper.hardWait(700);
    }

    public void clickDetails() {
        String originalWindow = driver.getWindowHandle();
        waitHelper.clickWithWait(detailsButton);
        waitHelper.hardWait(2500);

        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        waitHelper.hardWait(2500);
    }

    public void clickSectionActionAndEdit() {
        clickByLocatorWithFallbacks(new By[] {
            sectionAction,
            By.xpath("//*[@id='flush-collapse14']/div/div/div/div[1]/div")
        }, "Asset detail section action");
        waitHelper.hardWait(700);

        boolean clickedEdit = clickEditFromActionDropdown();
        if (!clickedEdit) {
            throw new RuntimeException("Failed to click Asset detail section edit from action dropdown");
        }
        waitHelper.hardWait(1000);
    }

    public void completeAssetDetailEditFlow() {
        ensureActiveWindow();
        clickSectionActionAndEdit();

        selectRandomOption(natureDropdown);
        selectRandomOption(assetTypeDropdown);
        String selectedCategory = selectRandomOption(categoryDropdown);
        fillCategorySpecificFields(selectedCategory);

        enterText(nameField, "ASSET-DETAIL-" + RandomDataGenerator.generateAlphanumeric(8));
        selectRandomAccountNumber();
        enterText(descriptionField, "Asset Description " + RandomDataGenerator.generateRandomAlphanumericString(14));
        enterText(currentHolderField, RandomDataGenerator.generateName());
        selectRandomOption(holdingStatusDropdown);
        enterText(estimatedValueField, RandomDataGenerator.generateNumeric(6));
        selectRandomOption(chargeTypeDropdown);
        enterText(periodField, RandomDataGenerator.generateRandomAlphanumericString(7));
        enterText(addressField, RandomDataGenerator.generateAddress());
        LocalDate possessionDate = generateRandomRecentLocalDate();
        enterDate(dateOfNoticePossessionField, formatDate(possessionDate));
        enterText(disputeCollateralField, RandomDataGenerator.generateRandomAlphanumericString(10));
        enterText(cersaiNumberField, RandomDataGenerator.generateNumeric(10));
        enterText(assetIdField, "AID" + RandomDataGenerator.generateNumeric(7));
        selectRandomOption(lockOverLockCasesDropdown);
        enterText(authorisedOfficerNameField, RandomDataGenerator.generateName());
        selectRandomOption(possessionStatusDropdown);

        enterText(eastField, RandomDataGenerator.generateRandomAlphanumericString(8));
        enterText(westField, RandomDataGenerator.generateRandomAlphanumericString(8));
        enterText(southField, RandomDataGenerator.generateRandomAlphanumericString(8));
        enterText(northField, RandomDataGenerator.generateRandomAlphanumericString(8));

        enterText(documentTitleField, "DOC-" + RandomDataGenerator.generateRandomAlphanumericString(8));
        enterText(documentDescriptionField, "Document Description " + RandomDataGenerator.generateRandomAlphanumericString(20));

        ensureActiveWindow();
        waitHelper.scrollToElement(driver.findElement(saveButton));
        waitHelper.hardWait(300);
        waitHelper.clickWithWait(saveButton);
        waitHelper.hardWait(2500);

        completeAddressSectionAddAndSave(possessionDate);
    }

    private void completeAddressSectionAddAndSave(LocalDate possessionDate) {
        ensureActiveWindow();

        clickByLocatorWithFallbacks(new By[] {
            addressAccordion,
            By.xpath("//*[@id='accordionFlushExample']/div[3]/div[1]/div")
        }, "Address accordion");
        waitHelper.hardWait(700);

        clickByLocatorWithFallbacks(new By[] {
            addressAddNewButton,
            By.xpath("//*[@id='AssetAddressContainer']/div/div[1]/div[2]/a")
        }, "Address add button");
        waitHelper.hardWait(800);

        selectRandomOption(addressTypeDropdown);
        enterText(customerAddress1Field, RandomDataGenerator.generateAddress());
        enterText(customerAddress2Field, "ADDR2-" + RandomDataGenerator.generateRandomAlphanumericString(10));
        enterText(customerAddress3Field, "ADDR3-" + RandomDataGenerator.generateRandomAlphanumericString(10));
        enterText(customerAddress4Field, "ADDR4-" + RandomDataGenerator.generateRandomAlphanumericString(10));

        selectRandomOption(stateDropdown);
        waitHelper.hardWait(1200);
        boolean isLocationSelected = selectRandomLocationOption();

        if (!isLocationSelected) {
            for (int i = 0; i < 3 && !isLocationSelected; i++) {
                selectRandomOption(stateDropdown);
                waitHelper.hardWait(1200);
                isLocationSelected = selectRandomLocationOption();
            }
        }

        enterText(zipcodeField, RandomDataGenerator.generateNumeric(6));

        clickByLocatorWithFallbacks(new By[] {
            addAssetAddressButton,
            By.xpath("//*[@id='add']"),
            By.xpath("//*[@id='add-assetaddress']/div/div[3]/div[6]")
        }, "Address add");
        waitHelper.hardWait(900);

        clickByLocatorWithJavaScriptFallback(new By[] {
            addressSaveButton,
            By.xpath("//*[@id='AddressButtonSave']")
        }, "Address save");
        waitHelper.hardWait(1800);

        LocalDate latestPossessionDate = possessionDate;
        latestPossessionDate = maxDate(latestPossessionDate, completeValuationSectionAddAndSave());
        latestPossessionDate = maxDate(latestPossessionDate, completeBiddersSectionAddAndSave());
        completeAuctionSectionAddAndSave(latestPossessionDate);
    }

    private LocalDate completeValuationSectionAddAndSave() {
        clickByLocatorWithFallbacks(new By[] {
            valuationAccordion,
            By.xpath("//*[@id='accordionFlushExample']/div[5]/div[1]/div")
        }, "Valuation details accordion");
        waitHelper.hardWait(700);

        clickByLocatorWithFallbacks(new By[] {
            valuationAddNewButton,
            By.xpath("//*[@id='AssetValuationContainer']/div/div[1]/div[2]/a")
        }, "Valuation add button");
        waitHelper.hardWait(800);

        selectRandomOption(valuationAssetDropdown);

        int estimatedMarketValue = 100000 + random.nextInt(900000);
        int realizableValue = 1 + random.nextInt(estimatedMarketValue);
        int distressSaleMax = Math.min(estimatedMarketValue, realizableValue);
        int distressSaleValue = 1 + random.nextInt(distressSaleMax);

        enterText(estMarketValueField, String.valueOf(estimatedMarketValue));
        enterText(realizableValueField, String.valueOf(realizableValue));
        enterText(distressSaleValueField, String.valueOf(distressSaleValue));

        selectRandomOption(valuationMethodDropdown);
        LocalDate valuationDate = generateRandomRecentLocalDate();
        enterDate(valuationDateField, formatDate(valuationDate));
        selectRandomOption(valuerDropdown);
        enterText(approverNameField, RandomDataGenerator.generateName());
        LocalDate approvalDate = generateDateOnOrAfter(valuationDate, 0, 25);
        enterDate(dateOfApprovalField, formatDate(approvalDate));
        enterText(valuationTitleField, "VAL-TITLE-" + RandomDataGenerator.generateRandomAlphanumericString(8));
        enterText(valuationDescriptionField, "Valuation description " + RandomDataGenerator.generateRandomAlphanumericString(20));

        clickByLocatorWithJavaScriptFallback(new By[] {
            valuationSaveButton,
            By.xpath("//*[@id='AddEditValuationButton']")
        }, "Valuation save");
        waitHelper.hardWait(1800);

        return maxDate(valuationDate, approvalDate);
    }

    private LocalDate completeBiddersSectionAddAndSave() {
        clickByLocatorWithFallbacks(new By[] {
            biddersAccordion,
            By.xpath("//*[@id='accordionFlushExample']/div[6]/div[1]/div")
        }, "Bidders accordion");
        waitHelper.hardWait(700);

        clickByLocatorWithFallbacks(new By[] {
            biddersAddNewButton,
            By.xpath("//*[@id='AssetBidderContainer']/div/div[1]/div[2]/a")
        }, "Bidders add button");
        waitHelper.hardWait(800);

        LocalDate plannedAuctionDate = generateRandomRecentLocalDate();
        LocalDate submissionDate = generateDateOnOrAfter(plannedAuctionDate, 0, 15);

        enterDate(plannedAuctionDateField, formatDate(plannedAuctionDate));
        enterDate(submissionDateField, formatDate(submissionDate));
        enterText(bidderEmdAmountField, RandomDataGenerator.generateNumeric(5));
        enterText(bidderNameField, RandomDataGenerator.generateName());
        enterText(bidderBidAmountField, RandomDataGenerator.generateNumeric(6));
        enterText(bidderPanField, RandomDataGenerator.generatePan());
        enterText(bidderMobileField, generateRandomMobileNumber10To12Digits());
        enterText(bidderEmailField, RandomDataGenerator.generateEmail());
        enterText(bidderAddressField, RandomDataGenerator.generateAddress());

        clickByLocatorWithJavaScriptFallback(new By[] {
            addBidderButton,
            By.xpath("//*[@id='AddBinder']")
        }, "Add bidder");
        waitHelper.hardWait(800);

        clickByLocatorWithJavaScriptFallback(new By[] {
            biddersSaveButton,
            By.xpath("//*[@id='edit-bidders']/form/div/div/div[7]/input")
        }, "Bidders save");
        waitHelper.hardWait(1800);

        return maxDate(plannedAuctionDate, submissionDate);
    }

    private void completeAuctionSectionAddAndSave(LocalDate latestPossessionDate) {
        clickByLocatorWithJavaScriptFallback(new By[] {
            auctionAccordion,
            By.xpath("//*[@id='accordionFlushExample']/div[7]/div[1]/div"),
            By.xpath("//*[@id='edit-bidders']/form/div/div/div[7]/input")
        }, "Fate of Sale / Auction");
        waitHelper.hardWait(700);

        clickByLocatorWithJavaScriptFallback(new By[] {
            auctionAddNewButton,
            By.xpath("//*[@id='AssetAuctionContainer']/div/div[1]/div[2]/a")
        }, "Auction add button");
        waitHelper.hardWait(800);

        enterText(auctionRefNoField, "AUC-" + RandomDataGenerator.generateRandomAlphanumericString(8));
        LocalDate auctionDate = generateRandomRecentLocalDate();
        enterDate(auctionDateField, formatDate(auctionDate));
        enterText(auctionTypeField, "TYPE-" + RandomDataGenerator.generateRandomAlphanumericString(6));
        selectRandomOption(auctionAssetDropdown);

        LocalDate maxRelevantDate = maxDate(latestPossessionDate, auctionDate);

        String saleStatus = selectRandomOption(saleStatusDropdown);
        boolean isSaleCompleted = saleStatus != null && saleStatus.trim().equalsIgnoreCase("Completed");
        if (isSaleCompleted) {
            enterText(purchaserField, RandomDataGenerator.generateName());
        }

        enterText(auctionLocationField, RandomDataGenerator.generateCity());
        enterText(marketValueField, RandomDataGenerator.generateNumeric(6));
        enterText(reserveValueField, RandomDataGenerator.generateNumeric(6));
        LocalDate emdDate = generateEmdDateOnOrBeforeToday(maxRelevantDate);
        enterDate(emdDateField, formatDate(emdDate));

        if (isSaleCompleted) {
            LocalDate finalPaymentDate = generateDateBetween(emdDate, LocalDate.now());
            enterDate(finalPaymentDateField, formatDate(finalPaymentDate));
        }

        enterText(emdPerField, String.valueOf(5 + random.nextInt(91)));
        enterText(acceptedBidAmountField, RandomDataGenerator.generateNumeric(6));
        enterText(auctioningAgencyField, "AGENCY-" + RandomDataGenerator.generateRandomAlphanumericString(7));
        enterText(auctionPaymentDetailsField, "Payment details " + RandomDataGenerator.generateRandomAlphanumericString(15));
        enterDate(bidConfirmationLetterDateField, formatDate(generateDateOnOrAfter(emdDate, 0, 20)));
        enterText(auctionAuthorisedOfficerNameField, RandomDataGenerator.generateName());
        enterText(biderNameField, RandomDataGenerator.generateName());
        enterText(biderAmountField, RandomDataGenerator.generateNumeric(6));
        enterText(panNumberField, RandomDataGenerator.generatePan());
        enterText(auctionEmdAmountField, RandomDataGenerator.generateNumeric(5));
        enterText(auctionDescriptionField, "Auction description " + RandomDataGenerator.generateRandomAlphanumericString(16));

        clickByLocatorWithJavaScriptFallback(new By[] {
            auctionSaveButton,
            By.xpath("//*[@id='AddEditAuctionButton']")
        }, "Auction save");
        waitHelper.hardWait(1800);
    }

    private void fillCategorySpecificFields(String category) {
        if (category == null) {
            return;
        }

        String normalized = category.trim().toLowerCase();

        if (normalized.equals("agricultural lands")) {
            enterText(areaField, RandomDataGenerator.generateNumeric(4));
            return;
        }

        if (normalized.equals("car")) {
            enterText(carBrandField, "Brand-" + RandomDataGenerator.generateRandomAlphanumericString(6));
            return;
        }

        if (normalized.equals("farm")) {
            enterText(northsideField, RandomDataGenerator.generateRandomAlphanumericString(8));
            enterText(siuthsideField, RandomDataGenerator.generateRandomAlphanumericString(8));
            enterText(eastsideField, RandomDataGenerator.generateRandomAlphanumericString(8));
            enterText(westsideField, RandomDataGenerator.generateRandomAlphanumericString(8));
            return;
        }

        if (normalized.equals("fd")) {
            enterText(capitalField, RandomDataGenerator.generateNumeric(6));
            return;
        }

        if (normalized.equals("flat")) {
            enterText(eastField, RandomDataGenerator.generateRandomAlphanumericString(8));
            enterText(westField, RandomDataGenerator.generateRandomAlphanumericString(8));
            enterText(northField, RandomDataGenerator.generateRandomAlphanumericString(8));
            enterText(southField, RandomDataGenerator.generateRandomAlphanumericString(8));
            return;
        }

        if (normalized.equals("gold")) {
            enterText(commodityField, "Commodity-" + RandomDataGenerator.generateRandomAlphanumericString(6));
            enterText(inCaratField, RandomDataGenerator.generateRandomAlphanumericString(5));
        }
    }

    private String selectRandomOption(By dropdownLocator) {
        try {
            WebElement dropdownElement = waitHelper.waitForElementVisible(dropdownLocator);
            waitHelper.waitForElementClickable(dropdownElement);
            Select select = new Select(dropdownElement);
            List<WebElement> options = select.getOptions();

            int startIndex = 0;
            if (!options.isEmpty() && options.get(0).getText().trim().equalsIgnoreCase("select")) {
                startIndex = 1;
            }

            if (options.size() <= startIndex) {
                return null;
            }

            int randomIndex = startIndex + random.nextInt(options.size() - startIndex);
            select.selectByIndex(randomIndex);
            waitHelper.hardWait(600);
            return options.get(randomIndex).getText().trim();
        } catch (Exception e) {
            System.err.println("Failed to select random option for: " + dropdownLocator + " - " + e.getMessage());
            return null;
        }
    }

    private boolean selectRandomLocationOption() {
        try {
            waitHelper.waitWithCustomCondition(8, d -> {
                Select select = new Select(d.findElement(locationDropdown));
                return select.getOptions().size() > 1;
            });

            WebElement dropdownElement = waitHelper.waitForElementVisible(locationDropdown);
            waitHelper.waitForElementClickable(dropdownElement);
            Select select = new Select(dropdownElement);
            List<WebElement> options = select.getOptions();

            int startIndex = 0;
            if (!options.isEmpty() && options.get(0).getText().trim().equalsIgnoreCase("select")) {
                startIndex = 1;
            }

            if (options.size() <= startIndex) {
                return false;
            }

            int randomIndex = startIndex + random.nextInt(options.size() - startIndex);
            select.selectByIndex(randomIndex);
            waitHelper.hardWait(600);
            return true;
        } catch (Exception e) {
            System.err.println("Failed to select random location option - " + e.getMessage());
            return false;
        }
    }

    private void selectRandomAccountNumber() {
        try {
            WebElement container = waitHelper.waitForElementClickable(accountNumberSelect2);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", container);
            waitHelper.hardWait(300);
            container.click();

            List<WebElement> clearButtons = container.findElements(By.cssSelector(".select2-selection__clear"));
            if (!clearButtons.isEmpty()) {
                clearButtons.get(0).click();
                waitHelper.hardWait(300);
                container.click();
            }

            WebElement searchInput = waitHelper.waitForElementVisible(By.xpath("//li[contains(@class,'select2-search--inline')]/input"));
            searchInput.sendKeys(" ");
            waitHelper.hardWait(1200);

            List<WebElement> options = driver.findElements(By.xpath("//li[contains(@class,'select2-results__option') and not(contains(@class,'disabled')) and not(contains(@class,'loading-results'))]"));
            if (!options.isEmpty()) {
                options.get(random.nextInt(options.size())).click();
                waitHelper.hardWait(700);
                return;
            }

            searchInput.sendKeys(Keys.ENTER);
            waitHelper.hardWait(700);
        } catch (Exception e) {
            System.err.println("Account number selection skipped: " + e.getMessage());
        }
    }

    private void enterDate(By field, String dateValue) {
        try {
            WebElement element = waitHelper.waitForElementVisible(field);
            waitHelper.waitForElementClickable(element);
            element.clear();
            element.sendKeys(dateValue);
            element.sendKeys(Keys.TAB);
            waitHelper.hardWait(400);
        } catch (Exception e) {
            System.err.println("Failed to enter date for " + field + " - " + e.getMessage());
        }
    }

    private String generateRandomRecentDate() {
        return formatDate(generateRandomRecentLocalDate());
    }

    private LocalDate generateRandomRecentLocalDate() {
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.now().minusDays(1);
        if (endDate.isBefore(startDate)) {
            endDate = LocalDate.now();
        }
        long daysBetween = endDate.toEpochDay() - startDate.toEpochDay();
        return startDate.plusDays(random.nextInt((int) daysBetween + 1));
    }

    private LocalDate generateDateBetween(LocalDate startInclusive, LocalDate endInclusive) {
        if (startInclusive == null && endInclusive == null) {
            return LocalDate.now();
        }
        if (startInclusive == null) {
            startInclusive = endInclusive;
        }
        if (endInclusive == null) {
            endInclusive = startInclusive;
        }
        if (endInclusive.isBefore(startInclusive)) {
            return startInclusive;
        }

        long daysBetween = endInclusive.toEpochDay() - startInclusive.toEpochDay();
        return startInclusive.plusDays(random.nextInt((int) daysBetween + 1));
    }

    private LocalDate generateEmdDateOnOrBeforeToday(LocalDate baseDate) {
        LocalDate today = LocalDate.now();
        if (baseDate == null) {
            return generateDateBetween(LocalDate.of(2024, 1, 1), today);
        }

        LocalDate earliestPossible = baseDate.plusDays(1);
        if (earliestPossible.isAfter(today)) {
            return today;
        }

        return generateDateBetween(earliestPossible, today);
    }

    private LocalDate generateDateOnOrAfter(LocalDate baseDate, int minDaysOffset, int maxDaysOffset) {
        if (baseDate == null) {
            return generateRandomRecentLocalDate();
        }
        int boundedMin = Math.max(0, minDaysOffset);
        int boundedMax = Math.max(boundedMin, maxDaysOffset);
        int daysToAdd = boundedMin + random.nextInt((boundedMax - boundedMin) + 1);
        return baseDate.plusDays(daysToAdd);
    }

    private LocalDate maxDate(LocalDate firstDate, LocalDate secondDate) {
        if (firstDate == null) {
            return secondDate;
        }
        if (secondDate == null) {
            return firstDate;
        }
        return firstDate.isAfter(secondDate) ? firstDate : secondDate;
    }

    private String formatDate(LocalDate date) {
        if (date == null) {
            return generateRandomRecentDate();
        }
        return date.format(APP_DATE_FORMAT);
    }

    private String generateRandomMobileNumber10To12Digits() {
        int length = 10 + random.nextInt(3);
        StringBuilder number = new StringBuilder();
        number.append(6 + random.nextInt(4));
        for (int i = 1; i < length; i++) {
            number.append(random.nextInt(10));
        }
        return number.toString();
    }

    private void enterText(By field, String value) {
        try {
            WebElement element = waitHelper.waitForElementVisible(field);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", element);
            waitHelper.hardWait(200);
            element.clear();
            element.sendKeys(value);
            waitHelper.hardWait(200);
        } catch (Exception e) {
            System.err.println("Failed to enter text for " + field + " - " + e.getMessage());
        }
    }

    private void clickByLocatorWithFallbacks(By[] locators, String elementName) {
        Exception lastException = null;
        for (By locator : locators) {
            try {
                WebElement element = waitHelper.waitForElementClickable(locator);
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", element);
                waitHelper.hardWait(200);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
                System.out.println("✓ Clicked " + elementName + " using locator: " + locator);
                return;
            } catch (Exception e) {
                lastException = e;
            }
        }
        throw new RuntimeException("Failed to click " + elementName + " with all locator fallbacks", lastException);
    }

    private void clickByLocatorWithJavaScriptFallback(By[] locators, String elementName) {
        Exception lastException = null;
        for (By locator : locators) {
            try {
                WebElement element = waitHelper.waitForElementPresent(locator);
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", element);
                waitHelper.hardWait(250);
                try {
                    element.click();
                } catch (Exception ignored) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
                }
                System.out.println("✓ Clicked " + elementName + " using JS fallback locator: " + locator);
                return;
            } catch (Exception e) {
                lastException = e;
            }
        }
        throw new RuntimeException("Failed to click " + elementName + " with JavaScript fallback locators", lastException);
    }

    private boolean clickEditFromActionDropdown() {
        By[] editLocators = new By[] {
            sectionEdit,
            By.xpath("//*[@id='flush-collapse14']/div/div/div/div[1]/div/ul/li[2]/a"),
            By.xpath("//*[@id='flush-collapse14']//ul/li[2]/a"),
            By.xpath("//*[@id='flush-collapse14']//a[contains(translate(normalize-space(.),'EDIT','edit'),'edit')]")
        };

        for (By locator : editLocators) {
            try {
                List<WebElement> elements = driver.findElements(locator);
                if (!elements.isEmpty()) {
                    WebElement element = elements.get(0);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", element);
                    waitHelper.hardWait(200);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
                    System.out.println("✓ Clicked Asset detail section edit using locator: " + locator);
                    return true;
                }
            } catch (Exception ignored) {
            }
        }

        try {
            Object clicked = ((JavascriptExecutor) driver).executeScript(
                "var root = document.evaluate(\"//*[@id='flush-collapse14']\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;"
              + "if(!root){return false;}"
              + "var node = document.evaluate(\".//div/div/div/div[1]/div/ul/li[2]/a\", root, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;"
              + "if(node){node.click(); return true;}"
              + "var links = root.querySelectorAll('a');"
              + "for (var i = 0; i < links.length; i++) {"
              + "  var t = (links[i].textContent || '').trim().toLowerCase();"
              + "  if (t.indexOf('edit') !== -1) { links[i].click(); return true; }"
              + "}"
              + "return false;");

            if (clicked instanceof Boolean && (Boolean) clicked) {
                System.out.println("✓ Clicked Asset detail section edit using JS fallback");
                return true;
            }
        } catch (Exception ignored) {
        }

        return false;
    }

    private void ensureActiveWindow() {
        try {
            String currentHandle = driver.getWindowHandle();
            if (driver.getWindowHandles().contains(currentHandle)) {
                return;
            }
        } catch (Exception ignored) {
        }

        try {
            java.util.Set<String> handles = driver.getWindowHandles();
            if (!handles.isEmpty()) {
                String lastHandle = null;
                for (String handle : handles) {
                    lastHandle = handle;
                }
                if (lastHandle != null) {
                    driver.switchTo().window(lastHandle);
                    waitHelper.hardWait(500);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("No active browser window available", e);
        }
    }
}
