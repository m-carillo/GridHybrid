package com.ump.automate.test.util;

import org.apache.log4j.Logger;

public class SeleniumRunner {

    private static final Logger log = Logger.getLogger(SeleniumRunner.class);

    private ScriptRunner scriptRunner;
    
//    public void displayScriptHello() {
//        System.out.println("scriptRunner: " + scriptRunner + " ...");
//        scriptRunner.scriptRunnerHello();
//    }
//    public void helloWorld() {
//        System.out.println("HelloWorld!");
//    }
    public boolean isRun(String excelPath, String objRepoPath, String naviPropertiesPath, String excelConfigPath) throws Exception {
        boolean isRun = false;
        try {
            if (log.isDebugEnabled()) {
                log.debug("MCI >> testSampleScript()");
            }
            isRun = scriptRunner.isTestExecuted(excelPath, objRepoPath, naviPropertiesPath, excelConfigPath);
            scriptRunner.displayTestResults();
        } catch (Exception e) {
            log.error("Error found. ", e);
            System.out.println("@SeRunner >> isRun " + e.getMessage());
            throw e;
        }
        if (log.isDebugEnabled()) {
            log.debug("MCO << testSampleScript()");
        }
        return isRun;
    }
     
    public void setScriptRunner(ScriptRunner scriptRunner) {
        this.scriptRunner = scriptRunner;
    }
}
