/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.forgeard.treillis;

public class Matrice {

    private int nbrLig;
    private int nbrCol;
    private double[][] coeffs;

    public Matrice(int nl, int nc) {
        this.nbrLig = nl;
        this.nbrCol = nc;
        this.coeffs = new double[nl][nc];
        for (int i = 0; i < this.nbrLig; i++) {
            for (int j = 0; j < this.nbrCol; j++) {
                this.coeffs[i][j] = 0;
            }
        }
    }

    public String toString() {
        String s;
        s = "";
        for (int i = 0; i < this.nbrLig; i++) {
            s = s + "[";
            for (int j = 0; j < this.nbrCol; j++) {
                s = s + String.format("%+4.2E", this.coeffs[i][j]);
                if (j != this.nbrCol - 1) {
                    s = s + " ";
                }
            }
            s = s + "]\n";
        }
        return s;
    }

    public static Matrice identite(int taille) {
        Matrice m = new Matrice(taille, taille);
        for (int i = 0; i < m.nbrLig; i++) {
            m.coeffs[i][i] = 1;
        }
        return m;
    }

    public static int aleaUnOuDeux() {
        return (int) (Math.random() * 2 + 1);
    }

    public static Matrice matAleaZeroUnDeux(int nbLig, int nbCol,double probaZero) {
        Matrice res;
        res = new Matrice(nbLig, nbCol);
        for (int i = 0; i < res.nbrLig; i++) {
            for (int j = 0; j < res.nbrCol; j++) {
                if (Math.random() > probaZero) {
                    res.coeffs[i][j] = Matrice.aleaUnOuDeux();
                }
            }
        }
        return res;
    }

    public int getNbrLig() {
        return this.nbrLig;
    }

    public double get(int lig, int col) {
        return this.coeffs[lig][col];
    }

    public Matrice concatLig(Matrice n) {
        if (this.getNbrCol() != n.getNbrCol()) {
            throw new Error("nombre de cols incompatible");
        }
        Matrice res = new Matrice(this.getNbrLig() + n.getNbrLig(),
                this.getNbrCol());
        for (int i = 0; i < this.getNbrLig(); i++) {
            for (int j = 0; j < this.getNbrCol(); j++) {
                res.set(i, j, this.get(i, j));
            }
        }
        for (int i = 0; i < n.getNbrLig(); i++) {
            for (int j = 0; j < n.getNbrCol(); j++) {
                res.set(i + this.getNbrLig(), j, n.get(i, j));
            }
        }
        return res;
    }

    public Matrice add(Matrice m2) {
        if (this.nbrLig != m2.nbrLig || this.nbrCol != m2.nbrCol) {
            throw new Error("incompatibles");
        }
        int nl = this.nbrLig;
        int nc = this.nbrCol;
        Matrice res = new Matrice(nl, nc);
        for (int i = 0; i < nl; i++) {
            for (int j = 0; j < nc; j++) {
                res.set(i, j, this.get(i, j) + m2.get(i, j));
            }
        }
        return res;
    }

    public Matrice opp() {
        int nl = this.nbrLig;
        int nc = this.nbrCol;
        Matrice res = new Matrice(nl, nc);
        for (int i = 0; i < nl; i++) {
            for (int j = 0; j < nc; j++) {
                res.set(i, j, -this.get(i, j));
            }
        }
        return res;
    }

    public Matrice moins(Matrice m2) {
        return this.add(m2.opp());
    }

    public void set(int lig, int col, double nVal) {
        this.coeffs[lig][col] = nVal;
    }

    public int getNbrCol() {
        return nbrCol;
    }

    public Matrice mult(Matrice m2) {
        if (this.nbrCol != m2.nbrLig) {
            throw new Error("Matrices incompatibles");
        }
        int n = this.nbrLig;
        int p = m2.nbrLig;
        int c = m2.nbrCol;
        Matrice res = new Matrice(n, c);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < c; j++) {
                double somme = 0;
                for (int k = 0; k < p; k++) {
                    somme = somme + (this.coeffs[i][k] * m2.coeffs[k][j]);
                }
                res.coeffs[i][j] = somme;
            }
        }
        return res;
    }

    public void permuteLigne(int i1, int i2) {
        if (i1 > this.nbrLig || i2 > this.nbrLig) {
            throw new Error("Matrice incompatible");
        }
        double t;
        for (int j = 0; j < this.nbrCol; j++) {
            t = this.coeffs[i1][j];
            this.set(i1, j, this.coeffs[i2][j]);
            this.set(i2, j, t);
        }
    }

    public void transvection(int i1, int i2) {
        if (i1 > this.nbrCol || i2 > this.nbrCol || this.coeffs[i1][i1] == 0) {
            throw new Error("Impossible");
        }
        double p = this.coeffs[i2][i1] / this.coeffs[i1][i1];
        for (int i = 0; i < this.nbrCol; i++) {
            this.set(i2, i, this.coeffs[i2][i] - (p * this.coeffs[i1][i]));
        }
        this.set(i2, i1, 0);
    }

    public int lignePlusGrandPivot(int colonne) {
        double epsilon = Math.pow(10.0, -8.0);
        double k = this.get(colonne, colonne);
        int imax = colonne;
        for (int i = colonne; i < this.nbrLig; i++) {
            if (Math.abs(this.get(i, colonne)) > k) {
                k = this.get(i, colonne);
                imax = i;
            }
        }
        if (k < epsilon) {
            return -1;
        } else {
            return imax;
        }


    public ResGauss DescenteGauss() {
        int r = 0;
        int sig = 0;
        for (int i = 0; i < this.nbrLig; i++) {
            if (this.lignePlusGrandPivot(i) != -1) {
                this.permuteLigne(i, this.lignePlusGrandPivot(i));
                for (int j = i + 1; j < this.nbrLig; j++) {
                    this.transvection(i, j);
                }
                sig++;
            }
        }

        for (int i = 0; i < this.nbrLig; i++) {
            double s = 0;
            for (int j = 0; j < this.nbrLig; j++) {
                s = s + Math.abs(this.coeffs[i][j]);
            }
            if (s != 0) {
                r++;
            }
        }

        if ((sig % 2) == 1) {
            sig = -1;
        } else {
            sig = 1;
        }

        ResGauss res = new ResGauss(r, sig);
        return res;
    }

    public double determinant() {
        int sig = this.descenteGauss().getSig();
        double deter = 1;
        for (int i = 0; i < this.nbrLig; i++) {
            deter = deter * this.coeffs[i][i];
        }
        deter = deter * sig;
        return deter;
    }

    public Matrice concatCol(Matrice n) {
        if (this.getNbrLig() != n.getNbrLig()) {
            throw new Error("nombre de cols incompatible");
        }
        Matrice res = new Matrice(this.getNbrLig(),
                this.getNbrCol() + n.getNbrCol());
        for (int i = 0; i < this.getNbrLig(); i++) {
            for (int j = 0; j < this.getNbrCol(); j++) {
                res.set(i, j, this.get(i, j));
            }
        }
        for (int i = 0; i < n.getNbrLig(); i++) {
            for (int j = 0; j < n.getNbrCol(); j++) {
                res.set(i, j + this.getNbrCol(), n.get(i, j));
            }
        }
        return res;
    }

    public void diagUnitaire() {
        for (int i = 0; i < this.nbrLig; i++) {
            if (this.coeffs[i][i] != 0) {
                for (int j = i + 1; j < this.nbrCol; j++) {
                    this.set(i, j, this.coeffs[i][j] / this.coeffs[i][i]);
                }
                this.set(i, i, 1);
            }
        }
    }

    public Matrice solSecondMembre() {
        Matrice m = new Matrice(this.nbrLig, 1);
        m.set((this.nbrLig - 1), 0, this.get(this.nbrLig - 1, this.nbrCol - 1));
        System.out.println(m);

        for (int i = this.nbrLig - 2; i > -1; i--) {
            double somme = this.coeffs[i][this.nbrCol - 1];
            for (int j = i + 1; j < this.nbrLig; j++) {
                somme = somme - (this.coeffs[i][j] * m.coeffs[j][0]);
            }
            m.set(i, 0, somme);
            System.out.println(m);
        }
        return m;
    }

}