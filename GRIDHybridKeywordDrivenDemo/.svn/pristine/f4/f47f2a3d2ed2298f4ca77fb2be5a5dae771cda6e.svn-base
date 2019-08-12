package com.ump.automate.test.util;

import java.io.File;

import org.apache.log4j.Logger;

import com.ump.automate.test.util.properties.PropertiesFileChecker;

/**
 * this class performs method that checks if a file exist
 * @author edward.cervantes
 */
public class FileChecker {

    private static final Logger log = Logger.getLogger(PropertiesFileChecker.class);

    /**
     * checks whether file exist
     * @param filePath
     * @return
     * @throws Exception
     */
    public boolean isFileExists(String filePath) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("MCI >> isFileExists()");
        }
        boolean isExist = false;
        try {
            log.info("checking if file exists...");
            File file = new File(filePath);
            if (file.exists() && !file.isDirectory() && file.isFile()) {
                isExist = true;
                log.info(new StringBuilder("file from ").append(filePath).append(" is a valid file...").toString());
            } else {
                log.info(new StringBuilder("file from directory '").append(filePath)
                        .append("' is not valid or existing. Please check the file path...")
                        .toString());
                isExist = false;
            }
        } catch (Exception e) {
            log.info(new StringBuilder("file from path ").append(filePath).append(" does not exists.").toString());
            log.error("Error found. ", e);
        }
        if (log.isDebugEnabled()) {
            log.debug("MCO << isFileExists()");
        }
        return isExist;
    }
}
