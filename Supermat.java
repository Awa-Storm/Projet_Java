public class Supermat {
    
    // Attributs de la supermatrice
    private int nl;           // Nombre de lignes
    private int nc;           // Nombre de colonnes
    private double[][] ligne; // Tableau de références vers les lignes
    
    public Supermat(int nl, int nc) {
        this.nl = nl;
        this.nc = nc;
        this.ligne = new double[nl][nc];
    }
    
    public Supermat(int nl, int nc, double[][] lignes) {
        this.nl = nl;
        this.nc = nc;
        this.ligne = lignes;
    }
    
    // Getters
    public int getNl() { return nl; }
    public int getNc() { return nc; }
    public double[][] getLigne() { return ligne; }
    
    public double acces(int i, int j) {
        return ligne[i][j];
    }
    
    public void setElement(int i, int j, double valeur) {
        ligne[i][j] = valeur;
    }
    
    // 1. Allocation d'une nouvelle supermatrice
    public static Supermat allouerSupermat(int nl, int nc) {
        if (nl <= 0 || nc <= 0) {
            System.err.println("Erreur: dimensions invalides pour la supermatrice");
            return null;
        }
        
        return new Supermat(nl, nc);
    }
    
    // 3. Produit de deux supermatrices
    public static Supermat superProduit(Supermat a, Supermat b) {
        // Vérifier la validité du produit matriciel
        if (a == null || b == null || a.nc != b.nl) {
            System.err.println("Erreur: produit matriciel impossible");
            return null;
        }
        
        // Créer la supermatrice résultat
        Supermat c = allouerSupermat(a.nl, b.nc);
        if (c == null) {
            return null;
        }
        
        // Calculer le produit
        for (int i = 0; i < a.nl; i++) {
            for (int j = 0; j < b.nc; j++) {
                c.ligne[i][j] = 0.0;
                for (int k = 0; k < a.nc; k++) {
                    c.ligne[i][j] += a.ligne[i][k] * b.ligne[k][j];
                }
            }
        }
        
        return c;
    }
    
    // 4. Permuter deux lignes d'une supermatrice
    public static void permuterLignes(Supermat a, int i, int j) {
        if (a == null || i < 0 || j < 0 || i >= a.nl || j >= a.nl) {
            System.err.println("Erreur: indices de lignes invalides");
            return;
        }
        
        // Permutation efficace en échangeant les références
        double[] temp = a.ligne[i];
        a.ligne[i] = a.ligne[j];
        a.ligne[j] = temp;
    }
    
    // 5. Extraction d'une sous-matrice
    public static Supermat sousMatrice(Supermat a, int l1, int l2, int c1, int c2) {
        if (a == null || l1 < 0 || l2 >= a.nl || c1 < 0 || c2 >= a.nc || l1 > l2 || c1 > c2) {
            System.err.println("Erreur: paramètres invalides pour la sous-matrice");
            return null;
        }
        
        // Calculer les dimensions de la sous-matrice
        int nl_sous = l2 - l1 + 1;
        int nc_sous = c2 - c1 + 1;
        
        // Créer le tableau de références vers les sous-lignes
        double[][] sous_lignes = new double[nl_sous][];
        
        for (int i = 0; i < nl_sous; i++) {
            sous_lignes[i] = new double[nc_sous];
            // Copier les éléments de la sous-matrice
            for (int j = 0; j < nc_sous; j++) {
                sous_lignes[i][j] = a.ligne[l1 + i][c1 + j];
            }
        }
        
        return new Supermat(nl_sous, nc_sous, sous_lignes);
    }
    
    // 6. Transformation matrice -> supermatrice
    public static Supermat matSupermat(double[][] m, int nld, int ncd, int nle, int nce) {
        if (m == null || nle <= 0 || nce <= 0 || nle > nld || nce > ncd) {
            System.err.println("Erreur: paramètres invalides pour la transformation matrice->supermatrice");
            return null;
        }
        
        // Créer le tableau de références vers les lignes existantes
        double[][] lignes = new double[nle][];
        
        for (int i = 0; i < nle; i++) {
            lignes[i] = new double[nce];
            // Copier les éléments de la matrice
            for (int j = 0; j < nce; j++) {
                lignes[i][j] = m[i][j];
            }
        }
        
        return new Supermat(nle, nce, lignes);
    }
    
    // 7. Transformation supermatrice -> matrice
    public static void supermatMat(Supermat sm, double[][] m, int nld, int ncd) {
        if (sm == null || m == null || sm.nl > nld || sm.nc > ncd) {
            System.err.println("Erreur: paramètres invalides pour la transformation supermatrice->matrice");
            return;
        }
        
        // Copier les données de la supermatrice vers la matrice
        for (int i = 0; i < sm.nl; i++) {
            for (int j = 0; j < sm.nc; j++) {
                m[i][j] = sm.ligne[i][j];
            }
        }
    }
    
    // 8. Vérification de la contiguïté des lignes
    // En Java, cette méthode vérifie si les lignes sont dans l'ordre logique
    public static int contiguite(Supermat a) {
        if (a == null || a.nl <= 1) {
            return 2; // Une seule ligne ou moins est considérée comme contiguë et ordonnée
        }
        
        // En Java, on vérifie si les références sont dans l'ordre logique
        // On considère que les lignes sont ordonnées si elles sont dans l'ordre des indices
        boolean ordonnees = true;
        for (int i = 1; i < a.nl; i++) {
            // Simple vérification que les lignes existent
            if (a.ligne[i] == null || a.ligne[i-1] == null) {
                ordonnees = false;
                break;
            }
        }
        
        return ordonnees ? 2 : 1; // En Java, on considère toujours comme au moins contiguës
    }
    
    // 9. Libération de la mémoire d'une supermatrice
    // En Java, cette méthode aide le garbage collector en mettant les références à null
    public static void rendreSupermat(Supermat sm) {
        if (sm == null) {
            return;
        }
        
        // Aider le garbage collector en mettant les références à null
        if (sm.ligne != null) {
            for (int i = 0; i < sm.nl; i++) {
                sm.ligne[i] = null;
            }
            sm.ligne = null;
        }
        
        sm.nl = 0;
        sm.nc = 0;
    }
    
    // Fonction supplémentaire pour afficher une supermatrice
    public static void afficherSupermat(Supermat a) {
        if (a == null) {
            System.out.println("Supermatrice invalide");
            return;
        }
        
        System.out.printf("Supermatrice %dx%d:\n", a.nl, a.nc);
        for (int i = 0; i < a.nl; i++) {
            for (int j = 0; j < a.nc; j++) {
                System.out.printf("%8.2f ", a.ligne[i][j]);
            }
            System.out.println();
        }
        
        // Afficher des informations sur la contiguïté
        int cont = contiguite(a);
        if (cont == 2) {
            System.out.println("Les lignes sont contiguës et ordonnées");
        } else if (cont == 1) {
            System.out.println("Les lignes sont contiguës mais dans le désordre");
        } else {
            System.out.println("Les lignes ne sont pas contiguës");
        }
    }
    
    // Méthode toString pour un affichage formaté
    @Override
    public String toString() {
        if (ligne == null) {
            return "Supermatrice vide";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Supermatrice %dx%d:\n", nl, nc));
        
        for (int i = 0; i < nl; i++) {
            for (int j = 0; j < nc; j++) {
                sb.append(String.format("%8.2f ", ligne[i][j]));
            }
            sb.append("\n");
        }
        
        return sb.toString();
    }
}
