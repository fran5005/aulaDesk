package com.alcoleagarridofran.auladeskv3;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AulaDeskV3Application {

    public static void main(String[] args) {
        Application.launch(JavaFX.class);
        System.out.println("[DEBUG] Aplicacion iniciada");
    }

}
