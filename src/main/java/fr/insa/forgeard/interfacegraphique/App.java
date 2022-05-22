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
    private int j = 0;
    private int l = 0;
    private int Forcex;
    private int Forcey;

    @Override
    public void start(Stage stage) throws IOException {
        Treillis treillis = new Treillis();

        //initialisation borderpane
        BorderPane root = new BorderPane();

        //créer les boites
        VBox vbox = new VBox();
        HBox hBoxCoordonneesPts = new HBox();
        HBox hBoxTypePoint = new HBox();
        HBox hbox2 = new HBox();
        HBox hbox3 = new HBox();
        HBox hbox4 = new HBox();
        HBox hbox5 = new HBox();
        HBox hBoxForces = new HBox();

        //création canvas
        Canvas canvas = new Canvas();
        canvas.setHeight(700);
        canvas.setWidth(1000);

        //Zone graphique
        GraphicsContext graphics_context = canvas.getGraphicsContext2D();
        graphics_context.setFill(Color.LIGHTGREY);
        graphics_context.fillRect(0, 0, 1000, 700);

        Group group = new Group(canvas);
        root.setCenter(group);

        //Onglet création de points
        Label barredecla = new Label("Création de point :");
        vbox.getChildren().add(barredecla);

        Label typepoint = new Label("Type de point :");
        hBoxTypePoint.getChildren().add(typepoint);

        ComboBox comboBoxTypeNoeud = new ComboBox();
        comboBoxTypeNoeud.getItems().add("Noeud Simple");
        comboBoxTypeNoeud.getItems().add("Noeud Appui Double");
        comboBoxTypeNoeud.getItems().add("Noeud Appui Simple");
        hBoxTypePoint.getChildren().add(comboBoxTypeNoeud);

        Label x = new Label("X:");
        hBoxCoordonneesPts.getChildren().add(x);

        TextField xinput = new TextField();
        xinput.setPrefWidth(50);
        xinput.setPrefHeight(8);
        hBoxCoordonneesPts.getChildren().add(xinput);

        Label y = new Label("Y:");
        hBoxCoordonneesPts.getChildren().add(y);

        TextField yinput = new TextField();
        yinput.setPrefWidth(50);
        yinput.setPrefHeight(8);
        hBoxCoordonneesPts.getChildren().add(yinput);
        vbox.getChildren().add(hBoxCoordonneesPts);
        vbox.getChildren().add(hBoxTypePoint);

        // Boutton créer un point
        Button bouttonCréerN = new Button("Créer !");
        vbox.getChildren().add(bouttonCréerN);

        Label labelNouvelleBarre = new Label("Nouvelle barre :");
        vbox.getChildren().add(labelNouvelleBarre);

        ComboBox comboBox1 = new ComboBox();
        hbox2.getChildren().add(comboBox1);

        ComboBox comboBox2 = new ComboBox();
        hbox2.getChildren().add(comboBox2);

        Button bouttonCréerBarre = new Button("Créer");
        hbox2.getChildren().add(bouttonCréerBarre);

        vbox.getChildren().add(hbox2);

        ComboBox comboBox3 = new ComboBox();
        hbox3.getChildren().add(comboBox3);
        ComboBox comboBox4 = new ComboBox();
        hbox4.getChildren().add(comboBox4);

        Button bouttonSupprimerN = new Button("Supprimer");
        hbox3.getChildren().add(bouttonSupprimerN);

        Label label2 = new Label("Supprimer point:");
        vbox.getChildren().add(label2);

        Button bouttonSupprimerBarre = new Button("Supprimer");
        hbox4.getChildren().add(bouttonSupprimerBarre);

        vbox.getChildren().add(hbox3);

        Label label3 = new Label("Supprimer barre:");
        vbox.getChildren().add(label3);
        vbox.getChildren().add(hbox4);

        Label label4 = new Label("Ajouter des efforts :");
        vbox.getChildren().add(label4);
        Label label5 = new Label("Noeuds :");
        ComboBox comboBox5 = new ComboBox();
        Label label6 = new Label("Sur x : ");
        Label label7 = new Label("Sur y : ");
        TextField Fx = new TextField();
        Fx.setPrefWidth(50);
        Fx.setPrefHeight(8);
        TextField Fy = new TextField();
        Fy.setPrefWidth(50);
        Fy.setPrefHeight(8);
        hbox5.getChildren().add(label5);
        hbox5.getChildren().add(comboBox5);
        hBoxForces.getChildren().add(label6);
        hBoxForces.getChildren().add(Fx);
        hBoxForces.getChildren().add(label7);
        hBoxForces.getChildren().add(Fy);
        Button bouttonAjouterForce = new Button("Ajouter force");

        vbox.getChildren().add(hbox5);
        vbox.getChildren().add(hBoxForces);
        vbox.getChildren().add(bouttonAjouterForce);

        //créer un point
        bouttonCréerN.setOnAction((ActionEvent event) -> {
            xpos = Integer.parseInt(xinput.getText());
            ypos = Integer.parseInt(yinput.getText());

            if ((xpos >= 0) && (xpos <= 1000) && (ypos <= 350) && (ypos >= -350)) {
                //Si noeud double, alors noeud en
                if (comboBoxTypeNoeud.getValue() == "Noeud Appui Double") {
                    Noeud n = new NoeudAppuiDouble(xpos, ypos);
                    treillis.addNoeud(n);
                }

                if (comboBoxTypeNoeud.getValue() == "Noeud Simple") {

                    Noeud n = new NoeudSimple(xpos, ypos);
                    treillis.addNoeud(n);
                }

                if (comboBoxTypeNoeud.getValue() == "Noeud Appui Simple") {

                    Noeud n = new NoeudAppuiSimple(xpos, ypos);
                    treillis.addNoeud(n);
                }

            }

            redraw(graphics_context, comboBox1, comboBox2, comboBox3, comboBox4, comboBox5, treillis);
        });

        //Créer une barre
        bouttonCréerBarre.setOnAction((ActionEvent event) -> {
            j = (int) comboBox1.getValue();
            l = (int) comboBox2.getValue();

            Barre b = new Barre(treillis.getNoeudByID(j), treillis.getNoeudByID(l));

            treillis.addBarre(b);
            System.out.println(b);

            redraw(graphics_context, comboBox1, comboBox2, comboBox3, comboBox4, comboBox5, treillis);
        });

        //supprimer un noeud
        bouttonSupprimerN.setOnAction((ActionEvent event) -> {
            j = (int) comboBox3.getValue();

            treillis.deleteNoeud(j);

            redraw(graphics_context, comboBox1, comboBox2, comboBox3, comboBox4, comboBox5, treillis);
        });

        //supprimer une barre
        bouttonSupprimerBarre.setOnAction((ActionEvent event) -> {
            j = (int) comboBox4.getValue();

            treillis.getBarres().remove(j - 1);
            treillis.rearrangeID();

            redraw(graphics_context, comboBox1, comboBox2, comboBox3, comboBox4, comboBox5, treillis);
        });

        //ajouter une force
        bouttonAjouterForce.setOnAction((ActionEvent event) -> {
            Forcex = Integer.parseInt(Fx.getText());
            Forcey = Integer.parseInt(Fy.getText());
            int n = (int) comboBox5.getValue();
            treillis.forces(new Vecteur2D(Forcex, Forcey), n);
            redraw(graphics_context, comboBox1, comboBox2, comboBox3, comboBox4, comboBox5, treillis);
        });

        //position bordepane
        root.setLeft(vbox);
        //root.setLeft(coordonneespts);

        //canvas
        // set fill for rectangle
        graphics_context.setFill(Color.LIGHTGREY);
        graphics_context.fillRect(0, 0, 1000, 700);

        root.setCenter(group);
        // fin test

        stage.getIcons().add(new Image("https://www.europodiumshop.com/wp-content/uploads/2017/03/G3_EPL_zoom.jpg"));
        Scene sc = new Scene(root, 1200, 700);
        stage.setScene(sc);
        stage.setTitle("Application Treillis");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    /**
     * Redessine le treillis et met à jour les comboBox
     * 
     * @param graphics_context
     * @param comboBox1
     * @param comboBox2
     * @param comboBox3
     * @param comboBox4
     * @param comboBox5
     * @param treillis 
     */
    public static void redraw(GraphicsContext graphics_context, ComboBox comboBox1, ComboBox comboBox2, ComboBox comboBox3, ComboBox comboBox4, ComboBox comboBox5, Treillis treillis) {
        //debug
        System.out.println(treillis);

        //Efface et redessinne tout le treillis
        graphics_context.clearRect(0, 0, 1000, 700);
        graphics_context.setFill(Color.LIGHTGREY);
        graphics_context.fillRect(0, 0, 1000, 700);
        comboBox1.getItems().clear();
        comboBox2.getItems().clear();
        comboBox3.getItems().clear();
        comboBox4.getItems().clear();
        comboBox5.getItems().clear();

        for (Barre barre1 : treillis.getBarres()) {

            comboBox4.getItems().add(barre1.getID()); //ajoute les barres à la combobox

            //dessine les barres
            Noeud node2 = barre1.getNoeudArrive();
            Noeud node3 = barre1.getNoeudDepart();
            graphics_context.setFill(barreColor(barre1.getForce()));
            graphics_context.setLineWidth(3);
            graphics_context.strokeLine(node2.getPx() + 5, -(node2.getPy() - 5 - 350), node3.getPx() + 5, -(node3.getPy() - 5 - 350));

        }

        for (Noeud n : treillis.getNoeuds()) {

            //ajoute le noeud au comboBox
            comboBox1.getItems().add(n.getID());
            comboBox2.getItems().add(n.getID());
            comboBox3.getItems().add(n.getID());
            comboBox5.getItems().add(n.getID());

            //Dessine le noeud, en variant la couleur selon son type.
            if (n.nombreInconnue() == 0) {
                graphics_context.setFill(Color.GREEN);
            }
            if (n.nombreInconnue() == 1) {
                graphics_context.setFill(Color.RED);
            }
            if (n.nombreInconnue() == 2) {
                graphics_context.setFill(Color.BLACK);
            }

            graphics_context.fillOval(n.getPx(), -(n.getPy() - 350), 10, 10);
        }
    }

    /**
     * Permet de faire varier la couleur des barres en fonction de la force
     * s'exerçant sur celles-ci
     *
     * @param force
     * @return
     */
    public static Color barreColor(double force) {
        double percent = force / 50 + 0.5;
        if (percent<0){
            percent = 0;
        } else if (percent >1){
            percent = 1;
        }
        return lerp(new Color(1, 0.5, 1 / 3, 1), new Color(0, 0, 1, 1), percent);
    }

    /**
     * Calcul une couleur en utilisant une fonction lerp
     *
     * @param a
     * @param b
     * @param percent
     * @return
     */
    public static Color lerp(Color a, Color b, double percent) {
        double red = lerp(a.getRed(), b.getRed(), percent);
        double blue = lerp(a.getBlue(), b.getBlue(), percent);
        double green = lerp(a.getGreen(), b.getGreen(), percent);
        return new Color(red, green, blue, 1);
    }

    /**
     * Definition de la fonction lerp de base.
     *
     * @param a
     * @param b
     * @param percent
     * @return
     */
    public static double lerp(double a, double b, double percent) {
        return a * percent + b * (1 - percent);
    }

}
