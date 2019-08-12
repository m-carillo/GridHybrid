package com.ump.automate.test.service.impl;

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

public class IsElementReadOnlyKeyword implements ActionKeyword {

    private static final Logger log = Logger.getLogger(IsElementReadOnlyKeyword.class);

    private AssertElement assertElement;

    private DriverScript driverScript;

    private PropertiesRetriever propertiesRetriever;

    private SeleniumDriverInt seleniumDriver;

    @Override
    public boolean isPerform(String pageObjectId, String dataInput, WebDriver driver) throws Exception {
        boolean isElemReadOnly = false;
        String propField = "";
        String reasonMessage = "";

        try {
            if (log.isDebugEnabled()) {
                log.debug("MCI >> isElementReadOnly()");
            }
            propField = propertiesRetriever.retrievePropertiesFile("objRepo").getProperty(pageObjectId);
            By locator = By.xpath(propField);
            if (assertElement.isElementPresent(locator, driver)) {
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                WebElement myDynamicElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(locator));
                WebElement webElement = null;
                webElement = driver.findElement(locator);
                String value = "";
                value = webElement.getAttribute("readonly");

                if (value != null && !"".equals(value) && !value.isEmpty()) {
                    isElemReadOnly = true;
                }

                seleniumDriver.setDriver(driver);
            }
        } catch (SeleniumHybridKeywordException e) {
            isElemReadOnly = false;
            log.info(e.getMessage());
            log.error("Error found. ", e);
            driverScript.setReason(e.getMessage());
        } catch (Exception e) {
            isElemReadOnly = false;
            reasonMessage = new StringBuilder("Not able to check if element is readOnly due to ").append(e.getMessage())
                    .append("...")
                    .toString();
            log.error("Error found. ", e);
            log.info(reasonMessage);
            driverScript.setReason(reasonMessage);
        }
        if (log.isDebugEnabled()) {
            log.debug("MCO << isElementReadOnly()");
        }
        return isElemReadOnly;
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
