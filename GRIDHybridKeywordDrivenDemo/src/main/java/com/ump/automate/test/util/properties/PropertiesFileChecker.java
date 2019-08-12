package com.ump.automate.test.util.properties;

import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

import com.ump.automate.test.exception.ExternalPropertiesException;
import com.ump.automate.test.util.FileChecker;

/**
 * this class performs method that checks if a .properties file exist
 * @author edward.cervantes
 */
public class PropertiesFileChecker {

    private static final Logger log = Logger.getLogger(PropertiesFileChecker.class);

    private FileChecker fileChecker;

    /**
     * checks whether properties file exist
     * @param filePath
     * @return
     * @throws Exception
     */
    public boolean isPropertyFileExists(String filePath) throws ExternalPropertiesException, Exception {
        if (log.isDebugEnabled()) {
            log.debug("MCI >> isPropertyFileExists()");
        }
        boolean isExist = false;
        String errMessage = "";
        try {
            log.info("checking if file exists...");
            File file = new File(filePath);
            if (fileChecker.isFileExists(filePath) && FilenameUtils.getExtension(filePath).equals("properties")) {
                isExist = true;
                log.info(new StringBuilder("file from ").append(filePath).append(" is a valid property file...").toString());
            } else {
                isExist = false;
                errMessage = new StringBuilder("file from directory '").append(filePath)
                        .append("' is not valid or existing. Please check the file path...")
                        .toString();
                log.info(errMessage);
                throw new ExternalPropertiesException(errMessage);
            }
        } catch (Exception e) {
            isExist = false;
            errMessage = new StringBuilder("file from path ").append(filePath).append(" does not exists.").toString();
            log.info(errMessage);
            log.error("Error found. ", e);
            throw new ExternalPropertiesException(errMessage);
        }
        if (log.isDebugEnabled()) {
            log.debug("MCO << isPropertyFileExists()");
        }
        return isExist;
    }

    public void setFileChecker(FileChecker fileChecker) {
        this.fileChecker = fileChecker;
    }
}
