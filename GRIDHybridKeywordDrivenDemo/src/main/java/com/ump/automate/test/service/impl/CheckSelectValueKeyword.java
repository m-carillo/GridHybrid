package com.ump.automate.test.service.impl;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
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
 * this class implements the ActionKeyword and performs method that compares the default value to that of the input
 * @author edward.cervantes
 */
public class CheckSelectValueKeyword implements ActionKeyword {

    private static final Logger log = Logger.getLogger(CheckSelectValueKeyword.class);

    private AssertElement assertElement;

    private DriverScript driverScript;

    private PropertiesRetriever propertiesRetriever;

    private SeleniumDriverInt seleniumDriver;

    @Override
    public boolean isPerform(String pageObjectId, String dataInput, WebDriver driver) throws Exception {
        boolean isWorking = false;
        String propField = "";
        String reasonMessage = "";

        try {
            if (log.isDebugEnabled()) {
                log.debug("MCI >> checkSelectValue()");
            }
            log.info("Checking the select value...");
            propField = propertiesRetriever.retrievePropertiesFile("objRepo").getProperty(pageObjectId);
            By locator = By.xpath(propField);
            if (assertElement.isElementPerformable(locator, driver)) {
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

                WebElement webElement = driver.findElement(locator);
                Select dropdown = new Select(webElement);

                // initialize variable
                String slctCurrentValue = "";
                // retrieve the currently selected value
                slctCurrentValue = dropdown.getFirstSelectedOption().getText();
                if ("".equals(slctCurrentValue)) {
                    slctCurrentValue = dropdown.getFirstSelectedOption().getAttribute("value");
                }
                // compare the currently selected value to that of the input
                if (!slctCurrentValue.equals(dataInput)) {
                    reasonMessage = new StringBuilder("selected value is '").append(slctCurrentValue)
                            .append("' while input is '")
                            .append(dataInput)
                            .append("'")
                            .toString();
                    isWorking = false;
                } else {
                    isWorking = true;
                }
                driverScript.setReason(reasonMessage);
                seleniumDriver.setDriver(driver);
            }
        } catch (SeleniumHybridKeywordException e) {
            isWorking = false;
            log.info(e.getMessage());
            log.error("Error found. ", e);
            driverScript.setReason(e.getMessage());
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
            log.debug("MCO << checkSelectValue()");
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
