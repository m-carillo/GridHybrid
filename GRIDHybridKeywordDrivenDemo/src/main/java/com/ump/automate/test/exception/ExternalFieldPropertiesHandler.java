package com.ump.automate.test.exception;

/**
 * this class performs methods that checks whether the input/s is/are comparable to the expected
 * @author edward.cervantes
 */
public class ExternalFieldPropertiesHandler {

    private boolean isPerformed;

    /**
     * this method checks if the input has a value
     * @param strFieldToTest
     * @return
     * @throws ExternalPropertiesException	if the value is either NULL or "".
     */
    public boolean isFieldNotNull(String strFieldToTest, String fieldPropert) throws ExternalPropertiesException {
        isPerformed = false;
        if (strFieldToTest == null || "".equals(strFieldToTest)) {
            throw new ExternalPropertiesException(new StringBuilder(ExternalPropertiesException.FIELD_ERROR).append(fieldPropert)
                    .toString());
        } else {
            isPerformed = true;
        }
        return isPerformed;
    }

    /**
     * this method checks if the input has a value and contains number/s(0-9) ONLY. 
     * "." is not allowed
     * @param strFieldToTest
     * @return
     * @throws ExternalPropertiesException	if the value is either NULL or "".
     */
    public boolean isFieldaNumber(String strFieldToTest, String fieldProperty) throws ExternalPropertiesException {
        isPerformed = false;
        try {
            isFieldNotNull(strFieldToTest, fieldProperty);
            if (strFieldToTest.matches("[0-9]+")) {
                isPerformed = true;
            } else {
                isPerformed = false;
            }
        } catch (ExternalPropertiesException e) {
            throw new ExternalPropertiesException(new StringBuilder(ExternalPropertiesException.FIELD_ERROR).append(fieldProperty)
                    .toString());
        }
        return isPerformed;
    }
}
