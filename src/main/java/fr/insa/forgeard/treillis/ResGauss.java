/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.forgeard.treillis;

/**
 *
 * @author Elève
 */
public class ResGauss {

    private int rang;
    private int signature;

    public ResGauss(int rang, int signature) {
        this.rang = rang;
        this.signature = signature;
    }

    public int getSig() {
        return this.signature;
    }

    public int getRang() {
        return this.rang;
    }

    @Override
    public String toString() {
        String s;
        s = "Résultat retourné :\n"
                + "{ResGauss : rang = " + this.rang + " ; sigPerm = " + this.signature + "}";
        return s;
    }

    public static void main(String[] args){
        ResGauss R = new ResGauss(0, 0);
        System.out.println(R);
    }

}