package com.ump.automate.test.service.impl;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ump.automate.test.AssertElement;
import com.ump.automate.test.browser.SeleniumDriverInt;
import com.ump.automate.test.exception.SeleniumHybridKeywordException;
import com.ump.automate.test.execution.DriverScript;
import com.ump.automate.test.service.ActionKeyword;
import com.ump.automate.test.util.properties.PropertiesRetriever;

/**
 * this class implements ActionKeyword and performs method that compare number input to the retrieved value
 * removes the string inputs that are not a number
 * @author edward.cervantes
 */
public class CompareNumbersOnlyKeyword implements ActionKeyword {

    private static final Logger log = Logger.getLogger(CompareNumbersOnlyKeyword.class);

    //	public static final LoggerAdapter log = CustomLogFactory.getLogger(InputKeyword.class);

    private AssertElement assertElement;

    private DriverScript driverScript;

    private SeleniumDriverInt seleniumDriver;

    private PropertiesRetriever propertiesRetriever;

    @Override
    public boolean isPerform(String pageObjectId, String dataInput, WebDriver driver) throws Exception {
        boolean isWorking = false;
        String propField = "";
        String reasonMessage = "";
        try {
            if (log.isDebugEnabled()) {
                log.debug("MCI >> compareNumbers()");
            }
            log.info(new StringBuilder("Entering the text in ").append(pageObjectId).toString());
            propField = propertiesRetriever.retrievePropertiesFile("objRepo").getProperty(pageObjectId);
            By locator = By.xpath(propField);
            if (assertElement.isElementPresent(locator, driver)) {
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                WebElement webElement = null;
                webElement = driver.findElement(locator);
                String retrievedString = "";
                String numbersOnly = "";
                // retrieved xpath value
                retrievedString = webElement.getText().trim();
                if ("".equals(retrievedString)) {
                    retrievedString = webElement.getAttribute("value").trim();
                }

                // removed unwanted string and keep numbers only
                numbersOnly = dataInput.replaceAll("([a-zA-z &!@#$%^*():;'<>/?|\"+,-]+)", "").trim();

                if (numbersOnly.equalsIgnoreCase(retrievedString)) {
                    isWorking = true;
                    reasonMessage = "retrieved string is the same as expected.";
                } else {
                    reasonMessage = new StringBuilder("retrieved: ").append(retrievedString)
                            .append("; input: ")
                            .append(dataInput)
                            .toString();
                }
                seleniumDriver.setDriver(driver);
            }
        } catch (SeleniumHybridKeywordException e) {
            isWorking = false;
            log.info(e.getMessage());
            log.error("Error found. ", e);
            driverScript.setReason(e.getMessage());
        } catch (Exception e) {
            isWorking = false;
            reasonMessage = new StringBuilder("Not able to get the object text due to ").append(e.getMessage())
                    .append("...")
                    .toString();
            log.info(reasonMessage);
            log.error("Error found. ", e);
        }
        driverScript.setReason(reasonMessage);
        if (log.isDebugEnabled()) {
            log.debug("MCO << compareText()");
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
