package com.ump.automate.test.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ump.automate.test.AssertElement;
import com.ump.automate.test.browser.SeleniumDriverInt;
import com.ump.automate.test.exception.SeleniumHybridKeywordException;
import com.ump.automate.test.execution.DriverScript;
import com.ump.automate.test.service.ActionKeyword;
import com.ump.automate.test.util.properties.PropertiesRetriever;

/**
 * this class implements ActionKeyword and perform methods that checks if the webElement contains the CSS class
 * @author edward.cervantes
 */
public class CheckElementContainsCssClassKeyword implements ActionKeyword {

    private static final Logger log = Logger.getLogger(CheckElementContainsCssClassKeyword.class);

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
                log.debug("MCI >> checkElementContainsCssClass()");
            }
            log.info("Retrieving element's css attribute from " + pageObjectId);
            propField = propertiesRetriever.retrievePropertiesFile("objRepo").getProperty(pageObjectId);
            By locator = By.xpath(propField);
            if (assertElement.isElementPerformable(locator, driver)) {
                WebDriverWait wait = new WebDriverWait(driver, 20);
                wait.until(ExpectedConditions.presenceOfElementLocated(locator));
                WebElement webElement = driver.findElement(locator);

                String elementClass = webElement.getAttribute("class");

                List<String> classList = Arrays.asList(elementClass.split(" "));

                reasonMessage = new StringBuilder("element css class: ").append(elementClass)
                        .append(" ;input: ")
                        .append(dataInput)
                        .toString();
                for (String className : classList) {
                    if (className.trim().equalsIgnoreCase(dataInput.trim())) {
                        isWorking = true;
                        reasonMessage = "element css class contains the input.";
                        break;
                    }
                }
                driverScript.setReason(reasonMessage);
                seleniumDriver.setDriver(driver);
                int implicitWaitTime = (10);
                driver.manage().timeouts().implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);
            }
        } catch (SeleniumHybridKeywordException e) {
            isWorking = false;
            log.info(e.getMessage());
            log.error("Error found. ", e);
            driverScript.setReason(e.getMessage());
       } catch (Exception e) {
            isWorking = false;
            reasonMessage = new StringBuilder("Not able to check css class if it contains the input due to ").append(e.getMessage())
                    .append("...")
                    .toString();
            log.info(reasonMessage);
            log.error("Error found. ", e);
            driverScript.setReason(reasonMessage);
        }
        if (log.isDebugEnabled()) {
            log.debug("MCO << checkElementContainsCssClass()");
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
