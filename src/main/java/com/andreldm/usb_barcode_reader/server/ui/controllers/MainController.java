package com.andreldm.usb_barcode_reader.server.ui.controllers;

import java.awt.AWTException;
import java.awt.Robot;

import com.andreldm.usb_barcode_reader.server.Server;
import com.andreldm.usb_barcode_reader.server.dialogs.DialogUtils;
import com.andreldm.usb_barcode_reader.server.keyboard.Keyboard;
import com.andreldm.usb_barcode_reader.server.keyboard.KeyboardFactory;
import com.andreldm.usb_barcode_reader.server.utils.ADBUtils;
import com.andreldm.usb_barcode_reader.server.utils.FxUtils;
import com.andreldm.usb_barcode_reader.server.utils.I18NUtils;
import com.andreldm.usb_barcode_reader.server.utils.Util;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class MainController {
    @FXML private Label fxLabelStatus;
    @FXML private Label fxLabelDevice;
    @FXML private Button fxButtonToggleServer;

    private Server server;

    @FXML
    public void handleToggleServer(ActionEvent event) {
        if (server == null) {
            try {
                Keyboard keyboard = KeyboardFactory.build(Util.whichOS(), new Robot());
                server = new Server(keyboard);
            } catch (AWTException e) {
                e.printStackTrace();
                DialogUtils.dialogError(I18NUtils.getString("error.server.setup"), event);
                Platform.exit();
                return;
            }
        }

        server.stop();

        fxLabelStatus.setText(I18NUtils.getString("label.server.not.running"));
        fxLabelStatus.setTextFill(Color.RED);
        fxButtonToggleServer.setText(I18NUtils.getString("button.start"));

        String device = ADBUtils.getDevice();
        if (device == null) {
            fxLabelDevice.setText(I18NUtils.getString("label.device.none"));
            fxLabelDevice.setTextFill(Color.RED);
            FxUtils.flash(fxLabelDevice);
            return;
        }

        fxLabelDevice.setText(I18NUtils.getString("label.device") + device);
        fxLabelDevice.setTextFill(Color.BLUE);

        if (!ADBUtils.setupReversePort()) {
            DialogUtils.dialogError(I18NUtils.getString("error.reversing.ports"), event);
            return;
        }

        server.start();

        fxLabelStatus.setText(I18NUtils.getString("label.server.running"));
        fxLabelStatus.setTextFill(Color.BLUE);
        fxButtonToggleServer.setText(I18NUtils.getString("button.restart"));
    }

    public void halt() {
        if (server != null)
            server.stop();
    }
}
