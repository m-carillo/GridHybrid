package com.ump.automate.test.service.impl;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ump.automate.test.AssertElement;
import com.ump.automate.test.exception.SeleniumHybridKeywordException;
import com.ump.automate.test.execution.DriverScript;
import com.ump.automate.test.service.ActionKeyword;
import com.ump.automate.test.util.properties.PropertiesRetriever;

/**
 * this class implements ActionKeyword and performs method that checks if the element is present and is visible
 * @author edward.cervantes
 */
public class VerifyFieldHasValueKeyword implements ActionKeyword {

    private static final Logger log = Logger.getLogger(VerifyFieldHasValueKeyword.class);

    private AssertElement assertElement;

    private DriverScript driverScript;

    private PropertiesRetriever propertiesRetriever;

    /**
     * method that to be called in Excel file to perform checking if page element exist
     */
    @Override
    public boolean isPerform(String pageObjectId, String dataInput, WebDriver driver) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("MCI >> assert()");
        }
        boolean isPresent = false;
        String propField = "";
        By locator = null;
        String fieldValue = "";
        String reasonMessage = "";
        try {
            propField = propertiesRetriever.retrievePropertiesFile("objRepo").getProperty(pageObjectId);
            propField = propField.replaceAll("fieldLabel", dataInput);
            locator = By.xpath(propField);
            isPresent = assertElement.isElementPresent(locator, driver);
            if (isPresent) {
            	fieldValue = driver.findElement(locator).getText();
            	//System.out.println("Field Text Value: " + fieldValue);
            	if("".equals(fieldValue)){
            		fieldValue = driver.findElement(locator).getAttribute("value");
            		//System.out.println("Field Value: " + fieldValue);
            		
            		if (fieldValue == null || "".equals(fieldValue)){
            			isPresent = false;
            			reasonMessage = "field does not contain a value";
                        log.info(reasonMessage);
                        driverScript.setReason(reasonMessage);
            		}
            	}

            	driverScript.setReason(dataInput + ": [" + fieldValue + "]");
            } else {
            	System.out.println("page object does not exist");
                driverScript.setReason("page object does not exist");
            }
        } catch (SeleniumHybridKeywordException e) {
            isPresent = false;
            log.info(e.getMessage());
            log.error("Error found. ", e);
            driverScript.setReason(e.getMessage());
        } catch (Exception e) {
            isPresent = false;
            log.error("Error found. ", e);
            driverScript.setReason(e.getMessage());
        }
        if (log.isDebugEnabled()) {
            log.debug("MCO << assert()");
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
