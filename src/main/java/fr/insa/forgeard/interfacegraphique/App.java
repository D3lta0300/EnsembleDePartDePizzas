package fr.insa.forgeard.interfacegraphique;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.image.Image;

import fr.insa.forgeard.treillis.Barre;
import fr.insa.forgeard.treillis.Noeud;
import fr.insa.forgeard.treillis.NoeudAppuiDouble;
import fr.insa.forgeard.treillis.NoeudAppuiSimple;
import fr.insa.forgeard.treillis.NoeudSimple;
import fr.insa.forgeard.treillis.Treillis;
import fr.insa.forgeard.treillis.Vecteur2D;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


/**
 * JavaFX App
 */
public class App extends Application {

    
    @Override
    public void start(Stage stage) throws IOException {
        Treillis treillis = new Treillis();
        VBox root = new VBox();
       
        root.setSpacing(10);
        root.setPadding(new Insets(15,20, 10,10));
       
        // Button 1
        Button button1= new Button("Button1");
        root.getChildren().add(button1);
       
       
        // Button 2
        Button button2 = new Button("Button2");
        button2.setPrefSize(100, 100);
        root.getChildren().add(button2);
       
        // TextField
        TextField textField = new TextField("Text Field");
        textField.setPrefWidth(110);
         
       
        root.getChildren().add(textField);
       
        // CheckBox
        CheckBox checkBox = new CheckBox("Check Box");
         
        root.getChildren().add(checkBox);
       
        // RadioButton
        RadioButton radioButton = new RadioButton("Radio Button");
        root.getChildren().add(radioButton);
        Scene sc = new Scene(root,550,250);
        stage.setScene(sc);
        stage.setTitle("Notest");
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }

}