package com.ump.automate.test.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

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
 * this class implements the ActionKeyword and performs method that compares the 'select' tag options to the inputs
 * comma delimited
 * @author edward.cervantes
 */
public class CheckSelectOptionsKeyword implements ActionKeyword {

    private static final Logger log = Logger.getLogger(CheckSelectOptionsKeyword.class);

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
                log.debug("MCI >> checkSelectOption()");
            }
            log.info(new StringBuilder("checking from: ").append(pageObjectId).append(" SELECT options...").toString());
            propField = propertiesRetriever.retrievePropertiesFile("objRepo").getProperty(pageObjectId);
            By locator = By.xpath(propField);
            if (assertElement.isElementPerformable(locator, driver)) {
                WebDriverWait wait = new WebDriverWait(driver, 20);
                wait.until(ExpectedConditions.presenceOfElementLocated(locator));

                WebElement webElement = driver.findElement(locator);
                Select dropdown = new Select(webElement);
                List<WebElement> dropdownList = dropdown.getOptions();

                // get the SELECT dropdown size()
                int listCount = 0;
                listCount = dropdownList.size();

                if ("".equals(dataInput.trim()) || listCount == 0) {
                    reasonMessage = "either inputted data or dropdown option/s is empty.";
                } else {
                    // place dataInput in a list
                    Collection<String> collDataInputList = new ArrayList(Arrays.asList(dataInput.trim().split("', '")));
                    List<String> tempDataInputList = new ArrayList<String>(collDataInputList);
                    List<String> dataInputList = new ArrayList<String>();
                    for (String strData : tempDataInputList) {
                        strData = strData.trim();
                        dataInputList.add(strData);
                    }

                    // get the size of dataInput list
                    int dataInputCount = 0;
                    dataInputCount = dataInputList.size();

                    // CHECK if available dropdown size and words to check are equal
                    if (dataInputCount != listCount) { // compare list sizes
                        isWorking = false;
                        reasonMessage = new StringBuilder("size of input is different from 'select options' size... retrieved: ").append(listCount)
                                .append(", input: ")
                                .append(dataInputCount)
                                .toString();
                    } else { // compare values of each list
                        Collection<String> colldDownList = new ArrayList<String>();
                        for (WebElement element : dropdownList) {
                            //						String stringElement = element.toString();
                            String stringElement = "";
                            stringElement = element.getText();
                            if ("".equals(stringElement)) {
                                stringElement = element.getAttribute("value");
                            }
                            colldDownList.add(stringElement.trim());
                        }
                        List<String> dDownList = new ArrayList<String>(colldDownList);

                        // remove SAME values
                        dataInputList.removeAll(colldDownList);
                        dDownList.removeAll(collDataInputList);
                        if (dataInputList.size() == 0 && dDownList.size() == 0) {
                            isWorking = true;
                            reasonMessage = "select options same as expected";
                        } else {
                            String dropDownExcessMessage = "";
                            String dataExcessMessage = "";
                            if (dataInputList != null) {
                                dataExcessMessage = new StringBuilder("data input list left: ").append(dataInputList)
                                        .append("; ")
                                        .toString();
                            }
                            if (dDownList != null) {
                                dropDownExcessMessage = new StringBuilder("dropdown list left: ").append(dDownList).toString();
                            }
                            reasonMessage = new StringBuilder(dataExcessMessage).append(dropDownExcessMessage).toString();
                        }
                    }
                }
                seleniumDriver.setDriver(driver);
            }
        } catch (SeleniumHybridKeywordException e) {
            isWorking = false;
            log.info(e.getMessage());
            log.error("Error found. ", e);
      } catch (Exception e) {
            isWorking = false;
            reasonMessage = new StringBuilder("Not able to check select's option due to ").append(e.getMessage())
                    .append("...")
                    .toString();
            log.info(reasonMessage);
            log.error("Error found. ", e);
        }
        driverScript.setReason(reasonMessage);
        if (log.isDebugEnabled()) {
            log.debug("MCO << checkSelectOption()");
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