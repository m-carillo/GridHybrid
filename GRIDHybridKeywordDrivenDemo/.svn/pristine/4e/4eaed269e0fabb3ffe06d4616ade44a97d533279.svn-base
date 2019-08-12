package com.ump.automate.test.service.impl;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ump.automate.test.AssertElement;
import com.ump.automate.test.browser.SeleniumDriverInt;
import com.ump.automate.test.exception.SeleniumHybridKeywordException;
import com.ump.automate.test.execution.DriverScript;
import com.ump.automate.test.service.ActionKeyword;
import com.ump.automate.test.util.properties.PropertiesRetriever;

/**
 * this class implements ActionKeyword and performs method that selects the option from dropdown
 * CASE SENSITIVE approach
 * @author edward.cervantes
 */
public class SelectFromDropdownByVisibleTextKeyword implements ActionKeyword {

    private static final Logger log = Logger.getLogger(SelectFromDropdownByVisibleTextKeyword.class);

    //	public static final LoggerAdapter log = CustomLogFactory.getLogger(SelectFromDropdownByVisibleTextKeyword.class);

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
                log.debug("MCI >> selectFromDropdownByVisibleText()");
            }
            log.info("Selecting from " + pageObjectId);

            propField = propertiesRetriever.retrievePropertiesFile("objRepo").getProperty(pageObjectId);
            By locator = By.xpath(propField);
            if (assertElement.isElementPerformable(locator, driver)) {
                WebDriverWait wait = new WebDriverWait(driver, 50);
                wait.until(ExpectedConditions.presenceOfElementLocated(locator));
                WebElement webElement = driver.findElement(locator);
                Select dropdown = new Select(webElement);
                log.info("selecting " + dataInput + " from the dropdown");
                dropdown.selectByVisibleText(dataInput.trim());
                seleniumDriver.setDriver(driver);
                isWorking = true;
            }
        } catch (SeleniumHybridKeywordException e) {
            isWorking = false;
            log.info(e.getMessage());
            log.error("Error found. ", e);
            driverScript.setReason(e.getMessage());
        } catch (Exception e) {
            isWorking = false;
            reasonMessage = new StringBuilder("Not able to enter ").append(pageObjectId)
                    .append(" due to ")
                    .append(e.getMessage())
                    .append("...")
                    .toString();
            log.info(reasonMessage);
            log.error("Error found. ", e);
            driverScript.setReason(reasonMessage);
        }
        if (log.isDebugEnabled()) {
            log.debug("MCO << selectFromDropdownByVisibleText()");
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