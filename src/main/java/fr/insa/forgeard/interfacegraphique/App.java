package fr.insa.forgeard.interfacegraphique;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.image.Image;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        CoucouPanel main = new CoucouPanel();
        Scene sc = new Scene(main);
        stage.setScene(sc);
        stage.setTitle("Nouveau");
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }

}