# Projet Supermatrices - Version Java

## Description

Ce projet est une adaptation en Java du projet "SUPERMATRICES" développé en langage C au semestre 1. Il implémente une bibliothèque de manipulation de supermatrices avec toutes les 8 méthodes demandées sous forme de méthodes statiques.

## Adaptation C vers Java

### Principales modifications :

1. **Pointeurs → Références d'objets** : Les pointeurs C sont remplacés par des références Java
2. **Méthodes statiques** : Toutes les 8 méthodes sont implémentées comme méthodes statiques
3. **Gestion mémoire** : Utilisation du garbage collector Java au lieu de malloc/free
4. **Tableaux dynamiques** : Utilisation des tableaux Java au lieu de l'allocation dynamique C
5. **Gestion d'erreurs** : Utilisation des exceptions Java et des messages d'erreur

## Structure du projet

```
supermat/
├── Supermat.java      # Classe principale avec les 8 méthodes statiques
├── TestSupermat.java  # Classe de test démonstrative
├── supermat.h         # Fichier d'en-tête C original (référence)
├── supermat.c         # Implémentation C originale (référence)
└── README.md          # Ce fichier
```

## Les 8 méthodes implémentées

### 1. `allouerSupermat(int nl, int nc)`
- **Description** : Alloue une nouvelle supermatrice
- **Paramètres** : `nl` (nombre de lignes), `nc` (nombre de colonnes)
- **Retour** : Nouvelle supermatrice ou `null` si erreur

### 2. `superProduit(Supermat a, Supermat b)`
- **Description** : Calcule le produit de deux supermatrices
- **Paramètres** : `a` et `b` (supermatrices à multiplier)
- **Retour** : Supermatrice résultat ou `null` si impossible

### 3. `permuterLignes(Supermat a, int i, int j)`
- **Description** : Permute deux lignes d'une supermatrice
- **Paramètres** : `a` (supermatrice), `i` et `j` (indices des lignes)
- **Retour** : void (modification sur place)

### 4. `sousMatrice(Supermat a, int l1, int l2, int c1, int c2)`
- **Description** : Extrait une sous-matrice
- **Paramètres** : `a` (supermatrice source), `l1`-`l2` (lignes), `c1`-`c2` (colonnes)
- **Retour** : Nouvelle supermatrice contenant la sous-matrice

### 5. `matSupermat(double[][] m, int nld, int ncd, int nle, int nce)`
- **Description** : Transforme une matrice en supermatrice
- **Paramètres** : `m` (matrice source), dimensions source et extraites
- **Retour** : Nouvelle supermatrice

### 6. `supermatMat(Supermat sm, double[][] m, int nld, int ncd)`
- **Description** : Transforme une supermatrice en matrice
- **Paramètres** : `sm` (supermatrice source), `m` (matrice destination), dimensions
- **Retour** : void (modification de la matrice destination)

### 7. `contiguite(Supermat a)`
- **Description** : Vérifie la contiguïté des lignes
- **Paramètres** : `a` (supermatrice à vérifier)
- **Retour** : 2 (ordonnées), 1 (contiguës), 0 (non contiguës)

### 8. `rendreSupermat(Supermat sm)`
- **Description** : Libère la mémoire d'une supermatrice
- **Paramètres** : `sm` (supermatrice à libérer)
- **Retour** : void (aide le garbage collector)

## Compilation et exécution

### Prérequis
- Java JDK 8 ou supérieur

### Compilation
```bash
javac Supermat.java TestSupermat.java
```

### Exécution
```bash
java TestSupermat
```

## Exemple d'utilisation

```java
// Créer une supermatrice 3x3
Supermat sm = Supermat.allouerSupermat(3, 3);

// Remplir avec des valeurs
for (int i = 0; i < 3; i++) {
    for (int j = 0; j < 3; j++) {
        sm.setElement(i, j, i * 3 + j + 1);
    }
}

// Afficher la supermatrice
Supermat.afficherSupermat(sm);

// Permuter les lignes 0 et 2
Supermat.permuterLignes(sm, 0, 2);

// Extraire une sous-matrice
Supermat sousSM = Supermat.sousMatrice(sm, 1, 2, 0, 1);

// Libérer la mémoire
Supermat.rendreSupermat(sm);
```

## Différences avec la version C

1. **Sécurité mémoire** : Java gère automatiquement la mémoire
2. **Vérification des types** : Java vérifie les types à la compilation
3. **Exceptions** : Gestion d'erreurs plus robuste
4. **Références** : Pas de pointeurs nulls dangereux
5. **Simplicité** : Code plus lisible et maintenable

## Tests inclus

La classe `TestSupermat` inclut :
- Tests de toutes les 8 méthodes
- Tests de cas d'erreur
- Affichage des résultats
- Validation des opérations

## Auteur

Awa_Niasse
# Projet_Java
