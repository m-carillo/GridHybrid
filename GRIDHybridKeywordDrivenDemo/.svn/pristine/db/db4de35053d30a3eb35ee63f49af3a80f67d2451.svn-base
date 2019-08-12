package com.ump.automate.test.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
 * this class implements ActionKeyword and perform methods that checks if the webElement's CSS class is same as the input
 * @author edward.cervantes
 */
public class CheckElementCompareCssClassKeyword implements ActionKeyword {

    private static final Logger log = Logger.getLogger(CheckElementCompareCssClassKeyword.class);

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
                log.debug("MCI >> checkElementCompareCssClass()");
            }
            log.info("Retrieving element's css attribute from " + pageObjectId);
            propField = propertiesRetriever.retrievePropertiesFile("objRepo").getProperty(pageObjectId);
            By locator = By.xpath(propField);
            if (assertElement.isElementPerformable(locator, driver)) {
                WebDriverWait wait = new WebDriverWait(driver, 20);
                wait.until(ExpectedConditions.presenceOfElementLocated(locator));
                WebElement webElement = driver.findElement(locator);

                String elementClass = webElement.getAttribute("class");

                Collection<String> classList = new ArrayList(Arrays.asList(elementClass.trim().split(" ")));
                Collection<String> inputList = new ArrayList(Arrays.asList(dataInput.trim().split(" ")));

                List<String> sourceList = new ArrayList<String>(classList);
                List<String> destinationList = new ArrayList<String>(inputList);

                sourceList.removeAll(inputList);
                destinationList.removeAll(classList);

                reasonMessage = new StringBuilder("element css class: ").append(elementClass)
                        .append(" ;input: ")
                        .append(dataInput)
                        .toString();

                if (sourceList.size() == 0 && sourceList.size() == destinationList.size()) {
                    isWorking = true;
                    reasonMessage = "element css class is as expected.";
                }
                seleniumDriver.setDriver(driver);
                int implicitWaitTime = (10);
                driver.manage().timeouts().implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);
            }
        } catch (SeleniumHybridKeywordException e) {
            isWorking = false;
            log.info(e.getMessage());
            log.error("Error found. ", e);
        } catch (Exception e) {
            isWorking = false;
            reasonMessage = new StringBuilder("Not able to compare css class and the input due to ").append(e.getMessage())
                    .append("...")
                    .toString();
            log.info(reasonMessage);
            log.error("Error found. ", e);
        }
        driverScript.setReason(reasonMessage);
        if (log.isDebugEnabled()) {
            log.debug("MCO << checkElementCompareCssClass()");
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
