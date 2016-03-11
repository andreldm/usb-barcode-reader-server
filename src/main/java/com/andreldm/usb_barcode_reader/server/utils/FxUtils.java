package com.andreldm.usb_barcode_reader.server.utils;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FxUtils {
    public static void fireNewEvent(Event origin, Event newEvent) {
        ((Node) origin.getSource()).fireEvent(newEvent);
    }

    public static void centerStage(Stage stage) {
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }

    public static void flash(Node node) {
        KeyFrame flashEnd = new KeyFrame(new Duration(200), new KeyValue(node.opacityProperty(), 0.0));
        KeyFrame flashStart = new KeyFrame(new Duration(400), new KeyValue(node.opacityProperty(), 1.0));

        new Timeline(flashEnd, flashStart).play();
    }
}
