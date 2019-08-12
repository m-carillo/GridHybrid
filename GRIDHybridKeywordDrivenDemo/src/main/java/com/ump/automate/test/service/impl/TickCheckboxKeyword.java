package com.ump.automate.test.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ump.automate.test.AssertElement;
import com.ump.automate.test.browser.SeleniumDriverInt;
import com.ump.automate.test.exception.SeleniumHybridKeywordException;
import com.ump.automate.test.execution.DriverScript;
import com.ump.automate.test.service.ActionKeyword;
import com.ump.automate.test.util.properties.PropertiesRetriever;

/**
 * Function: 
 * @author louie.carillo
 */
public class TickCheckboxKeyword implements ActionKeyword {

    private static final Logger log = Logger.getLogger(TickCheckboxKeyword.class);

    private AssertElement assertElement;

    private DriverScript driverScript;

    private PropertiesRetriever propertiesRetriever;

    private SeleniumDriverInt seleniumDriver;
    
    public StoreValueKeyword StoreValueKeyword;

    @Override
    public boolean isPerform(String pageObjectId, String dataInput, WebDriver driver) throws Exception {
        boolean isWorking = false;
        String propField = "";
        String reasonMessage = "";
        
        try {
            if (log.isDebugEnabled()) {
                log.debug("MCI >> ticking check box()");
            }
            log.info(new StringBuilder("Ticking on Webelement: ").append(pageObjectId).toString());
            propField = propertiesRetriever.retrievePropertiesFile("objRepo").getProperty(pageObjectId);
//            
//            StoreValueKeyword storedValueKeyword = new StoreValueKeyword ();
//        	storedValue = storedValueKeyword.tempValue;
//            
//            if("from stored".equalsIgnoreCase(dataInput)){
//            	propField = propField.replaceAll("qwerty",storedValue);
//            } else {
//            	propField = propField.replaceAll("qwerty", dataInput);
//            }
            List<String> propFieldList = Arrays.asList(propField.split(";"));
            String rowSizeXpath = propFieldList.get(0);
            String rowXpath = propFieldList.get(1);
            String elementToClick = "";
            By locator = By.xpath(rowSizeXpath);
            Boolean isElementExisting = null;
            int elementSize = driver.findElements(locator).size();
            System.out.println("Element size: " + elementSize);
            
            if (assertElement.isElementPerformable(locator, driver)) {
                          
                for (int i = 1; i < elementSize + 1; i++ ){
                	elementToClick = rowXpath.replaceAll("rowNum", String.valueOf(i)).replaceAll("qwerty", dataInput);
                	isElementExisting = driver.findElements(By.xpath(elementToClick)).size() > 0;
                	
                	if(isElementExisting ){
                		System.out.println("[" + i + "] Element has been clicked");
                		driver.findElement(By.xpath(elementToClick)).click();
                	} else {
                		System.out.println("Element does not exist and/or not clickable");
                	}
                }
                
                seleniumDriver.setDriver(driver);
                isWorking = true;
                int implicitWaitTime = 10;
                driver.manage().timeouts().implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);
            }
        } catch (SeleniumHybridKeywordException e) {
            isWorking = false;
            log.info(e.getMessage());
            log.error("Error found. ", e);
            driverScript.setReason(e.getMessage());
        } catch (Exception e) {
            isWorking = false;
            reasonMessage = new StringBuilder("Not able to click due to ").append(e.getMessage()).toString();
            log.info(reasonMessage);
            log.error("Error found. ", e);
            driverScript.setReason(reasonMessage);
        }
        if (log.isDebugEnabled()) {
            log.debug("MCO << click()");
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
