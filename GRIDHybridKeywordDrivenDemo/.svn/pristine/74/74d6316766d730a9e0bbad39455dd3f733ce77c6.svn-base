package com.ump.automate.test.execution.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ump.automate.test.bean.ResultBean;
import com.ump.automate.test.execution.DriverScript;
import com.ump.automate.test.util.ExcelUtils;
import com.ump.automate.test.util.KeywordClassFactory;
import com.ump.automate.test.util.properties.ExternalPropertiesResource;

/**
 * this class implements DriverScript and is the 'brain' of the program
 * @author edward.cervantes
 */
public class DriverScriptImpl implements DriverScript {

    private static final Logger log = Logger.getLogger(DriverScriptImpl.class);

    private ExcelUtils excelUtils;

    private KeywordClassFactory keywordClassPicker;

    private ExternalPropertiesResource externalPropertiesResource;

    private String sheetTestCases = "";

    private String keywordPASS = "";

    private String keywordFAIL = "";

    private int colTestCaseId = 0;

    private int colSheetTitle = 0;

    private int colRunMode = 0;

    private int colResult = 0;

    private int colPageObj = 0;

    private int colActionKeyword = 0;

    private int colDataSet = 0;

    private int colTestSheetResult = 0;

    private int colTestSheetReason = 0;

    private int colScreenshotDir = 0;

    private int testCaseId = 0;

    public boolean loadFields() {
        boolean isLoaded = false;
        if (log.isDebugEnabled()) {
            log.debug("MCI >> loadFields()");
        }
        try {
            sheetTestCases = externalPropertiesResource.getSheetTestCasesProp();

            keywordPASS = externalPropertiesResource.getKeywordPassProp();
            keywordFAIL = externalPropertiesResource.getKeywordFailProp();

            colTestCaseId = externalPropertiesResource.getColTestCasesIdProp();
            colSheetTitle = externalPropertiesResource.getColSheetTitleProp();
            colRunMode = externalPropertiesResource.getColRunModeProp();
            colResult = externalPropertiesResource.getColResultProp();
            colScreenshotDir = externalPropertiesResource.getColScreenshotDirProp();

            colPageObj = externalPropertiesResource.getColPageObjectProp();
            colActionKeyword = externalPropertiesResource.getColActionKeywordProp();
            colDataSet = externalPropertiesResource.getColDataSetProp();
            colTestSheetResult = externalPropertiesResource.getColTestSheetResultProp();
            colTestSheetReason = externalPropertiesResource.getColTestSheetReasonProp();

            testCaseId = externalPropertiesResource.getColTestCasesIdProp();
            isLoaded = true;
        } catch (Exception e) {
            log.error(new StringBuilder("properties file fields did not load well. ").append(e.getMessage()).toString());
        }
        if (log.isDebugEnabled()) {
            log.debug("MCO << loadFields()");
        }
        return isLoaded;
    }

    private String sActionKeyword = "";

    private String sPageObject = "";

    private int iTestStep = 0;

    private int iTestLastStep = 0;

    private int iTestCase = 1;

    private String sTestCaseID = "";

    private String sRunMode = "";

    private String sData = "";

    private String sSheetTitle = "";

    private boolean isRetrieved = false;

    public String retrieveSTestCaseID() {
        if (log.isDebugEnabled()) {
            log.debug("MCI >> retrieveSTestCaseID()");
        }
        log.info("sTestCaseID: " + this.sTestCaseID);
        if (log.isDebugEnabled()) {
            log.debug("MCO << retrieveSTestCaseID()");
        }
        return this.sTestCaseID;
    }

    @Override
    public ResultBean executeTestCase() throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("MCI >> executeTestCase()");
        }
        ResultBean bean = new ResultBean();
        int totalTestCasesRan = 0;
        int totalFailedTestCases = 0;
        List<String> failedList = new ArrayList<String>();
        List<String> passedList = new ArrayList<String>();

 
        int iTotalTestCases = 0;
        // retrieves the total number of rows in the excel file
        iTotalTestCases = excelUtils.retrieveTotalRowCount(sheetTestCases);

        // iterates each row
        for (; iTestCase < iTotalTestCases; iTestCase++) {
            isRetrieved = true;

            // retrieves the current string cell in the column "run mode" in test case sheet
            sRunMode = excelUtils.retrieveCellData(iTestCase, colRunMode, sheetTestCases);

            if ("yes".equalsIgnoreCase(sRunMode.trim())) {
                // retrieves the current cell in string form in the column "test case id" in test case sheet
                sTestCaseID = excelUtils.retrieveCellData(iTestCase, colTestCaseId, sheetTestCases);
                // retrieves the sheet title(sub sheet)
                sSheetTitle = excelUtils.retrieveCellData(iTestCase, colSheetTitle, sheetTestCases);

                totalTestCasesRan++;

                if (!"".equals(sTestCaseID) && !"".equals(sSheetTitle)) {
                    // retrieves the (first)row-position(as int) of sTestCaseID in sheet Test Steps
                    iTestStep = excelUtils.retrieveRowPosition(sTestCaseID, colTestCaseId, sSheetTitle);

                    // retrieves the position(first) where sTestCaseID can't be found
                    iTestLastStep = excelUtils.retrieveTestStepsCount(sSheetTitle, sTestCaseID, iTestStep, testCaseId);
                    // iterate test steps
                    for (; iTestStep < iTestLastStep; iTestStep++) {
                        // retrieves cell data
                        isRetrieved = isCellDataRetrieved(iTestStep);
                        if (isRetrieved != true) {
                            // setting test case result
                            excelUtils.setCellData(keywordFAIL, iTestCase, colResult, sheetTestCases);
                            System.out.println(new StringBuilder(sTestCaseID).append(":")
                                    .append("\t")
                                    .append(keywordFAIL)
                                    .toString());
                            totalFailedTestCases++;
                            failedList.add(sTestCaseID);
                            break;
                        }
                    }
                    if (isRetrieved != false) {
                        excelUtils.setCellData(keywordPASS, iTestCase, colResult, sheetTestCases);
                        System.out.println(new StringBuilder(sTestCaseID).append(":").append("\t").append(keywordPASS).toString());
                        passedList.add(sTestCaseID);
                    }
                }
            }
        }
        bean.setTotalTestCaseRan(totalTestCasesRan);
        bean.setTotalFailedTestCaseRan(totalFailedTestCases);
        bean.setFailedTestCasesIdList(failedList);
        bean.setPassedTestCasesIdList(passedList);
        if (log.isDebugEnabled()) {
            log.debug("MCO << executeTestCase()");
        }
        return bean;
    }

    @Override
    public boolean isCellDataRetrieved(int rowNum) {
        try {
            if (log.isDebugEnabled()) {
                log.debug("MCI >> isCellDataRetrieved()");
            }
            // retrieves the action keyword or method name for the class ActionKeywords  
            sActionKeyword = excelUtils.retrieveCellData(rowNum, colActionKeyword, sSheetTitle);
            // retrieves the current string cell in the column "page object" in test steps sheet
            sPageObject = excelUtils.retrieveCellData(rowNum, colPageObj, sSheetTitle);
            // retrieves the current string cell in the column "data set" in test steps sheet
            sData = excelUtils.retrieveCellData(rowNum, colDataSet, sSheetTitle);

            // performs the action
            isRetrieved = keywordClassPicker.isCalled(sActionKeyword, sPageObject, sData);
        } catch (Exception e) {
            log.error("Error found. ", e);
            isRetrieved = false;
        } finally {
            try {
                setTestStepsResult(rowNum, isRetrieved);
            } catch (Exception e) {
                log.error(e);
            }
        }
        if (log.isDebugEnabled()) {
            log.debug("MCO << isCellDataRetrieved()");
        }
        return isRetrieved;
    }

    //	p
    @Override
    public void setTestStepsResult(int rowNum, boolean isRetrieved) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("MCI >> setTestStepsResult()");
        }
        if (isRetrieved != false) {
            excelUtils.setCellData(keywordPASS, rowNum, colTestSheetResult, sSheetTitle);
        } else {
            excelUtils.setCellData(keywordFAIL, rowNum, colTestSheetResult, sSheetTitle);
            keywordClassPicker.isCalled("closeBrowser", sPageObject, sData);
        }
        if (log.isDebugEnabled()) {
            log.debug("MCO << setTestStepsResult()");
        }
    }

    @Override
    public void setReason(String reason) {
        if (log.isDebugEnabled()) {
            log.debug("MCI >> setReason()");
        }
        try {
            excelUtils.setCellData(reason, iTestStep, colTestSheetReason, sSheetTitle);
        } catch (Exception e) {
            log.error("Error found. ", e);
        }
        if (log.isDebugEnabled()) {
            log.debug("MCO << setReason()");
        }
    }

    @Override
    public void setScreenshotDir(String screenshotDirectory) throws Exception {
        
        if (log.isDebugEnabled()) {
            log.debug("MCI >> setScreenshotDir()");
        }
        try {
            excelUtils.setCellData(screenshotDirectory, iTestCase, colScreenshotDir, sheetTestCases);
        } catch (Exception e) {
            log.error("Error found. ", e);
        }
        if (log.isDebugEnabled()) {
            log.debug("MCO << setScreenshotDir()");
        }
    }

    public void setKeywordClassPicker(KeywordClassFactory keywordClassPicker) {
        this.keywordClassPicker = keywordClassPicker;
    }

    public void setExcelUtils(ExcelUtils excelUtils) {
        this.excelUtils = excelUtils;
    }

    public void setExternalPropertiesResource(ExternalPropertiesResource externalPropertiesResource) {
        this.externalPropertiesResource = externalPropertiesResource;
    }
}
