package com.andreldm.usb_barcode_reader.server.keyboard;

import java.awt.Robot;

import com.andreldm.usb_barcode_reader.server.Constants;

public class KeyboardFactory {
	public static Keyboard build(int os, Robot robot) {
		switch (os) {
		case Constants.OS_LINUX:
			return new CopyAndPasteKeyboard(robot);
		case Constants.OS_WINDOWS:
			return new WindowUnicodeKeyboard(robot);
		default:
			return new CopyAndPasteKeyboard(robot);
		}
	};
}
