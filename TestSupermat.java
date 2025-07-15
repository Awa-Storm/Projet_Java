/**
 * Classe de test pour démontrer l'utilisation de la bibliothèque Supermat
 * Teste toutes les 8 méthodes statiques demandées
 */
public class TestSupermat {
    
    public static void main(String[] args) {
        System.out.println("=== Test de la bibliothèque Supermat Java ===\n");
        
        // Test 1: Allocation d'une nouvelle supermatrice
        System.out.println("1. Test d'allocation d'une supermatrice 3x3:");
        Supermat sm1 = Supermat.allouerSupermat(3, 3);
        if (sm1 != null) {
            // Remplir la matrice avec des valeurs
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    sm1.setElement(i, j, i * 3 + j + 1);
                }
            }
            Supermat.afficherSupermat(sm1);
        }
        System.out.println();
        
        // Test 2: Création d'une deuxième matrice pour le produit
        System.out.println("2. Création d'une deuxième matrice 3x2:");
        Supermat sm2 = Supermat.allouerSupermat(3, 2);
        if (sm2 != null) {
            // Remplir avec des valeurs différentes
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 2; j++) {
                    sm2.setElement(i, j, (i + 1) * (j + 1));
                }
            }
            Supermat.afficherSupermat(sm2);
        }
        System.out.println();
        
        // Test 3: Produit de deux supermatrices
        System.out.println("3. Test du produit de matrices (3x3) * (3x2):");
        Supermat produit = Supermat.superProduit(sm1, sm2);
        if (produit != null) {
            Supermat.afficherSupermat(produit);
        }
        System.out.println();
        
        // Test 4: Permutation de lignes
        System.out.println("4. Test de permutation de lignes (lignes 0 et 2 de sm1):");
        System.out.println("Avant permutation:");
        Supermat.afficherSupermat(sm1);
        Supermat.permuterLignes(sm1, 0, 2);
        System.out.println("Après permutation:");
        Supermat.afficherSupermat(sm1);
        System.out.println();
        
        // Test 5: Extraction d'une sous-matrice
        System.out.println("5. Test d'extraction d'une sous-matrice (lignes 1-2, colonnes 0-1):");
        Supermat sousMatrice = Supermat.sousMatrice(sm1, 1, 2, 0, 1);
        if (sousMatrice != null) {
            Supermat.afficherSupermat(sousMatrice);
        }
        System.out.println();
        
        // Test 6: Transformation matrice -> supermatrice
        System.out.println("6. Test de transformation matrice -> supermatrice:");
        double[][] matrice = {
            {1.0, 2.0, 3.0, 4.0},
            {5.0, 6.0, 7.0, 8.0},
            {9.0, 10.0, 11.0, 12.0},
            {13.0, 14.0, 15.0, 16.0}
        };
        
        System.out.println("Matrice source 4x4:");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.printf("%8.2f ", matrice[i][j]);
            }
            System.out.println();
        }
        
        // Extraire une supermatrice 3x3
        Supermat smFromMat = Supermat.matSupermat(matrice, 4, 4, 3, 3);
        if (smFromMat != null) {
            System.out.println("Supermatrice extraite (3x3):");
            Supermat.afficherSupermat(smFromMat);
        }
        System.out.println();
        
        // Test 7: Transformation supermatrice -> matrice
        System.out.println("7. Test de transformation supermatrice -> matrice:");
        double[][] destination = new double[4][4];
        
        // Initialiser la matrice destination avec des zéros
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                destination[i][j] = 0.0;
            }
        }
        
        // Copier la supermatrice dans la matrice
        Supermat.supermatMat(smFromMat, destination, 4, 4);
        
        System.out.println("Matrice destination après copie:");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.printf("%8.2f ", destination[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        
        // Test 8: Vérification de la contiguïté
        System.out.println("8. Test de vérification de la contiguïté:");
        System.out.println("Contiguïté de sm1:");
        int cont1 = Supermat.contiguite(sm1);
        System.out.println("Résultat: " + cont1 + " (2=ordonnées, 1=contiguës, 0=non contiguës)");
        
        System.out.println("Contiguïté de la sous-matrice:");
        int cont2 = Supermat.contiguite(sousMatrice);
        System.out.println("Résultat: " + cont2 + " (2=ordonnées, 1=contiguës, 0=non contiguës)");
        System.out.println();
        
        // Test 9: Libération de la mémoire
        System.out.println("9. Test de libération de la mémoire:");
        System.out.println("Avant libération - sm1:");
        System.out.println("Dimensions: " + sm1.getNl() + "x" + sm1.getNc());
        
        Supermat.rendreSupermat(sm1);
        System.out.println("Après libération - sm1:");
        System.out.println("Dimensions: " + sm1.getNl() + "x" + sm1.getNc());
        System.out.println();
        
        // Test des cas d'erreur
        System.out.println("=== Tests de cas d'erreur ===");
        
        // Allocation avec dimensions invalides
        System.out.println("Test d'allocation avec dimensions invalides:");
        Supermat invalid = Supermat.allouerSupermat(-1, 5);
        System.out.println("Résultat: " + (invalid == null ? "null (correct)" : "erreur"));
        
        // Produit impossible
        System.out.println("Test de produit avec dimensions incompatibles:");
        Supermat sm3 = Supermat.allouerSupermat(2, 3);
        Supermat sm4 = Supermat.allouerSupermat(2, 2); // 2x3 * 2x2 impossible
        Supermat invalidProduit = Supermat.superProduit(sm3, sm4);
        System.out.println("Résultat: " + (invalidProduit == null ? "null (correct)" : "erreur"));
        
        // Permutation avec indices invalides
        System.out.println("Test de permutation avec indices invalides:");
        Supermat.permuterLignes(sm2, -1, 5);
        System.out.println("Test terminé (doit afficher un message d'erreur)");
        
        System.out.println("\n=== Fin des tests ===");
    }
}
