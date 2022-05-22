/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.forgeard.treillis;


/**
 *
 * @author titouan
 */
public class Test {

    public static void main(String[] args) {
        
        System.out.println("alpha 1");
        System.out.println(Math.atan2(-3, -1));
        System.out.println("alpha3");
        System.out.println(Math.atan2(3, 1));
        System.out.println("omega1");
        System.out.println(Math.atan2(-3, 1));
        System.out.println("omega2");
        System.out.println(Math.atan2(3, -1));
        
        Treillis treillis = new Treillis();
        Noeud n1,n2,n3;
        n1 = new NoeudSimple(0,0);
        n2 = new NoeudAppuiSimple(3, 1);
        n3 = new NoeudAppuiDouble(1,0);
        treillis.addNoeud(n1);
        treillis.addNoeud(n2);
        treillis.addNoeud(n3);
        Barre b1, b2, b3;
        b1 = new Barre(n1, n3);
        b2 = new Barre(n2,n3);
        b3 = new Barre(n1,n2);
        treillis.addBarre(b1);
        treillis.addBarre(b2);
        treillis.addBarre(b3);
        
        System.out.println(n1.toString() + n2.toString() + n3.toString());
        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b3);
        
        Vecteur2D force = new Vecteur2D(1, 2);
        treillis.forces(force, 1);
        
        treillis.save(Treillis.choseFile());
        
    }
}
