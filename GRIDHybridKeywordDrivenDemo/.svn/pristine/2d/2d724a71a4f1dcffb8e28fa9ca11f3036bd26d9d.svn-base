package com.ump.automate.test.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ump.automate.test.exception.ExternalPropertiesException;

public class ExcelUtils {

    private static final Logger log = Logger.getLogger(ExcelUtils.class);

    private String pathExcel = "";

    private static XSSFSheet ExcelWSheet;

    private static XSSFWorkbook ExcelWBook;

    private static org.apache.poi.ss.usermodel.Cell Cell;

    private static XSSFRow Row;

    private boolean isDone;

    /**
     *  method that checks if excel file exists
     */
    public boolean isExcelFileExists(String filePath) throws ExternalPropertiesException, Exception {
        if (log.isDebugEnabled()) {
            log.debug("MCI >> isExcelFileExists()");
        }
        String message = "";
        try {
            File file = new File(filePath);
            if (file.exists() && !file.isDirectory()
                    && file.isFile()
                    && (FilenameUtils.getExtension(filePath).equals("xlsx") || FilenameUtils.getExtension(filePath).equals("xls"))) {
                isDone = setExcelFile(filePath);
            } else {
                isDone = false;
                message = new StringBuilder("file from directory '").append(filePath)
                        .append("' is not a valid excel file! Please check the file path...")
                        .toString();
                log.info(message);
                throw new ExternalPropertiesException(message);
            }
        } catch (ExternalPropertiesException e) {
            message = new StringBuilder("file from directory '").append(filePath)
                    .append("' is not a valid excel file! Please check the file path...")
                    .toString();
            log.info(message);
            throw new ExternalPropertiesException(message);
        } catch (Exception e) {
            isDone = false;
            message = new StringBuilder("file from directory '").append(filePath)
                    .append("' is not a valid excel file! Please check the file path...")
                    .toString();
            log.info(message);
            log.error("Error found. ", e);
        }
        if (log.isDebugEnabled()) {
            log.debug("MCO << isExcelFileExists()");
        }
        return isDone;
    }

    /**
     * method that retrieves the excel file
     */
    public boolean setExcelFile(String path) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("MCI >> setExcelFile()");
        }
        FileInputStream ExcelFile = null;
        try {
            log.info(new StringBuilder("excel path: ").append(path).append(".......").toString());
            pathExcel = path;
            File file = new File(path);
            log.info("importing excel file...");
            ExcelFile = new FileInputStream(file);
            ExcelWBook = new XSSFWorkbook(ExcelFile);
            isDone = true;
            log.info(new StringBuilder("imported excel file: ").append(file.getName()).toString());
        } catch (FileNotFoundException e) {
            log.error(e);
            log.error(e.getMessage());
            log.info(new StringBuilder("file cannot be seen... Please check the path ").append(path).toString());
            isDone = false;
        } catch (IOException e) {
            log.error(e);
            log.error(e.getMessage());
            log.info(new StringBuilder("file cannot be seen due to ").append(e).toString());
            isDone = false;
        } finally {
            if (ExcelFile != null) {
                try {
                    ExcelFile.close();
                } catch (IOException ex) {
                    // ignore ... any significant errors should already have been
                    // reported via an IOException from the final flush.
                }
            }
        }
        if (log.isDebugEnabled()) {
            log.debug("MCO << setExcelFile()");
        }
        return isDone;
    }

    /**
     * returns the string DATA of the cell
     */
    public String retrieveCellData(int rowNum, int colNum, String sheetName) throws Exception {
        String cellData = "";
        try {
            if (log.isDebugEnabled()) {
                log.debug("MCI >> retrieveCellData()");
            }
            log.info("retrieving data from cell...");
            ExcelWSheet = ExcelWBook.getSheet(sheetName);
            Cell = ExcelWSheet.getRow(rowNum).getCell(colNum);
            cellData = Cell.getStringCellValue();
            log.info(new StringBuilder("cell data retrieved: ").append(cellData).toString());
        } catch (NullPointerException np) {
            log.info(new StringBuilder("nothing is written on cell... ").append(cellData).toString());
        } catch (IllegalStateException e) {

        } catch (Exception e) {
            log.error(e);
        }
        if (log.isDebugEnabled()) {
            log.debug("MCO << retrieveCellData()");
        }
        return cellData.trim();
    }

    /**
     * returns the total number of rows in the given sheet
     */
    public int retrieveTotalRowCount(String sheetName) throws Exception {
        int iNumber = 0;
        try {
            if (log.isDebugEnabled()) {
                log.debug("MCI >> retrieveTotalRowCount()");
            }
            log.info(new StringBuilder("retrieving total number of rows in '").append(sheetName).append("' sheet...").toString());
            ExcelWSheet = ExcelWBook.getSheet(sheetName);
            iNumber = ExcelWSheet.getLastRowNum() + 1;
            log.info(new StringBuilder("total row count: ").append(iNumber).toString());
        } catch (Exception e) {
            log.error(e);
        }
        if (log.isDebugEnabled()) {
            log.debug("MCO << retrieveTotalRowCount()");
        }
        return iNumber;
    }

    /** 
     * @return the first row number where the table title is found
     */
    public int retrieveRowPosition(String sTestCaseName, int colNum, String sheetName) throws Exception {
        int iRowNum = 0;
        String cellData = "";
        try {
            if (log.isDebugEnabled()) {
                log.debug("MCI >> retrieveRowPosition()");
            }
            int rowCount = retrieveTotalRowCount(sheetName);
            for (; iRowNum < rowCount; iRowNum++) {
                cellData = retrieveCellData(iRowNum, colNum, sheetName);
                if (cellData.equalsIgnoreCase(sTestCaseName)) {
                    break;
                }
            }
        } catch (Exception e) {
            log.error(e);
        }
        if (log.isDebugEnabled()) {
            log.debug("MCO << retrieveRowPosition()");
        }
        return iRowNum;
    }

    /**
     * returns the first row position where sTestCaseId(test case name) isn't existing 
     * 	OR the total number of rows of sheet
     */
    public int retrieveTestStepsCount(String sheetName, String sTestCaseID, int iTestCaseStart, int testCaseId) throws Exception {
        int totalRowCount = 0;
        String cellData = "";
        try {
            if (log.isDebugEnabled()) {
                log.debug("MCI >> retrieveTestStepsCount()");
            }
            totalRowCount = retrieveTotalRowCount(sheetName);
            for (int i = iTestCaseStart; i <= totalRowCount; i++) {
                cellData = retrieveCellData(i, testCaseId, sheetName);
                if (!cellData.equals(sTestCaseID)) {
                    totalRowCount = i;
                }
            }
        } catch (Exception e) {
            log.error(e);
            totalRowCount = 0;
        }
        if (log.isDebugEnabled()) {
            log.debug("MCO << retrieveTestStepsCount()");
        }
        return totalRowCount;
    }

    /**
     * set string on the cell
     */
    public boolean setCellData(String result, int rowNum, int colNum, String sheetName) throws Exception {
        isDone = true;
        try {
            if (log.isDebugEnabled()) {
                log.debug("MCI >> setCellData()");
            }
            log.info(new StringBuilder("setting data... '").append(result)
                    .append("' at row '")
                    .append(rowNum)
                    .append("', column '")
                    .append(colNum)
                    .append("' at '")
                    .append(sheetName)
                    .append("'")
                    .toString());
            ExcelWSheet = ExcelWBook.getSheet(sheetName);
            Row = ExcelWSheet.getRow(rowNum);
            Cell = Row.getCell(colNum, Row.RETURN_BLANK_AS_NULL);
            if (Cell == null) {
                Cell = Row.createCell(colNum);
                Cell.setCellValue(result);
            } else {
                Cell.setCellValue(result);
            }
            FileOutputStream fileOut = new FileOutputStream(pathExcel);
            ExcelWBook.write(fileOut);
            fileOut.close();
            ExcelWBook = new XSSFWorkbook(new FileInputStream(pathExcel));
        } catch (Exception e) {
            log.error(e);
            isDone = false;
        }
        if (log.isDebugEnabled()) {
            log.debug("MCO << setCellData()");
        }
        return isDone;
    }

}