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
    private int sigPerm;

    public ResGauss(int rang, int sig) {
        this.rang = rang;
        this.sigPerm = sig;
    }

    public int getSig() {
        return this.sigPerm;
    }

    public int getRang() {
        return rang;
    }

    public void setRang(int rang) {
        this.rang = rang;
    }

    public int getSigPerm() {
        return sigPerm;
    }

    @Override
    public String toString() {
        String res = "ResGauss: rang = " + this.rang + " ; sigPerm = " + this.sigPerm;
        return res;
    }
}
