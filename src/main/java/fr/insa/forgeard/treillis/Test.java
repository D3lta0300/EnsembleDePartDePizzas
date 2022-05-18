/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.forgeard.treillis;

//import java.io.Console;
import java.util.Scanner;

/**
 *
 * @author titouan
 */
public class Test {

    public static void main(String[] args) {
        
        Integer a = 3;
        Integer b = 7;
        Matrice test = new Matrice(3,3);
        test.set(0, 0, 0);
        test.set(0, 1, -5);
        test.set(0, 2, -5);
        test.set(1, 0, -2);
        test.set(1, 1, -4);
        test.set(1, 2, -2);
        test.set(2, 0, 4);
        test.set(2, 1, 3);
        test.set(2, 2, 1);
        System.out.println("Test vaux : \n");
        System.out.println(test);
        test = test.concatCol(Matrice.identite(3));
        System.out.println("Après descente de Gauss : \n");
        test.descenteGauss();
        System.out.println(test);
        System.out.println("Sa matrice inverse est : \n");
        test.remontéeGauss();
        test.diagUnitaire();
        System.out.println(test);
        
    }
}
