/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.forgeard.interfacegraphique;

import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 *
 * @author artus
 */
public class Controller {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label mylabel;
    @FXML
    private TextField xposition;
    @FXML
    private TextField yposition;
    @FXML
    public Button submitted;
    int positionx;
    int positiony;
    
    
    public void switchToSecondary() throws IOException {
        App.setRoot("editeur");
    }
    
    public void switchTocoordonees(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("coordonees.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToline(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("Line.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToSupport(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("support.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void ChargerUnFichier(ActionEvent event) throws IOException{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.showOpenDialog(stage);
        }
    public void Submit(ActionEvent event) throws IOException{
        positiony = Integer.parseInt(yposition.getText());
        positionx = Integer.parseInt(xposition.getText());
        System.out.println(positionx);
        System.out.println(positiony);
        Circle circle = new Circle();
        circle.setCenterX(positionx);
        circle.setCenterY(positiony);
        circle.setRadius(5);
        
        
    }
        
    }

