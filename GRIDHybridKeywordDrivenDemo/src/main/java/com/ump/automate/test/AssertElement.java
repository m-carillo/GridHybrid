package com.ump.automate.test;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ump.automate.test.exception.SeleniumHybridKeywordException;

/**
 * this class performs methods that checks the visibility of web elements
 * @author edward.cervantes
 */
public class AssertElement {

    private static final Logger log = Logger.getLogger(AssertElement.class);

    /**
     * method that checks whether the page element exist(if it is on the page whether hidden or not)
     * @param locator
     * @param driver
     * @return
     * @throws Exception
     */
    public boolean isElementPresent(By locator, WebDriver driver) throws SeleniumHybridKeywordException, Exception {
        if (log.isDebugEnabled()) {
            log.debug("MCI >> isElementPresent()");
        }
        boolean isElementExisting = false;
        String reasonMessage = "";
        try {
        	driver.switchTo().defaultContent();
        	//[09/21]louie.carillo added scrollIntoView
//        	WebElement objElement = driver.findElement(locator);
//            JavascriptExecutor executor = (JavascriptExecutor) driver;
//            executor.executeScript("arguments[0].scrollIntoView();", objElement);
        	
            WebDriverWait wait = new WebDriverWait(driver, 20);
            //          wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            // findElements will return an empty list if no matching elements are found instead of an exception
            isElementExisting = driver.findElements(locator).size() > 0;
        } catch (TimeoutException e) {
            isElementExisting = false;
            reasonMessage = new StringBuilder("Cannot find the element... element does not exist within time allocated ").append(e.getMessage())
                    .toString();
            log.info(reasonMessage);
            log.error("Error found. ", e);
            throw new SeleniumHybridKeywordException(reasonMessage, e);
        } catch (NoSuchElementException e) {
            isElementExisting = false;
            reasonMessage = new StringBuilder("Cannot find the element... No such Element Exception ").append(e.getMessage())
                    .toString();
            log.info(reasonMessage);
            log.error("Error found. ", e);
            throw new SeleniumHybridKeywordException(reasonMessage, e);
        } catch (Exception e) {
            isElementExisting = false;
            reasonMessage = new StringBuilder("Cannot find the element... exception is: ").append(e.getMessage()).toString();
            log.info(reasonMessage);
            log.error("Error found. ", e);
            throw new SeleniumHybridKeywordException(reasonMessage, e);
        }
        if (log.isDebugEnabled()) {
            log.debug("MCO << isElementPresent()");
        }
        return isElementExisting;
    }

    /**
     * method that checks whether the page element exist and is visible
     * @param locator
     * @param driver
     * @return
     * @throws Exception 
     */
    public boolean isElementPerformable(By locator, WebDriver driver) throws SeleniumHybridKeywordException, Exception {
        if (log.isDebugEnabled()) {
            log.debug("MCI >> isElementPerformable()");
        }
        boolean isElementExisting = false;
        boolean isElementVisible = false;
        String reasonMessage = "";
        WebElement element = null;
        try {
            isElementExisting = isElementPresent(locator, driver);

            // driver.findElement(By.xpath(propField)).isDisplayed();
            // Note: Do not confuse this method with element present on the page or not. 
            // This will return true if the element is present on the page and throw a 
            // NoSuchElementFound exception if the element is not present on the page. 
            // This refers the property of the element, sometimes the element is present 
            // this will return false, as the element is present in the DOM but not visible to us.
            if (isElementExisting) {
                //              checks the visibility of element
                //              WebDriverWait wait = new WebDriverWait(driver, 30);
                //              wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

                element = driver.findElement(locator);
                isElementVisible = element.isDisplayed();
                if (!isElementVisible) {
                    reasonMessage = "Element exist but not visible";
                    throw new SeleniumHybridKeywordException(reasonMessage);
                }
            } else {
                reasonMessage = "Cannot find the element... Please check the xpath or id.";
                throw new SeleniumHybridKeywordException(reasonMessage);
            }
        } catch (SeleniumHybridKeywordException e) {
            throw e;
        } catch (NoSuchElementException e) {
            reasonMessage = new StringBuilder("Cannot find the element... No such Element Exception ").append(e.getMessage())
                    .toString();
            log.info(reasonMessage);
            log.error("Error found. ", e);
            throw new SeleniumHybridKeywordException(reasonMessage, e);
        } catch (Exception e) {
            reasonMessage = new StringBuilder("Cannot find the element... exception is: ").append(e.getMessage()).toString();
            log.info(reasonMessage);
            log.error("Error found. ", e);
            throw new SeleniumHybridKeywordException(reasonMessage, e);
        }
        if (log.isDebugEnabled()) {
            log.debug("MCO << isElementPerformable()");
        }
        return isElementVisible;
    }
}
