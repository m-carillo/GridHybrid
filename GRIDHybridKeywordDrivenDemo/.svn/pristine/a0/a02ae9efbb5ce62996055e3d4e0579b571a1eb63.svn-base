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
 * USES CSS
 * this class implements ActionKeyword and performs method that checks if the retrieve text CONTAINS the input text
 * case insensitive
 * @author edward.cervantes
 */
public class ContainsTextKeyword implements ActionKeyword {

    private static final Logger log = Logger.getLogger(ContainsTextKeyword.class);

    //	public static final LoggerAdapter log = CustomLogFactory.getLogger(InputKeyword.class);

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
                log.debug("MCI >> containsText()");
            }
            log.info("Entering the text in " + pageObjectId);
            propField = propertiesRetriever.retrievePropertiesFile("objRepo").getProperty(pageObjectId);
            By locator = By.xpath(propField);
            if (assertElement.isElementPresent(locator, driver)) {
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                WebElement webElement = null;
                webElement = driver.findElement(locator);
                String retrievedString = "";
                retrievedString = webElement.getText();

                if ("".equals(retrievedString)) {
                    retrievedString = webElement.getAttribute("value");
                }
                if (retrievedString.toLowerCase().contains(dataInput.toLowerCase().trim())) {
                    isWorking = true;
                    reasonMessage = "retrieved text contains input text.";
                } else {
                    reasonMessage = new StringBuilder("retrieved: '").append(retrievedString)
                            .append("'; input: '")
                            .append(dataInput)
                            .append("'")
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
            reasonMessage = new StringBuilder("Not able to compare the object text due to ").append(e.getMessage())
                    .append("...")
                    .toString();
            log.info(reasonMessage);
            log.error("Error found. ", e);
        }
        driverScript.setReason(reasonMessage);
        if (log.isDebugEnabled()) {
            log.debug("MCO << containsText()");
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
