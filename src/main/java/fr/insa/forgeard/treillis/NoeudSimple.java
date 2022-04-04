/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.forgeard.treillis;

/**
 *
 * @author titouan
 */
public class NoeudSimple extends Noeud {

    public NoeudSimple(int id, double px, double py, Vecteur2D force) {
        super(id, px, py, force);
    }
    
    public NoeudSimple(){
        this(-1,0.0,0.0, new Vecteur2D());
    }
    
    @Override
    public int nombreInconnue(){
        return 0;
    }

}
