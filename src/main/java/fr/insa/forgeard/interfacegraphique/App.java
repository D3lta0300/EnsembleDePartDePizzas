package fr.insa.forgeard.interfacegraphique;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.image.Image;

import fr.insa.forgeard.treillis.Barre;
import fr.insa.forgeard.treillis.Treillis;
import fr.insa.forgeard.treillis.Noeud;
import fr.insa.forgeard.treillis.NoeudAppuiDouble;
import fr.insa.forgeard.treillis.NoeudAppuiSimple;
import fr.insa.forgeard.treillis.NoeudSimple;
import fr.insa.forgeard.treillis.Treillis;
import fr.insa.forgeard.treillis.Vecteur2D;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;


/**
 * JavaFX App
 */
public class App extends Application {
    private int xpos;
    private int ypos;
    private int i = 1;
    private int posx;
    private int posy;
    private int j = 0;
    private int l = 0;
    
    

    
    @Override
    public void start(Stage stage) throws IOException {
        Treillis treillis = new Treillis();
        
        //initialisation borderpane
        BorderPane root = new BorderPane();
        
        //créer les boites
        VBox pointscreate = new VBox();
        HBox coordonneespts = new HBox();
        HBox hbox1 = new HBox();
        HBox hbox2 = new HBox();
        
        //création canvas
        Canvas canvas = new Canvas();
        canvas.setHeight(700);
        canvas.setWidth(1000);
        
        //Zone graphique
        GraphicsContext graphics_context = 
            canvas.getGraphicsContext2D();
        graphics_context.setFill(Color.LIGHTGREY);
        graphics_context.fillRect(0, 0, 1000, 700);
        
        Group group = new Group(canvas);
        root.setCenter(group);
        
        //Onglet création de points
        Label barredecla = new Label("Création de point :");
        pointscreate.getChildren().add(barredecla);
        
        Label typepoint = new Label("Type de point :");
        hbox1.getChildren().add(typepoint);
        
        
        ComboBox comboBox = new ComboBox();
        comboBox.getItems().add("Noeud Double");
        comboBox.getItems().add("Noeud Simple");
        comboBox.getItems().add("Noeud Appui");
        hbox1.getChildren().add(comboBox);
        
        Label x = new Label("X:");
        coordonneespts.getChildren().add(x);
        
        TextField xinput = new TextField();
        xinput.setPrefWidth(50);
        xinput.setPrefHeight(8);
        coordonneespts.getChildren().add(xinput);
        
        Label y= new Label("Y:");
        coordonneespts.getChildren().add(y);
        
        TextField yinput = new TextField();
        yinput.setPrefWidth(50);
        yinput.setPrefHeight(8);
        coordonneespts.getChildren().add(yinput);
        pointscreate.getChildren().add(coordonneespts);
        pointscreate.getChildren().add(hbox1);
        // Button créer un point
        Button button1= new Button("Créer !");
        pointscreate.getChildren().add(button1);
        
        Label label1 = new Label("Nouvelle barre :");
        pointscreate.getChildren().add(label1);
        
        ComboBox comboBox1 = new ComboBox();
        hbox2.getChildren().add(comboBox1);
        
        ComboBox comboBox2 = new ComboBox();
        hbox2.getChildren().add(comboBox2);
        
        pointscreate.getChildren().add(hbox2);
        
        Button button2 = new Button("Créer");
        pointscreate.getChildren().add(button2);
        
        
        button1.setOnAction(new EventHandler<ActionEvent>(){
  
    public void handle(ActionEvent event){
        xpos = Integer.parseInt(xinput.getText());
        ypos = Integer.parseInt(yinput.getText());
        
        if ((xpos>=0)&&(xpos<=1000)&&(ypos<=700)&&(ypos>=0)){
        //Si noeud double, alors noeud en 
            if (comboBox.getValue() == "Noeud Double"){
                graphics_context.setFill(Color.RED);
                graphics_context.fillOval(xpos, -(ypos-350), 10, 10 );
                comboBox1.getItems().add(i);
                comboBox2.getItems().add(i);
                Noeud n = new Noeud(xpos, ypos) {
                    @Override
                    public int nombreInconnue() {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }
                };
                treillis.addNoeud(n);
            }
        
            if (comboBox.getValue() == "Noeud Simple"){
                graphics_context.setFill(Color.BLUE);
                graphics_context.fillOval(xpos, -(ypos-350), 10, 10 );
                comboBox1.getItems().add(i);
                comboBox2.getItems().add(i);
                Noeud n = new Noeud(xpos, ypos) {
                    @Override
                    public int nombreInconnue() {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }
                };
                treillis.addNoeud(n);
            }

            
            if (comboBox.getValue() == "Noeud Appui"){
                graphics_context.setFill(Color.GREEN);
                graphics_context.fillOval(xpos, -(ypos-350), 10, 10 );
                comboBox1.getItems().add(i);
                comboBox2.getItems().add(i);
                Noeud n = new Noeud(xpos, ypos) {
                    @Override
                    public int nombreInconnue() {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }
                };
                treillis.addNoeud(n);
            }
            i=i+1;
 
        //graphics_context.clearRect(0, 0,1000, 700);
        //graphics_context.setFill(Color.LIGHTGREY);
        //graphics_context.fillRect(0, 0, 1000, 700);
    
        //for (int i = 0; i<treillis.getNoeuds().size(); i++) {
            
            //graphics_context.setFill(Color.GREEN);
            //graphics_context.fillOval(treillis.getNoeudByID(i).getPx()-1, treillis.getNoeudByID(i).getPy()-1, 10, 10 );
            //System.out.println(treillis.getNoeudByID(i).getPx());
            //System.out.println(treillis.getNoeudByID(i).getPy());
        }
        
        //}
    }
});
       
        button2.setOnAction(new EventHandler<ActionEvent>(){
  
    public void handle(ActionEvent event){
        System.out.println(comboBox1.getItems());
        j = (int) comboBox1.getValue();
        l = (int) comboBox2.getValue();
    
        Barre b = new Barre(treillis.getNoeudByID(j),treillis.getNoeudByID(l));
        Line line = new Line();
        graphics_context.setStroke(Color.BLUE);
        graphics_context.setLineWidth(3);
        graphics_context.strokeLine(treillis.getNoeudByID(j).getPx()+5, -(treillis.getNoeudByID(j).getPy()-5-350), treillis.getNoeudByID(l).getPx()+5, -(treillis.getNoeudByID(l).getPy()-5-350));
    }
        });
        
        
        
        
        
        
        
        
        
        
        
        //position bordepane
        root.setLeft(pointscreate);
        //root.setLeft(coordonneespts);
        
        //canvas
  
        // set fill for rectangle
        graphics_context.setFill(Color.LIGHTGREY);
        graphics_context.fillRect(0, 0, 1000, 700);
        
        root.setCenter(group);
        // fin test
        
        
        stage.getIcons().add(new Image("https://www.europodiumshop.com/wp-content/uploads/2017/03/G3_EPL_zoom.jpg"));
        Scene sc = new Scene(root,1200,700);
        stage.setScene(sc);
        stage.setTitle("Application Treillis");
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }

    


}