package com.andreldm.usb_barcode_reader.server.utils;

import com.andreldm.usb_barcode_reader.server.Constants;

public class Util {
    public static int whichOS() {
        String os = System.getProperty("os.name");
        if (os == null || os.isEmpty()) {
            return Constants.OS_UNKNOWN;
        }

        if (os.toLowerCase().contains("linux")) {
            return Constants.OS_LINUX;
        }

        if (os.toLowerCase().contains("windows")) {
            return Constants.OS_WINDOWS;
        }

        return Constants.OS_UNKNOWN;
    }
}
