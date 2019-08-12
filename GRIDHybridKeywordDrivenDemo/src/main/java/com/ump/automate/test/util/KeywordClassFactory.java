package com.ump.automate.test.util;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.ump.automate.test.browser.SeleniumDriverInt;
import com.ump.automate.test.execution.DriverScript;
import com.ump.automate.test.service.ActionKeyword;

public class KeywordClassFactory {

    private static final Logger log = Logger.getLogger(KeywordClassFactory.class);

    private ActionKeyword assertKeyword;

    private ActionKeyword assertMultipleKeyword;

    private ActionKeyword assertUsingInputKeyword;
    
    private ActionKeyword basicOperatorKeyword;

    private ActionKeyword checkCompareTextMultipleSectionByCssSelectorKeyword;

    private ActionKeyword checkContainsTextMultipleSectionByCssSelectorKeyword;

    private ActionKeyword checkElementCompareCssClassKeyword;

    private ActionKeyword checkElementContainsCssClassKeyword;

    private ActionKeyword checkSelectOptionNotContainsValueKeyword;

    private ActionKeyword checkSelectOptionsKeyword;

    private ActionKeyword checkSelectValueKeyword;

    private ActionKeyword clickKeyword;

    private ActionKeyword clickUntilElementPresentKeyword;
    
    private ActionKeyword clickUsingInputKeyword;

    private ActionKeyword closeBrowserKeyword;
    
    private ActionKeyword compareFromResultKeyword;

    private ActionKeyword compareNumbersOnlyKeyword;

    private ActionKeyword comparePlaceholderTextKeyword;

    private ActionKeyword compareStoredValueKeyword;
    
    private ActionKeyword compareTextKeyword;

    private ActionKeyword containsTextKeyword;

    private ActionKeyword countByCssSelectorKeyword;

    private ActionKeyword countCompareTextByCssSelectorKeyword;

    private ActionKeyword countContainsTextByCssSelectorKeyword;
    
    private ActionKeyword datePickerKeyword;

    private ActionKeyword fieldNotNullKeyword;

    private ActionKeyword fileDownloadCheckKeyword;

    private ActionKeyword hoverKeyword;

    private ActionKeyword inputFromStoredKeyword;

    private ActionKeyword inputKeyword;

    private ActionKeyword inputNumberOnlyKeyword;

    private ActionKeyword isAscendingKeyword;

    private ActionKeyword isCheckedKeyword;

    private ActionKeyword isDateInBetweenKeyword;

    private ActionKeyword isDescendingKeyword;

    private ActionKeyword isElementDisabledKeyword;

    private ActionKeyword isElementEnabledKeyword;

    private ActionKeyword isElementReadOnlyKeyword;

    private ActionKeyword isUncheckedKeyword;
    
    private ActionKeyword jsClickKeyword;

    private ActionKeyword navigateKeyword;

    private ActionKeyword nonExistentKeyword;

    private ActionKeyword nonExistentUsingInputKeyword;
    
    private ActionKeyword openBrowserKeyword;

    private ActionKeyword selectCaseInsensitiveDropdownKeyword;

    private ActionKeyword selectIndexDropdownKeyword;

    private ActionKeyword selectVisibleNumbersOnlyDropdownKeyword;

    private ActionKeyword selectVisibleTextDropdownKeyword;

    private ActionKeyword storeMultipleValuesKeyword;
    
    private ActionKeyword storeValueKeyword;
    
    private ActionKeyword submitKeyword;
    
    private ActionKeyword switchWindowKeyword;

    private ActionKeyword textNotEqualKeyword;
    
    private ActionKeyword tickCheckboxKeyword;

    private ActionKeyword verifyDateStateKeyword;
    
    private ActionKeyword verifyFieldHasValueKeyword;
    
    private ActionKeyword waitForKeyword;

    private DriverScript driverScript;

    private ScreenshotsUtil screenshotsUtil;

    private SeleniumDriverInt seleniumDriver;

    private WebDriver driver;

    /**
     * chooses which implementation of the interface will be called
     * @param keyword
     * @param pageObjectId
     * @param dataInput
     * @throws Exception
     */
    public boolean isCalled(String keyword, String pageObjectId, String dataInput) {
        if (log.isDebugEnabled()) {
            log.debug("MCI >> isCalled()");
        }
        boolean isWorking = false;
        String reasonMsg = "";
        try {
            log.info("picking which class to call... ");

            keyword = keyword.replaceAll("\\s", "");

            if ("assert".equalsIgnoreCase(keyword)) {
                isWorking = assertKeyword.isPerform(pageObjectId, dataInput, driver);
            } else if ("assertMultiple".equalsIgnoreCase(keyword)) {
                isWorking = assertMultipleKeyword.isPerform(pageObjectId, dataInput, driver);
            } else if ("assertUsingInput".equalsIgnoreCase(keyword)) {
                isWorking = assertUsingInputKeyword.isPerform(pageObjectId, dataInput, driver);
            } else if ("basicOperator".equalsIgnoreCase(keyword)) {
                isWorking = basicOperatorKeyword.isPerform(pageObjectId, dataInput, driver);
            } else if ("checkCompareTextMultipleSection".equalsIgnoreCase(keyword)) {
                isWorking = checkCompareTextMultipleSectionByCssSelectorKeyword.isPerform(pageObjectId, dataInput, driver);
                driver = seleniumDriver.getDriver();
            } else if ("checkContainsTextMultipleSection".equalsIgnoreCase(keyword)) {
                isWorking = checkContainsTextMultipleSectionByCssSelectorKeyword.isPerform(pageObjectId, dataInput, driver);
                driver = seleniumDriver.getDriver();
            } else if ("checkElementCompareCssClass".equalsIgnoreCase(keyword)) {
                isWorking = checkElementCompareCssClassKeyword.isPerform(pageObjectId, dataInput, driver);
                driver = seleniumDriver.getDriver();
            } else if ("checkElementContainsCssClass".equalsIgnoreCase(keyword)) {
                isWorking = checkElementContainsCssClassKeyword.isPerform(pageObjectId, dataInput, driver);
                driver = seleniumDriver.getDriver();
            } else if ("checkSelectOptionNotContainsValue".equalsIgnoreCase(keyword)) {
                isWorking = checkSelectOptionNotContainsValueKeyword.isPerform(pageObjectId, dataInput, driver);
                driver = seleniumDriver.getDriver();
            } else if ("checkSelectOptions".equalsIgnoreCase(keyword)) {
                isWorking = checkSelectOptionsKeyword.isPerform(pageObjectId, dataInput, driver);
                driver = seleniumDriver.getDriver();
            } else if ("checkSelectValue".equalsIgnoreCase(keyword)) {
                isWorking = checkSelectValueKeyword.isPerform(pageObjectId, dataInput, driver);
                driver = seleniumDriver.getDriver();
            } else if ("click".equalsIgnoreCase(keyword)) {
                isWorking = clickKeyword.isPerform(pageObjectId, dataInput, driver);
                driver = seleniumDriver.getDriver();
            } else if ("clickUntilElementPresent".equalsIgnoreCase(keyword)) {
                isWorking = clickUntilElementPresentKeyword.isPerform(pageObjectId, dataInput, driver);
                driver = seleniumDriver.getDriver();
            } else if ("clickUsingInput".equalsIgnoreCase(keyword)) {
                isWorking = clickUsingInputKeyword.isPerform(pageObjectId, dataInput, driver);
                driver = seleniumDriver.getDriver();
            } else if ("closeBrowser".equalsIgnoreCase(keyword)) {
                isWorking = closeBrowserKeyword.isPerform(pageObjectId, dataInput, driver);
                driver = seleniumDriver.getDriver();
            } else if ("compareFromResult".equalsIgnoreCase(keyword)) {
                isWorking = compareFromResultKeyword.isPerform(pageObjectId, dataInput, driver);
                driver = seleniumDriver.getDriver();
            } else if ("compareNumbersOnly".equalsIgnoreCase(keyword)) {
                isWorking = compareNumbersOnlyKeyword.isPerform(pageObjectId, dataInput, driver);
                driver = seleniumDriver.getDriver();
            } else if ("comparePlaceholderText".equalsIgnoreCase(keyword)) {
                isWorking = comparePlaceholderTextKeyword.isPerform(pageObjectId, dataInput, driver);
                driver = seleniumDriver.getDriver();
            } else if ("compareStoredValue".equalsIgnoreCase(keyword)) {
                isWorking = compareStoredValueKeyword.isPerform(pageObjectId, dataInput, driver);
                driver = seleniumDriver.getDriver();
            } else if ("compareText".equalsIgnoreCase(keyword)) {
                isWorking = compareTextKeyword.isPerform(pageObjectId, dataInput, driver);
                driver = seleniumDriver.getDriver();
            } else if ("containsText".equalsIgnoreCase(keyword)) {
                isWorking = containsTextKeyword.isPerform(pageObjectId, dataInput, driver);
                driver = seleniumDriver.getDriver();
            } else if ("count".equalsIgnoreCase(keyword)) {
                isWorking = countByCssSelectorKeyword.isPerform(pageObjectId, dataInput, driver);
                driver = seleniumDriver.getDriver();
            } else if ("countCompareText".equalsIgnoreCase(keyword)) {
                isWorking = countCompareTextByCssSelectorKeyword.isPerform(pageObjectId, dataInput, driver);
                driver = seleniumDriver.getDriver();
            } else if ("countContainsText".equalsIgnoreCase(keyword)) {
                isWorking = countContainsTextByCssSelectorKeyword.isPerform(pageObjectId, dataInput, driver);
                driver = seleniumDriver.getDriver();
            } else if ("datePicker".equalsIgnoreCase(keyword)) {
                isWorking = datePickerKeyword.isPerform(pageObjectId, dataInput, driver);
                driver = seleniumDriver.getDriver();
            }else if ("fieldNotNull".equalsIgnoreCase(keyword)) {
                isWorking = fieldNotNullKeyword.isPerform(pageObjectId, dataInput, driver);
                driver = seleniumDriver.getDriver();
            } else if ("fileDownloadCheck".equalsIgnoreCase(keyword)) {
                isWorking = fileDownloadCheckKeyword.isPerform(pageObjectId, dataInput, driver);
            } else if ("hover".equalsIgnoreCase(keyword)) {
                isWorking = hoverKeyword.isPerform(pageObjectId, dataInput, driver);
                driver = seleniumDriver.getDriver();
            } else if ("inputFromStored".equalsIgnoreCase(keyword)) {
                isWorking = inputFromStoredKeyword.isPerform(pageObjectId, dataInput, driver);
                driver = seleniumDriver.getDriver();
            } else if ("input".equalsIgnoreCase(keyword)) {
                isWorking = inputKeyword.isPerform(pageObjectId, dataInput, driver);
                driver = seleniumDriver.getDriver();
            } else if ("inputNumberOnly".equalsIgnoreCase(keyword)) {
                isWorking = inputNumberOnlyKeyword.isPerform(pageObjectId, dataInput, driver);
                driver = seleniumDriver.getDriver();
            } else if ("isAscending".equalsIgnoreCase(keyword)) {
                isWorking = isAscendingKeyword.isPerform(pageObjectId, dataInput, driver);
                driver = seleniumDriver.getDriver();
            } else if ("isChecked".equalsIgnoreCase(keyword)) {
                isWorking = isCheckedKeyword.isPerform(pageObjectId, dataInput, driver);
                driver = seleniumDriver.getDriver();
            } else if ("isDateInBetween".equalsIgnoreCase(keyword)) {
                isWorking = isDateInBetweenKeyword.isPerform(pageObjectId, dataInput, driver);
                driver = seleniumDriver.getDriver();
            } else if ("isDescending".equalsIgnoreCase(keyword)) {
                isWorking = isDescendingKeyword.isPerform(pageObjectId, dataInput, driver);
                driver = seleniumDriver.getDriver();
            } else if ("isElementDisabled".equalsIgnoreCase(keyword)) {
                isWorking = isElementDisabledKeyword.isPerform(pageObjectId, dataInput, driver);
                driver = seleniumDriver.getDriver();
            } else if ("isElementEnabled".equalsIgnoreCase(keyword)) {
                isWorking = isElementEnabledKeyword.isPerform(pageObjectId, dataInput, driver);
                driver = seleniumDriver.getDriver();
            } else if ("isElementReadOnly".equalsIgnoreCase(keyword)) {
                isWorking = isElementReadOnlyKeyword.isPerform(pageObjectId, dataInput, driver);
                driver = seleniumDriver.getDriver();
            } else if ("isUnchecked".equalsIgnoreCase(keyword)) {
                isWorking = isUncheckedKeyword.isPerform(pageObjectId, dataInput, driver);
                driver = seleniumDriver.getDriver();
            } else if ("jsclick".equalsIgnoreCase(keyword)) {
                isWorking = jsClickKeyword.isPerform(pageObjectId, dataInput, driver);
                driver = seleniumDriver.getDriver();
            } else if ("navigate".equalsIgnoreCase(keyword)) {
                isWorking = navigateKeyword.isPerform(pageObjectId, dataInput, driver);
                driver = seleniumDriver.getDriver();
            } else if ("nonExistent".equalsIgnoreCase(keyword)) {
                isWorking = nonExistentKeyword.isPerform(pageObjectId, dataInput, driver);
                driver = seleniumDriver.getDriver();
            } else if ("nonExistentUsingInput".equalsIgnoreCase(keyword)) {
                isWorking = nonExistentUsingInputKeyword.isPerform(pageObjectId, dataInput, driver);
                driver = seleniumDriver.getDriver();
            } else if ("openBrowser".equalsIgnoreCase(keyword)) {
                isWorking = openBrowserKeyword.isPerform(pageObjectId, dataInput, driver);
                driver = seleniumDriver.getDriver();
            } else if ("selectDropdownCaseInsensitive".equalsIgnoreCase(keyword)) {
                isWorking = selectCaseInsensitiveDropdownKeyword.isPerform(pageObjectId, dataInput, driver);
                driver = seleniumDriver.getDriver();
            } else if ("selectFromDropdownByIndex".equalsIgnoreCase(keyword)) {
                isWorking = selectIndexDropdownKeyword.isPerform(pageObjectId, dataInput, driver);
                driver = seleniumDriver.getDriver();
            } else if ("selectFromDropdownByVisibleNumbersOnly".equalsIgnoreCase(keyword)) {
                isWorking = selectVisibleNumbersOnlyDropdownKeyword.isPerform(pageObjectId, dataInput, driver);
                driver = seleniumDriver.getDriver();
            } else if ("selectFromDropdownByVisibleText".equalsIgnoreCase(keyword)) {
                isWorking = selectVisibleTextDropdownKeyword.isPerform(pageObjectId, dataInput, driver);
                driver = seleniumDriver.getDriver();
            } else if ("storeMultipleValues".equalsIgnoreCase(keyword)) {
                isWorking = storeMultipleValuesKeyword.isPerform(pageObjectId, dataInput, driver);
                driver = seleniumDriver.getDriver();
            } else if ("storeValue".equalsIgnoreCase(keyword)) {
                isWorking = storeValueKeyword.isPerform(pageObjectId, dataInput, driver);
                driver = seleniumDriver.getDriver();
            } else if ("submit".equalsIgnoreCase(keyword)) {
                isWorking = submitKeyword.isPerform(pageObjectId, dataInput, driver);
                driver = seleniumDriver.getDriver();
            } else if ("switchWindow".equalsIgnoreCase(keyword)) {
                isWorking = switchWindowKeyword.isPerform(pageObjectId, dataInput, driver);
                driver = seleniumDriver.getDriver();
            } else if ("textNotEqual".equalsIgnoreCase(keyword)) {
                isWorking = textNotEqualKeyword.isPerform(pageObjectId, dataInput, driver);
                driver = seleniumDriver.getDriver();
            } else if ("tickCheckbox".equalsIgnoreCase(keyword)) {
                isWorking = tickCheckboxKeyword.isPerform(pageObjectId, dataInput, driver);
                driver = seleniumDriver.getDriver();
            } else if ("verifyDateState".equalsIgnoreCase(keyword)) {
                isWorking = verifyDateStateKeyword.isPerform(pageObjectId, dataInput, driver);
                driver = seleniumDriver.getDriver();
            } else if ("verifyFieldHasValue".equalsIgnoreCase(keyword)) {
                isWorking = verifyFieldHasValueKeyword.isPerform(pageObjectId, dataInput, driver);
            } else if ("waitFor".equalsIgnoreCase(keyword)) {
                isWorking = waitForKeyword.isPerform(pageObjectId, dataInput, driver);
                driver = seleniumDriver.getDriver();
            } else {
                reasonMsg = "action keyword is incorrect or not existing...";
                driverScript.setReason(reasonMsg);
                log.info(reasonMsg);
                reasonMsg = "";
                isWorking = false;
            }
            //            if (!isWorking && "".equals(reasonMsg)) {
            //                screenshotsUtil.takeScreenshot(driver);
            //            }

            // takes screenshot for any failed method 
            // EXCEPT for 'fileDownloadCheck' method
            // and openBrowser since Se will start in this method
            if (!isWorking && !"fileDownloadCheck".equalsIgnoreCase(keyword) && !"openBrowser".equalsIgnoreCase(keyword)) {
                screenshotsUtil.takeScreenshot(driver);
            }

        } catch (Exception e) {
            log.error("Error found. ", e);
        }
        if (log.isDebugEnabled()) {
            log.debug("MCO << isCalled()");
        }
        return isWorking;
    }

    public void setAssertKeyword(ActionKeyword assertKeyword) {
        this.assertKeyword = assertKeyword;
    }

    public void setAssertMultipleKeyword(ActionKeyword assertMultipleKeyword) {
        this.assertMultipleKeyword = assertMultipleKeyword;
    }
    
    public void setAssertUsingInputKeyword(ActionKeyword assertUsingInputKeyword) {
        this.assertUsingInputKeyword = assertUsingInputKeyword;
    }
    
    public void setBasicOperatorKeyword(ActionKeyword basicOperatorKeyword) {
        this.basicOperatorKeyword = basicOperatorKeyword;
    }

    public void setCheckContainsTextMultipleSectionByCssSelectorKeyword(ActionKeyword checkContainsTextMultipleSectionByCssSelectorKeyword) {
        this.checkContainsTextMultipleSectionByCssSelectorKeyword = checkContainsTextMultipleSectionByCssSelectorKeyword;
    }

    public void setCheckCompareTextMultipleSectionByCssSelectorKeyword(ActionKeyword checkCompareTextMultipleSectionByCssSelectorKeyword) {
        this.checkCompareTextMultipleSectionByCssSelectorKeyword = checkCompareTextMultipleSectionByCssSelectorKeyword;
    }

    public void setCheckElementCompareCssClassKeyword(ActionKeyword checkElementCompareCssClassKeyword) {
        this.checkElementCompareCssClassKeyword = checkElementCompareCssClassKeyword;
    }

    public void setCheckElementContainsCssClassKeyword(ActionKeyword checkElementContainsCssClassKeyword) {
        this.checkElementContainsCssClassKeyword = checkElementContainsCssClassKeyword;
    }

    public void setCheckSelectOptionNotContainsValueKeyword(ActionKeyword checkSelectOptionNotContainsValueKeyword) {
        this.checkSelectOptionNotContainsValueKeyword = checkSelectOptionNotContainsValueKeyword;
    }

    public void setCheckSelectOptionsKeyword(ActionKeyword checkSelectOptionsKeyword) {
        this.checkSelectOptionsKeyword = checkSelectOptionsKeyword;
    }

    public void setCheckSelectValueKeyword(ActionKeyword checkSelectValueKeyword) {
        this.checkSelectValueKeyword = checkSelectValueKeyword;
    }

    public void setClickKeyword(ActionKeyword clickKeyword) {
        this.clickKeyword = clickKeyword;
    }
    
    public void setClickUntilElementPresentKeyword(ActionKeyword clickUntilElementPresentKeyword) {
        this.clickUntilElementPresentKeyword = clickUntilElementPresentKeyword;
    }
    
    public void setClickUsingInputKeyword(ActionKeyword clickUsingInputKeyword) {
        this.clickUsingInputKeyword = clickUsingInputKeyword;
    }

    public void setCloseBrowserKeyword(ActionKeyword closeBrowserKeyword) {
        this.closeBrowserKeyword = closeBrowserKeyword;
    }

    public void setCompareFromResultKeyword(ActionKeyword compareFromResultKeyword) {
        this.compareFromResultKeyword = compareFromResultKeyword;
    }
    
    public void setCompareNumbersOnlyKeyword(ActionKeyword compareNumbersOnlyKeyword) {
        this.compareNumbersOnlyKeyword = compareNumbersOnlyKeyword;
    }

    public void setComparePlaceholderTextKeyword(ActionKeyword comparePlaceholderTextKeyword) {
        this.comparePlaceholderTextKeyword = comparePlaceholderTextKeyword;
    }

    public void setCompareStoredValueKeyword(ActionKeyword compareStoredValueKeyword) {
        this.compareStoredValueKeyword = compareStoredValueKeyword;
    }
    
    public void setCompareTextKeyword(ActionKeyword compareTextKeyword) {
        this.compareTextKeyword = compareTextKeyword;
    }

    public void setContainsTextKeyword(ActionKeyword containsTextKeyword) {
        this.containsTextKeyword = containsTextKeyword;
    }

    public void setCountByCssSelectorKeyword(ActionKeyword countByCssSelectorKeyword) {
        this.countByCssSelectorKeyword = countByCssSelectorKeyword;
    }

    public void setCountCompareTextByCssSelectorKeyword(ActionKeyword countCompareTextByCssSelectorKeyword) {
        this.countCompareTextByCssSelectorKeyword = countCompareTextByCssSelectorKeyword;
    }

    public void setCountContainsTextByCssSelectorKeyword(ActionKeyword countContainsTextByCssSelectorKeyword) {
        this.countContainsTextByCssSelectorKeyword = countContainsTextByCssSelectorKeyword;
    }

    public void setDatePickerKeyword(ActionKeyword datePickerKeyword) {
        this.datePickerKeyword = datePickerKeyword;
    }
    
    public void setFieldNotNullKeyword(ActionKeyword fieldNotNullKeyword) {
        this.fieldNotNullKeyword = fieldNotNullKeyword;
    }

    public void setFileDownloadCheckKeyword(ActionKeyword fileDownloadCheckKeyword) {
        this.fileDownloadCheckKeyword = fileDownloadCheckKeyword;
    }

    public void setHoverKeyword(ActionKeyword hoverKeyword) {
        this.hoverKeyword = hoverKeyword;
    }

    public void setInputFromStoredKeyword(ActionKeyword inputFromStoredKeyword) {
        this.inputFromStoredKeyword = inputFromStoredKeyword;
    }
    
    public void setInputKeyword(ActionKeyword inputKeyword) {
        this.inputKeyword = inputKeyword;
    }

    public void setInputNumberOnlyKeyword(ActionKeyword inputNumberOnlyKeyword) {
        this.inputNumberOnlyKeyword = inputNumberOnlyKeyword;
    }

    public void setIsAscendingKeyword(ActionKeyword isAscendingKeyword) {
        this.isAscendingKeyword = isAscendingKeyword;
    }

    public void setIsCheckedKeyword(ActionKeyword isCheckedKeyword) {
        this.isCheckedKeyword = isCheckedKeyword;
    }

    public void setIsDateInBetweenKeyword(ActionKeyword isDateInBetweenKeyword) {
        this.isDateInBetweenKeyword = isDateInBetweenKeyword;
    }

    public void setIsDescendingKeyword(ActionKeyword isDescendingKeyword) {
        this.isDescendingKeyword = isDescendingKeyword;
    }

    public void setIsElementDisabledKeyword(ActionKeyword isElementDisabledKeyword) {
        this.isElementDisabledKeyword = isElementDisabledKeyword;
    }

    public void setIsElementEnabledKeyword(ActionKeyword isElementEnabledKeyword) {
        this.isElementEnabledKeyword = isElementEnabledKeyword;
    }

    public void setIsElementReadOnlyKeyword(ActionKeyword isElementReadOnlyKeyword) {
        this.isElementReadOnlyKeyword = isElementReadOnlyKeyword;
    }

    public void setIsUncheckedKeyword(ActionKeyword isUncheckedKeyword) {
        this.isUncheckedKeyword = isUncheckedKeyword;
    }

    public void setJsClickKeyword(ActionKeyword jsClickKeyword) {
        this.jsClickKeyword = jsClickKeyword;
    }
    
    public void setNavigateKeyword(ActionKeyword navigateKeyword) {
        this.navigateKeyword = navigateKeyword;
    }

    public void setNonExistentKeyword(ActionKeyword nonExistentKeyword) {
        this.nonExistentKeyword = nonExistentKeyword;
    }
    
    public void setNonExistentUsingInputKeyword(ActionKeyword nonExistentUsingInputKeyword) {
        this.nonExistentUsingInputKeyword = nonExistentUsingInputKeyword;
    }

    public void setOpenBrowserKeyword(ActionKeyword openBrowserKeyword) {
        this.openBrowserKeyword = openBrowserKeyword;
    }

    public void setSelectCaseInsensitiveDropdownKeyword(ActionKeyword selectCaseInsensitiveDropdownKeyword) {
        this.selectCaseInsensitiveDropdownKeyword = selectCaseInsensitiveDropdownKeyword;
    }

    public void setSelectIndexDropdownKeyword(ActionKeyword selectIndexDropdownKeyword) {
        this.selectIndexDropdownKeyword = selectIndexDropdownKeyword;
    }

    public void setSelectVisibleNumbersOnlyDropdownKeyword(ActionKeyword selectVisibleNumbersOnlyDropdownKeyword) {
        this.selectVisibleNumbersOnlyDropdownKeyword = selectVisibleNumbersOnlyDropdownKeyword;
    }

    public void setSelectVisibleTextDropdownKeyword(ActionKeyword selectVisibleTextDropdownKeyword) {
        this.selectVisibleTextDropdownKeyword = selectVisibleTextDropdownKeyword;
    }

    public void setStoreMultipleValuesKeyword(ActionKeyword storeMultipleValuesKeyword) {
        this.storeMultipleValuesKeyword = storeMultipleValuesKeyword;
    }
    
    public void setStoreValueKeyword(ActionKeyword storeValueKeyword) {
        this.storeValueKeyword = storeValueKeyword;
    }
    
    public void setSubmitKeyword(ActionKeyword submitKeyword) {
        this.submitKeyword = submitKeyword;
    }
    
    public void setSwitchWindowKeyword(ActionKeyword switchWindowKeyword) {
        this.switchWindowKeyword = switchWindowKeyword;
    }

    public void setTextNotEqualKeyword(ActionKeyword textNotEqualKeyword) {
        this.textNotEqualKeyword = textNotEqualKeyword;
    }
    
    public void setTickCheckboxKeyword(ActionKeyword tickCheckboxKeyword) {
        this.tickCheckboxKeyword = tickCheckboxKeyword;
    }  
    
    public void setVerifyDateStateKeyword(ActionKeyword verifyDateStateKeyword) {
        this.verifyDateStateKeyword = verifyDateStateKeyword;
    }
    
    public void setVerifyFieldHasValueKeyword(ActionKeyword verifyFieldHasValueKeyword) {
        this.verifyFieldHasValueKeyword = verifyFieldHasValueKeyword;
    }

    public void setWaitForKeyword(ActionKeyword waitForKeyword) {
        this.waitForKeyword = waitForKeyword;
    }

    public void setDriverScript(DriverScript driverScript) {
        this.driverScript = driverScript;
    }

    public void setScreenshotsUtil(ScreenshotsUtil screenshotsUtil) {
        this.screenshotsUtil = screenshotsUtil;
    }

    public void setSeleniumDriver(SeleniumDriverInt seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
    }
}
