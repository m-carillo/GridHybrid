package com.ump.automate.test.bean;

import java.util.List;

public class ResultBean {

	public String testCaseId;

	public List<String> failedTestCasesIdList;

	public List<String> passedTestCasesIdList;

	public int totalPassedTestCaseRan;

	public int totalFailedTestCaseRan;

	public int totalTestCaseRan;

	public String getTestCaseId() {
		return testCaseId;
	}

	public void setTestCaseId(String testCaseId) {
		this.testCaseId = testCaseId;
	}

	public List<String> getFailedTestCasesIdList() {
		return failedTestCasesIdList;
	}

	public void setFailedTestCasesIdList(List<String> failedTestCasesIdList) {
		this.failedTestCasesIdList = failedTestCasesIdList;
	}

	public List<String> getPassedTestCasesIdList() {
		return passedTestCasesIdList;
	}

	public void setPassedTestCasesIdList(List<String> passedTestCasesIdList) {
		this.passedTestCasesIdList = passedTestCasesIdList;
	}

	public int getTotalPassedTestCaseRan() {
		return totalPassedTestCaseRan;
	}

	public void setTotalPassedTestCaseRan(int totalPassedTestCaseRan) {
		this.totalPassedTestCaseRan = totalPassedTestCaseRan;
	}

	public int getTotalFailedTestCaseRan() {
		return totalFailedTestCaseRan;
	}

	public void setTotalFailedTestCaseRan(int totalFailedTestCaseRan) {
		this.totalFailedTestCaseRan = totalFailedTestCaseRan;
	}

	public int getTotalTestCaseRan() {
		return totalTestCaseRan;
	}

	public void setTotalTestCaseRan(int totalTestCaseRan) {
		this.totalTestCaseRan = totalTestCaseRan;
	}
}
