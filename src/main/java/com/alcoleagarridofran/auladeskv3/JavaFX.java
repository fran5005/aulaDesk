package com.alcoleagarridofran.auladeskv3;

import com.alcoleagarridofran.auladeskv3.controller.CONTROLLER;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class JavaFX extends Application{
    private ConfigurableApplicationContext context;


    @Override
    public void init() throws Exception {
        context = new SpringApplicationBuilder(AulaDeskV3Application.class).run();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("/com/java/fx/main.fxml"));
        fxml.setControllerFactory(context::getBean);

        Scene scene = new Scene(fxml.load());
        stage.setTitle("AULA DESK");
        Image applicationIcon = new Image(getClass().getResourceAsStream("/images/logoAulaDesk.png"));
        stage.getIcons().add(applicationIcon);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        if (context != null) {
            context.close();
        }
    }
}

