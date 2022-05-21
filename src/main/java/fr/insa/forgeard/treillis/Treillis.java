/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.forgeard.treillis;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 *
 * @author titouan
 */
public class Treillis {

    private List<Noeud> noeuds;
    private List<Barre> barres;

    /**
     * @return les noeuds
     */
    public List<Noeud> getNoeuds() {
        return noeuds;
    }

    /**
     * @param noeuds the nodes to set
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
     * @param barres les barres à ajouter
     */
    public void setBarres(List<Barre> barres) {
        this.barres = barres;
    }

    /**
     * Constructeur
     * @param noeuds
     * @param barres 
     */
    public Treillis(List<Noeud> noeuds, List<Barre> barres) {
        this.noeuds = noeuds;
        this.barres = barres;
    }

    /**
     * Constructeur vide
     */
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

    /**
     * Ajoute un noeud au treillis, modifie également son ID pour correspondre au treillis.
     * @param n 
     */
    public void addNoeud(Noeud n) {
        for (Noeud a : this.noeuds) {
            if (n == a) { //verifie que l'on est pas en train d'essayer d'ajouter un noeud existant déjà
                throw new Error("Le noeud existe deja");
            }
        }
        n.setID(this.maxIDNoeud() + 1);
        this.noeuds.add(n);
    }

    /**
     * Ajoute un noeud au treillis, modifie également son ID pour correspondre au treillis.
     * @param nouvelleBarre 
     */
    public void addBarre(Barre nouvelleBarre) {

        //Verifie que l'on est pas en train d'ajouter une barre deja existente
        for (Barre barre : this.barres) {
            if (nouvelleBarre == barre) {
                throw new Error("La barre existe déjà");
            }
        }

        //Verifie la presence des noeuds de depart et d'arrive dans le treillis
        boolean testArrive = true; //Ces variables passe à false si un même est détectée
        boolean testDepart = true;
        for (Noeud noeud : this.noeuds) {
            if (noeud == nouvelleBarre.getNoeudArrive()) {
                testArrive = false;
            }
            if (noeud == nouvelleBarre.getNoeudDepart()) {
                testDepart = false;
            }
        }
        if (testArrive) { //ajoute les noeuds au treillis si ils n'y sont pas déjà
            this.noeuds.add(nouvelleBarre.getNoeudArrive());
        }
        if (testDepart) {
            this.noeuds.add(nouvelleBarre.getNoeudDepart());
        }

        nouvelleBarre.setID(this.maxIDBarre() + 1);
        this.barres.add(nouvelleBarre);
    }

    /**
     * Affiche la liste des noeuds
     */
    public void showNoeuds() {
        String out = "";
        for (Noeud n : this.noeuds) {
            out += n.toString();
        }
        System.out.println(out);
    }

    /**
     * Affiche la liste des barres
     */
    public void showBarres() {
        String out = "";
        for (Barre b : this.barres) {
            out += b.toString();
        }
        System.out.println(out);
    }

    /**
     * @param id
     * @return le noeud correspondant à l'ID
     */
    public Noeud getNoeudByID(int id) {
        Noeud out = new NoeudSimple();
        for (Noeud n : this.noeuds) {
            if (id == n.getID()) {
                out = n;
            }
        }
        return out;
    }

    /**
     * @param id
     * @return la barre correspondant à l'ID fournit
     */
    public Barre getBarreByID(int id) {
        Barre out = new Barre(new NoeudSimple(), new NoeudSimple());
        for (Barre b : this.barres) {
            if (id == b.getID()) {
                out = b;
            }
        }
        return out;
    }

    /**
     * Supprime un noeuds à condition qu'il n'y est pas de barres l'utilisant
     * @param id 
     */
    public void deleteNoeud(int id) {
        List<Integer> barresProblemmatique = new ArrayList<Integer>();
        boolean deletable = true;
        for (Barre b : this.barres) { //vérifie qu'aucune barre n'est reliée au noeud
            if (b.getNoeudArrive().getID() == id) {
                barresProblemmatique.add(b.getID());
                deletable = false;
            } else if (b.getNoeudDepart().getID() == id) {
                barresProblemmatique.add(b.getID());
                deletable = false;
            }
        }

        if (deletable) { //supprime le noeuds
            this.getNoeuds().remove(this.getNoeudByID(id));
            System.out.println("Le noeud a bien été supprimé");
        } else if (!deletable) { //informe l'utilisateur que ce n'est pas possible
            System.out.print("Les barres aux ID suivantes empêchent la suppression du noeud " + id + " : ");
            for (int i : barresProblemmatique) {
                System.out.print(i + " ; ");
            }
            System.out.println("");
        }
        
        this.rearrangeID();
    }
    
    /**
     * réarrange les ID pour que les numéros se suivent
     */
    public void rearrangeID(){
        int i = 1;
        
        for (Noeud n : this.getNoeuds()){
            n.setID(i);
            i++;
        }
        i=1;
        for (Barre b : this.getBarres()){
            b.setID(i);
            i++;
        }
    }
    
    /**
     * Calculs les forces s'exercants dans le treillis
     * @param force
     * @param noeudID
     * @return 
     */
    public Matrice forces(Vecteur2D force, int noeudID){
        this.getNoeudByID(noeudID).setForce(force);
        
        int ns = this.getNoeuds().size(); //nombre de noeuds
        int nb = this.getBarres().size(); //nombre de barres
        int nsas = 0; //nombre de noeuds appui simple
        int nsad = 0; //nombre de noeuds appui double
        
        for (Noeud n : this.getNoeuds()){ //compte les noeuds appui double et simple
            if (n.nombreInconnue()==1){
                nsas++;
            } else if (n.nombreInconnue() == 2){
                nsad++;
            }
        }
        
        Matrice resultat = new Matrice(2*ns,1); //matrice qui contiendra le résultat
        
        if (2*ns!=nb+nsas+2*nsad){ //test d'inversibilité
            System.out.println("La matrice n'est pas soluble.");
        } else {
            Matrice coefs = new Matrice(2*ns,2*ns); //créé la matrice qui correspondra au système d'équations
            int nombreReactions = 0; //variable permettant de s'assurer de la position des réactions dans la matrice
            
            //définition de la matrice
            for (Noeud n : this.getNoeuds()){ //place les indices correspondant du système d'équation dans la matrice
                for (Barre b : n.barresIncidentes()){
                    coefs.set(n.getID()-1, b.getID()-1, b.angle(n));
                    coefs.set(n.getID(), b.getID()-1, b.angle(n));
                }
                if (n.nombreInconnue()==1){ //gère les appuis simple
                    coefs.set(n.getID(), nb+nombreReactions, 1);
                    nombreReactions++;
                } else if (n.nombreInconnue()==2){ //gère les appuis double
                    coefs.set(n.getID()-1, nb+nombreReactions, 1);
                    nombreReactions++;
                    coefs.set(n.getID(), nb+nombreReactions, 1);
                    nombreReactions++;
                }
            }
            
            //calcul des forces
            
            Matrice forces = new Matrice(2*ns,1);
            //ajoute les conditions initiales
            forces.set(noeudID-1,0, force.getPx());
            forces.set(noeudID, 0, force.getPy());
            
            coefs.concatCol(forces);
            coefs.descenteGauss();
            coefs.remontéeGauss();
            coefs.diagUnitaire();
            
            //résultats
            for (int i =0; i<2*ns;i++){
                resultat.set(i,0,coefs.get(i, 2*ns));
            }
        }
        
        for (Barre b:this.getBarres()){ //ajoute les résultats dans les barres correspondantes
            b.setForce(resultat.get(b.getID()-1, 0));
        }
        
        return resultat;
    }

    /**
     * Un menu permettant de tester le fonctionnement des principales méthodes du treillis.
     */
    public static void menuTexte() {
        System.out.println("Bienvenue dans l'éditeur de treillis ! Vous pouvez quitter un menu à tout moment en entrant \"0\"");
        int action = 1;
        Treillis treillis = new Treillis();
        Scanner scanner = new Scanner(System.in);
        while (action != 0) {

            //initialisation des deux premiers noeuds et du tablier
            if (treillis.getNoeuds().isEmpty()) {
                System.out.println("Vous devez dans un premier temps définir les points d'ancrages. \n \n Quel est la coordonnée x du noeud appui double ? :");
                double x = scanner.nextDouble();
                System.out.println("Sa coordonée en y ?");
                double y = scanner.nextDouble();
                Noeud n1 = new NoeudAppuiDouble(x, y);
                treillis.addNoeud(n1);
                
                System.out.println("Quel est la coordonée en x du noeud appui simple ?");
                x = scanner.nextDouble();
                System.out.println("Sa coordonée en y ?");
                y = scanner.nextDouble();
                Noeud n2 = new NoeudAppuiSimple(x, y);
                treillis.addNoeud(n2);

                treillis.addBarre(new Barre(n1, n2)); //correspondra au tablier

                System.out.println("Information : Une barre correspondant au tablier a été ajoutée entre ces noeuds. \n");
            }
            
            //menu principale
            System.out.println(treillis);
            System.out.println("Que voulez vous faire ? :\n  - Entrez \"1\" pour ajouter un élément.\n  - Entrez \"2\" pour en supprimer un.\n");
            action = scanner.nextInt();
            
            
            if (action == 1) {
                while (action != 0) {
                    //menu secondaire 1, ajouter des éléments
                    System.out.println("Quel type d'élément souhaitez vous ajouter ? (1 = barre ; 2 = noeud");
                    action = scanner.nextInt();
                    
                    if (action == 1) {
                        System.out.println("Choisissez un noeud de départ dans la liste ci-dessous (entrez son id) : ");
                        treillis.showNoeuds();
                        int selection = scanner.nextInt();
                        Noeud noeudDepart = treillis.getNoeudByID(selection);

                        System.out.println("Quel est le noeud d'arrive ? ");
                        selection = scanner.nextInt();
                        Noeud noeudArrive = treillis.getNoeudByID(selection);

                        treillis.addBarre(new Barre(noeudDepart, noeudArrive));
                        System.out.println("La barre a bien été créée et ajoutée au treillis.\n");

                    } else if (action == 2) {
                        System.out.println("Px : ");
                        double px = scanner.nextDouble();

                        System.out.println("Py : ");
                        double py = scanner.nextDouble();

                        treillis.addNoeud(new NoeudSimple(px, py));
                        System.out.println("'Le noeud a bien été créé et ajouté au treillis.\n");

                    } else {
                        System.out.println("Veuillez rentrer un entier entre 0 et 2.\n");
                    }
                }
                action = 1;
            } else if (action == 2) {
                while (action != 0) {
                    //menu secondaire 2, supprime des éléments
                    System.out.println("Que souhaitez vous supprimer ?\n  - 1 : une barre\n  - 2 : un noeud \n \n");
                    action = scanner.nextInt();

                    if (action == 1) {
                        System.out.println("Quelle barre souhaitez vous supprimez d'apres cette liste ? (entrez l'ID) ");
                        treillis.showBarres();
                        action = scanner.nextInt();

                        treillis.getBarres().remove(treillis.getBarreByID(action));
                        System.out.println("La barre a bien été supprimée.\n\n");
                    } else if (action == 2) {
                        System.out.println("Quel noeud souhaitez vous supprimez d'apres cette liste ? (entrez l'ID) ");
                        treillis.showNoeuds();
                        treillis.deleteNoeud(scanner.nextInt());
                        System.out.println("\n");
                    } else {
                        System.out.println("Veuillez entrer un nombre compris entre 0 et 2.\n");
                    }
                }
                action = 1;
            }

        }
    }
}
