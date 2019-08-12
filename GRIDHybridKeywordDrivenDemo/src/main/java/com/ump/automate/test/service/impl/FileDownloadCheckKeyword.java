package com.ump.automate.test.service.impl;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.ump.automate.test.execution.DriverScript;
import com.ump.automate.test.service.ActionKeyword;
import com.ump.automate.test.util.FileChecker;

/**
 * this class implements ActionKeyword and perform methods that checks if the downloaded file exists in the file download path provided
 * @author adriel.bunoan
 */
public class FileDownloadCheckKeyword implements ActionKeyword {

    private static final Logger log = Logger.getLogger(FileDownloadCheckKeyword.class);

    private DriverScript driverScript;

    private FileChecker fileChecker;

    @Override
    public boolean isPerform(String pageObjectId, String dataInput, WebDriver driver) throws Exception {
        boolean isWorking = false;
        String reasonMessage = "";
        if (log.isDebugEnabled()) {
            log.debug("MCI >> isPerform()");
        }

        try {
            log.info("checking file path : " + dataInput);
            isWorking = fileChecker.isFileExists(dataInput);
        }

        catch (Exception e) {
            isWorking = false;
            reasonMessage = new StringBuilder("Not able to find downloaded file due to ").append(e.getMessage())
                    .append("...")
                    .toString();
            log.info(reasonMessage);
            log.error("Error found. ", e);
            driverScript.setReason(reasonMessage);
        }
        if (log.isDebugEnabled()) {
            log.debug("MCO << isPerform()");
        }
        return isWorking;

    }

    public void setDriverScript(DriverScript driverScript) {
        this.driverScript = driverScript;
    }

    public void setFileChecker(FileChecker fileChecker) {
        this.fileChecker = fileChecker;
    }

}
