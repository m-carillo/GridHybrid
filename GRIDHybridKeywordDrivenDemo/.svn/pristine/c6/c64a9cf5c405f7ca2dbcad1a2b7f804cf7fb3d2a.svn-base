package com.ump.automate.test.execution;

import com.ump.automate.test.bean.ResultBean;

public interface DriverScript {

	public String retrieveSTestCaseID();

	/**
	 * method that reads the excel files test cases
	 * @throws Exception
	 */
	// public void executeTestCase() throws Exception;
	public ResultBean executeTestCase() throws Exception;

	/**
	 * method that sets fields: sActionKeyword, sPageObject, sData
	 * @param rowNum
	 */
	public boolean isCellDataRetrieved(int rowNum);

    /**
     * method that set the result on the test
     * @throws Exception
     */
    public void setTestStepsResult(int rowNum, boolean isWorking) throws Exception;

    public void setReason(String reason) throws Exception;

    public void setScreenshotDir(String screenshotDirectory) throws Exception;

}
