/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.forgeard.treillis;

import java.util.List;

/**
 *
 * @author titouan
 */
public class Treillis {
    private List<Noeud> noeuds;
    private List<Barre> barres;

    /**
     * @return the noeuds
     */
    public List<Noeud> getNoeuds() {
        return noeuds;
    }

    /**
     * @param noeuds the noeuds to set
     */
    public void setNoeuds(List<Noeud> noeuds) {
        this.noeuds = noeuds;
    }

    /**
     * @return the barres
     */
    public List<Barre> getBarres() {
        return barres;
    }

    /**
     * @param barres the barres to set
     */
    public void setBarres(List<Barre> barres) {
        this.barres = barres;
    }

    public Treillis(List<Noeud> noeuds, List<Barre> barres) {
        this.noeuds = noeuds;
        this.barres = barres;
    }

    @Override
    public String toString() {
        return "Treillis{" + "noeuds=" + noeuds + ", barres=" + barres + '}';
    }
    
    /**
     * @return renvoie l'id le plus élevé des noeuds du treillis
     */
    public int maxIDNoeud(){
        int a = 0;
        for (Noeud b:this.noeuds){
            if (b.getId()>a){
                a=b.getId();
            }
        }
        return a;
    }
    
    /**
     * @return renvoie l'id le plus élevé des barres du treillis
     */
    public int maxIdBarre(){
        int a = 0;
        for (Barre b:this.barres){
            if (b.getId()>a){
                a=b.getId();
            }
        }
        return a;
    }
    
    public void ajouteNoeud(Noeud n){
        for (Noeud a:this.noeuds){
            if (n==a){
                throw new Error("Le noeud ne correspond pas");
            }
        }
        n.setID(this.maxIDNoeud()+1);
        this.noeuds.add(n);
    }
}
