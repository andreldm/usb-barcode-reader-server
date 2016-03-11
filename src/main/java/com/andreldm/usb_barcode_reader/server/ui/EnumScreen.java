package com.andreldm.usb_barcode_reader.server.ui;

public enum EnumScreen {
    MAIN("Main.fxml");

	private final String path;
	private final String basePath = "/fxml/";

	EnumScreen(String path) {
		this.path = path;
	}

	public String getPath() {
		return basePath + path;
	}
}