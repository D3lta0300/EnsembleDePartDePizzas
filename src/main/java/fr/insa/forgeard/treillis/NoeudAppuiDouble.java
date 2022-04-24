/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.forgeard.treillis;

/**
 *
 * @author titouan
 */
public class NoeudAppuiDouble extends NoeudAppui {
    public NoeudAppuiDouble(int id, double px, double py, Vecteur2D force) {
        super(id, px, py, force);
    }
    
    public NoeudAppuiDouble(double x, double y){
        super(0,x,y,new Vecteur2D());
    }
    
    @Override
    public String toString(){
        return "Le noeud " + this.getID() + " est en (" + this.getPx() + ", " +  this.getPy() + ") et subbit une force " + this.getForce();
    }
    
    @Override
    public int nombreInconnue(){
        return 2;
    }

}
