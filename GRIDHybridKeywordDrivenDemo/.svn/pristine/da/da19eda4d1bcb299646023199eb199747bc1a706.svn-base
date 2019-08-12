package com.ump.automate.test.util;

import org.apache.log4j.Logger;

import com.ump.automate.test.bean.ResultBean;
import com.ump.automate.test.exception.ExternalPropertiesException;
import com.ump.automate.test.execution.impl.DriverScriptImpl;
import com.ump.automate.test.util.properties.ExternalPropertiesResource;
import com.ump.automate.test.util.properties.PropertiesFileChecker;
import com.ump.automate.test.util.properties.PropertiesRetriever;

/**
 * this class performs methods that 'checks and loads' the needed files then runs script
 * @author edward.cervantes
 */
public class ScriptRunner {

    private static final Logger log = Logger.getLogger(ScriptRunner.class);

    private DriverScriptImpl driverScript;

    private PropertiesRetriever propertiesRetriever;

    private PropertiesFileChecker propertiesFileChecker;

    private ExcelUtils excelUtils;

    private ExternalPropertiesResource externalPropertiesResource;

    private ResultBean bean;


    /**
     * this method checks if all the files needed are existing on the stated or given paths.
     * calls the method that executes the test if all files are existing
     * @param excelPath
     * @param objRepoPath
     * @param naviPropertiesPath
     * @param excelConfigPath
     * @return
     * @throws ExternalPropertiesException
     * @throws Exception
     */
    public boolean isTestExecuted(String excelPath, String objRepoPath, String naviPropertiesPath, String excelConfigPath) throws ExternalPropertiesException,
        Exception {
        boolean isExist = false;
        String currentFile = "excel file: ";
        try {
            if (log.isDebugEnabled()) {
                log.debug("MCI >> isTestExecuted()");
            }

            // check if excel file exists
            isExist = excelUtils.isExcelFileExists(excelPath);

            if (isExist) {
                currentFile = "object repository property file: ";
                // check if page object properties file exists
                isExist = propertiesFileChecker.isPropertyFileExists(objRepoPath);
            }
            if (isExist) {
                currentFile = "navigate property file: ";
                // check if navigate properties file exists
                isExist = propertiesFileChecker.isPropertyFileExists(naviPropertiesPath);
            }
            if (isExist) {
                currentFile = "excel configuration property file: ";
                // check if excel configuration properties file exists
                isExist = propertiesFileChecker.isPropertyFileExists(excelConfigPath);
            }
            if (isExist) {
                currentFile = "loading properties files and configurations... ";
                isExist = propertiesRetriever.loadPropertyFiles(excelConfigPath, naviPropertiesPath, objRepoPath);
            }
            if (isExist) {
                currentFile = "setting field properties... ";
                // set fields in PropertiesResource file exists
                isExist = externalPropertiesResource.setFieldProperties();
            }
            if (isExist) {
                currentFile = "external files were loaded properly.. ";
                // set fields for driverScript
                driverScript.loadFields();
                // set fields for ExcelUtils
                currentFile = "loading of files completed...";
            }
        } catch (ExternalPropertiesException e) {
            log.info(new StringBuilder("external properties file problem/s. ").append(e.getMessage()));
            log.error("Error found. ", e);
            throw e;
        } catch (Exception e) {
            log.error("Error found. ", e);
//            e.printStackTrace();
//            System.out.print(currentFile + " ");
//            System.out.println(e.getMessage());
        }

        // if all the files are existing and initialization of other field are done, it runs the test
        if (isExist) {
            try {
                bean = driverScript.executeTestCase();
                isExist = true;
            } catch (Exception e) {
                log.error("Error found. ", e);
                System.out.println("'system error'... There was a problem while running the program...");
            }
        }
        if (log.isDebugEnabled()) {
            log.debug("MCO << isTestExecuted()");
        }
        return isExist;
    }

    /**
     * method that displays test results
     */
    public void displayTestResults() {
        if (log.isDebugEnabled()) {
            log.debug("MCI >> displayResults()");
        }
        System.out.println(new StringBuilder("ran ").append(bean.getTotalTestCaseRan()).append(" test case/s").toString());
        System.out.println(bean.getTotalFailedTestCaseRan() + " case/s failed");
        if (bean.getTotalFailedTestCaseRan() > 0) {
            System.out.print("fails at: ");
            if (bean.getTotalFailedTestCaseRan() == 1) {
                System.out.println(bean.getFailedTestCasesIdList().get(0));
                System.out.println("");
            } else {
                int count = 0;
                for (String testCaseId : bean.getFailedTestCasesIdList()) {
                    count++;
                    if (bean.getTotalFailedTestCaseRan() != count) {
                        System.out.print(testCaseId + ", ");
                    } else {
                        System.out.print(testCaseId);
                    }
                }
                System.out.println("");
            }
        }
        if (log.isDebugEnabled()) {
            log.debug("MCO << displayResults()");
        }
    }

    // this method returns the result bean which contains the test info
    public ResultBean getResultBean() {
        return bean;
    }

    public void setDriverScript(DriverScriptImpl driverScript) {
        this.driverScript = driverScript;
    }

    public void setExcelUtils(ExcelUtils excelUtils) {
        this.excelUtils = excelUtils;
    }

    public void setExternalPropertiesResource(ExternalPropertiesResource externalPropertiesResource) {
        this.externalPropertiesResource = externalPropertiesResource;
    }

    public void setPropertiesFileChecker(PropertiesFileChecker propertiesFileChecker) {
        this.propertiesFileChecker = propertiesFileChecker;
    }

    public void setPropertiesRetriever(PropertiesRetriever propertiesRetriever) {
        this.propertiesRetriever = propertiesRetriever;
    }
}
