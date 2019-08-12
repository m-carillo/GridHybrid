package com.ump.automate.test.util;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ump.automate.test.exception.ExternalPropertiesException;
import com.ump.automate.test.execution.impl.DriverScriptImpl;
import com.ump.automate.test.util.properties.PropertiesFileChecker;
import com.ump.automate.test.util.properties.ExternalPropertiesResource;
import com.ump.automate.test.util.properties.PropertiesRetriever;

public class Runner {

    private static final Logger log = Logger.getLogger(Runner.class);

    private ApplicationContext context;

    private ScriptRunner scriptRunner;

    public void run(String excelPath, String objRepoPath, String naviPropertiesPath, String excelConfigPath) throws Exception {
        try {
            if (log.isDebugEnabled()) {
                log.debug("MCI >> run()");
            }
            context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
            System.out.print("context loaded: ");
            System.out.println(context != null);

            scriptRunner = (ScriptRunner) context.getBean("scriptRunner", ScriptRunner.class);
            System.out.print("scriptRunner loaded: ");
            System.out.println(scriptRunner != null);

            scriptRunner.isTestExecuted(excelPath, objRepoPath, naviPropertiesPath, excelConfigPath);
            scriptRunner.displayTestResults();
        } catch (ExternalPropertiesException e) {
            log.error("Error found. ", e);
            throw e;
        } catch (Exception e) {
            log.error("Error found. ", e);
            throw e;
        }
        if (log.isDebugEnabled()) {
            log.debug("MCO << run()");
        }
    }


    public static void main(String[] args) {
        String servicetoInvoke = null;
        String inputToService = null;
        String inputToService2 = null;
        String inputToService3 = null;
        String inputToService4 = null;

        if (args != null) {
            if (args.length >= 1) {
                servicetoInvoke = args[0];
                if (args.length >= 2) {
                    inputToService = args[1];
                    if (args.length >= 3) {
                        inputToService2 = args[2];
                        if (args.length >= 4) {
                            inputToService3 = args[3];
                            if (args.length >= 5) {
                                inputToService4 = args[4];
                            }
                        }
                    }
                }
            }
        }

        if ("RUN".equalsIgnoreCase(servicetoInvoke) && inputToService != null
                && inputToService2 != null
                && inputToService3 != null
                && inputToService4 != null) {

            System.out.println("**                  _ _                              _      __ _ _      **");
            System.out.println("**  _ _ ___ __ _ __| (_)_ _  __ _     _____ ____ ___| |    / _(_) |___  **");
            System.out.println("** | '_/ -_) _` / _` | | ' \\/ _` |   / -_) \\ / _/ -_) |   |  _| | / -_) **");
            System.out.println("** |_| \\___\\__,_\\__,_|_|_||_\\__, |   \\___/_\\_\\__\\___|_|   |_| |_|_\\___| **");
            System.out.println("**                          |___/					**");

            try {
                Runner runner = new Runner();
                System.out.println("");
                System.out.println("*** excel file path:");
                System.out.println("       " + inputToService);
                System.out.println("");
                System.out.println("*** object repositories properties file path:");
                System.out.println("       " + inputToService2);
                System.out.println("");
                System.out.println("*** navi properties file path:");
                System.out.println("       " + inputToService3);
                System.out.println("");
                System.out.println("*** excel configuration properties file path:");
                System.out.println("       " + inputToService4);
                System.out.println("");
                System.out.println("*******************************************************************************");
                System.out.println("");
                System.out.println("calling testSampleScript...");
                System.out.println("");
                runner.run(inputToService, inputToService2, inputToService3, inputToService4);
                System.out.println("               _              ");
                System.out.println("	    __| |___ _ _  ___  ");
                System.out.println("	   / _` / _ \\ ' \\/ -_) ");
                System.out.println("	   \\__,_\\___/_||_\\___|");
            } catch (ExternalPropertiesException e) {
                System.out.println("");
                System.out.println("     " + e.getMessage());
                System.out.println("");
                System.out.println("You can view the logs at 'C:\\log-files-selenium'.");
            } catch (Exception e) {
                System.out.println("             _                  ");
                System.out.println(" __ __ _____| |__ ___ _ __  ___ ");
                System.out.println(" \\ V  V / -_) / _/ _ \\ '  \\/ -_)");
                System.out.println("  \\_/\\_/\\___|_\\__\\___/_|_|_\\___|");
                System.out.println(" ");
                System.out.println("**NOTE: ");
                System.out.println("    the following comes with a single(1) space only.");
                System.out.println("    ");
                System.out.println("   java -Dhttp.proxyHost=(your proxy host)");
                System.out.println("   -Dhttp.proxyPort=(your proxy port)");
                System.out.println("   -jar (path_to_jar_file)");
                System.out.println("   RUN (path_to_excel_file)");
                System.out.println("   (path_to_object_repository_property_file)");
                System.out.println("   (path_to_navigation_property_file)");
                System.out.println("   (path_to_excel_configuration_file)");
                System.out.println(" ");
                System.out.println(" ");
                System.out.println(" eg. java -Dhttp.proxyHost=(your proxy host) -Dhttp.proxyPort=(your proxy port) -jar (path_to_jar_file) RUN (path_to_excel_file) (path_to_object_repository_property_file) (path_to_navigation_property_file) (path_to_excel_configuration_file)");
                System.out.println(" ");
                System.out.println(" ");
                System.out.println("             _ ");
                System.out.println("     ___ _ _  __| |");
                System.out.println("    / -_) ' \\/ _` |");
                System.out.println("    \\___|_||_\\__,_|");
            }
            System.out.println("*******************************************************************************");
            System.out.println("*******************************************************************************");
        } else {
            System.out.println("             _                  ");
            System.out.println(" __ __ _____| |__ ___ _ __  ___ ");
            System.out.println(" \\ V  V / -_) / _/ _ \\ '  \\/ -_)");
            System.out.println("  \\_/\\_/\\___|_\\__\\___/_|_|_\\___|");
            System.out.println(" ");
            System.out.println("**NOTE: ");
            System.out.println("    the following comes with a single(1) space only.");
            System.out.println("    ");
            System.out.println("   java -Dhttp.proxyHost=(your proxy host)");
            System.out.println("   -Dhttp.proxyPort=(your proxy port)");
            System.out.println("   -jar (path_to_jar_file)");
            System.out.println("   RUN (path_to_excel_file)");
            System.out.println("   (path_to_object_repository_property_file)");
            System.out.println("   (path_to_navigation_property_file)");
            System.out.println("   (path_to_excel_configuration_file)");
            System.out.println(" ");
            System.out.println(" ");
            System.out.println(" eg. java -Dhttp.proxyHost=(your proxy host) -Dhttp.proxyPort=(your proxy port) -jar (path_to_jar_file) RUN (path_to_excel_file) (path_to_object_repository_property_file) (path_to_navigation_property_file) (path_to_excel_configuration_file)");
            System.out.println(" ");
            System.out.println(" ");
            System.out.println("   		     _ ");
            System.out.println("	 ___ _ _  __| |");
            System.out.println("	/ -_) ' \\/ _` |");
            System.out.println("	\\___|_||_\\__,_|");
        }
    }
}
