package com.ump.automate.test.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ump.automate.test.AssertElement;
import com.ump.automate.test.browser.SeleniumDriverInt;
import com.ump.automate.test.exception.SeleniumHybridKeywordException;
import com.ump.automate.test.execution.DriverScript;
import com.ump.automate.test.service.ActionKeyword;
import com.ump.automate.test.util.properties.PropertiesRetriever;

/**
 * this class implements ActionKeyword and performs checking of retrieved dates if it is between the input dates
 * @author edward.cervantes
 */
public class IsDateInBetweenKeyword implements ActionKeyword {

    private static final Logger log = Logger.getLogger(IsDateInBetweenKeyword.class);

    //	public static final LoggerAdapter log = CustomLogFactory.getLogger(InputKeyword.class);

    private AssertElement assertElement;

    private DriverScript driverScript;

    private PropertiesRetriever propertiesRetriever;

    private SeleniumDriverInt seleniumDriver;

    /**
     * input format ('minDate'-'maxDate'): mm/dd/yyyy - mm/dd/yyyy
     */
    @Override
    public boolean isPerform(String pageObjectId, String dataInput, WebDriver driver) throws Exception {
        boolean isWorking = false;
        String propField = "";
        String reasonMessage = "";
        String[] inputStrList = null;
        String[] retrievedStrList = null;
        int listSize = 0;
        Date minDate, maxDate, dateToCheck;
        int minMonth, maxMonth = 0;
        int minDay, maxDay = 0;
        int minYear, maxYear = 0;
        int dateCheckDay, dateCheckMonth, dateCheckYear = 0;

        try {
            if (log.isDebugEnabled()) {
                log.debug("MCI >> isDateInBetween()");
            }
            log.info("Checking dates if between the inputs...");
            propField = propertiesRetriever.retrievePropertiesFile("objRepo").getProperty(pageObjectId);
            By locator = By.cssSelector(propField);
            if (assertElement.isElementPerformable(locator, driver)) {///
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                List<WebElement> elements = new ArrayList<WebElement>();
                elements = driver.findElements(locator);

                inputStrList = dataInput.split("[/,-\\]+");
                listSize = inputStrList.length;
                for (String str : inputStrList) { // just a check if input values can be converted to Integer
                    int a = Integer.valueOf(str.trim());
                }

                if (listSize >= 6) {
                    minMonth = Integer.valueOf(inputStrList[0]) - 1;
                    minDay = Integer.valueOf(inputStrList[1]);
                    minYear = Integer.valueOf(inputStrList[2]);
                    minDate = new Date(minYear, minMonth, minDay);
                    maxMonth = Integer.valueOf(inputStrList[3]) - 1;
                    maxDay = Integer.valueOf(inputStrList[4]);
                    maxYear = Integer.valueOf(inputStrList[5]);
                    maxDate = new Date(maxYear, maxMonth, maxDay);

                    for (WebElement webElement : elements) {
                        String value = "";
                        value = webElement.getText();
                        if ("".equals(value)) {
                            value = webElement.getAttribute("value");
                        }
                        retrievedStrList = value.split("[/-\\]+");

                        dateCheckMonth = Integer.valueOf(retrievedStrList[0]);
                        dateCheckDay = Integer.valueOf(retrievedStrList[1]);
                        dateCheckYear = Integer.valueOf(retrievedStrList[2]);
                        dateToCheck = new Date(dateCheckYear, dateCheckMonth, dateCheckDay);
                        if (!(dateToCheck.after(minDate) && dateToCheck.before(maxDate))) {
                            isWorking = false;
                            reasonMessage = new StringBuilder("date is not between '").append(dataInput).append("'").toString();
                            break;
                        } else {
                            isWorking = true;
                        }
                    }
                } else {
                    reasonMessage = new StringBuilder("Please check your input: '").append(dataInput)
                            .append("'... Format is ('min'-'max'): mm/dd/yyyy - mm/dd/yyyy")
                            .toString();
                }
                seleniumDriver.setDriver(driver);
            }
        } catch (SeleniumHybridKeywordException e) {
            isWorking = false;
            log.info(e.getMessage());
            log.error("Error found. ", e);
            driverScript.setReason(e.getMessage());
        } catch (Exception e) {
            isWorking = false;
            reasonMessage = new StringBuilder("Not able to count the object due to ").append(e.getMessage())
                    .append("...")
                    .toString();
            log.info(reasonMessage);
            log.error("Error found. ", e);
        }
        driverScript.setReason(reasonMessage);
        if (log.isDebugEnabled()) {
            log.debug("MCO << isDateInBetween()");
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
