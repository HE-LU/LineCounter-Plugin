package com.strv.linecounter.utility;

import com.strv.linecounter.LineCounterConfig;


public class Log {
    public static void d(String msg) {
        if (LineCounterConfig.LOGS) System.out.println(msg);
    }


    public static void e(String msg) {
        if (LineCounterConfig.LOGS) System.err.println(msg);
    }
}
