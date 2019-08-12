package com.ump.automate.test.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.ump.automate.test.execution.DriverScript;

/**
 * this class contains method that takes a screenshot of a web page
 * @author edward.cervantes
 */
public class ScreenshotsUtil {

    private static final Logger log = Logger.getLogger(ScreenshotsUtil.class);

    private DriverScript driverScript;

    /**
     * this method takes screenshot on the current webpage
     * @param driver
     * @return
     * @throws Exception
     */
    public boolean takeScreenshot(WebDriver driver) throws Exception {
        boolean isDone = false;
        String tcID = "";
        tcID = driverScript.retrieveSTestCaseID();
        if ("".equals(tcID)) {
            UUID uuid = UUID.randomUUID();
            String uuidStr = uuid.toString();
            tcID = uuidStr.substring(0, 8);
        }
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
//        Date date = new Date();
//        String toDate = dateFormat.format(date); //2016/11/16 12:08:43    
//        String destinationPath = new StringBuilder("C:\\screenshots\\").append(tcID).append("\\").append(toDate).append(".png").toString();


        String destinationPath = new StringBuilder("C:\\screenshots\\").append(tcID).toString();

        try {
            if (log.isDebugEnabled()) {
                log.debug("MCI >> takeScreenshot()");
            }
            
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            // Now you can do whatever you need to do with it, for example copy somewhere
            FileUtils.copyFileToDirectory(scrFile, new File(destinationPath));
            driverScript.setScreenshotDir(destinationPath);

            isDone = true;
        } catch (IOException e) {
            log.error(new StringBuilder("cannot take a screenshot due to ").append(e.getMessage()).toString());

            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            // Now you can do whatever you need to do with it, for example copy somewhere
            FileUtils.copyFileToDirectory(scrFile, new File(destinationPath));
            driverScript.setScreenshotDir(destinationPath);

        } catch (Exception e) {
            log.error(new StringBuilder("cannot take a screenshot due to ").append(e.getMessage()).toString());

            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            // Now you can do whatever you need to do with it, for example copy somewhere
            FileUtils.copyFileToDirectory(scrFile, new File(destinationPath));
            driverScript.setScreenshotDir(destinationPath);

        }

        if (log.isDebugEnabled()) {
            log.debug("MCO << takeScreenshot()");
        }
        return isDone;
    }

    public void setDriverScript(DriverScript driverScript) {
        this.driverScript = driverScript;
    }
}