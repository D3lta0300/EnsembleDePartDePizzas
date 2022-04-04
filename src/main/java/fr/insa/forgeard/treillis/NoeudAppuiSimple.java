/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.forgeard.treillis;

/**
 *
 * @author titouan
 */
public class NoeudAppuiSimple extends NoeudAppui {
    public NoeudAppuiSimple(int id, double px, double py, Vecteur2D force) {
        super(id, px, py, force);
    }
    
    @Override
    public int nombreInconnue(){
        return 1;
    }

}
