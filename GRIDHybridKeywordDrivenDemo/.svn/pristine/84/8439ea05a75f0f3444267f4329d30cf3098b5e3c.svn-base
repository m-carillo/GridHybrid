package com.ump.automate.test.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ump.automate.test.AssertElement;
import com.ump.automate.test.exception.SeleniumHybridKeywordException;
import com.ump.automate.test.execution.DriverScript;
import com.ump.automate.test.service.ActionKeyword;
import com.ump.automate.test.util.properties.PropertiesRetriever;

/**
 * this class implements ActionKeyword and performs method that checks if the elements are present and are visible
 * USES CSS SELECTOR
 * @author edward.cervantes
 */
public class AssertMultipleKeyword implements ActionKeyword {

    private static final Logger log = Logger.getLogger(CheckCompareTextMultipleSectionByCssSelectorKeyword.class);

    private AssertElement assertElement;

    private DriverScript driverScript;

    private PropertiesRetriever propertiesRetriever;

    @Override
    public boolean isPerform(String pageObjectId, String dataInput, WebDriver driver) throws Exception {
        boolean isPresent = false;

        String propField = "";
        String reasonMessage = "";

        try {
            if (log.isDebugEnabled()) {
                log.debug("MCI >> checkCompareTextMultipleSection()");
            }
            propField = propertiesRetriever.retrievePropertiesFile("objRepo").getProperty(pageObjectId);
            By locator = By.cssSelector(propField);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            List<WebElement> elements = new ArrayList<WebElement>();
            elements = driver.findElements(locator);

            for (WebElement webElement : elements) {
                isPresent = assertElement.isElementPresent(locator, driver);
            }
        } catch (SeleniumHybridKeywordException e) {
            isPresent = false;
            log.info(e.getMessage());
            log.error("Error found. ", e);
            driverScript.setReason(e.getMessage());
        } catch (Exception e) {
            isPresent = false;
            reasonMessage = e.toString();
            log.info(reasonMessage);
            log.error("Error found. ", e);
        }
        driverScript.setReason(reasonMessage);
        if (log.isDebugEnabled()) {
            log.debug("MCO << checkCompareTextMultipleSection()");
        }
        return isPresent;
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
}