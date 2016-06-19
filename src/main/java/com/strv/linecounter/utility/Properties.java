package com.strv.linecounter.utility;


import com.intellij.ide.util.PropertiesComponent;

public class Properties {
    // Preference keys for this package
    private static final String ROWS_METHOD = "rows_method";
    private static final String ROWS_CLASS = "rows_class";

    private PropertiesComponent mProperties;


    public Properties() {
        mProperties = PropertiesComponent.getInstance();
    }

    public void setRowsMethod(String value) {
        mProperties.setValue(ROWS_METHOD, value);
    }

    public String getRowsMethod() {
        return mProperties.getValue(ROWS_METHOD, "50");
    }

    public void setRowsClass(String value) {
        mProperties.setValue(ROWS_CLASS, value);
    }

    public String getRowsClass() {
        return mProperties.getValue(ROWS_CLASS, "500");
    }
}
