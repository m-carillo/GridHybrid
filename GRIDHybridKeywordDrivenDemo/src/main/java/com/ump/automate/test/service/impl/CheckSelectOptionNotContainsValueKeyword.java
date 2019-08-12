package com.ump.automate.test.service.impl;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.ump.automate.test.AssertElement;
import com.ump.automate.test.browser.SeleniumDriverInt;
import com.ump.automate.test.exception.SeleniumHybridKeywordException;
import com.ump.automate.test.execution.DriverScript;
import com.ump.automate.test.service.ActionKeyword;
import com.ump.automate.test.util.properties.PropertiesRetriever;

/**
 * this class implements the ActionKeyword and performs method that checks if select option value exist
 * @author edward.cervantes
 */
public class CheckSelectOptionNotContainsValueKeyword implements ActionKeyword {

    private static final Logger log = Logger.getLogger(CheckSelectOptionNotContainsValueKeyword.class);

    private AssertElement assertElement;

    private DriverScript driverScript;

    private PropertiesRetriever propertiesRetriever;

    private SeleniumDriverInt seleniumDriver;

    @Override
    public boolean isPerform(String pageObjectId, String dataInput, WebDriver driver) throws Exception {
        boolean isWorking = true;
        String propField = "";
        String reasonMessage = "";

        try {
            if (log.isDebugEnabled()) {
                log.debug("MCI >> checkSelectOptionNotContainsValue()");
            }
            log.info("Checking the select value...");
            propField = propertiesRetriever.retrievePropertiesFile("objRepo").getProperty(pageObjectId);
            By locator = By.xpath(propField);
            if (assertElement.isElementPerformable(locator, driver)) {
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

                WebElement webElement = driver.findElement(locator);
                Select dropdown = new Select(webElement);
                dropdown.selectByVisibleText(dataInput.trim());
                reasonMessage = "option value exists";
                isWorking = false;
            }
        } catch (SeleniumHybridKeywordException e) {
            isWorking = false;
            log.info(e.getMessage());
            log.error("Error found. ", e);
        } catch (NoSuchElementException e) {
            // if the value entered not found in the dropdown option's list, it will throw 
            // org.openqa.selenium.NoSuchElementException and will go here
            isWorking = true;
            reasonMessage = "value not in dropdown list.";
            seleniumDriver.setDriver(driver);
        } catch (Exception e) {
            isWorking = false;
            reasonMessage = new StringBuilder("Not able to check the select option due to ").append(e.getMessage())
                    .append("...")
                    .toString();
            log.info(reasonMessage);
            log.error("Error found. ", e);
        }
        driverScript.setReason(reasonMessage);
        if (log.isDebugEnabled()) {
            log.debug("MCO << checkSelectOptionNotContainsValue()");
        }
        return isWorking;
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
