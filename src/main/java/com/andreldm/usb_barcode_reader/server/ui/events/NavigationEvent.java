package com.andreldm.usb_barcode_reader.server.ui.events;

import com.andreldm.usb_barcode_reader.server.ui.EnumScreen;

import javafx.event.Event;
import javafx.event.EventType;

public class NavigationEvent extends Event {
	private static final long serialVersionUID = -5966242602376622555L;

	public static final EventType<NavigationEvent> GOTO = new EventType<>(ANY, "NavigationEvent.GOTO");

	private EnumScreen screen;
	private Object data;

	public NavigationEvent(EventType<NavigationEvent> type, EnumScreen screen) {
		super(type);
		this.screen = screen;
	}

	public NavigationEvent(EventType<NavigationEvent> type, EnumScreen screen, Object data) {
		super(type);
		this.screen = screen;
		this.data = data;
	}

	public EnumScreen getScreen() {
		return screen;
	}

	public Object getData() {
		return data;
	}
}