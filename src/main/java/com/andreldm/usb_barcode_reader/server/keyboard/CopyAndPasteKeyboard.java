package com.andreldm.usb_barcode_reader.server.keyboard;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class CopyAndPasteKeyboard implements Keyboard {
	private Clipboard clipboard;
	private Robot robot;

	public CopyAndPasteKeyboard(Robot robot) {
		clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		this.robot = robot;
	}

	@Override
	public void type(CharSequence characters) {
	    if (characters == null) return;

		StringSelection stringSelection = new StringSelection(characters.toString());
		clipboard.setContents(stringSelection, stringSelection);

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
	}
}
