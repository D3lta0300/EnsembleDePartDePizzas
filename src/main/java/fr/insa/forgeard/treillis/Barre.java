/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.forgeard.treillis;
//V1 !
/**
 *
 * @author titouan
 */
public class Barre {

    private int id;
    private Noeud noeudDepart, noeudArrive;
    private double tractionMax, compressionMax;
    private double coûtMetre;

    public Noeud getNoeudDepart() {
        return noeudDepart;
    }

    public void setNoeudDepart(Noeud noeudDepart) {
        this.noeudDepart = noeudDepart;
    }

    public Noeud getNoeudArrive() {
        return noeudArrive;
    }

    public void setNoeudArrive(Noeud noeudArrive) {
        this.noeudArrive = noeudArrive;
    }

    public int getID() {
        return id;
    }
    
    public void setID(int id){
        this.id = id;
    }

    public double getTractionMax() {
        return tractionMax;
    }

    public void setTractionMax(double tractionMax) {
        this.tractionMax = tractionMax;
    }

    public double getCompressionMax() {
        return compressionMax;
    }

    public void setCompressionMax(double compressionMax) {
        this.compressionMax = compressionMax;
    }

    public double getCoûtMetre() {
        return coûtMetre;
    }

    public void setCoûtMetre(double coûtMetre) {
        this.coûtMetre = coûtMetre;
    }

    public Barre(int id, Noeud noeudDepart, Noeud noeudArrive, double tractionMax, double compressionMax, double coûtMetre) {
        this.id = id;
        this.noeudDepart = noeudDepart;
        noeudDepart.getBarresDepart().add(this);
        this.noeudArrive = noeudArrive;
        noeudArrive.getBarresArrive().add(this);
        this.tractionMax = tractionMax;
        this.coûtMetre = coûtMetre;
    }
    
    public Barre(Noeud noeudDepart, Noeud noeudArrive){
        this(0, noeudDepart, noeudArrive,0.0,0.0,0.0);
    }

    @Override
    public String toString() {
        return "Barre{" + "id=" + id + ", noeudDepart=" + noeudDepart + ", noeudArrive=" + noeudArrive + '}';
    }
    
    
    public Noeud noeudOppose(Noeud in){
        Noeud out = new NoeudSimple();
        if (in == this.noeudArrive){
            out = this.noeudDepart;
        } else if (in == this.noeudDepart){
            out = this.noeudArrive;
        } else {
            throw new Error("Le noeud ne correspond pas");
        }
        return out;
    }
    
    public double angle(Noeud n){
        double a = n.getPx()-this.noeudOppose(n).getPx();
        double b = n.getPy()-this.noeudOppose(n).getPy();
        return Math.atan2(b, a);
    }

}