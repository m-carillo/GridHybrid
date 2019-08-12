package com.ump.automate.test.util;

import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;

import com.ump.automate.test.util.properties.ExternalPropertiesResource;

/**
 * this class has a method that picks/chooses the type of platform to run in virtual machine
 * @author edward.cervantes
 */
public class PlatformVMFactory {

    private static final Logger log = Logger.getLogger(PlatformVMFactory.class);

    private ExternalPropertiesResource externalPropertiesResource;

    private String vmPlatform = "";

    public PlatformVMFactory(ExternalPropertiesResource externalPropertiesResource) {
        vmPlatform = externalPropertiesResource.getVmPlatformProp();
    }

    /**
     * this method returns the platform of choice
     * @return
     */
    public Platform setVMPlatform() {
        if (log.isDebugEnabled()) {
            log.debug("MCI >> setVMPlatform()");
        }
        Platform platform;

        if (vmPlatform.equalsIgnoreCase("win10")) {
            platform = Platform.WIN10;
        } else if (vmPlatform.equalsIgnoreCase("win8")) {
            platform = Platform.WIN8;
        } else if (vmPlatform.equalsIgnoreCase("win8.1")) {
            platform = Platform.WIN8_1;
        } else if (vmPlatform.equalsIgnoreCase("winXP")) {
            platform = Platform.XP;
        } else if (vmPlatform.equalsIgnoreCase("linux")) {
            platform = Platform.LINUX;
        } else if (vmPlatform.equalsIgnoreCase("mac")) {
            platform = Platform.MAC;
        } else if (vmPlatform.equalsIgnoreCase("winVISTA")) {
            platform = Platform.VISTA;
        } else {
            platform = Platform.WINDOWS;
        }
        if (log.isDebugEnabled()) {
            log.debug("MCO << setVMPlatform()");
        }
        return platform;
    }
}
