package com.andreldm.usb_barcode_reader.server.dialogs;

import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Window;

public class DialogUtils {
	public static void dialogAlert(String msg, Event event) {
		dialogAlert(msg, event, AlertType.WARNING);
	}

	public static void dialogAlert(String msg, Event event, AlertType tipo) {
		Alert alert = new Alert(tipo);
		if (event != null)
			alert.initOwner(getWindowFromEvent(event));
		alert.setTitle("Atention");
		alert.setHeaderText(null);
		alert.setContentText(msg);
		alert.show();
	}

	public static void dialogError(String msg, Event event) {
		dialogError(msg, getWindowFromEvent(event));
	}

	public static void dialogError(String msg, Window window) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.initOwner(window);
		alert.setTitle("Error");
		alert.setHeaderText(null);
		alert.setContentText(msg);
		alert.show();
	}

	public static Window getWindowFromEvent(Event e) {
		return ((Node) e.getSource()).getScene().getWindow();
	}
}
