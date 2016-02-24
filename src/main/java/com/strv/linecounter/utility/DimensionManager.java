package com.strv.linecounter.utility;


import java.awt.*;

public class DimensionManager {
    final public static int FONT_SMALL = 12;
    final public static int FONT_MEDIUM = 14;
    final public static int FONT_MEDIUM_LARGE = 16;
    final public static int FONT_LARGE = 18;
    final public static int FONT_XLARGE = 20;

    public static int fontSize(int size) {
        double screen = Toolkit.getDefaultToolkit().getScreenResolution();
        return (int) (size * (screen / 96f));
    }

    public static int reDimension(int dimension) {
        double screen = Toolkit.getDefaultToolkit().getScreenResolution();
        return (int) (dimension * (screen / 96f));
    }
}
