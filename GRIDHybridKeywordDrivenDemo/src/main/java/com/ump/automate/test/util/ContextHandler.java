package com.ump.automate.test.util;

import org.apache.log4j.Logger;

/**
 * this class handles the 'passing' of field values
 * works/use as a helper class?
 * @author edward.cervantes
 */
public class ContextHandler {

    private static final Logger log = Logger.getLogger(ContextHandler.class);

    private String globalType;

    private String globalValue;

    /**
     * this method attached the value and type to fields with type String
     * @param type
     * @param name
     */
    public void putSomething(String type, String value) {
        try {
            if (log.isDebugEnabled()) {
                log.debug("MCI >> putSomething()");
            }
            log.info(new StringBuilder("attaching field value '").append(value)
                    .append("'")
                    .append(" with the type '")
                    .append(type)
                    .append("'"));
            globalType = type;
            globalValue = value;
        } catch (Exception e) {
            //            log.info(reasonMessage);
            log.error("Error found. ", e);
        }
        if (log.isDebugEnabled()) {
            log.debug("MCO << putSomething()");
        }
    }

    /**
     * this method gives the value attached to the given type
     * works with this class method 'putSomething(String type, String value)'
     * @param type
     * @return
     */
    public String getSomething(String type) {
        String str = "";
        try {
            if (log.isDebugEnabled()) {
                log.debug("MCI >> getSomething()");
            }
            log.info(new StringBuilder("getting the attached value with the type '").append(type).append("'"));
            if (globalType.equals(type)) {
                str = globalValue;
                log.info(new StringBuilder("field value is '").append(str).append("'"));
            }
        } catch (Exception e) {
            //            log.info(reasonMessage);
            log.error("Error found. ", e);
        }
        if (log.isDebugEnabled()) {
            log.debug("MCO << getSomething()");
        }
        return str;
    }

    /**
     * this method removes the attached values of the fields 'type' and 'value'
     * @return
     */
    public boolean isSomethingRemove() {
        boolean isRemove = false;
        try {
            if (log.isDebugEnabled()) {
                log.debug("MCI >> isSomethingRemove()");
            }
            log.info("removing the field values...");
            globalValue = "";
            globalType = "";
            isRemove = true;
        } catch (Exception e) {
            //            log.info(reasonMessage);
            log.error("Error found. ", e);
        }
        if (log.isDebugEnabled()) {
            log.debug("MCO << isSomethingRemove()");
        }
        return isRemove;
    }
}
