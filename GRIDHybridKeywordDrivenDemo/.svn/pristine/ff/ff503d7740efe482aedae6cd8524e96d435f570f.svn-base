package com.ump.automate.test.util.properties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * this class loads the property files through PropertiesFileLoader class methods 
 * and has a properties file retriever method
 * @author edward.cervantes
 */
public class PropertiesRetriever {

    private PropertiesFileLoader propertiesFileLoader;

    public void setPropertiesFileLoader(PropertiesFileLoader propertiesFileLoader) {
        this.propertiesFileLoader = propertiesFileLoader;
    }

    private Properties excelProp = null;

    private Properties naviProp = null;

    private Properties objRepoProp = null;

    /**
     * method that loads the .properties files and attached it to the instantiated Properties inside this class
     * @param excelConfigPath	= path of excel configuration
     * @param naviPath			= path of navi(contains URL's and virtual machine configurations
     * @param objRepoPath		= path of object repository(contains the web elements)
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public boolean loadPropertyFiles(String excelConfigPath, String naviPath, String objRepoPath) throws FileNotFoundException,
        IOException {
        boolean isLoaded = false;

        excelProp = propertiesFileLoader.loadProperties(excelConfigPath);

        if (excelProp != null) {
            naviProp = propertiesFileLoader.loadProperties(naviPath);
        }
        if (naviProp != null) {
            objRepoProp = propertiesFileLoader.loadProperties(objRepoPath);
        }

        isLoaded = true;
        return isLoaded;
    }

    /**
     * works like a factory for retrieving the .properties file
     * @param fileProperty = properties to be retrieve
     * @return
     */
    public Properties retrievePropertiesFile(String fileProperty) {
        Properties propertiesFile = null;

        if ("excel".equals(fileProperty)) {
            propertiesFile = excelProp;
        } else if ("navi".equals(fileProperty)) {
            propertiesFile = naviProp;
        } else if ("objRepo".equals(fileProperty)) {
            propertiesFile = objRepoProp;
        }
        return propertiesFile;
    }
}
