package com.andreldm.usb_barcode_reader.server.ui.events;

import javafx.event.Event;
import javafx.event.EventType;

public class DialogEvent extends Event {
	private static final long serialVersionUID = -828408989917243117L;

	public static final EventType<DialogEvent> SHOW = new EventType<>(ANY, "DialogEvent.SHOW");
	public static final EventType<DialogEvent> CLOSE = new EventType<>(ANY, "DialogEvent.CLOSE");

	public DialogEvent(EventType<DialogEvent> evt) {
		super(evt);
	}
}
