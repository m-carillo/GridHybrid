package com.ump.automate.test.service.impl;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.ump.automate.test.browser.SeleniumDriverInt;
import com.ump.automate.test.execution.DriverScript;
import com.ump.automate.test.service.ActionKeyword;
import com.ump.automate.test.util.ContextHandler;

/**
 * this class implements ActionKeyword and performs method that CLOSES the current browser
 * @author edward.cervantes
 */
public class CloseBrowserKeyword implements ActionKeyword {

    private static final Logger log = Logger.getLogger(CloseBrowserKeyword.class);

    private ContextHandler contextHandler;

    private DriverScript driverScript;

    private SeleniumDriverInt seleniumDriver;

    @Override
    public boolean isPerform(String pageObjectId, String dataInput, WebDriver driver) throws Exception {
        boolean isWorking = false;
        String reasonMessage = "";
        try {
            if (log.isDebugEnabled()) {
                log.debug("MCI >> closeBrowser()");
            }
            log.info("Closing the browser...");
            driver.quit();
            seleniumDriver.setDriver(driver);
            isWorking = true;
        } catch (Exception e) {
            isWorking = false;
            String str = "";
            str = contextHandler.getSomething("browser");
            if ("mozilla".equals(str)) {
                isWorking = true;
            }
            reasonMessage = new StringBuilder("Not able to close the browser due to ").append(e).append("...").toString();
            log.info(reasonMessage);
            log.error("Error found. ", e);
        }
        if (log.isDebugEnabled()) {
            log.debug("MCO << closeBrowser()");
        }
        return isWorking;
    }

    public void setContextHandler(ContextHandler contextHandler) {
        this.contextHandler = contextHandler;
    }

    public void setDriverScript(DriverScript driverScript) {
        this.driverScript = driverScript;
    }

    public void setSeleniumDriver(SeleniumDriverInt seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
    }
}
