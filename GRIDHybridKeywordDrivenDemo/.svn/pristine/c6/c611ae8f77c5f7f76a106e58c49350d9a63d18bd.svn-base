package com.ump.automate.test.service.impl;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.ump.automate.test.browser.Browser;
import com.ump.automate.test.browser.SeleniumDriverInt;
import com.ump.automate.test.exception.SeleniumHybridKeywordException;
import com.ump.automate.test.execution.DriverScript;
import com.ump.automate.test.service.ActionKeyword;
import com.ump.automate.test.util.properties.ExternalPropertiesResource;

/**
 * this class implements ActionKeyword and performs method that opens the browser of choice
 * from the indicated node(for virtual machines)
 * @author edward.cervantes
 */
public class OpenBrowserKeyword implements ActionKeyword {

    private static final Logger log = Logger.getLogger(OpenBrowserKeyword.class);

    private DriverScript driverScript;

    private ExternalPropertiesResource externalPropertiesResource;

    private SeleniumDriverInt seleniumDriver;

    private Browser localBrowser;

    private Browser gridBrowser;

    private WebDriver webDriver;

    @Override
    public boolean isPerform(String pageObjectId, String dataInput, WebDriver driver) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("MCI >> openBrowserKeyword()");
        }

        boolean isWorking = false;
        String machineToOpen = "";
        String reasonMessage = "";
        try {
            machineToOpen = externalPropertiesResource.getMachineToOpen();
            if ("grid".equalsIgnoreCase(machineToOpen))
                webDriver = gridBrowser.openBrowser(dataInput);
            else {
                webDriver = localBrowser.openBrowser(dataInput);
            }
            
            if(webDriver!=null) {
                seleniumDriver.setDriver(webDriver);
                isWorking = true;
            }else {
                reasonMessage = "Opening the browser is not possible because the webDriver(drivers @resource folder) is/are not compatible or DOES NOT exist. Also when running selenium framework through the command line please run the batch file in the same directory where the resource folder(from selenium framework zip file) exist.";
                log.error(reasonMessage);
                driverScript.setReason(reasonMessage);
            }
        } catch(SeleniumHybridKeywordException e) {
            log.info(e.getMessage());
            log.error("Error found. ", e);
            driverScript.setReason(e.getMessage());
        } catch (Exception e) {
            reasonMessage = new StringBuilder("Not able to open the Browser due to ").append(e.getMessage())
                    .append("...")
                    .toString();
            log.info(reasonMessage);
            log.error("Error found", e);
            isWorking = false;
            driverScript.setReason(reasonMessage);
        }
        if (log.isDebugEnabled()) {
            log.debug("MCO << openBrowserKeyword()");
        }
        return isWorking;
    }

    public void setLocalBrowser(Browser localBrowser) {
        this.localBrowser = localBrowser;
    }

    public void setGridBrowser(Browser gridBrowser) {
        this.gridBrowser = gridBrowser;
    }

    public void setDriverScript(DriverScript driverScript) {
        this.driverScript = driverScript;
    }

    public void setExternalPropertiesResource(ExternalPropertiesResource externalPropertiesResource) {
        this.externalPropertiesResource = externalPropertiesResource;
    }

    public void setSeleniumDriver(SeleniumDriverInt seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
    }
}
