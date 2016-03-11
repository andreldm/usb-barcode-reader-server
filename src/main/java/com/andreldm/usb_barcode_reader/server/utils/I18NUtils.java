package com.andreldm.usb_barcode_reader.server.utils;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18NUtils {
    private static ResourceBundle strings;

    static {
        strings = ResourceBundle.getBundle("bundles.strings", new Locale("pt", "BR"));
    }

    public static ResourceBundle getStrings() {
        return strings;
    }

    public static String getString(String key) {
        return strings.getString(key);
    }
}
