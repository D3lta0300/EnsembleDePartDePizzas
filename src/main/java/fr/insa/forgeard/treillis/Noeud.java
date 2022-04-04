/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.forgeard.treillis;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author titouan
 */
public abstract class  Noeud {
    private double px,py;
    private Vecteur2D force;
    private int id;
    private List<Barre> barresDepart;
    private List<Barre> barresArrive = new ArrayList<Barre>();
    
    public double getPx() {
        return px;
    }

    public void setPx(double px) {
        this.px = px;
    }

    public double getPy() {
        return py;
    }

    public void setPy(double py) {
        this.py = py;
    }
    
    public int getId(){
        return this.id;
    }

    public Vecteur2D getForce() {
        return force;
    }

    public void setForce(Vecteur2D force) {
        this.force = force;
    }

    public Noeud(int id, double px, double py, Vecteur2D force) {
        this.px = px;
        this.py = py;
        this.force = force;
        this.id = id;
        this.barresDepart = new ArrayList<Barre>();
        this.barresArrive = new ArrayList<Barre>();
    }
    
    public Noeud(double px, double py, Vecteur2D force){
        this(-1, px, py, force);
    }
    
    public Noeud(double px, double py){
        this(-1, px, py, new Vecteur2D());
    }
    
    public static Noeud entreeNoeud(){
        Noeud n1 = new NoeudSimple(-1,0.0,0.0, new Vecteur2D());
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez entrer la position x du noeud : ");
        n1.setPx(sc.nextFloat());
        System.out.println("Veuillez entrer la position y du noeud : ");
        n1.setPy(sc.nextFloat());
        return n1;
    }
    
    public abstract int nombreInconnue();
    
    @Override
    public String toString(){
        return "Le noeud " + id + " est en (" + this.px + ", " +  this.py + ") et subbit une force " + this.force;
    }

    /**
     * @return the barresDepart
     */
    public List<Barre> getBarresDepart() {
        return barresDepart;
    }

    /**
     * @return the barresArrive
     */
    public List<Barre> getBarresArrive() {
        return barresArrive;
    }
}
