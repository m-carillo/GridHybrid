package com.ump.automate.test.util.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * this class contains method that loads a properties file
 * @author edward.cervantes
 */
public class PropertiesFileLoader {

    private static final Logger log = Logger.getLogger(PropertiesFileLoader.class);

    public Properties loadProperties(String propPath) throws FileNotFoundException, IOException {

        if (log.isDebugEnabled()) {
            log.debug("MCI >> loadProperties()");
        }
        log.info("loading properties file...");
        log.info("property path: " + propPath + ".......");

        Properties propertyFile = null;
        FileInputStream inputStrem = null;

        try {
            inputStrem = new FileInputStream(propPath);
            propertyFile = new Properties(System.getProperties());
            propertyFile.load(inputStrem);
        } catch (FileNotFoundException e) {
            log.info(new StringBuilder("file cannot be seen... Please check the path ").append(propPath).toString());
            log.error("Error found. ", e);
        } catch (IOException e) {
            log.info(new StringBuilder("file cannot be seen due to ").append(e).toString());
            log.error("Error found. ", e);
        } finally {
            if (inputStrem != null) {
                try {
                    inputStrem.close();
                } catch (IOException ex) {
                    // ignore ... any significant errors should already have been
                    // reported via an IOException from the final flush.
                }
            }
        }
        if (log.isDebugEnabled()) {
            log.debug("MCO << loadProperties()");
        }

        return propertyFile;
    }

}
