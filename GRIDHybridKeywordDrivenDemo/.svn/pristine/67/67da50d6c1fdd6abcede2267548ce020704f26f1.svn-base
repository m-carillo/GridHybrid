package com.ump.automate.test.service.impl;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ump.automate.test.AssertElement;
import com.ump.automate.test.browser.SeleniumDriverInt;
import com.ump.automate.test.exception.SeleniumHybridKeywordException;
import com.ump.automate.test.execution.DriverScript;
import com.ump.automate.test.service.ActionKeyword;
import com.ump.automate.test.util.properties.PropertiesRetriever;

/**
 * this class implements the ActionKeyword and checks whether the element exist or not
 * @author edward.cervantes
 */
public class NonExistentKeyword implements ActionKeyword {

    private static final Logger log = Logger.getLogger(NonExistentKeyword.class);

    private AssertElement assertElement;

    private DriverScript driverScript;

    private PropertiesRetriever propertiesRetriever;

    private SeleniumDriverInt seleniumDriver;

    /**
     * this method handles the inspection of element whether it exists or not
     * this returns true if the element can't be found
     */
    @Override
    public boolean isPerform(String pageObjectId, String dataInput, WebDriver driver) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("MCI >> nonExistent()");
        }
        boolean isPresent = false;
        boolean isNotPresent = false;
        String propField = "";
        propField = propertiesRetriever.retrievePropertiesFile("objRepo").getProperty(pageObjectId);
        By locator = null;
        try {
            locator = By.xpath(propField);
            isPresent = assertElement.isElementPresent(locator, driver);
            if (!isPresent) {
                isNotPresent = true;
            }
            seleniumDriver.setDriver(driver);
        } catch (SeleniumHybridKeywordException e) {
            isNotPresent = true;
            log.info(e.getMessage());
            log.error("Error found. ", e);
            driverScript.setReason(e.getMessage());
        } catch (Exception e) {
            isNotPresent = true;
            log.error("Error found. ", e);
        }
        if (isNotPresent) {
            driverScript.setReason("page object does not exist...");
        } else {
            driverScript.setReason("page object exist...");
        }
        if (log.isDebugEnabled()) {
            log.debug("MCO << nonExistent()");
        }
        return isNotPresent;
    }

    public void setAssertElement(AssertElement assertElement) {
        this.assertElement = assertElement;
    }

    public void setDriverScript(DriverScript driverScript) {
        this.driverScript = driverScript;
    }

    public void setPropertiesRetriever(PropertiesRetriever propertiesRetriever) {
        this.propertiesRetriever = propertiesRetriever;
    }

    public void setSeleniumDriver(SeleniumDriverInt seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
    }
}
