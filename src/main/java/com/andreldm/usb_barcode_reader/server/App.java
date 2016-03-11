package com.andreldm.usb_barcode_reader.server;

import java.util.HashMap;
import java.util.Map;

import com.andreldm.usb_barcode_reader.server.ui.EnumScreen;
import com.andreldm.usb_barcode_reader.server.ui.controllers.MainController;
import com.andreldm.usb_barcode_reader.server.ui.events.NavigationEvent;
import com.andreldm.usb_barcode_reader.server.utils.FxUtils;
import com.andreldm.usb_barcode_reader.server.utils.I18NUtils;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {
    private Map<EnumScreen, Scene> scenes;
    private Map<EnumScreen, Object> controllers;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        scenes = new HashMap<>();
        controllers = new HashMap<>();
        initUI(stage);
    }

    @Override
    public void stop() {
        MainController controller = (MainController) controllers.get(EnumScreen.MAIN);
        if (controller != null) {
            controller.halt();
        }
    }

    private void initUI(Stage stage) {
        stage.addEventHandler(NavigationEvent.GOTO, new EventHandler<NavigationEvent>() {
            @Override
            public void handle(NavigationEvent evt) {
                Scene scene = loadScene(evt.getScreen());
                stage.setScene(scene);
                FxUtils.centerStage(stage);
            }
        });

        Scene scene = loadScene(EnumScreen.MAIN);
        stage.setScene(scene);
        stage.setTitle(Constants.APP_NAME);
        stage.getIcons().add(new Image("images/icon.png"));
        stage.show();
        FxUtils.centerStage(stage);
    }

    private Scene loadScene(EnumScreen screen) {
        Scene scene = scenes.get(screen);

        if (scene == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(screen.getPath()), I18NUtils.getStrings());
                scene = new Scene(loader.load());

                scenes.put(screen, scene);
                controllers.put(screen, loader.getController());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return scene;
    }
}
