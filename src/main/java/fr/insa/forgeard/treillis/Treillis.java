/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.forgeard.treillis;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public Treillis() {
        this(new ArrayList<Noeud>(), new ArrayList<Barre>());
    }

    @Override
    public String toString() {
        return "Treillis{" + "noeuds=" + noeuds + ", barres=" + barres + '}';
    }

    /**
     * @return renvoie l'id le plus élevé des noeuds du treillis
     */
    public int maxIDNoeud() {
        int a = 0;
        for (Noeud b : this.noeuds) {
            if (b.getID() > a) {
                a = b.getID();
            }
        }
        return a;
    }

    /**
     * @return renvoie l'id le plus élevé des barres du treillis
     */
    public int maxIDBarre() {
        int a = 0;
        for (Barre b : this.barres) {
            if (b.getID() > a) {
                a = b.getID();
            }
        }
        return a;
    }

    public void addNoeud(Noeud n) {
        for (Noeud a : this.noeuds) {
            if (n == a) {
                throw new Error("Le noeud existe deja");
            }
        }
        n.setID(this.maxIDNoeud() + 1);
        this.noeuds.add(n);
    }

    public void addBarre(Barre nouvelleBarre) {

        //Verifie que l'on est pas en train d'ajouter une barre deja existente
        for (Barre barre : this.barres) {
            if (nouvelleBarre == barre) {
                throw new Error("La barre existe déjà");
            }
        }

        //Verifie la presence des noeuds de depart et d'arrive
        boolean testArrive = true;
        boolean testDepart = true;
        for (Noeud noeud : this.noeuds) {
            if (noeud == nouvelleBarre.getNoeudArrive()) {
                testArrive = false;
            }
            if (noeud == nouvelleBarre.getNoeudDepart()) {
                testDepart = false;
            }
        }
        if (testArrive) {
            this.noeuds.add(nouvelleBarre.getNoeudArrive());
        }
        if (testDepart) {
            this.noeuds.add(nouvelleBarre.getNoeudDepart());
        }

        nouvelleBarre.setID(this.maxIDBarre() + 1);
        this.barres.add(nouvelleBarre);
    }

    public static void menuTexte() {
        System.out.println("Bienvenue dans l'éditeur de treillis ! Vous pouvez quitter un menu à tout moment en entrant \"0\"");
        int action = 1;
        Treillis treillis = new Treillis();
        Scanner scanner = new Scanner(System.in);
        while (action != 0) {
            System.out.println(treillis);
            System.out.println("Que voulez vous faire ? \n Entrez \"0\" pour terminer.\n Entrez \"1\" pour ajouter un élément.\n Entrez \"2\" pour en supprimer un.");
            action = scanner.nextInt();
            
            //initialisation
            if (treillis.getNoeuds() == new ArrayList<Noeud>()) {
                System.out.println("Vous devez dans un premier temps définir les points d'ancrages. \n \n Quel est la coordonnée x du noeud appui double ? :");
                double x = scanner.nextDouble();
                System.out.println("Sa coordonée en y ?");
                double y = scanner.nextDouble();
                Noeud n1 = new NoeudAppuiDouble(x, y);

                System.out.println("Quel est la coordonée en x du noeud appui simple ?");
                x = scanner.nextDouble();
                System.out.println("Sa coordonée en y ?");
                y = scanner.nextDouble();
                Noeud n2 = new NoeudAppuiSimple(x, y);

                Barre tablier = new Barre(n1, n2);
                treillis.addBarre(tablier);

                System.out.println("Information : Une barre correspondant au tablier a été ajoutée entre ces noeuds.");
            }

            if (action == 1) {
                System.out.println("Quel type d'élément souhaitez vous ajouter ? (1 = barre ; 2 = noeud");
            }
        }
    }
}
