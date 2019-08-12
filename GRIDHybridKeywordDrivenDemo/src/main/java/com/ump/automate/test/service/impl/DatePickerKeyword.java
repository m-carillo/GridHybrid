package com.ump.automate.test.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ump.automate.test.browser.SeleniumDriverInt;
import com.ump.automate.test.execution.DriverScript;
import com.ump.automate.test.service.ActionKeyword;
import com.ump.automate.test.util.properties.PropertiesRetriever;

/**
 * function
 * @author louie.carillo
 */
public class DatePickerKeyword implements ActionKeyword {

    private static final Logger log = Logger.getLogger(DatePickerKeyword.class);

    private DriverScript driverScript;

    private PropertiesRetriever propertiesRetriever;

    private SeleniumDriverInt seleniumDriver;

    @Override
    public boolean isPerform(String pageObjectId, String dataInput, WebDriver driver) throws Exception {
        boolean isWorking = false;
        boolean isElementPresent = false;
        String propField = "";
        String reasonMessage = "";
        String month = "";
        String year = "";
        String day = "";
        Date date = null;
        try {
            if (log.isDebugEnabled()) {
                log.debug("MCI >> datePicker()");
            }
            log.info(new StringBuilder("Picking Date: ").append(pageObjectId).toString());
            //System.out.println("Input Date: " + dataInput.toString());
            
            DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
            
            if(dataInput.equalsIgnoreCase("date today")){
            	date = new Date();
            	//System.out.println("Date today: " + df.format(date));
            } else {
            	date = (Date)df.parse(dataInput);
            }  
            
            //System.out.println("Formatted Date: " + df.format(date));
            List<String> inputDate = Arrays.asList(df.format(date).split("-"));
        	month = inputDate.get(1).toString();
        	//System.out.println("Month: " + month);
        	year = inputDate.get(2).toString();
        	//System.out.println("Year: " + year);
        	day = inputDate.get(0).toString();
        	//System.out.println("Day: " + day);
        	
            
            propField = propertiesRetriever.retrievePropertiesFile("objRepo").getProperty(pageObjectId);
            //System.out.println("Input Date Xpath: " + propField);
            List <String> dateLocator = Arrays.asList(propField.split(";"));
            
            String monthXpath = dateLocator.get(1).toString();
            String yearXpath = dateLocator.get(2).toString();
            String dayXpath = dateLocator.get(0).toString().replaceAll("day", day.replaceAll("^0+", ""));
            
            By monthLocator = By.xpath(monthXpath);
            //System.out.println(monthLocator);
            By yearLocator = By.xpath(yearXpath);
            //System.out.println(yearLocator);
            By dayLocator = By.xpath(dayXpath);
            //System.out.println(dayLocator);
            
            
            if(driver.findElement(monthLocator).isDisplayed()){
            	WebDriverWait wait = new WebDriverWait(driver, 10);
                wait.until(ExpectedConditions.presenceOfElementLocated(monthLocator));
                WebElement webElement = driver.findElement(monthLocator);
                Select dropdown = new Select(webElement);
                log.info("selecting " + month + " from the dropdown");
                int index = 0;
                String dropdownText = "";
                for (WebElement option : dropdown.getOptions()) {
                    dropdownText = option.getText();
                    if ("".equals(dropdownText)) {
                        dropdownText = option.getAttribute("value");
                    }
                    if (dropdownText.trim().equalsIgnoreCase(month.trim()))
                        break;
                    index++;
                }
                dropdown.selectByIndex(index);
                seleniumDriver.setDriver(driver);
                isWorking = true;
            }

            if(driver.findElement(yearLocator).isDisplayed()){
            	WebDriverWait wait = new WebDriverWait(driver, 10);
                wait.until(ExpectedConditions.presenceOfElementLocated(yearLocator));
                WebElement webElement = driver.findElement(yearLocator);
                Select dropdown = new Select(webElement);
                log.info("selecting " + year + " from the dropdown");
                int index = 0;
                String dropdownText = "";
                for (WebElement option : dropdown.getOptions()) {
                    dropdownText = option.getText();
                    if ("".equals(dropdownText)) {
                        dropdownText = option.getAttribute("value");
                    }
                    if (dropdownText.trim().equalsIgnoreCase(year.trim()))
                        break;
                    index++;
                }
                dropdown.selectByIndex(index);
                seleniumDriver.setDriver(driver);
                isWorking = true;
            }
            
            int implicitWaitTime = 5;
            driver.manage().timeouts().implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOfElementLocated(dayLocator));
            isElementPresent = driver.findElements(dayLocator).size() > 0;
            
//            if(driver.findElement(dayLocator).isDisplayed()){
//            	//System.out.println("Locator is present");
//            	isElementPresent = true;
//            }
            if (isElementPresent) {
                
            	WebElement webElement = driver.findElement(dayLocator);

                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].click();", webElement);
            	
            	
//            	
//            	WebElement webElement = null;
//                webElement = driver.findElement(dayLocator);
//                webElement.click();
//                
//                driver.manage().timeouts().implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);
                
                //System.out.println("Date has been picked!");
                seleniumDriver.setDriver(driver);
                isWorking = true;
                
            }
        }  
//        catch (SeleniumHybridKeywordException e) {
//            isWorking = false;
//            log.info(e.getMessage());
//            log.error("Error found. ", e);
//            driverScript.setReason(e.getMessage());
//        }
        catch (Exception e) {
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
