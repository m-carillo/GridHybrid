package com.ump.automate.test.util.properties;

import java.util.Properties;

import org.apache.log4j.Logger;

import com.ump.automate.test.exception.ExternalFieldPropertiesHandler;
import com.ump.automate.test.exception.ExternalPropertiesException;

/**
 * this class retrieved external properties' fields from properties file/s
 * @author edward.cervantes
 */
public class ExternalPropertiesResource {

    private static final Logger log = Logger.getLogger(ExternalPropertiesResource.class);

    private PropertiesRetriever propertiesRetriever;

    private ExternalFieldPropertiesHandler externalFieldPropertiesHandler;

    private Properties excelProperties = null;

    private Properties naviProperties = null;

    // first sheet info of excel test
    private int colTestCasesIdProp = 0;

    private int colSheetTitleProp = 0;

    private int colRunModeProp = 0;

    private int colResultProp = 0;

    private int colScreenshotDirProp = 0;

    // 'other' excel sheet info
    private int colPageObjectProp = 0;

    private int colActionKeywordProp = 0;

    private int colDataSetProp = 0;

    private int colTestSheetResultProp = 0;

    private int colTestSheetReasonProp = 0;

    // name of the first sheet of excel to test
    private String sheetTestCasesProp = "";

    private String keywordPassProp = "";

    private String keywordFailProp = "";

    // machine to open
    private String machineToOpen = "";

    // vm configurations
    private String vmNodeURLProp = "";

    private String vmPlatformProp = "";

    /**
     * assigns the field properties for excel configurations and navi for vm
     * @return
     * @throws Exception
     */
    public boolean setFieldProperties() throws Exception {
        boolean isSet = false;
        try {
            if (log.isDebugEnabled()) {
                log.debug("MCI >> setFieldProperties()");
            }
            isSet = setExcelPropertiesField();
            if (isSet) {
                isSet = setNaviPropertiesField();
            }
            isSet = true;
        } catch (ExternalPropertiesException e) {
            throw e;
        } catch (Exception e) {
            log.info(new StringBuilder("cannot set the fields in property files due to ").append(e).toString());
            log.error("Error found. ", e);
        }
        if (log.isDebugEnabled()) {
            log.debug("MCO << setFieldProperties");
        }
        return isSet;
    }

    /**
     * assigns the field properties for virtual machine
     * @return
     * @throws ExternalPropertiesException
     * @throws Exception
     */
    public boolean setNaviPropertiesField() throws ExternalPropertiesException, Exception {
        boolean isSet = false;

        try {
            if (log.isDebugEnabled()) {
                log.debug("MCI >> setNaviPropertiesField()");
            }
            // loads property file
            naviProperties = propertiesRetriever.retrievePropertiesFile("navi");
            if (naviProperties == null) {
                throw new ExternalPropertiesException("navi configuration properties can't be found. Please check its path.");
            }

            machineToOpen = this.naviProperties.getProperty("machine.open");

            if (machineToOpen != null) {
                // VM configuration
                vmNodeURLProp = this.naviProperties.getProperty("vm.nodeURL");
                //checks if 'vmNodeURLProp' has value
                externalFieldPropertiesHandler.isFieldNotNull(vmNodeURLProp, "vm.nodeURL");

                vmPlatformProp = this.naviProperties.getProperty("vm.platform");
                //checks if 'vmPlatformProp' has value
                externalFieldPropertiesHandler.isFieldNotNull(vmPlatformProp, "vm.platform");
            }
            isSet = true;
        } catch (ExternalPropertiesException e) {
            log.info(new StringBuilder("external properties file problem/s. ").append(e.getMessage()));
            log.error("Error found. ", e);
            throw e;
        } catch (Exception e) {
            log.info(new StringBuilder("cannot set the fields in property files due to ").append(e).toString());
            log.error("Error found. ", e);
        }
        if (log.isDebugEnabled()) {
            log.debug("MCO << setNaviPropertiesField");
        }
        return isSet;
    }

    /**
     * assigns the field properties for excel configurations
     * @return
     * @throws ExternalPropertiesException
     * @throws Exception
     */
    public boolean setExcelPropertiesField() throws ExternalPropertiesException, Exception {
        boolean isSet = false;
        try {
            if (log.isDebugEnabled()) {
                log.debug("MCI >> setExcelPropertiesField()");
            }
            // loads properties file
            excelProperties = propertiesRetriever.retrievePropertiesFile("excel");
            if (excelProperties == null) {
                throw new ExternalPropertiesException("excel configuration properties can't be found. Please check its path.");
            }

            // excel files
            String stringColTestCasesIdProp = this.excelProperties.getProperty("column.testCaseId");
            externalFieldPropertiesHandler.isFieldaNumber(stringColTestCasesIdProp, "column.testCaseId");
            colTestCasesIdProp = Integer.valueOf(stringColTestCasesIdProp);

            String stringColSheetTitleProp = this.excelProperties.getProperty("column.sheetTitle");
            externalFieldPropertiesHandler.isFieldaNumber(stringColSheetTitleProp, "column.sheetTitle");
            colSheetTitleProp = Integer.valueOf(stringColSheetTitleProp);

            String stringColRunmodeProp = this.excelProperties.getProperty("column.runMode");
            externalFieldPropertiesHandler.isFieldaNumber(stringColRunmodeProp, "column.runMode");
            colRunModeProp = Integer.valueOf(stringColRunmodeProp);

            String stringColResultProp = this.excelProperties.getProperty("column.result");
            externalFieldPropertiesHandler.isFieldaNumber(stringColResultProp, "column.result");
            colResultProp = Integer.valueOf(stringColResultProp);

            String stringColScreenshotDirProp = this.excelProperties.getProperty("column.screenshotDir");
            externalFieldPropertiesHandler.isFieldaNumber(stringColScreenshotDirProp, "column.screenshotDir");
            colScreenshotDirProp = Integer.valueOf(stringColScreenshotDirProp);

            String stringColPageObject = this.excelProperties.getProperty("column.objectRepo");
            externalFieldPropertiesHandler.isFieldaNumber(stringColPageObject, "column.objectRepo");
            colPageObjectProp = Integer.valueOf(stringColPageObject);

            String stringColActionKeywordProp = this.excelProperties.getProperty("column.actionKeyword");
            externalFieldPropertiesHandler.isFieldaNumber(stringColActionKeywordProp, "column.actionKeyword");
            colActionKeywordProp = Integer.valueOf(stringColActionKeywordProp);

            String stringColDataSetProp = this.excelProperties.getProperty("column.dataSet");
            externalFieldPropertiesHandler.isFieldaNumber(stringColDataSetProp, "column.dataSet");
            colDataSetProp = Integer.valueOf(stringColDataSetProp);

            String stringColTestStepResultProp = this.excelProperties.getProperty("column.testSheetResult");
            externalFieldPropertiesHandler.isFieldaNumber(stringColTestStepResultProp, "column.testSheetResult");
            colTestSheetResultProp = Integer.valueOf(stringColTestStepResultProp);

            String stringColTestStepReasonProp = this.excelProperties.getProperty("column.testSheetReason");
            externalFieldPropertiesHandler.isFieldaNumber(stringColTestStepReasonProp, "column.testSheetReason");
            colTestSheetReasonProp = Integer.valueOf(stringColTestStepReasonProp);

            sheetTestCasesProp = this.excelProperties.getProperty("sheet.testCases");
            externalFieldPropertiesHandler.isFieldNotNull(sheetTestCasesProp, "sheet.testCases");

            keywordFailProp = this.excelProperties.getProperty("keyword.fail");
            if (null == keywordFailProp || "".equals(keywordFailProp)) {
                keywordFailProp = "FAILED";
            }
            keywordPassProp = this.excelProperties.getProperty("keyword.pass");
            if (null == keywordPassProp || "".equals(keywordPassProp)) {
                keywordPassProp = "PASSED";
            }

            isSet = true;
        } catch (ExternalPropertiesException e) {
            log.info(new StringBuilder("external properties file problem/s. ").append(e.getMessage()));
            log.error("Error found. ", e);
            throw e;
        } catch (Exception e) {
            log.info(new StringBuilder("cannot set the fields in property files due to ").append(e).toString());
            log.error("Error found. ", e);
        }
        if (log.isDebugEnabled()) {
            log.debug("MCO << setExcelPropertiesField");
        }
        return isSet;
    }

    // EXCEL CONFIG 
    public int getColTestCasesIdProp() {
        return colTestCasesIdProp;
    }

    public int getColSheetTitleProp() {
        return colSheetTitleProp;
    }

    public int getColRunModeProp() {
        return colRunModeProp;
    }

    public int getColResultProp() {
        return colResultProp;
    }

    public int getColScreenshotDirProp() {
        return colScreenshotDirProp;
    }

    public int getColPageObjectProp() {
        return colPageObjectProp;
    }

    public int getColActionKeywordProp() {
        return colActionKeywordProp;
    }

    public int getColDataSetProp() {
        return colDataSetProp;
    }

    public int getColTestSheetResultProp() {
        return colTestSheetResultProp;
    }

    public int getColTestSheetReasonProp() {
        return colTestSheetReasonProp;
    }

    public String getSheetTestCasesProp() {
        return sheetTestCasesProp;
    }

    public String getKeywordPassProp() {
        return keywordPassProp;
    }

    public String getKeywordFailProp() {
        return keywordFailProp;
    }

    public String getMachineToOpen() {
        return machineToOpen;
    }

    // VM CONFIG
    public String getVmNodeURLProp() {
        return vmNodeURLProp;
    }

    public String getVmPlatformProp() {
        return vmPlatformProp;
    }

    public void setExternalFieldPropertiesHandler(ExternalFieldPropertiesHandler externalFieldPropertiesHandler) {
        this.externalFieldPropertiesHandler = externalFieldPropertiesHandler;
    }

    public void setPropertiesRetriever(PropertiesRetriever propertiesRetriever) {
        this.propertiesRetriever = propertiesRetriever;
    }
}
