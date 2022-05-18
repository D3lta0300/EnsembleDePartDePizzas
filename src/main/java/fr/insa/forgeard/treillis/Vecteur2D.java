/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.forgeard.treillis;

/**
 *
 * @author titouan
 */
public class Vecteur2D {        //test
    private double px,py;
    
    public double getPx(){
        return this.px;
    }
    public double getPy(){
        return this.py;
    }
    public void setPy(double py){
        this.py = py;
    }
    public void setPx(double px){
        this.px = px;
    }
    public String toStrig(){
        return "(" + this.px + ", " + this.py + ")";
    }
    public Vecteur2D(double px, double py){
        this.px = px;
        this.py = py;
    }
    public Vecteur2D(){
        this(0.0,0.0);
    }
}
