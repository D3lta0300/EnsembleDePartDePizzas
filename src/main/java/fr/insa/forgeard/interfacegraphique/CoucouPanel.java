/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.forgeard.interfacegraphique;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author titouan
 */
public class CoucouPanel extends BorderPane{
    private BorderPane entete;
    private Label lNom;
    private TextField tfNom;
        
    private TextArea messages;
    
    private HBox bouton;
    private Button bCoucou;
    private Button bSalut;
    
    public CoucouPanel(){
        this.lNom = new Label("nom :");
        this.tfNom = new TextField();
        this.entete = new BorderPane();
        this.entete.setLeft(this.lNom);
        this.entete.setCenter(this.tfNom);
        this.setTop(this.entete);
        
        this.messages = new TextArea();
        this.setCenter(this.messages);
        
        this.bCoucou = new Button("Coucou");
        this.bSalut = new Button("Salut");
        this.bouton = new HBox();
        this.bouton.getChildren().add(this.bCoucou);
        this.bouton.getChildren().add(this.bSalut);
        this.setBottom(this.bouton);
        
        this.bCoucou.setOnAction((t) -> {
            String nom = this.tfNom.getText();
            this.messages.appendText("Coucou " + nom + "\n");
        });
        this.bCoucou.setOnMouseEntered((t) -> {
            bSalut.setStyle("-fx-background-color: #ff0000;");
        });
        this.bCoucou.setOnMouseExited((t) -> {
            bSalut.setStyle("");
        });
    }
}
