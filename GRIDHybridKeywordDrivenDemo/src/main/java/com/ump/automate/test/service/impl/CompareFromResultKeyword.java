package com.ump.automate.test.service.impl;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ump.automate.test.AssertElement;
import com.ump.automate.test.exception.SeleniumHybridKeywordException;
import com.ump.automate.test.execution.DriverScript;
import com.ump.automate.test.service.ActionKeyword;
import com.ump.automate.test.util.properties.PropertiesRetriever;

/**
 * Function:
 * @author louie.carillo
 */
public class CompareFromResultKeyword implements ActionKeyword {

    private static final Logger log = Logger.getLogger(CompareFromResultKeyword.class);

    private AssertElement assertElement;

    private DriverScript driverScript;

    private PropertiesRetriever propertiesRetriever;
    
    public BasicOperatorKeyword BasicOperatorKeyword;

    /**
     * Function: 
     */
    @Override
    public boolean isPerform(String pageObjectId, String dataInput, WebDriver driver) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("MCI >> assert()");
        }
        boolean isPresent = false;
        boolean isWorking = false;
        String propField = "";
        By locator = null;
        String fieldValue = "";
        String resultValue = "";
        String reasonMessage = "";
        try {
            propField = propertiesRetriever.retrievePropertiesFile("objRepo").getProperty(pageObjectId);
            locator = By.xpath(propField);
            isPresent = assertElement.isElementPresent(locator, driver);
            if (isPresent) {
            	fieldValue = driver.findElement(locator).getText();
            	System.out.println("Text: " + fieldValue);
            	if("".equals(fieldValue)){
            		fieldValue = driver.findElement(locator).getAttribute("value");
            		System.out.println("Value: " + fieldValue);
            		
            		if (fieldValue == null || "".equals(fieldValue)){
            			isPresent = false;
            			reasonMessage = "field does not contain a value";
                        log.info(reasonMessage);
                        driverScript.setReason(reasonMessage);
            		}
            	}
            	BasicOperatorKeyword storedValue = new BasicOperatorKeyword ();
            	resultValue = Double.toString(storedValue.result).replaceAll("\\.0*$", "");
            	fieldValue = fieldValue.replaceAll(",", "").replaceAll("\\.0*$", "");
            	System.out.println("Stored Value: " + resultValue);
            	System.out.println("Field Value: " + fieldValue);
            	if(resultValue.equalsIgnoreCase(fieldValue)){
            		System.out.println("retrieved text is the same as expected results");
            		driverScript.setReason(dataInput + ": [" + fieldValue + "]");
            		isWorking = true;
            	} else if (resultValue.contains("-")){
            		resultValue = "(" + resultValue.replaceAll("-", "") + ")";
            		if (resultValue.equalsIgnoreCase(fieldValue)){
            			System.out.println("retrieved text is the same as expected results");
                		driverScript.setReason(dataInput + ": [" + fieldValue + "]");
                		isWorking = true;
            		}
            	} else {
            	
            		System.out.println("doesn't matched");
            		reasonMessage = new StringBuilder("retrieved: ").append(fieldValue)
                            .append("; input: ")
                            .append(dataInput)
                            .toString();
            	}
            		return isWorking;
            	
            } else {
                driverScript.setReason("page object does not exist");
            }
        } catch (SeleniumHybridKeywordException e) {
            isPresent = false;
            isWorking = false;
            log.info(e.getMessage());
            log.error("Error found. ", e);
            driverScript.setReason(e.getMessage());
        } catch (Exception e) {
            isPresent = false;
            isWorking = false;
            log.error("Error found. ", e);
            driverScript.setReason(e.getMessage());
        }
        if (log.isDebugEnabled()) {
            log.debug("MCO << assert()");
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
}
