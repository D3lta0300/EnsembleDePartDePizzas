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

    @Override
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

    public static Matrice matAleaZeroUnDeux(int nbLig, int nbCol, double probaZero) {
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

    public int getNbrCol() {
        return this.nbrCol;
    }

    public void setNbrLig(int nbrLig) {
        this.nbrLig = nbrLig;
    }

    public void setNbrCol(int nbrCol) {
        this.nbrCol = nbrCol;
    }

    public double get(int lig, int col) {
        return this.coeffs[lig][col];
    }

    public void set(int lig, int col, double nVal) {
        this.coeffs[lig][col] = nVal;
    }

    public Matrice concatLig(Matrice n) {
        if (this.getNbrCol() != n.getNbrCol()) {
            throw new Error("nombre de cols incompatible");
        }
        Matrice res = new Matrice(this.getNbrLig() + n.getNbrLig(), this.getNbrCol());
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

    public Matrice subCols(int cMin, int cMax) {
        if ((0 > cMin) || (cMin > cMax) || (cMax > this.getNbrCol())) {
            throw new Error("les paramètres ne remplissent pas les conditions demandées");
        }
        Matrice R = new Matrice(this.getNbrLig(), cMax - cMin + 1);
        for (int lig = 0; lig < R.getNbrLig(); lig++) {
            for (int col = 0; col < R.getNbrCol(); col++) {
                R.set(lig, col, this.get(lig, cMin + col));
            }
        }
        return R;
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
    }

    public int descenteGauss() {
        int lignepivot;
        int res = 0;
        for (int i = 0; i < Math.min(this.getNbrLig(), this.getNbrCol()); i++) {
            lignepivot = this.lignePlusGrandPivot(i);
            if (lignepivot != -1) {
                res++;
                for (int i2 = i + 1; i2 < this.getNbrLig(); i2++) {
                    this.transvection(i, i2);
                }
            } else {
                System.out.println("la matrice n'est pas inversible");
            }
        }
        System.out.println(this.toString());
        System.out.println(res);
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

    public Matrice remontéeGauss() {
        for (int i = this.getNbrLig() - 1; i > 0; i--) {
            for (int j = i - 1; j > -1; j--) {
                this.transvection(i, j);
            }
        }
        return this;
    }

    public static void resolution() {
        System.out.println("Combien y a t'il d'inconnues?");
        int inc = Lire.i();
        Matrice m = new Matrice(inc, inc + 1);
        for (int i = 0; i < m.getNbrLig(); i++) {
            for (int j = 0; j < m.getNbrCol(); j++) {
                System.out.println("Entrez le cofficient (" + (i + 1) + "," + (j + 1) + ")");
                m.setCoeffs(i, j, Lire.d());
            }
        }

        m.descenteGauss();
        m.remontéeGauss().diagUnitaire();
        System.out.println("Les solutions sont:");
        System.out.println(m.subCols(m.getNbrCol() - 1, m.getNbrCol() - 1).toString());
    }
}
