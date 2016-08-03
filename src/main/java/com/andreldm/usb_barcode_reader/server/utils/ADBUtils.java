package com.andreldm.usb_barcode_reader.server.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.andreldm.usb_barcode_reader.server.Constants;

public class ADBUtils {
    private static final String ADB_PATH = String.format("%s%sadb%s",
            Paths.get(".").toAbsolutePath().normalize().toString(), File.separator, File.separator);
    private static final String CMD_DEVICES = String.format("%s devices", getExecutable());
    private static final String CMD_REVERSE = String.format("%s reverse tcp:%s tcp:%s",
            getExecutable(), Constants.PORT, Constants.PORT);

    public static String getDevice() {
        List<String> lines = executeCommand(CMD_DEVICES);
        if (lines == null || lines.size() <= 1) {
            return null;
        }

        // List of devices attached
        // 0018366408 device
        String line = lines.get(1);
        if (line.trim().isEmpty()) {
            return null;
        }

        return line.split("\\s+")[0];
    }

    public static boolean setupReversePort() {
        List<String> lines = executeCommand(CMD_REVERSE);
        return lines != null;
    }

    private static List<String> executeCommand(String command) {
        try {
            Runtime run = Runtime.getRuntime();
            Process p = run.exec(ADB_PATH + command);

            p.waitFor();

            if (p.exitValue() != 0)
                return null;

            BufferedReader buf = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            List<String> lines = new ArrayList<>();

            while ((line = buf.readLine()) != null) {
                lines.add(line);
            }

            return lines;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
    private static String getExecutable() {
        return Util.isLinux() ? "adb" :
            Util.isWindows() ? "adb.exe" : "";
            
    }
}
